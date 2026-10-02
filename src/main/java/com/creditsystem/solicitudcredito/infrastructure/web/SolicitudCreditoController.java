package com.creditsystem.solicitudcredito.infrastructure.web;


import com.creditsystem.solicitudcredito.domain.model.EstadoSolicitud;
import com.creditsystem.solicitudcredito.application.commands.CrearSolicitudCommand;
import com.creditsystem.solicitudcredito.application.queries.ObtenerEstadoSolicitudQuery;
import com.creditsystem.solicitudcredito.application.usecases.ProcesarSolicitudCreditoUseCase;
import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/solicitudes")
@Tag(name = "Solicitudes de Crédito", description = "API para gestionar solicitudes de crédito")
public class SolicitudCreditoController {

    private final ProcesarSolicitudCreditoUseCase procesarSolicitudUseCase;
    private final ObtenerEstadoSolicitudQuery obtenerEstadoQuery;

    public SolicitudCreditoController(
            ProcesarSolicitudCreditoUseCase procesarSolicitudUseCase,
            ObtenerEstadoSolicitudQuery obtenerEstadoQuery) {
        this.procesarSolicitudUseCase = procesarSolicitudUseCase;
        this.obtenerEstadoQuery = obtenerEstadoQuery;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Crear solicitud de crédito", description = "Crea una nueva solicitud de crédito y la procesa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Solicitud creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos"),
            @ApiResponse(responseCode = "409", description = "Solicitud duplicada (idempotencia)"),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor")
    })
    public Mono<ResponseEntity<SolicitudCredito>> crearSolicitud(
            @Valid @RequestBody CrearSolicitudCommand command) {
        return procesarSolicitudUseCase.ejecutar(command)
                .map(solicitud -> ResponseEntity.status(HttpStatus.CREATED).body(solicitud))
                .onErrorResume(Error.class, e ->
                        Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Obtener solicitud por ID", description = "Recupera una solicitud de crédito por su identificador único")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud encontrada"),
            @ApiResponse(responseCode = "404", description = "Solicitud no encontrada")
    })
    public Mono<ResponseEntity<SolicitudCredito>> obtenerPorId(
            @Parameter(description = "ID de la solicitud", required = true)
            @PathVariable UUID id) {
        return Mono.fromCallable(() -> id)
                .flatMap(obtenerEstadoQuery::buscarPorId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/correo/{correo}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar solicitudes por correo", description = "Lista todas las solicitudes de crédito asociadas a un correo electrónico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de solicitudes retornada")
    })
    public Flux<SolicitudCredito> obtenerPorCorreo(
            @Parameter(description = "Correo del solicitante", required = true)
            @PathVariable String correo) {
        return obtenerEstadoQuery.buscarPorCorreo(correo);
    }

    @GetMapping(value = "/estado/{estado}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Listar solicitudes por estado", description = "Lista todas las solicitudes de crédito con un estado específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de solicitudes retornada")
    })
    public Flux<SolicitudCredito> obtenerPorEstado(
            @Parameter(description = "Estado de la solicitud", required = true)
            @PathVariable SolicitudCredito.EstadoSolicitud estado) {
        return obtenerEstadoQuery.buscarPorEstado(estado);
    }
}