package com.creditsystem.solicitudcredito.domain.ports;

import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito;
import reactor.core.publisher.Mono;

/**
 * Puerto de salida para el servicio de notificación.
 * Define el contrato que la infraestructura debe implementar para enviar
 * notificaciones a los clientes sobre el resultado de su solicitud de crédito.
 */
public interface NotificacionService {

    /**
     * Envía una notificación al cliente con el resultado de su solicitud.
     * @param solicitud La solicitud procesada con su estado final
     * @return Mono vacío que indica éxito, o error si la notificación falla
     */
    Mono<Void> notificarResultado(SolicitudCredito solicitud);

    /**
     * Envía una notificación asíncrona de confirmación de recepción.
     * @param solicitud La solicitud recibida
     * @return Mono vacío que indica éxito
     */
    Mono<Void> notificarRecepcion(SolicitudCredito solicitud);
}