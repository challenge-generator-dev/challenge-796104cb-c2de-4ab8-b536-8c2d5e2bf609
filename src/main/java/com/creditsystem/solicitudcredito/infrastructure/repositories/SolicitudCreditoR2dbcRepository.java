package com.creditsystem.solicitudcredito.infrastructure.repositories;

import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito;
import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito.EstadoSolicitud;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Repositorio reactivo para la entidad SolicitudCredito utilizando Spring Data R2DBC.
 * Proporciona operaciones de persistencia asíncronas hacia PostgreSQL.
 */
public interface SolicitudCreditoR2dbcRepository extends R2dbcRepository<SolicitudCreditoEntity, UUID> {

    /**
     * Busca solicitudes por correo del solicitante.
     * @param correo Correo electrónico del solicitante
     * @return Flux de solicitudes encontradas
     */
    Flux<SolicitudCreditoEntity> findByCorreoSolicitante(String correo);

    /**
     * Busca solicitudes por estado.
     * @param estado Estado de la solicitud
     * @return Flux de solicitudes en el estado especificado
     */
    Flux<SolicitudCreditoEntity> findByEstado(EstadoSolicitud estado);

    /**
     * Verifica si existe una solicitud con la misma clave de idempotencia.
     * @param idemKey Clave de idempotencia
     * @return Mono con true si existe, false en caso contrario
     */
    @Transient
    Mono<Boolean> existsByIdempotenciaKey(String idemKey);

    /**
     * Entidad mapeada a la tabla solicitud_credito en PostgreSQL.
     */
    @Table("solicitud_credito")
    class SolicitudCreditoEntity {

        @Id
        private UUID id;

        @Column("tipo_credito")
        private String tipoCredito;

        @Column("monto")
        private BigDecimal monto;

        @Column("plazo_meses")
        private Integer plazoMeses;

        @Column("correo_solicitante")
        private String correoSolicitante;

        @Column("nombre_solicitante")
        private String nombreSolicitante;

        @Column("identificacion")
        private String identificacion;

        @Column("estado")
        private EstadoSolicitud estado;

        @Column("motivo_rechazo")
        private String motivoRechazo;

        @Column("idempotencia_key")
        private String idempotenciaKey;

        @Column("fecha_creacion")
        private LocalDateTime fechaCreacion;

        @Column("fecha_actualizacion")
        private LocalDateTime fechaActualizacion;

        @Column("version")
        private Long version;

        public SolicitudCreditoEntity() {
        }

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getTipoCredito() {
            return tipoCredito;
        }

        public void setTipoCredito(String tipoCredito) {
            this.tipoCredito = tipoCredito;
        }

        public BigDecimal getMonto() {
            return monto;
        }

        public void setMonto(BigDecimal monto) {
            this.monto = monto;
        }

        public Integer getPlazoMeses() {
            return plazoMeses;
        }

        public void setPlazoMeses(Integer plazoMeses) {
            this.plazoMeses = plazoMeses;
        }

        public String getCorreoSolicitante() {
            return correoSolicitante;
        }

        public void setCorreoSolicitante(String correoSolicitante) {
            this.correoSolicitante = correoSolicitante;
        }

        public String getNombreSolicitante() {
            return nombreSolicitante;
        }

        public void setNombreSolicitante(String nombreSolicitante) {
            this.nombreSolicitante = nombreSolicitante;
        }

        public String getIdentificacion() {
            return identificacion;
        }

        public void setIdentificacion(String identificacion) {
            this.identificacion = identificacion;
        }

        public EstadoSolicitud getEstado() {
            return estado;
        }

        public void setEstado(EstadoSolicitud estado) {
            this.estado = estado;
        }

        public String getMotivoRechazo() {
            return motivoRechazo;
        }

        public void setMotivoRechazo(String motivoRechazo) {
            this.motivoRechazo = motivoRechazo;
        }

        public String getIdempotenciaKey() {
            return idempotenciaKey;
        }

        public void setIdempotenciaKey(String idempotenciaKey) {
            this.idempotenciaKey = idempotenciaKey;
        }

        public LocalDateTime getFechaCreacion() {
            return fechaCreacion;
        }

        public void setFechaCreacion(LocalDateTime fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
        }

        public LocalDateTime getFechaActualizacion() {
            return fechaActualizacion;
        }

        public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
            this.fechaActualizacion = fechaActualizacion;
        }

        public Long getVersion() {
            return version;
        }

        public void setVersion(Long version) {
            this.version = version;
        }

        /**
         * Convierte la entidad al modelo del dominio.
         * @return Instancia de SolicitudCredito
         */
        public SolicitudCredito toDomain() {
            SolicitudCredito solicitud = new SolicitudCredito(
                id,
                tipoCredito,
                monto,
                plazoMeses,
                correoSolicitante,
                nombreSolicitante,
                identificacion,
                estado,
                motivoRechazo,
                idempotenciaKey,
                fechaCreacion,
                fechaActualizacion,
                version
            );
            return solicitud;
        }

        /**
         * Crea una entidad desde el modelo del dominio.
         * @param solicitud Modelo del dominio
         * @return Nueva instancia de SolicitudCreditoEntity
         */
        public static SolicitudCreditoEntity fromDomain(SolicitudCredito solicitud) {
            SolicitudCreditoEntity entity = new SolicitudCreditoEntity();
            entity.setId(solicitud.id());
            entity.setTipoCredito(solicitud.tipoCredito());
            entity.setMonto(solicitud.monto());
            entity.setPlazoMeses(solicitud.plazoMeses());
            entity.setCorreoSolicitante(solicitud.correoSolicitante());
            entity.setNombreSolicitante(solicitud.nombreSolicitante());
            entity.setIdentificacion(solicitud.identificacion());
            entity.setEstado(solicitud.estado());
            entity.setMotivoRechazo(solicitud.motivoRechazo());
            entity.setIdempotenciaKey(solicitud.idempotenciaKey());
            entity.setFechaCreacion(solicitud.fechaCreacion());
            entity.setFechaActualizacion(solicitud.fechaActualizacion());
            entity.setVersion(solicitud.version());
            return entity;
        }
    }
}