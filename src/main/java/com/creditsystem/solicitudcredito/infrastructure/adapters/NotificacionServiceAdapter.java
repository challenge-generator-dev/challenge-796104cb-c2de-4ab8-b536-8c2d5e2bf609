package com.creditsystem.solicitudcredito.infrastructure.adapters;

import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito;
import com.creditsystem.solicitudcredito.domain.ports.NotificacionService;
import com.creditsystem.solicitudcredito.infrastructure.events.SolicitudCreditoEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class NotificacionServiceAdapter implements NotificacionService {

    private static final Logger log = LoggerFactory.getLogger(NotificacionServiceAdapter.class);

    private final KafkaTemplate<String, SolicitudCreditoEvent> kafkaTemplate;
    private final String notificationTopic;
    private final int deliveryTimeoutSeconds;

    public NotificacionServiceAdapter(
            KafkaTemplate<String, SolicitudCreditoEvent> kafkaTemplate,
            @Value("${spring.kafka.topic.notifications:solicitud-credito-notificaciones}") String notificationTopic,
            @Value("${spring.kafka.producer.delivery-timeout-seconds:30}") int deliveryTimeoutSeconds) {
        this.kafkaTemplate = kafkaTemplate;
        this.notificationTopic = notificationTopic;
        this.deliveryTimeoutSeconds = deliveryTimeoutSeconds;
    }

    @Override
    public Mono<Boolean> enviarNotificacion(SolicitudCredito solicitud) {
        log.info("Enviando notificación para solicitud: id={}, correo={}, estado={}",
                solicitud.id(), solicitud.correoSolicitante(), solicitud.estado());

        SolicitudCreditoEvent event = buildEvent(solicitud);
        String key = solicitud.id().toString();

        return Mono.fromFuture(() -> {
            CompletableFuture<SendResult<String, SolicitudCreditoEvent>> future =
                    kafkaTemplate.send(notificationTopic, key, event);

            return future.whenComplete((result, ex) -> {
                if (ex != null) {
                    log.error("Error al enviar notificación para solicitud {}: {}",
                            solicitud.id(), ex.getMessage(), ex);
                } else {
                    log.info("Notificación enviada exitosamente para solicitud {}. Partición={}, Offset={}",
                            solicitud.id(),
                            result.getRecordMetadata().partition(),
                            result.getRecordMetadata().offset());
                }
            });
        }).map(result -> true)
        .onErrorResume(error -> {
            log.error("Error al enviar notificación para solicitud {}: {}",
                    solicitud.id(), error.getMessage());
            return Mono.just(false);
        });
    }

    @Override
    public Mono<Boolean> enviarNotificacionAsync(SolicitudCredito solicitud) {
        log.debug("Enviando notificación asíncrona para solicitud: id={}", solicitud.id());

        SolicitudCreditoEvent event = buildEvent(solicitud);
        String key = solicitud.id().toString();

        kafkaTemplate.send(notificationTopic, key, event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Error en notificación asíncrona para solicitud {}: {}",
                                solicitud.id(), ex.getMessage(), ex);
                    } else {
                        log.debug("Notificación asíncrona enviada para solicitud {}. Partición={}, Offset={}",
                                solicitud.id(),
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                    }
                });

        return Mono.just(true);
    }

    private SolicitudCreditoEvent buildEvent(SolicitudCredito solicitud) {
        return new SolicitudCreditoEvent(
                UUID.randomUUID(),
                solicitud.id(),
                solicitud.correoSolicitante(),
                solicitud.estado().name(),
                solicitud.tipoCredito(),
                solicitud.monto(),
                solicitud.plazoMeses(),
                solicitud.motivoRechazo(),
                Instant.now()
        );
    }
}