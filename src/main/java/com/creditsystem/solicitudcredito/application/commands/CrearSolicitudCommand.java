package com.creditsystem.solicitudcredito.application.commands;


import com.creditsystem.solicitudcredito.domain.model.EstadoSolicitud;
import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CrearSolicitudCommand(
    @NotBlank(message = "El correo del solicitante es obligatorio")
    @Email(message = "El correo debe tener un formato válido")
    String correoSolicitante,

    @NotBlank(message = "El tipo de crédito es obligatorio")
    @Size(min = 3, max = 50, message = "El tipo de crédito debe tener entre 3 y 50 caracteres")
    String tipoCredito,

    @NotNull(message = "El monto solicitado es obligatorio")
    @Positive(message = "El monto debe ser mayor a cero")
    @DecimalMin(value = "1000.00", message = "El monto mínimo es 1000")
    @DecimalMax(value = "500000.00", message = "El monto máximo es 500000")
    BigDecimal montoSolicitado,

    @NotNull(message = "El plazo en meses es obligatorio")
    @Min(value = 6, message = "El plazo mínimo es 6 meses")
    @Max(value = 120, message = "El plazo máximo es 120 meses")
    Integer plazoMeses,

    @NotBlank(message = "El nombre del solicitante es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    String nombreSolicitante,

    String telefono,
    String direccion,
    Integer edad,
    Integer scoreBuro,
    Boolean tieneHistorialCrediticio,
    BigDecimal ingresoMensual,
    BigDecimal otrosIngresos,
    String idempotencyKey
) {
    public SolicitudCredito toEntity() {
        return new SolicitudCredito(
            UUID.randomUUID(),
            tipoCredito,
            montoSolicitado,
            plazoMeses,
            nombreSolicitante,
            correoSolicitante,
            telefono,
            direccion,
            edad,
            scoreBuro,
            tieneHistorialCrediticio,
            ingresoMensual,
            otrosIngresos,
            SolicitudCredito.EstadoSolicitud.PENDIENTE,
            LocalDateTime.now(),
            null,
            idempotencyKey != null ? idempotencyKey : UUID.randomUUID().toString(),
            null
        );
    }

    public SolicitudCredito toEntityWithId(UUID id) {
        return new SolicitudCredito(
            id,
            tipoCredito,
            montoSolicitado,
            plazoMeses,
            nombreSolicitante,
            correoSolicitante,
            telefono,
            direccion,
            edad,
            scoreBuro,
            tieneHistorialCrediticio,
            ingresoMensual,
            otrosIngresos,
            SolicitudCredito.EstadoSolicitud.PENDIENTE,
            LocalDateTime.now(),
            null,
            idempotencyKey != null ? idempotencyKey : UUID.randomUUID().toString(),
            null
        );
    }

    public String generarIdempotencyKey() {
        if (idempotencyKey != null && !idempotencyKey.isBlank()) {
            return idempotencyKey;
        }
        return String.format("%s-%s-%s-%s", tipoCredito, montoSolicitado.toPlainString(), plazoMeses, correoSolicitante);
    }

    public boolean tieneDatosCompletos() {
        return correoSolicitante != null && tipoCredito != null && 
               montoSolicitado != null && plazoMeses != null && 
               nombreSolicitante != null;
    }

    public boolean esMontoValidoParaPerfil() {
        if (edad != null && edad < 25) {
            return montoSolicitado.compareTo(new BigDecimal("50000")) <= 0;
        }
        if (scoreBuro != null && scoreBuro < 600) {
            return montoSolicitado.compareTo(new BigDecimal("30000")) <= 0;
        }
        return true;
    }

    public boolean requiereValidacionAdicional() {
        return (edad != null && edad < 21) ||
               (scoreBuro != null && scoreBuro < 650) ||
               (montoSolicitado.compareTo(new BigDecimal("100000")) > 0);
    }
}