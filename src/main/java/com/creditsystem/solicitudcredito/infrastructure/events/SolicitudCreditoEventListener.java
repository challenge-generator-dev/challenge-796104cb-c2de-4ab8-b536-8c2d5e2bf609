package com.creditsystem.solicitudcredito.infrastructure.events;

import com.creditsystem.solicitudcredito.domain.ports.NotificacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

@Component
public class SolicitudCreditoEventListener {

    private static final Logger log = LoggerFactory.getLogger(SolicitudCreditoEventListener.class);
    private static final String TOPICO_SOLICITUDES = "solicitudes-credito-events";

    private final NotificacionService notificacionService;

    public SolicitudCreditoEventListener(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @KafkaListener(topics = TOPICO_SOLICITUDES, groupId = "${spring.kafka.consumer.group-id:credito-notificaciones}")
    public void consumirEventoSolicitud(Map<String, Object> evento) {
        String eventType = (String) evento.get("eventType");
        UUID solicitudId = UUID.fromString((String) evento.get("solicitudId"));
        String correoCliente = (String) evento.get("correoCliente");
        String mensaje = (String) evento.get("mensaje");

        log.info("Evento recibido: tipo={}, solicitudId={}, correo={}", eventType, solicitudId, correoCliente);

        switch (eventType) {
            case "SOLICITUD_RECIBIDA" -> procesarSolicitudRecibida(solicitudId, correoCliente, mensaje);
            case "SOLICITUD_APROBADA" -> procesarSolicitudAprobada(solicitudId, correoCliente, mensaje);
            case "SOLICITUD_RECHAZADA" -> procesarSolicitudRechazada(solicitudId, correoCliente, mensaje);
            default -> log.warn("Tipo de evento desconocido: {}", eventType);
        }
    }

    private void procesarSolicitudRecibida(UUID solicitudId, String correoCliente, String mensajeBase) {
        String mensaje = mensajeBase != null ? mensajeBase 
            : "Su solicitud de crédito ha sido recibida y está siendo procesada.";
        
        notificacionService.enviarNotificacion(correoCliente, "Solicitud Recepcional", mensaje)
            .doOnSuccess(v -> log.info("Notificación de recepción enviada para solicitud: {}", solicitudId))
            .doOnError(e -> log.error("Error al enviar notificación de recepción para solicitud: {}", solicitudId, e))
            .subscribe();
    }

    private void procesarSolicitudAprobada(UUID solicitudId, String correoCliente, String mensajeBase) {
        String mensaje = mensajeBase != null ? mensajeBase 
            : "¡Felicitaciones! Su solicitud de crédito ha sido aprobada.";
        
        notificacionService.enviarNotificacion(correoCliente, "Aprobación de Crédito", mensaje)
            .doOnSuccess(v -> log.info("Notificación de aprobación enviada para solicitud: {}", solicitudId))
            .doOnError(e -> log.error("Error al enviar notificación de aprobación para solicitud: {}", solicitudId, e))
            .subscribe();
    }

    private void procesarSolicitudRechazada(UUID solicitudId, String correoCliente, String mensajeBase) {
        String mensaje = mensajeBase != null ? mensajeBase 
            : "Lamentamos informarle que su solicitud de crédito ha sido rechazada.";
        
        String motivo = (String) evento.get("motivoRechazo");
        if (motivo != null && !motivo.isBlank()) {
            mensaje += "\nMotivo: " + motivo;
        }
        
        notificacionService.enviarNotificacion(correoCliente, "Rechazo de Crédito", mensaje)
            .doOnSuccess(v -> log.info("Notificación de rechazo enviada para solicitud: {}", solicitudId))
            .doOnError(e -> log.error("Error al enviar notificación de rechazo para solicitud: {}", solicitudId, e))
            .subscribe();
    }

    public Mono<Void> procesarEventoSincrono(Map<String, Object> evento) {
        String eventType = (String) evento.get("eventType");
        UUID solicitudId = UUID.fromString((String) evento.get("solicitudId"));
        String correoCliente = (String) evento.get("correoCliente");
        String mensaje = (String) evento.get("mensaje");

        return switch (eventType) {
            case "SOLICITUD_RECIBIDA" -> {
                String msg = mensaje != null ? mensaje 
                    : "Su solicitud de crédito ha sido recibida y está siendo procesada.";
                yield notificacionService.enviarNotificacion(correoCliente, "Solicitud Recepcional", msg);
            }
            case "SOLICITUD_APROBADA" -> {
                String msg = mensaje != null ? mensaje 
                    : "¡Felicitaciones! Su solicitud de crédito ha sido aprobada.";
                yield notificacionService.enviarNotificacion(correoCliente, "Aprobación de Crédito", msg);
            }
            case "SOLICITUD_RECHAZADA" -> {
                String msg = mensaje != null ? mensaje 
                    : "Lamentamos informarle que su solicitud de crédito ha sido rechazada.";
                yield notificacionService.enviarNotificacion(correoCliente, "Rechazo de Crédito", msg);
            }
            default -> Mono.empty();
        };
    }
}