package com.creditsystem.solicitudcredito.infrastructure.adapters;



import com.creditsystem.solicitudcredito.infrastructure.repositories.SolicitudCreditoEntity;
import com.creditsystem.solicitudcredito.domain.model.EstadoSolicitud;
import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito;
import com.creditsystem.solicitudcredito.domain.ports.SolicitudCreditoRepository;
import com.creditsystem.solicitudcredito.infrastructure.repositories.SolicitudCreditoR2dbcRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public class SolicitudCreditoRepositoryAdapter implements SolicitudCreditoRepository {

    private static final Logger log = LoggerFactory.getLogger(SolicitudCreditoRepositoryAdapter.class);

    private final SolicitudCreditoR2dbcRepository r2dbcRepository;

    public SolicitudCreditoRepositoryAdapter(SolicitudCreditoR2dbcRepository r2dbcRepository) {
        this.r2dbcRepository = r2dbcRepository;
    }

    @Override
    public Mono<SolicitudCredito> save(SolicitudCredito solicitud) {
        log.info("Guardando solicitud de crédito con ID: {}", solicitud.id());
        return r2dbcRepository.save(toEntity(solicitud))
            .doOnSuccess(entity -> log.info("Solicitud guardada exitosamente: {}", entity.getId()))
            .doOnError(error -> log.error("Error al guardar solicitud: {}", error.getMessage()))
            .map(this::toRecord);
    }

    @Override
    public Mono<SolicitudCredito> findById(UUID id) {
        log.debug("Buscando solicitud por ID: {}", id);
        return r2dbcRepository.findById(id)
            .switchIfEmpty(Mono.defer(() -> {
                log.warn("No se encontró solicitud con ID: {}", id);
                return Mono.empty();
            }))
            .map(this::toRecord);
    }

    @Override
    public Flux<SolicitudCredito> findByCorreoSolicitante(String correo) {
        log.debug("Buscando solicitudes para correo: {}", correo);
        return r2dbcRepository.findByCorreoSolicitante(correo)
            .map(this::toRecord);
    }

    @Override
    public Flux<SolicitudCredito> findByEstado(SolicitudCredito.EstadoSolicitud estado) {
        log.debug("Buscando solicitudes con estado: {}", estado);
        return r2dbcRepository.findByEstado(estado.name())
            .map(this::toRecord);
    }

    @Override
    public Mono<Boolean> existsByIdempotenciaKey(UUID id, String tipoCredito, BigDecimal monto, Integer plazoMeses, String correo) {
        log.debug("Verificando clave de idempotencia para solicitud existente");
        return r2dbcRepository.existsByIdempotenciaKey(id, tipoCredito, monto, plazoMeses, correo)
            .defaultIfEmpty(false);
    }

    private com.creditsystem.solicitudcredito.infrastructure.repositories.SolicitudCreditoEntity toEntity(SolicitudCredito solicitud) {
        com.creditsystem.solicitudcredito.infrastructure.repositories.SolicitudCreditoEntity entity = 
            new com.creditsystem.solicitudcredito.infrastructure.repositories.SolicitudCreditoEntity();
        entity.setId(solicitud.id());
        entity.setTipoCredito(solicitud.tipoCredito());
        entity.setMontoSolicitado(solicitud.montoSolicitado());
        entity.setPlazoMeses(solicitud.plazoMeses());
        entity.setNombreSolicitante(solicitud.nombreSolicitante());
        entity.setCorreoSolicitante(solicitud.correoSolicitante());
        entity.setTelefono(solicitud.telefono());
        entity.setDireccion(solicitud.direccion());
        entity.setEdad(solicitud.edad());
        entity.setScoreBuro(solicitud.scoreBuro());
        entity.setTieneHistorialCrediticio(solicitud.tieneHistorialCrediticio());
        entity.setIngresoMensual(solicitud.ingresoMensual());
        entity.setOtrosIngresos(solicitud.otrosIngresos());
        entity.setEstado(solicitud.estado().name());
        entity.setFechaSolicitud(solicitud.fechaSolicitud());
        entity.setFechaActualizacion(solicitud.fechaActualizacion());
        entity.setIdempotenciaKey(solicitud.idempotenciaKey());
        entity.setMotivoRechazo(solicitud.motivoRechazo());
        return entity;
    }

    private SolicitudCredito toRecord(com.creditsystem.solicitudcredito.infrastructure.repositories.SolicitudCreditoEntity entity) {
        return new SolicitudCredito(
            entity.getId(),
            entity.getTipoCredito(),
            entity.getMontoSolicitado(),
            entity.getPlazoMeses(),
            entity.getNombreSolicitante(),
            entity.getCorreoSolicitante(),
            entity.getTelefono(),
            entity.getDireccion(),
            entity.getEdad(),
            entity.getScoreBuro(),
            entity.getTieneHistorialCrediticio(),
            entity.getIngresoMensual(),
            entity.getOtrosIngresos(),
            SolicitudCredito.EstadoSolicitud.valueOf(entity.getEstado()),
            entity.getFechaSolicitud(),
            entity.getFechaActualizacion(),
            entity.getIdempotenciaKey(),
            entity.getMotivoRechazo()
        );
    }
}