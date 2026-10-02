package com.creditsystem.solicitudcredito.infrastructure.events;


import com.creditsystem.solicitudcredito.domain.model.EstadoSolicitud;
import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record SolicitudCreditoEvent(
        UUID eventId,
        UUID solicitudId,
        String tipoEvento,
        SolicitudCredito.EstadoSolicitud estadoAnterior,
        SolicitudCredito.EstadoSolicitud estadoNuevo,
        String correoSolicitante,
        BigDecimal montoSolicitado,
        Integer plazoMeses,
        String tipoCredito,
        Instant timestamp,
        String motivoRechazo,
        String correlationId
) {
    public static SolicitudCreditoEvent crear(
            UUID solicitudId,
            String tipoEvento,
            SolicitudCredito.EstadoSolicitud estadoAnterior,
            SolicitudCredito.EstadoSolicitud estadoNuevo,
            String correoSolicitante,
            BigDecimal montoSolicitado,
            Integer plazoMeses,
            String tipoCredito,
            String motivoRechazo,
            String correlationId) {
        return new SolicitudCreditoEvent(
                UUID.randomUUID(),
                solicitudId,
                tipoEvento,
                estadoAnterior,
                estadoNuevo,
                correoSolicitante,
                montoSolicitado,
                plazoMeses,
                tipoCredito,
                Instant.now(),
                motivoRechazo,
                correlationId
        );
    }

    public static final String EVENTO_SOLICITUD_CREADA = "SOLICITUD_CREADA";
    public static final String EVENTO_SOLICITUD_APROBADA = "SOLICITUD_APROBADA";
    public static final String EVENTO_SOLICITUD_RECHAZADA = "SOLICITUD_RECHAZADA";
    public static final String EVENTO_SOLICITUD_EN_VALIDACION = "SOLICITUD_EN_VALIDACION";
    public static final String EVENTO_ERROR_VALIDACION = "ERROR_VALIDACION";
}