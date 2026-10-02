package com.creditsystem.solicitudcredito.domain.ports;

import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito;
import reactor.core.publisher.Mono;

public interface ValidacionCreditoService {
    Mono<ResultadoValidacion> validarSolicitud(SolicitudCredito solicitud);

    record ResultadoValidacion(
        boolean aprobada,
        String motivoRechazo
    ) {
        public static ResultadoValidacion aprobada() {
            return new ResultadoValidacion(true, null);
        }
        
        public static ResultadoValidacion rechazada(String motivo) {
            return new ResultadoValidacion(false, motivo);
        }
    }
}