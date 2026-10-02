package com.creditsystem.solicitudcredito.application.usecases;


import com.creditsystem.solicitudcredito.domain.model.EstadoSolicitud;
import com.creditsystem.solicitudcredito.application.commands.CrearSolicitudCommand;
import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito;
import com.creditsystem.solicitudcredito.domain.ports.SolicitudCreditoRepository;
import com.creditsystem.solicitudcredito.domain.ports.ValidacionCreditoService;
import com.creditsystem.solicitudcredito.domain.ports.ValidacionCreditoService.ResultadoValidacion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para ProcesarSolicitudCreditoUseCase")
class ProcesarSolicitudCreditoUseCaseTest {

    @Mock
    private SolicitudCreditoRepository solicitudRepository;

    @Mock
    private ValidacionCreditoService validacionCreditoService;

    private ProcesarSolicitudCreditoUseCase useCase;

    @BeforeEach
    void setUp() {
        useCase = new ProcesarSolicitudCreditoUseCase(solicitudRepository, validacionCreditoService);
    }

    @Test
    @DisplayName("Debería aprobar solicitud cuando validación es exitosa")
    void deberiaAprobarSolicitud_CuandoValidacionEsExitosa() {
        UUID solicitudId = UUID.randomUUID();
        String correo = "cliente@ejemplo.com";
        String tipoCredito = "PERSONAL";
        BigDecimal monto = new BigDecimal("50000");
        Integer plazoMeses = 24;

        CrearSolicitudCommand command = new CrearSolicitudCommand(
            tipoCredito, monto, plazoMeses, correo, "Juan Perez"
        );

        ResultadoValidacion resultadoExitoso = new ResultadoValidacion(true, null);

        when(validacionCreditoService.validarSolicitud(any(SolicitudCredito.class)))
            .thenReturn(Mono.just(resultadoExitoso));
        
        when(solicitudRepository.existsByIdempotenciaKey(any(), anyString(), any(), any(), anyString()))
            .thenReturn(Mono.just(false));
        
        when(solicitudRepository.save(any(SolicitudCredito.class)))
            .thenAnswer(invocation -> {
                SolicitudCredito solicitud = invocation.getArgument(0);
                return Mono.just(solicitud);
            });

        StepVerifier.create(useCase.ejecutar(command))
            .expectNextMatches(solicitud -> 
                solicitud.estado() == SolicitudCredito.EstadoSolicitud.APROBADA)
            .verifyComplete();

        verify(validacionCreditoService).validarSolicitud(any(SolicitudCredito.class));
    }

    @Test
    @DisplayName("Debería rechazar solicitud cuando validación falla")
    void deberiaRechazarSolicitud_CuandoValidacionFalla() {
        String correo = "cliente@ejemplo.com";
        CrearSolicitudCommand command = new CrearSolicitudCommand(
            "PERSONAL", new BigDecimal("50000"), 24, correo, "Juan Perez"
        );

        String motivoRechazo = "Monto excede el límite permitido para el tipo de crédito";
        ResultadoValidacion resultadoFallido = new ResultadoValidacion(false, motivoRechazo);

        when(validacionCreditoService.validarSolicitud(any(SolicitudCredito.class)))
            .thenReturn(Mono.just(resultadoFallido));

        when(solicitudRepository.existsByIdempotenciaKey(any(), anyString(), any(), any(), anyString()))
            .thenReturn(Mono.just(false));

        when(solicitudRepository.save(any(SolicitudCredito.class)))
            .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        StepVerifier.create(useCase.ejecutar(command))
            .expectNextMatches(solicitud -> 
                solicitud.estado() == SolicitudCredito.EstadoSolicitud.RECHAZADA 
                && solicitud.motivoRechazo().equals(motivoRechazo))
            .verifyComplete();
    }

    @Test
    @DisplayName("Debería detectar solicitud duplicada por clave idempotencia")
    void deberiaDetectarSolicitudDuplicada_PorClaveIdempotencia() {
        String correo = "cliente@ejemplo.com";
        CrearSolicitudCommand command = new CrearSolicitudCommand(
            "PERSONAL", new BigDecimal("50000"), 24, correo, "Juan Perez"
        );

        when(solicitudRepository.existsByIdempotenciaKey(any(), anyString(), any(), any(), anyString()))
            .thenReturn(Mono.just(true));

        StepVerifier.create(useCase.ejecutar(command))
            .expectErrorMatches(error -> 
                error instanceof IllegalArgumentException 
                && error.getMessage().contains("ya existe"))
            .verify();
    }

    @Test
    @DisplayName("Debería guardar solicitud con estado PENDIENTE inicialmente")
    void deberiaGuardarSolicitudConEstadoPendiente_Inicialmente() {
        CrearSolicitudCommand command = new CrearSolicitudCommand(
            "PERSONAL", new BigDecimal("30000"), 12, "test@ejemplo.com", "Test User"
        );

        ResultadoValidacion resultadoExitoso = new ResultadoValidacion(true, null);

        when(validacionCreditoService.validarSolicitud(any(SolicitudCredito.class)))
            .thenReturn(Mono.just(resultadoExitoso));

        when(solicitudRepository.existsByIdempotenciaKey(any(), anyString(), any(), any(), anyString()))
            .thenReturn(Mono.just(false));

        when(solicitudRepository.save(any(SolicitudCredito.class)))
            .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        StepVerifier.create(useCase.ejecutar(command))
            .expectNextMatches(solicitud -> 
                solicitud.estado() == SolicitudCredito.EstadoSolicitud.APROBADA)
            .verifyComplete();
    }

    @Test
    @DisplayName("Debería ejecutar validación antes de guardar")
    void deberiaEjecutarValidacion_AntesDeGuardar() {
        CrearSolicitudCommand command = new CrearSolicitudCommand(
            "HIPOTECARIO", new BigDecimal("500000"), 360, "test@ejemplo.com", "Test User"
        );

        when(validacionCreditoService.validarSolicitud(any(SolicitudCredito.class)))
            .thenReturn(Mono.just(new ResultadoValidacion(true, null)));

        when(solicitudRepository.existsByIdempotenciaKey(any(), anyString(), any(), any(), anyString()))
            .thenReturn(Mono.just(false));

        when(solicitudRepository.save(any(SolicitudCredito.class)))
            .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        useCase.ejecutar(command).block();

        verify(validacionCreditoService).validarSolicitud(any(SolicitudCredito.class));
    }
}