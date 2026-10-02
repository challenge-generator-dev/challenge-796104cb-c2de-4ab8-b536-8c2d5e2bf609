package com.creditsystem.solicitudcredito.infrastructure.web;


import com.creditsystem.solicitudcredito.domain.model.EstadoSolicitud;
import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito;
import com.creditsystem.solicitudcredito.domain.ports.SolicitudCreditoRepository;
import com.creditsystem.solicitudcredito.domain.ports.ValidacionCreditoService;
import com.creditsystem.solicitudcredito.domain.ports.ValidacionCreditoService.ResultadoValidacion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@DisplayName("Tests de integración para SolicitudCreditoController")
class SolicitudCreditoControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private SolicitudCreditoRepository solicitudRepository;

    @MockBean
    private ValidacionCreditoService validacionCreditoService;

    @Test
    @DisplayName("POST /api/solicitudes debería crear solicitud exitosamente")
    void postSolicitudes_DeberiaCrearSolicitudExitosamente() {
        String tipoCredito = "PERSONAL";
        BigDecimal monto = new BigDecimal("25000.00");
        Integer plazoMeses = 18;
        String correo = "cliente@banco.com";
        String nombreCompleto = "María García López";

        SolicitudCredito solicitudCreada = new SolicitudCredito(
            UUID.randomUUID(), tipoCredito, monto, plazoMeses, correo, 
            nombreCompleto, SolicitudCredito.EstadoSolicitud.APROBADA, 
            null, UUID.randomUUID().toString(), null
        );

        when(validacionCreditoService.validarSolicitud(any(SolicitudCredito.class)))
            .thenReturn(Mono.just(new ResultadoValidacion(true, null)));

        when(solicitudRepository.existsByIdempotenciaKey(any(), any(), any(), any(), any()))
            .thenReturn(Mono.just(false));

        when(solicitudRepository.save(any(SolicitudCredito.class)))
            .thenReturn(Mono.just(solicitudCreada));

        webTestClient.post()
            .uri("/api/solicitudes")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue("""
                {
                    "tipoCredito": "%s",
                    "monto": %s,
                    "plazoMeses": %d,
                    "correoSolicitante": "%s",
                    "nombreCompleto": "%s"
                }
                """.formatted(tipoCredito, monto.toString(), plazoMeses, correo, nombreCompleto))
            .exchange()
            .expectStatus().isCreated()
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.estado").isEqualTo("APROBADA");
    }

    @Test
    @DisplayName("POST /api/solicitudes debería validar entrada requerida")
    void postSolicitudes_DeberiaValidarEntradaRequerida() {
        webTestClient.post()
            .uri("/api/solicitudes")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue("""
                {
                    "tipoCredito": "PERSONAL"
                }
                """)
            .exchange()
            .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("POST /api/solicitudes debería validar monto positivo")
    void postSolicitudes_DeberiaValidarMontoPositivo() {
        webTestClient.post()
            .uri("/api/solicitudes")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue("""
                {
                    "tipoCredito": "PERSONAL",
                    "monto": -1000,
                    "plazoMeses": 12,
                    "correoSolicitante": "test@test.com",
                    "nombreCompleto": "Test User"
                }
                """)
            .exchange()
            .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("POST /api/solicitudes debería rechazar solicitud inválida")
    void postSolicitudes_DeberiaRechazarSolicitudInvalida() {
        String correo = "cliente@banco.com";

        when(validacionCreditoService.validarSolicitud(any(SolicitudCredito.class)))
            .thenReturn(Mono.just(new ResultadoValidacion(false, "Score crediticio insuficiente")));

        when(solicitudRepository.existsByIdempotenciaKey(any(), any(), any(), any(), any()))
            .thenReturn(Mono.just(false));

        when(solicitudRepository.save(any(SolicitudCredito.class)))
            .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        webTestClient.post()
            .uri("/api/solicitudes")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue("""
                {
                    "tipoCredito": "PERSONAL",
                    "monto": 100000,
                    "plazoMeses": 60,
                    "correoSolicitante": "%s",
                    "nombreCompleto": "Test User"
                }
                """.formatted(correo))
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.estado").isEqualTo("RECHAZADA");
    }

    @Test
    @DisplayName("GET /api/solicitudes/{id} debería obtener solicitud por ID")
    void getSolicitudPorId_DeberiaObtenerSolicitud() {
        UUID solicitudId = UUID.randomUUID();
        SolicitudCredito solicitud = new SolicitudCredito(
            solicitudId, "CONSUMO", new BigDecimal("15000"), 12,
            "cliente@test.com", "Pedro Martínez",
            SolicitudCredito.EstadoSolicitud.APROBADA, null,
            "key-123", null
        );

        when(solicitudRepository.findById(solicitudId))
            .thenReturn(Mono.just(solicitud));

        webTestClient.get()
            .uri("/api/solicitudes/{id}", solicitudId)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.id").isEqualTo(solicitudId.toString())
            .jsonPath("$.tipoCredito").isEqualTo("CONSUMO");
    }

    @Test
    @DisplayName("GET /api/solicitudes/{id} debería retornar 404 si no existe")
    void getSolicitudPorId_DeberiaRetornar404SiNoExiste() {
        UUID solicitudId = UUID.randomUUID();

        when(solicitudRepository.findById(solicitudId))
            .thenReturn(Mono.empty());

        webTestClient.get()
            .uri("/api/solicitudes/{id}", solicitudId)
            .exchange()
            .expectStatus().isNotFound();
    }

    @Test
    @DisplayName("GET /api/solicitaciones por correo debería filtrar correctamente")
    void getSolicitudesPorCorreo_DeberiaFiltrarCorrectamente() {
        String correo = "buscado@ejemplo.com";

        when(solicitudRepository.findByCorreoSolicitante(correo))
            .thenReturn(reactor.core.publisher.Flux.empty());

        webTestClient.get()
            .uri("/api/solicitaciones?correo={correo}", correo)
            .exchange()
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$").isArray();
    }
}