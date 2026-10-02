package com.creditsystem.solicitudcredito.infrastructure.adapters;

import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito;
import com.creditsystem.solicitudcredito.domain.ports.ValidacionCreditoService;
import com.creditsystem.solicitudcredito.domain.ports.ValidacionCreditoService.ResultadoValidacion;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.UUID;

@Component
public class ValidacionCreditoServiceAdapter implements ValidacionCreditoService {

    private static final Logger log = LoggerFactory.getLogger(ValidacionCreditoServiceAdapter.class);

    private final WebClient webClient;
    private final String serviceUrl;
    private final Duration timeout;

    public ValidacionCreditoServiceAdapter(
            @Value("${external.validation-service.url:http://localhost:8081/api/validacion}") String serviceUrl,
            @Value("${external.validation-service.timeout:5000}") int timeoutMs) {
        this.webClient = WebClient.builder()
                .baseUrl(serviceUrl)
                .defaultHeader("Content-Type", "application/json")
                .build();
        this.serviceUrl = serviceUrl;
        this.timeout = Duration.ofMillis(timeoutMs);
    }

    @Override
    @CircuitBreaker(name = "validacionCreditoCircuitBreaker", fallbackMethod = "fallbackValidacion")
    @Retry(name = "validacionCreditoRetry")
    public Mono<ResultadoValidacion> validarSolicitud(SolicitudCredito solicitud) {
        log.info("Iniciando validación de solicitud de crédito: id={}, correo={}, monto={}, plazo={} meses",
                solicitud.id(), solicitud.correoSolicitante(), solicitud.monto(), solicitud.plazoMeses());

        ValidacionRequest request = new ValidacionRequest(
                solicitud.id(),
                solicitud.tipoCredito(),
                solicitud.monto(),
                solicitud.plazoMeses(),
                solicitud.correoSolicitante(),
                solicitud.ingresoMensual()
        );

        return webClient.post()
                .uri("/validar")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ValidacionResponse.class)
                .timeout(timeout)
                .map(response -> new ResultadoValidacion(
                        response.aprobada(),
                        response.score(),
                        response.mensaje(),
                        response.codigoRiesgo()
                ))
                .doOnSuccess(result -> log.info("Validación completada: id={}, aprobada={}, score={}",
                        solicitud.id(), result.aprobada(), result.score()))
                .doOnError(error -> log.error("Error en validación de solicitud {}: {}",
                        solicitud.id(), error.getMessage()));
    }

    private Mono<ResultadoValidacion> fallbackValidacion(SolicitudCredito solicitud, Throwable error) {
        log.warn("Fallback activado para validación de solicitud {}: {}", solicitud.id(), error.getMessage());
        return Mono.just(new ResultadoValidacion(
                false,
                0,
                "Servicio de validación no disponible. Por favor intente más tarde.",
                "SERVICIO_NO_DISPONIBLE"
        ));
    }

    private record ValidacionRequest(
            UUID id,
            String tipoCredito,
            java.math.BigDecimal monto,
            Integer plazoMeses,
            String correo,
            java.math.BigDecimal ingresoMensual
    ) {}

    private record ValidacionResponse(
            boolean aprobada,
            int score,
            String mensaje,
            String codigoRiesgo
    ) {}
}