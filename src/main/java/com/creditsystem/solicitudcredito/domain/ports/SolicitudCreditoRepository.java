package com.creditsystem.solicitudcredito.domain.ports;


import com.creditsystem.solicitudcredito.domain.model.EstadoSolicitud;
import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;
import java.util.UUID;

public interface SolicitudCreditoRepository {
    Mono<SolicitudCredito> save(SolicitudCredito solicitud);
    
    Mono<SolicitudCredito> findById(UUID id);
    
    Flux<SolicitudCredito> findByCorreoSolicitante(String correo);
    
    Flux<SolicitudCredito> findByEstado(SolicitudCredito.EstadoSolicitud estado);
    
    Mono<Boolean> existsByIdempotenciaKey(UUID id, String tipoCredito, BigDecimal monto, Integer plazoMeses, String correo);
}