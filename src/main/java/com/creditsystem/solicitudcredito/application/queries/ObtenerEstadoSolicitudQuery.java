package com.creditsystem.solicitudcredito.application.queries;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ObtenerEstadoSolicitudQuery(
    @NotNull(message = "El ID de la solicitud es obligatorio")
    UUID solicitudId
) {
    public ObtenerEstadoSolicitudQuery {
        if (solicitudId == null) {
            throw new IllegalArgumentException("El ID de la solicitud no puede ser nulo");
        }
    }

    public static ObtenerEstadoSolicitudQuery of(UUID solicitudId) {
        return new ObtenerEstadoSolicitudQuery(solicitudId);
    }

    public static ObtenerEstadoSolicitudQuery of(String solicitudIdString) {
        return new ObtenerEstadoSolicitudQuery(UUID.fromString(solicitudIdString));
    }
}