package com.creditsystem.solicitudcredito.domain.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record SolicitudCredito(
    @NotNull UUID id,
    @NotNull @Positive(message = "El monto debe ser positivo") BigDecimal monto,
    @NotNull @Positive(message = "El plazo debe ser positivo") Integer plazoMeses,
    @NotNull @Pattern(regexp = "^[A-Z]{3}$", message = "El tipo de crédito debe ser un código de 3 letras mayúsculas") String tipoCredito,
    @NotNull @Email(message = "El correo debe ser válido") String correoSolicitante,
    @NotNull EstadoSolicitud estado,
    @NotNull LocalDateTime fechaCreacion,
    LocalDateTime fechaActualizacion,
    String motivoRechazo
) {
    public enum EstadoSolicitud {
        PENDIENTE,
        VALIDANDO,
        APROBADA,
        RECHAZADA
    }

    public SolicitudCredito {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto debe ser positivo");
        }
        if (plazoMeses <= 0) {
            throw new IllegalArgumentException("El plazo debe ser positivo");
        }
        if (!tipoCredito.matches("^[A-Z]{3}$")) {
            throw new IllegalArgumentException("El tipo de crédito debe ser un código de 3 letras mayúsculas");
        }
    }

    public SolicitudCredito conEstado(EstadoSolicitud nuevoEstado) {
        return new SolicitudCredito(
            this.id,
            this.monto,
            this.plazoMeses,
            this.tipoCredito,
            this.correoSolicitante,
            nuevoEstado,
            this.fechaCreacion,
            LocalDateTime.now(),
            this.motivoRechazo
        );
    }

    public SolicitudCredito conMotivoRechazo(String motivo) {
        return new SolicitudCredito(
            this.id,
            this.monto,
            this.plazoMeses,
            this.tipoCredito,
            this.correoSolicitante,
            EstadoSolicitud.RECHAZADA,
            this.fechaCreacion,
            LocalDateTime.now(),
            motivo
        );
    }

    public boolean esIdempotente(SolicitudCredito otra) {
        return this.id.equals(otra.id) &&
               this.monto.equals(otra.monto) &&
               this.plazoMeses.equals(otra.plazoMeses) &&
               this.tipoCredito.equals(otra.tipoCredito) &&
               this.correoSolicitante.equals(otra.correoSolicitante);
    }
}