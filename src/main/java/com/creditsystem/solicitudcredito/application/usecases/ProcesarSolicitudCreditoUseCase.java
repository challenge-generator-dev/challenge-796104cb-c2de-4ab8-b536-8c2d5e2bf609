package com.creditsystem.solicitudcredito.application.usecases;

import com.creditsystem.solicitudcredito.application.commands.CrearSolicitudCommand;
import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito;
import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito.EstadoSolicitud;
import com.creditsystem.solicitudcredito.domain.ports.NotificacionService;
import com.creditsystem.solicitudcredito.domain.ports.SolicitudCreditoRepository;
import com.creditsystem.solicitudcredito.domain.ports.ValidacionCreditoService;
import com.creditsystem.solicitudcredito.domain.ports.ValidacionCreditoService.ResultadoValidacion;
import com.creditsystem.solicitudcredito.infrastructure.events.SolicitudCreditoEvent;
import com.creditsystem.solicitudcredito.infrastructure.repositories.SolicitudCreditoR2dbcRepository.SolicitudCreditoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Caso de uso principal para el procesamiento de solicitudes de crédito.
 * Orquesta el flujo completo: validación, persistencia, notificación y publicación de eventos.
 */
@Service
public class ProcesarSolicitudCreditoUseCase {

    private static final Logger log = LoggerFactory.getLogger(ProcesarSolicitudCreditoUseCase.class);
    private static final String TOPIC_SOLICITUDES = "solicitud-credito-events";

    private final SolicitudCreditoRepository repository;
    private final ValidacionCreditoService validacionService;
    private final NotificacionService notificacionService;
    private final KafkaTemplate<String, SolicitudCreditoEvent> kafkaTemplate;

    public ProcesarSolicitudCreditoUseCase(
            @Qualifier("solicitudCreditoRepositoryAdapter") SolicitudCreditoRepository repository,
            @Qualifier("validacionCreditoServiceAdapter") ValidacionCreditoService validacionService,
            @Qualifier("notificacionServiceAdapter") NotificacionService notificacionService,
            KafkaTemplate<String, SolicitudCreditoEvent> kafkaTemplate) {
        this.repository = repository;
        this.validacionService = validacionService;
        this.notificacionService = notificacionService;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Ejecuta el flujo completo de procesamiento de una solicitud de crédito.
     * 1. Verifica idempotencia
     * 2. Valida la solicitud
     * 3. Persiste la solicitud
     * 4. Notifica al cliente
     * 5. Publica evento asíncrono
     *
     * @param command Comando con los datos de la solicitud
     * @return Mono con la solicitud procesada
     */
    public Mono<SolicitudCredito> execute(CrearSolicitudCommand command) {
        log.info("Iniciando procesamiento de solicitud para correo: {}", command.correo());

        return verificarIdempotencia(command)
            .switchIfEmpty(Mono.defer(() -> crearSolicitud(command)))
            .flatMap(this::validarSolicitud)
            .flatMap(this::persistirSolicitud)
            .flatMap(this::notificarResultado)
            .flatMap(this::publicarEvento)
            .doOnSuccess(solicitud -> log.info("Solicitud {} procesada exitosamente con estado: {}",
                solicitud.id(), solicitud.estado()))
            .doOnError(error -> log.error("Error al procesar solicitud: {}", error.getMessage()));
    }

    /**
     * Verifica si existe una solicitud previa con la misma clave de idempotencia.
     * Si existe, retorna la solicitud anterior para garantizar idempotencia.
     */
    private Mono<SolicitudCredito> verificarIdempotencia(CrearSolicitudCommand command) {
        String idempotenciaKey = generarIdempotenciaKey(command);
        log.debug("Verificando idempotencia con key: {}", idempotenciaKey);

        return repository.findByIdempotenciaKey(
                command.id() != null ? command.id() : UUID.randomUUID(),
                command.tipoCredito(),
                command.monto(),
                command.plazoMeses(),
                command.correo()
            )
            .filter(existe -> existe)
            .flatMap(existe -> {
                log.info("Solicitud duplicada detectada para key: {}", idempotenciaKey);
                return repository.findByCorreoSolicitante(command.correo())
                    .filter(s -> s.idempotenciaKey() != null && s.idempotenciaKey().equals(idempotenciaKey))
                    .take(1)
                    .singleOrEmpty();
            });
    }

    /**
     * Crea una nueva solicitud a partir del comando.
     */
    private Mono<SolicitudCredito> crearSolicitud(CrearSolicitudCommand command) {
        UUID id = command.id() != null ? command.id() : UUID.randomUUID();
        String idempotenciaKey = generarIdempotenciaKey(command);
        LocalDateTime ahora = LocalDateTime.now();

        SolicitudCredito solicitud = new SolicitudCredito(
            id,
            command.tipoCredito(),
            command.monto(),
            command.plazoMeses(),
            command.correo(),
            command.nombre(),
            command.identificacion(),
            EstadoSolicitud.PENDIENTE,
            null,
            idempotenciaKey,
            ahora,
            ahora,
            0L
        );

        log.debug("Creando nueva solicitud con id: {} y estado: PENDIENTE", id);
        return Mono.just(solicitud);
    }

    /**
     * Valida la solicitud utilizando el servicio de validación.
     */
    private Mono<SolicitudCredito> validarSolicitud(SolicitudCredito solicitud) {
        log.debug("Validando solicitud {}", solicitud.id());

        return validacionService.validarSolicitud(solicitud)
            .map(resultado -> {
                if (resultado.esValida()) {
                    log.info("Solicitud {} aprobada en validación", solicitud.id());
                    return solicitud.conEstado(EstadoSolicitud.APROBADA);
                } else {
                    log.info("Solicitud {} rechazada en validación: {}", 
                        solicitud.id(), resultado.motivoRechazo());
                    return solicitud.conEstado(EstadoSolicitud.RECHAZADA)
                        .conMotivoRechazo(resultado.motivoRechazo());
                }
            });
    }

    /**
     * Persiste la solicitud en el repositorio.
     */
    private Mono<SolicitudCredito> persistirSolicitud(SolicitudCredito solicitud) {
        log.debug("Persistiendo solicitud {}", solicitud.id());

        return repository.save(solicitud)
            .doOnSuccess(saved -> log.info("Solicitud {} persistida exitosamente", saved.id()))
            .doOnError(error -> log.error("Error al persistir solicitud: {}", error.getMessage()));
    }

    /**
     * Envía notificación al cliente con el resultado.
     */
    private Mono<SolicitudCredito> notificarResultado(SolicitudCredito solicitud) {
        log.debug("Enviando notificación para solicitud {}", solicitud.id());

        return notificacionService.notificarResultado(solicitud)
            .thenReturn(solicitud)
            .doOnSuccess(s -> log.info("Notificación enviada para solicitud {}", s.id()))
            .onErrorResume(error -> {
                log.warn("Error al enviar notificación para solicitud {}: {}", 
                    solicitud.id(), error.getMessage());
                return Mono.just(solicitud);
            });
    }

    /**
     * Publica un evento asíncrono a Kafka para consumo por otros servicios.
     */
    private Mono<SolicitudCredito> publicarEvento(SolicitudCredito solicitud) {
        log.debug("Publicando evento para solicitud {}", solicitud.id());

        SolicitudCreditoEvent event = new SolicitudCreditoEvent(
            solicitud.id().toString(),
            solicitud.correoSolicitante(),
            solicitud.estado().name(),
            solicitud.monto(),
            LocalDateTime.now()
        );

        return kafkaTemplate.send(TOPIC_SOLICITUDES, solicitud.id().toString(), event)
            .doOnSuccess(result -> log.info("Evento publicado para solicitud {} en partition: {}",
                solicitud.id(), result.getRecordMetadata().partition()))
            .doOnError(error -> log.error("Error al publicar evento: {}", error.getMessage()))
            .thenReturn(solicitud);
    }

    /**
     * Genera la clave de idempotencia basada en los parámetros de la solicitud.
     */
    private String generarIdempotenciaKey(CrearSolicitudCommand command) {
        return String.format("%s-%s-%s-%s",
            command.tipoCredito(),
            command.monto(),
            command.plazoMeses(),
            command.correo());
    }
}