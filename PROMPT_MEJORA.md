# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/creditsystem/solicitudcredito/Application.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.OpenAPI pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/creditsystem/solicitudcredito/domain/ports/SolicitudCreditoRepository.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/creditsystem/solicitudcredito/domain/ports/ValidacionCreditoService.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/creditsystem/solicitudcredito/domain/ports/NotificacionService.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/ValidacionCreditoServiceAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/ValidacionCreditoServiceAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/config/KafkaConfig.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/web/SolicitudCreditoController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/web/SolicitudCreditoController.java` — `reactor.core.publisher`: El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/events/SolicitudCreditoEventListener.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/events/SolicitudCreditoEventListener.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCaseTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/creditsystem/solicitudcredito/infrastructure/web/SolicitudCreditoControllerTest.java` — `reactor.core.publisher`: El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setId`: Se invoca `setId` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.id`: Se invoca `id` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setTipoCredito`: Se invoca `setTipoCredito` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.tipoCredito`: Se invoca `tipoCredito` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setMonto`: Se invoca `setMonto` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.monto`: Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setPlazoMeses`: Se invoca `setPlazoMeses` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.plazoMeses`: Se invoca `plazoMeses` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setCorreoSolicitante`: Se invoca `setCorreoSolicitante` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.correoSolicitante`: Se invoca `correoSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setNombreSolicitante`: Se invoca `setNombreSolicitante` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.nombreSolicitante`: Se invoca `nombreSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setIdentificacion`: Se invoca `setIdentificacion` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.identificacion`: Se invoca `identificacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setEstado`: Se invoca `setEstado` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.estado`: Se invoca `estado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setMotivoRechazo`: Se invoca `setMotivoRechazo` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.motivoRechazo`: Se invoca `motivoRechazo` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setIdempotenciaKey`: Se invoca `setIdempotenciaKey` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.idempotenciaKey`: Se invoca `idempotenciaKey` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setFechaCreacion`: Se invoca `setFechaCreacion` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.fechaCreacion`: Se invoca `fechaCreacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setFechaActualizacion`: Se invoca `setFechaActualizacion` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.fechaActualizacion`: Se invoca `fechaActualizacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setVersion`: Se invoca `setVersion` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.version`: Se invoca `version` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `CrearSolicitudCommand.correo`: Se invoca `correo` sobre `CrearSolicitudCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.id`: Se invoca `id` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.estado`: Se invoca `estado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCreditoRepository.findByIdempotenciaKey`: Se invoca `findByIdempotenciaKey` sobre `SolicitudCreditoRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `CrearSolicitudCommand.id`: Se invoca `id` sobre `CrearSolicitudCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `CrearSolicitudCommand.tipoCredito`: Se invoca `tipoCredito` sobre `CrearSolicitudCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `CrearSolicitudCommand.monto`: Se invoca `monto` sobre `CrearSolicitudCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `CrearSolicitudCommand.plazoMeses`: Se invoca `plazoMeses` sobre `CrearSolicitudCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `CrearSolicitudCommand.nombre`: Se invoca `nombre` sobre `CrearSolicitudCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `CrearSolicitudCommand.identificacion`: Se invoca `identificacion` sobre `CrearSolicitudCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.correoSolicitante`: Se invoca `correoSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.monto`: Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.id`: Se invoca `id` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoR2dbcRepository.save`: Se invoca `save` sobre `SolicitudCreditoR2dbcRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getId`: Se invoca `getId` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoR2dbcRepository.findById`: Se invoca `findById` sobre `SolicitudCreditoR2dbcRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `EstadoSolicitud.name`: Se invoca `name` sobre `EstadoSolicitud`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setId`: Se invoca `setId` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setTipoCredito`: Se invoca `setTipoCredito` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.tipoCredito`: Se invoca `tipoCredito` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setMontoSolicitado`: Se invoca `setMontoSolicitado` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.montoSolicitado`: Se invoca `montoSolicitado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setPlazoMeses`: Se invoca `setPlazoMeses` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.plazoMeses`: Se invoca `plazoMeses` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setNombreSolicitante`: Se invoca `setNombreSolicitante` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.nombreSolicitante`: Se invoca `nombreSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setCorreoSolicitante`: Se invoca `setCorreoSolicitante` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.correoSolicitante`: Se invoca `correoSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setTelefono`: Se invoca `setTelefono` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.telefono`: Se invoca `telefono` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setDireccion`: Se invoca `setDireccion` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.direccion`: Se invoca `direccion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setEdad`: Se invoca `setEdad` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.edad`: Se invoca `edad` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setScoreBuro`: Se invoca `setScoreBuro` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.scoreBuro`: Se invoca `scoreBuro` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setTieneHistorialCrediticio`: Se invoca `setTieneHistorialCrediticio` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.tieneHistorialCrediticio`: Se invoca `tieneHistorialCrediticio` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setIngresoMensual`: Se invoca `setIngresoMensual` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.ingresoMensual`: Se invoca `ingresoMensual` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setOtrosIngresos`: Se invoca `setOtrosIngresos` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.otrosIngresos`: Se invoca `otrosIngresos` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setEstado`: Se invoca `setEstado` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.estado`: Se invoca `estado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setFechaSolicitud`: Se invoca `setFechaSolicitud` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.fechaSolicitud`: Se invoca `fechaSolicitud` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setFechaActualizacion`: Se invoca `setFechaActualizacion` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.fechaActualizacion`: Se invoca `fechaActualizacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setIdempotenciaKey`: Se invoca `setIdempotenciaKey` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.idempotenciaKey`: Se invoca `idempotenciaKey` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setMotivoRechazo`: Se invoca `setMotivoRechazo` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.motivoRechazo`: Se invoca `motivoRechazo` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getTipoCredito`: Se invoca `getTipoCredito` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getMontoSolicitado`: Se invoca `getMontoSolicitado` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getPlazoMeses`: Se invoca `getPlazoMeses` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getNombreSolicitante`: Se invoca `getNombreSolicitante` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getCorreoSolicitante`: Se invoca `getCorreoSolicitante` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getTelefono`: Se invoca `getTelefono` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getDireccion`: Se invoca `getDireccion` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getEdad`: Se invoca `getEdad` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getScoreBuro`: Se invoca `getScoreBuro` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getTieneHistorialCrediticio`: Se invoca `getTieneHistorialCrediticio` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getIngresoMensual`: Se invoca `getIngresoMensual` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getOtrosIngresos`: Se invoca `getOtrosIngresos` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getEstado`: Se invoca `getEstado` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getFechaSolicitud`: Se invoca `getFechaSolicitud` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getFechaActualizacion`: Se invoca `getFechaActualizacion` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getIdempotenciaKey`: Se invoca `getIdempotenciaKey` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getMotivoRechazo`: Se invoca `getMotivoRechazo` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/ValidacionCreditoServiceAdapter.java` — `SolicitudCredito.id`: Se invoca `id` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/ValidacionCreditoServiceAdapter.java` — `SolicitudCredito.correoSolicitante`: Se invoca `correoSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/ValidacionCreditoServiceAdapter.java` — `SolicitudCredito.monto`: Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/ValidacionCreditoServiceAdapter.java` — `SolicitudCredito.plazoMeses`: Se invoca `plazoMeses` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/ValidacionCreditoServiceAdapter.java` — `SolicitudCredito.tipoCredito`: Se invoca `tipoCredito` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/ValidacionCreditoServiceAdapter.java` — `SolicitudCredito.ingresoMensual`: Se invoca `ingresoMensual` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java` — `SolicitudCredito.id`: Se invoca `id` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java` — `SolicitudCredito.correoSolicitante`: Se invoca `correoSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java` — `SolicitudCredito.estado`: Se invoca `estado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java` — `SolicitudCredito.tipoCredito`: Se invoca `tipoCredito` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java` — `SolicitudCredito.monto`: Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java` — `SolicitudCredito.plazoMeses`: Se invoca `plazoMeses` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java` — `SolicitudCredito.motivoRechazo`: Se invoca `motivoRechazo` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/web/SolicitudCreditoController.java` — `ProcesarSolicitudCreditoUseCase.ejecutar`: Se invoca `ejecutar` sobre `ProcesarSolicitudCreditoUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/web/SolicitudCreditoController.java` — `ObtenerEstadoSolicitudQuery.buscarPorCorreo`: Se invoca `buscarPorCorreo` sobre `ObtenerEstadoSolicitudQuery`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/web/SolicitudCreditoController.java` — `ObtenerEstadoSolicitudQuery.buscarPorEstado`: Se invoca `buscarPorEstado` sobre `ObtenerEstadoSolicitudQuery`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/events/SolicitudCreditoEventListener.java` — `NotificacionService.enviarNotificacion`: Se invoca `enviarNotificacion` sobre `NotificacionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCaseTest.java` — `ProcesarSolicitudCreditoUseCase.ejecutar`: Se invoca `ejecutar` sobre `ProcesarSolicitudCreditoUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCaseTest.java` — `SolicitudCredito.estado`: Se invoca `estado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCaseTest.java` — `SolicitudCredito.motivoRechazo`: Se invoca `motivoRechazo` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `pom.xml` — `io.r2dbc:r2dbc-postgresql@1.0.5.RELEASE`: io.r2dbc:r2dbc-postgresql declara la version 1.0.5.RELEASE, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Spring, Tecnología Java, Senior L2

### Brecha de conocimiento
x

### Reto
- Tema: E2E inval
- Seniority: senior-l2
- Tipo: mixed
- Título: Diseño y validación de flujo E2E para solicitud de crédito
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Definición de requisitos y validación de entrada — objetivo: Establecer los requisitos funcionales y no funcionales para el flujo de solicitud de crédito. — entregable (NO resolver): Documento de requisitos funcionales y no funcionales para el flujo de solicitud de crédito.
- Fase 2: Diseño del flujo E2E — objetivo: Diseñar el flujo completo de solicitud de crédito desde la entrada hasta la notificación del resultado. — entregable (NO resolver): Diagrama del flujo E2E y documento de decisiones de diseño.
- Fase 3: Validación y optimización del flujo — objetivo: Validar el diseño del flujo y optimizar su rendimiento y robustez. — entregable (NO resolver): Reporte de validación y optimización del flujo E2E.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.creditsystem</groupId>
    <artifactId>solicitud-credito</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>solicitud-credito</name>
    <description>Sistema de solicitud de crédito con manejo reactivo y eventos</description>

    <properties>
        <java.version>21</java.version>
        <resilience4j.version>2.2.0</resilience4j.version>
        <r2dbc.version>1.0.5.RELEASE</r2dbc.version>
        <springdoc-openapi.version>2.9.1</springdoc-openapi.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-webflux</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-r2dbc</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.kafka</groupId>
            <artifactId>spring-kafka</artifactId>
        </dependency>

        <!-- R2DBC -->
        <dependency>
            <groupId>io.r2dbc</groupId>
            <artifactId>r2dbc-postgresql</artifactId>
            <version>${r2dbc.version}</version>
        </dependency>

        <!-- Resilience4j -->
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-spring-boot3</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>
        <dependency>
            <groupId>io.github.resilience4j</groupId>
            <artifactId>resilience4j-reactor</artifactId>
            <version>${resilience4j.version}</version>
        </dependency>

        <!-- SpringDoc OpenAPI -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webflux-ui</artifactId>
            <version>${springdoc-openapi.version}</version>
        </dependency>

        <!-- Test -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.testcontainers</groupId>
            <artifactId>postgresql</artifactId>
            <version>1.20.1</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.kafka</groupId>
            <artifactId>spring-kafka-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>io.projectreactor</groupId>
            <artifactId>reactor-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${java.version}</source>
                    <target>${java.version}</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>

// === ARCHIVO: src/main/java/com/creditsystem/solicitudcredito/Application.java ===
package com.creditsystem.solicitudcredito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.config.EnableWebFlux;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@SpringBootApplication
@EnableWebFlux
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Solicitud de Crédito")
                        .version("1.0")
                        .description("API reactiva para gestionar solicitudes de crédito con validación y notificaciones")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  r2dbc:
    url: r2dbc:postgresql://localhost:5432/creditsystem
    username: postgres
    password: postgres
    pool:
      enabled: true
      initial-size: 10
      max-size: 30
      max-idle-time: 30m
      validation-query: SELECT 1
  kafka:
    bootstrap-servers: localhost:9092
    producer:
      key-serializer: org.apache.kafka.common.serialization.StringSerializer
      value-serializer: org.springframework.kafka.support.serializer.JsonSerializer
      properties:
        spring.json.add.type.headers: false
    consumer:
      group-id: solicitud-credito-group
      key-deserializer: org.apache.kafka.common.serialization.StringDeserializer
      value-deserializer: org.springframework.kafka.support.serializer.JsonDeserializer
      properties:
        spring.json.trusted.packages: "com.creditsystem.solicitudcredito.infrastructure.events"
        spring.json.use.type.headers: false
      auto-offset-reset: earliest

resilience4j:
  circuitbreaker:
    configs:
      default:
        slidingWindowSize: 10
        minimumNumberOfCalls: 5
        permittedNumberOfCallsInHalfOpenState: 3
        automaticTransitionFromOpenToHalfOpenEnabled: true
        waitDurationInOpenState: 5s
        failureRateThreshold: 50
        eventConsumerBufferSize: 10
        recordExceptions:
          - org.springframework.web.client.HttpServerErrorException
          - java.io.IOException
          - java.util.concurrent.TimeoutException
          - org.springframework.kafka.KafkaException
  retry:
    configs:
      default:
        maxAttempts: 3
        waitDuration: 100ms
        retryExceptions:
          - org.springframework.web.client.HttpServerErrorException
          - java.io.IOException
          - java.util.concurrent.TimeoutException
  ratelimiter:
    configs:
      default:
        limitForPeriod: 1500
        limitRefreshPeriod: 1s
        timeoutDuration: 0
        registerHealthIndicator: true

management:
  endpoints:
    web:
      exposure:
        include: health,metrics,prometheus,circuitbreakers,ratelimiters
  endpoint:
    health:
      show-details: always
  health:
    circuitbreakers:
      enabled: true
    ratelimiters:
      enabled: true

logging:
  level:
    root: INFO
    com.creditsystem: DEBUG
    org.springframework.r2dbc: DEBUG
    io.r2dbc.postgresql.QUERY: DEBUG
    io.r2dbc.postgresql.PARAM: DEBUG
    org.springframework.kafka: INFO

// === ARCHIVO: src/main/java/com/creditsystem/solicitudcredito/domain/model/SolicitudCredito.java ===
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

// === ARCHIVO: src/main/java/com/creditsystem/solicitudcredito/domain/ports/SolicitudCreditoRepository.java ===
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

// === ARCHIVO: src/main/java/com/creditsystem/solicitudcredito/domain/ports/ValidacionCreditoService.java ===
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

// === ARCHIVO: src/main/java/com/creditsystem/solicitudcredito/domain/ports/NotificacionService.java ===
package com.creditsystem.solicitudcredito.domain.ports;

import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito;
import reactor.core.publisher.Mono;

/**
 * Puerto de salida para el servicio de notificación.
 * Define el contrato que la infraestructura debe implementar para enviar
 * notificaciones a los clientes sobre el resultado de su solicitud de crédito.
 */
public interface NotificacionService {

    /**
     * Envía una notificación al cliente con el resultado de su solicitud.
     * @param solicitud La solicitud procesada con su estado final
     * @return Mono vacío que indica éxito, o error si la notificación falla
     */
    Mono<Void> notificarResultado(SolicitudCredito solicitud);

    /**
     * Envía una notificación asíncrona de confirmación de recepción.
     * @param solicitud La solicitud recibida
     * @return Mono vacío que indica éxito
     */
    Mono<Void> notificarRecepcion(SolicitudCredito solicitud);
}

// === ARCHIVO: src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java ===
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

// === ARCHIVO: src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java ===
package com.creditsystem.solicitudcredito.application.usecases;

import com.creditsystem.solicitudcredito.application.commands.CrearSolicitudCommand;
import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito;
import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito.EstadoSolicitud;
import com.creditsystem.solicitudcredito.domain.ports.NotificacionService;
import com.creditsystem.solicitudcredito.domain.ports.SolicitudCreditoRepository;
import com.creditsystem.solicitudcredito.domain.ports.ValidacionCreditoService;
import com.creditsystem.solicitudcredito.domain.ports.ValidacionCreditoService.ResultadoValidacion;
import com.creditsystem.solicitudcredito.infrastructure.events.SolicitudCreditoEvent;
import com.creditsystem.solicitudcredito.infrastructure.repositories.SolicitudCreditoR2dbcRepository.SolicitudCreditoEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Caso de uso principal para el procesamiento de solicitudes de crédito.
 * Orquesta el flujo completo: validación, persistencia, notificación y publicación de eventos.
 */
@Service
public class ProcesarSolicitudCreditoUseCase {

    private static final Logger log = LoggerFactory.getLogger(ProcesarSolicitudCreditoUseCase.class);
    private static final String TOPIC_SOLICITUDES = "solicitud-credito-events";

    private final SolicitudCreditoRepository repository;
    private final ValidacionCreditoService validacionService;
    private final NotificacionService notificacionService;
    private final KafkaTemplate<String, SolicitudCreditoEvent> kafkaTemplate;

    public ProcesarSolicitudCreditoUseCase(
            @Qualifier("solicitudCreditoRepositoryAdapter") SolicitudCreditoRepository repository,
            @Qualifier("validacionCreditoServiceAdapter") ValidacionCreditoService validacionService,
            @Qualifier("notificacionServiceAdapter") NotificacionService notificacionService,
            KafkaTemplate<String, SolicitudCreditoEvent> kafkaTemplate) {
        this.repository = repository;
        this.validacionService = validacionService;
        this.notificacionService = notificacionService;
        this.kafkaTemplate = kafkaTemplate;
    }

    /**
     * Ejecuta el flujo completo de procesamiento de una solicitud de crédito.
     * 1. Verifica idempotencia
     * 2. Valida la solicitud
     * 3. Persiste la solicitud
     * 4. Notifica al cliente
     * 5. Publica evento asíncrono
     *
     * @param command Comando con los datos de la solicitud
     * @return Mono con la solicitud procesada
     */
    public Mono<SolicitudCredito> execute(CrearSolicitudCommand command) {
        log.info("Iniciando procesamiento de solicitud para correo: {}", command.correo());

        return verificarIdempotencia(command)
            .switchIfEmpty(Mono.defer(() -> crearSolicitud(command)))
            .flatMap(this::validarSolicitud)
            .flatMap(this::persistirSolicitud)
            .flatMap(this::notificarResultado)
            .flatMap(this::publicarEvento)
            .doOnSuccess(solicitud -> log.info("Solicitud {} procesada exitosamente con estado: {}",
                solicitud.id(), solicitud.estado()))
            .doOnError(error -> log.error("Error al procesar solicitud: {}", error.getMessage()));
    }

    /**
     * Verifica si existe una solicitud previa con la misma clave de idempotencia.
     * Si existe, retorna la solicitud anterior para garantizar idempotencia.
     */
    private Mono<SolicitudCredito> verificarIdempotencia(CrearSolicitudCommand command) {
        String idempotenciaKey = generarIdempotenciaKey(command);
        log.debug("Verificando idempotencia con key: {}", idempotenciaKey);

        return repository.findByIdempotenciaKey(
                command.id() != null ? command.id() : UUID.randomUUID(),
                command.tipoCredito(),
                command.monto(),
                command.plazoMeses(),
                command.correo()
            )
            .filter(existe -> existe)
            .flatMap(existe -> {
                log.info("Solicitud duplicada detectada para key: {}", idempotenciaKey);
                return repository.findByCorreoSolicitante(command.correo())
                    .filter(s -> s.idempotenciaKey() != null && s.idempotenciaKey().equals(idempotenciaKey))
                    .take(1)
                    .singleOrEmpty();
            });
    }

    /**
     * Crea una nueva solicitud a partir del comando.
     */
    private Mono<SolicitudCredito> crearSolicitud(CrearSolicitudCommand command) {
        UUID id = command.id() != null ? command.id() : UUID.randomUUID();
        String idempotenciaKey = generarIdempotenciaKey(command);
        LocalDateTime ahora = LocalDateTime.now();

        SolicitudCredito solicitud = new SolicitudCredito(
            id,
            command.tipoCredito(),
            command.monto(),
            command.plazoMeses(),
            command.correo(),
            command.nombre(),
            command.identificacion(),
            EstadoSolicitud.PENDIENTE,
            null,
            idempotenciaKey,
            ahora,
            ahora,
            0L
        );

        log.debug("Creando nueva solicitud con id: {} y estado: PENDIENTE", id);
        return Mono.just(solicitud);
    }

    /**
     * Valida la solicitud utilizando el servicio de validación.
     */
    private Mono<SolicitudCredito> validarSolicitud(SolicitudCredito solicitud) {
        log.debug("Validando solicitud {}", solicitud.id());

        return validacionService.validarSolicitud(solicitud)
            .map(resultado -> {
                if (resultado.esValida()) {
                    log.info("Solicitud {} aprobada en validación", solicitud.id());
                    return solicitud.conEstado(EstadoSolicitud.APROBADA);
                } else {
                    log.info("Solicitud {} rechazada en validación: {}", 
                        solicitud.id(), resultado.motivoRechazo());
                    return solicitud.conEstado(EstadoSolicitud.RECHAZADA)
                        .conMotivoRechazo(resultado.motivoRechazo());
                }
            });
    }

    /**
     * Persiste la solicitud en el repositorio.
     */
    private Mono<SolicitudCredito> persistirSolicitud(SolicitudCredito solicitud) {
        log.debug("Persistiendo solicitud {}", solicitud.id());

        return repository.save(solicitud)
            .doOnSuccess(saved -> log.info("Solicitud {} persistida exitosamente", saved.id()))
            .doOnError(error -> log.error("Error al persistir solicitud: {}", error.getMessage()));
    }

    /**
     * Envía notificación al cliente con el resultado.
     */
    private Mono<SolicitudCredito> notificarResultado(SolicitudCredito solicitud) {
        log.debug("Enviando notificación para solicitud {}", solicitud.id());

        return notificacionService.notificarResultado(solicitud)
            .thenReturn(solicitud)
            .doOnSuccess(s -> log.info("Notificación enviada para solicitud {}", s.id()))
            .onErrorResume(error -> {
                log.warn("Error al enviar notificación para solicitud {}: {}", 
                    solicitud.id(), error.getMessage());
                return Mono.just(solicitud);
            });
    }

    /**
     * Publica un evento asíncrono a Kafka para consumo por otros servicios.
     */
    private Mono<SolicitudCredito> publicarEvento(SolicitudCredito solicitud) {
        log.debug("Publicando evento para solicitud {}", solicitud.id());

        SolicitudCreditoEvent event = new SolicitudCreditoEvent(
            solicitud.id().toString(),
            solicitud.correoSolicitante(),
            solicitud.estado().name(),
            solicitud.monto(),
            LocalDateTime.now()
        );

        return kafkaTemplate.send(TOPIC_SOLICITUDES, solicitud.id().toString(), event)
            .doOnSuccess(result -> log.info("Evento publicado para solicitud {} en partition: {}",
                solicitud.id(), result.getRecordMetadata().partition()))
            .doOnError(error -> log.error("Error al publicar evento: {}", error.getMessage()))
            .thenReturn(solicitud);
    }

    /**
     * Genera la clave de idempotencia basada en los parámetros de la solicitud.
     */
    private String generarIdempotenciaKey(CrearSolicitudCommand command) {
        return String.format("%s-%s-%s-%s",
            command.tipoCredito(),
            command.monto(),
            command.plazoMeses(),
            command.correo());
    }
}

// === ARCHIVO: src/main/java/com/creditsystem/solicitudcredito/application/commands/CrearSolicitudCommand.java ===
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

// === ARCHIVO: src/main/java/com/creditsystem/solicitudcredito/application/queries/ObtenerEstadoSolicitudQuery.java ===
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

// === ARCHIVO: src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java ===
package com.creditsystem.solicitudcredito.infrastructure.adapters;



import com.creditsystem.solicitudcredito.infrastructure.repositories.SolicitudCreditoEntity;
import com.creditsystem.solicitudcredito.domain.model.EstadoSolicitud;
import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito;
import com.creditsystem.solicitudcredito.domain.ports.SolicitudCreditoRepository;
import com.creditsystem.solicitudcredito.infrastructure.repositories.SolicitudCreditoR2dbcRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public class SolicitudCreditoRepositoryAdapter implements SolicitudCreditoRepository {

    private static final Logger log = LoggerFactory.getLogger(SolicitudCreditoRepositoryAdapter.class);

    private final SolicitudCreditoR2dbcRepository r2dbcRepository;

    public SolicitudCreditoRepositoryAdapter(SolicitudCreditoR2dbcRepository r2dbcRepository) {
        this.r2dbcRepository = r2dbcRepository;
    }

    @Override
    public Mono<SolicitudCredito> save(SolicitudCredito solicitud) {
        log.info("Guardando solicitud de crédito con ID: {}", solicitud.id());
        return r2dbcRepository.save(toEntity(solicitud))
            .doOnSuccess(entity -> log.info("Solicitud guardada exitosamente: {}", entity.getId()))
            .doOnError(error -> log.error("Error al guardar solicitud: {}", error.getMessage()))
            .map(this::toRecord);
    }

    @Override
    public Mono<SolicitudCredito> findById(UUID id) {
        log.debug("Buscando solicitud por ID: {}", id);
        return r2dbcRepository.findById(id)
            .switchIfEmpty(Mono.defer(() -> {
                log.warn("No se encontró solicitud con ID: {}", id);
                return Mono.empty();
            }))
            .map(this::toRecord);
    }

    @Override
    public Flux<SolicitudCredito> findByCorreoSolicitante(String correo) {
        log.debug("Buscando solicitudes para correo: {}", correo);
        return r2dbcRepository.findByCorreoSolicitante(correo)
            .map(this::toRecord);
    }

    @Override
    public Flux<SolicitudCredito> findByEstado(SolicitudCredito.EstadoSolicitud estado) {
        log.debug("Buscando solicitudes con estado: {}", estado);
        return r2dbcRepository.findByEstado(estado.name())
            .map(this::toRecord);
    }

    @Override
    public Mono<Boolean> existsByIdempotenciaKey(UUID id, String tipoCredito, BigDecimal monto, Integer plazoMeses, String correo) {
        log.debug("Verificando clave de idempotencia para solicitud existente");
        return r2dbcRepository.existsByIdempotenciaKey(id, tipoCredito, monto, plazoMeses, correo)
            .defaultIfEmpty(false);
    }

    private com.creditsystem.solicitudcredito.infrastructure.repositories.SolicitudCreditoEntity toEntity(SolicitudCredito solicitud) {
        com.creditsystem.solicitudcredito.infrastructure.repositories.SolicitudCreditoEntity entity = 
            new com.creditsystem.solicitudcredito.infrastructure.repositories.SolicitudCreditoEntity();
        entity.setId(solicitud.id());
        entity.setTipoCredito(solicitud.tipoCredito());
        entity.setMontoSolicitado(solicitud.montoSolicitado());
        entity.setPlazoMeses(solicitud.plazoMeses());
        entity.setNombreSolicitante(solicitud.nombreSolicitante());
        entity.setCorreoSolicitante(solicitud.correoSolicitante());
        entity.setTelefono(solicitud.telefono());
        entity.setDireccion(solicitud.direccion());
        entity.setEdad(solicitud.edad());
        entity.setScoreBuro(solicitud.scoreBuro());
        entity.setTieneHistorialCrediticio(solicitud.tieneHistorialCrediticio());
        entity.setIngresoMensual(solicitud.ingresoMensual());
        entity.setOtrosIngresos(solicitud.otrosIngresos());
        entity.setEstado(solicitud.estado().name());
        entity.setFechaSolicitud(solicitud.fechaSolicitud());
        entity.setFechaActualizacion(solicitud.fechaActualizacion());
        entity.setIdempotenciaKey(solicitud.idempotenciaKey());
        entity.setMotivoRechazo(solicitud.motivoRechazo());
        return entity;
    }

    private SolicitudCredito toRecord(com.creditsystem.solicitudcredito.infrastructure.repositories.SolicitudCreditoEntity entity) {
        return new SolicitudCredito(
            entity.getId(),
            entity.getTipoCredito(),
            entity.getMontoSolicitado(),
            entity.getPlazoMeses(),
            entity.getNombreSolicitante(),
            entity.getCorreoSolicitante(),
            entity.getTelefono(),
            entity.getDireccion(),
            entity.getEdad(),
            entity.getScoreBuro(),
            entity.getTieneHistorialCrediticio(),
            entity.getIngresoMensual(),
            entity.getOtrosIngresos(),
            SolicitudCredito.EstadoSolicitud.valueOf(entity.getEstado()),
            entity.getFechaSolicitud(),
            entity.getFechaActualizacion(),
            entity.getIdempotenciaKey(),
            entity.getMotivoRechazo()
        );
    }
}

// === ARCHIVO: src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/ValidacionCreditoServiceAdapter.java ===
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

// === ARCHIVO: src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java ===
package com.creditsystem.solicitudcredito.infrastructure.adapters;

import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito;
import com.creditsystem.solicitudcredito.domain.ports.NotificacionService;
import com.creditsystem.solicitudcredito.infrastructure.events.SolicitudCreditoEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
public class NotificacionServiceAdapter implements NotificacionService {

    private static final Logger log = LoggerFactory.getLogger(NotificacionServiceAdapter.class);

    private final KafkaTemplate<String, SolicitudCreditoEvent> kafkaTemplate;
    private final String notificationTopic;
    private final int deliveryTimeoutSeconds;

    public NotificacionServiceAdapter(
            KafkaTemplate<String, SolicitudCreditoEvent> kafkaTemplate,
            @Value("${spring.kafka.topic.notifications:solicitud-credito-notificaciones}") String notificationTopic,
            @Value("${spring.kafka.producer.delivery-timeout-seconds:30}") int deliveryTimeoutSeconds) {
        this.kafkaTemplate = kafkaTemplate;
        this.notificationTopic = notificationTopic;
        this.deliveryTimeoutSeconds = deliveryTimeoutSeconds;
    }

    @Override
    public Mono<Boolean> enviarNotificacion(SolicitudCredito solicitud) {
        log.info("Enviando notificación para solicitud: id={}, correo={}, estado={}",
                solicitud.id(), solicitud.correoSolicitante(), solicitud.estado());

        SolicitudCreditoEvent event = buildEvent(solicitud);
        String key = solicitud.id().toString();

        return Mono.fromFuture(() -> {
            CompletableFuture<SendResult<String, SolicitudCreditoEvent>> future =
                    kafkaTemplate.send(notificationTopic, key, event);

            return future.whenComplete((result, ex) -> {
                if (ex != null) {
                    log.error("Error al enviar notificación para solicitud {}: {}",
                            solicitud.id(), ex.getMessage(), ex);
                } else {
                    log.info("Notificación enviada exitosamente para solicitud {}. Partición={}, Offset={}",
                            solicitud.id(),
                            result.getRecordMetadata().partition(),
                            result.getRecordMetadata().offset());
                }
            });
        }).map(result -> true)
        .onErrorResume(error -> {
            log.error("Error al enviar notificación para solicitud {}: {}",
                    solicitud.id(), error.getMessage());
            return Mono.just(false);
        });
    }

    @Override
    public Mono<Boolean> enviarNotificacionAsync(SolicitudCredito solicitud) {
        log.debug("Enviando notificación asíncrona para solicitud: id={}", solicitud.id());

        SolicitudCreditoEvent event = buildEvent(solicitud);
        String key = solicitud.id().toString();

        kafkaTemplate.send(notificationTopic, key, event)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Error en notificación asíncrona para solicitud {}: {}",
                                solicitud.id(), ex.getMessage(), ex);
                    } else {
                        log.debug("Notificación asíncrona enviada para solicitud {}. Partición={}, Offset={}",
                                solicitud.id(),
                                result.getRecordMetadata().partition(),
                                result.getRecordMetadata().offset());
                    }
                });

        return Mono.just(true);
    }

    private SolicitudCreditoEvent buildEvent(SolicitudCredito solicitud) {
        return new SolicitudCreditoEvent(
                UUID.randomUUID(),
                solicitud.id(),
                solicitud.correoSolicitante(),
                solicitud.estado().name(),
                solicitud.tipoCredito(),
                solicitud.monto(),
                solicitud.plazoMeses(),
                solicitud.motivoRechazo(),
                Instant.now()
        );
    }
}

// === ARCHIVO: src/main/java/com/creditsystem/solicitudcredito/infrastructure/config/KafkaConfig.java ===
package com.creditsystem.solicitudcredito.infrastructure.config;

import com.creditsystem.solicitudcredito.infrastructure.events.SolicitudCreditoEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.backoff.FixedBackOff;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
@EnableKafka
public class KafkaConfig {

    private static final Logger log = LoggerFactory.getLogger(KafkaConfig.class);

    @Value("${spring.kafka.bootstrap-servers:localhost:9092}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id:solicitud-credito-group}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset:earliest}")
    private String autoOffsetReset;

    @Value("${spring.kafka.producer.key-serializer:org.apache.kafka.common.serialization.StringSerializer}")
    private String keySerializer;

    @Value("${spring.kafka.producer.value-serializer:org.springframework.kafka.support.serializer.JsonSerializer}")
    private String valueSerializer;

    @Value("${spring.kafka.producer.acks:all}")
    private String acks;

    @Value("${spring.kafka.producer.retries:3}")
    private int retries;

    @Value("${spring.kafka.producer.delivery-timeout-seconds:30}")
    private int deliveryTimeoutSeconds;

    @Value("${spring.kafka.topic.notifications:solicitud-credito-notificaciones}")
    private String notificationsTopic;

    @Value("${spring.kafka.topic.notifications-partitions:3}")
    private int notificationsPartitions;

    @Value("${spring.kafka.topic.notifications-replication-factor:1}")
    private int notificationsReplicationFactor;

    @Bean
    public NewTopic notificationsTopic() {
        log.info("Creando tema de Kafka: {} con {} particiones y factor de replicación {}",
                notificationsTopic, notificationsPartitions, notificationsReplicationFactor);
        return TopicBuilder.name(notificationsTopic)
                .partitions(notificationsPartitions)
                .replicas(notificationsReplicationFactor)
                .build();
    }

    @Bean
    public ProducerFactory<String, SolicitudCreditoEvent> producerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configProps.put(ProducerConfig.ACKS_CONFIG, acks);
        configProps.put(ProducerConfig.RETRIES_CONFIG, retries);
        configProps.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, deliveryTimeoutSeconds * 1000);
        configProps.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        configProps.put(ProducerConfig.TRANSACTION_TIMEOUT_MS_CONFIG, deliveryTimeoutSeconds * 1000);

        log.debug("Configuración del ProducerFactory: bootstrapServers={}, acks={}, retries={}",
                bootstrapServers, acks, retries);

        DefaultKafkaProducerFactory<String, SolicitudCreditoEvent> factory =
                new DefaultKafkaProducerFactory<>(configProps);
        factory.setTransactionIdPrefix("solicitud-credito-tx-");
        return factory;
    }

    @Bean
    public KafkaTemplate<String, SolicitudCreditoEvent> kafkaTemplate() {
        KafkaTemplate<String, SolicitudCreditoEvent> template = new KafkaTemplate<>(producerFactory());
        template.setTransactionIdPrefix("solicitud-credito-tx-");
        log.info("KafkaTemplate creado exitosamente");
        return template;
    }

    @Bean
    public ConsumerFactory<String, SolicitudCreditoEvent> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 10);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "com.creditsystem.solicitudcredito.infrastructure.events");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, SolicitudCreditoEvent.class.getName());

        log.debug("Configuración del ConsumerFactory: bootstrapServers={}, groupId={}, autoOffsetReset={}",
                bootstrapServers, groupId, autoOffsetReset);

        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SolicitudCreditoEvent> kafkaListenerContainerFactory(
            ConsumerFactory<String, SolicitudCreditoEvent> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, SolicitudCreditoEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        factory.setConcurrency(3);
        factory.getContainerProperties().setAckMode(org.springframework.kafka.listener.ContainerProperties.AckMode.MANUAL_IMMEDIATE);

        DefaultErrorHandler errorHandler = new DefaultErrorHandler(
                new DeadLetterPublishingRecoverer(kafkaTemplate()),
                new FixedBackOff(1000L, 3)
        );
        factory.setCommonErrorHandler(errorHandler);

        log.info("ConcurrentKafkaListenerContainerFactory configurado con concurrencia 3");
        return factory;
    }

    @Bean
    public KafkaTransactionManager<String, SolicitudCreditoEvent> transactionManager(
            ProducerFactory<String, SolicitudCreditoEvent> producerFactory) {
        KafkaTransactionManager<String, SolicitudCreditoEvent> manager =
                new KafkaTransactionManager<>(producerFactory);
        log.info("KafkaTransactionManager configurado");
        return manager;
    }
}

// === ARCHIVO: src/main/java/com/creditsystem/solicitudcredito/infrastructure/config/Resilience4jConfig.java ===
package com.creditsystem.solicitudcredito.infrastructure.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.ratelimiter.RateLimiterConfig;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4jConfig {

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .build();
        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .build();
        return RetryRegistry.of(config);
    }

    @Bean
    public RateLimiterRegistry rateLimiterRegistry() {
        RateLimiterConfig config = RateLimiterConfig.custom()
                .limitRefreshPeriod(Duration.ofSeconds(1))
                .limitForPeriod(100)
                .timeoutDuration(Duration.ofMillis(100))
                .build();
        return RateLimiterRegistry.of(config);
    }
}

// === ARCHIVO: src/main/java/com/creditsystem/solicitudcredito/infrastructure/web/SolicitudCreditoController.java ===
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

// === ARCHIVO: src/main/java/com/creditsystem/solicitudcredito/infrastructure/events/SolicitudCreditoEvent.java ===
package com.creditsystem.solicitudcredito.infrastructure.events;


import com.creditsystem.solicitudcredito.domain.model.EstadoSolicitud;
import com.creditsystem.solicitudcredito.domain.model.SolicitudCredito;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record SolicitudCreditoEvent(
        UUID eventId,
        UUID solicitudId,
        String tipoEvento,
        SolicitudCredito.EstadoSolicitud estadoAnterior,
        SolicitudCredito.EstadoSolicitud estadoNuevo,
        String correoSolicitante,
        BigDecimal montoSolicitado,
        Integer plazoMeses,
        String tipoCredito,
        Instant timestamp,
        String motivoRechazo,
        String correlationId
) {
    public static SolicitudCreditoEvent crear(
            UUID solicitudId,
            String tipoEvento,
            SolicitudCredito.EstadoSolicitud estadoAnterior,
            SolicitudCredito.EstadoSolicitud estadoNuevo,
            String correoSolicitante,
            BigDecimal montoSolicitado,
            Integer plazoMeses,
            String tipoCredito,
            String motivoRechazo,
            String correlationId) {
        return new SolicitudCreditoEvent(
                UUID.randomUUID(),
                solicitudId,
                tipoEvento,
                estadoAnterior,
                estadoNuevo,
                correoSolicitante,
                montoSolicitado,
                plazoMeses,
                tipoCredito,
                Instant.now(),
                motivoRechazo,
                correlationId
        );
    }

    public static final String EVENTO_SOLICITUD_CREADA = "SOLICITUD_CREADA";
    public static final String EVENTO_SOLICITUD_APROBADA = "SOLICITUD_APROBADA";
    public static final String EVENTO_SOLICITUD_RECHAZADA = "SOLICITUD_RECHAZADA";
    public static final String EVENTO_SOLICITUD_EN_VALIDACION = "SOLICITUD_EN_VALIDACION";
    public static final String EVENTO_ERROR_VALIDACION = "ERROR_VALIDACION";
}

// === ARCHIVO: src/main/java/com/creditsystem/solicitudcredito/infrastructure/events/SolicitudCreditoEventListener.java ===
package com.creditsystem.solicitudcredito.infrastructure.events;

import com.creditsystem.solicitudcredito.domain.ports.NotificacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;

@Component
public class SolicitudCreditoEventListener {

    private static final Logger log = LoggerFactory.getLogger(SolicitudCreditoEventListener.class);
    private static final String TOPICO_SOLICITUDES = "solicitudes-credito-events";

    private final NotificacionService notificacionService;

    public SolicitudCreditoEventListener(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @KafkaListener(topics = TOPICO_SOLICITUDES, groupId = "${spring.kafka.consumer.group-id:credito-notificaciones}")
    public void consumirEventoSolicitud(Map<String, Object> evento) {
        String eventType = (String) evento.get("eventType");
        UUID solicitudId = UUID.fromString((String) evento.get("solicitudId"));
        String correoCliente = (String) evento.get("correoCliente");
        String mensaje = (String) evento.get("mensaje");

        log.info("Evento recibido: tipo={}, solicitudId={}, correo={}", eventType, solicitudId, correoCliente);

        switch (eventType) {
            case "SOLICITUD_RECIBIDA" -> procesarSolicitudRecibida(solicitudId, correoCliente, mensaje);
            case "SOLICITUD_APROBADA" -> procesarSolicitudAprobada(solicitudId, correoCliente, mensaje);
            case "SOLICITUD_RECHAZADA" -> procesarSolicitudRechazada(solicitudId, correoCliente, mensaje);
            default -> log.warn("Tipo de evento desconocido: {}", eventType);
        }
    }

    private void procesarSolicitudRecibida(UUID solicitudId, String correoCliente, String mensajeBase) {
        String mensaje = mensajeBase != null ? mensajeBase 
            : "Su solicitud de crédito ha sido recibida y está siendo procesada.";
        
        notificacionService.enviarNotificacion(correoCliente, "Solicitud Recepcional", mensaje)
            .doOnSuccess(v -> log.info("Notificación de recepción enviada para solicitud: {}", solicitudId))
            .doOnError(e -> log.error("Error al enviar notificación de recepción para solicitud: {}", solicitudId, e))
            .subscribe();
    }

    private void procesarSolicitudAprobada(UUID solicitudId, String correoCliente, String mensajeBase) {
        String mensaje = mensajeBase != null ? mensajeBase 
            : "¡Felicitaciones! Su solicitud de crédito ha sido aprobada.";
        
        notificacionService.enviarNotificacion(correoCliente, "Aprobación de Crédito", mensaje)
            .doOnSuccess(v -> log.info("Notificación de aprobación enviada para solicitud: {}", solicitudId))
            .doOnError(e -> log.error("Error al enviar notificación de aprobación para solicitud: {}", solicitudId, e))
            .subscribe();
    }

    private void procesarSolicitudRechazada(UUID solicitudId, String correoCliente, String mensajeBase) {
        String mensaje = mensajeBase != null ? mensajeBase 
            : "Lamentamos informarle que su solicitud de crédito ha sido rechazada.";
        
        String motivo = (String) evento.get("motivoRechazo");
        if (motivo != null && !motivo.isBlank()) {
            mensaje += "\nMotivo: " + motivo;
        }
        
        notificacionService.enviarNotificacion(correoCliente, "Rechazo de Crédito", mensaje)
            .doOnSuccess(v -> log.info("Notificación de rechazo enviada para solicitud: {}", solicitudId))
            .doOnError(e -> log.error("Error al enviar notificación de rechazo para solicitud: {}", solicitudId, e))
            .subscribe();
    }

    public Mono<Void> procesarEventoSincrono(Map<String, Object> evento) {
        String eventType = (String) evento.get("eventType");
        UUID solicitudId = UUID.fromString((String) evento.get("solicitudId"));
        String correoCliente = (String) evento.get("correoCliente");
        String mensaje = (String) evento.get("mensaje");

        return switch (eventType) {
            case "SOLICITUD_RECIBIDA" -> {
                String msg = mensaje != null ? mensaje 
                    : "Su solicitud de crédito ha sido recibida y está siendo procesada.";
                yield notificacionService.enviarNotificacion(correoCliente, "Solicitud Recepcional", msg);
            }
            case "SOLICITUD_APROBADA" -> {
                String msg = mensaje != null ? mensaje 
                    : "¡Felicitaciones! Su solicitud de crédito ha sido aprobada.";
                yield notificacionService.enviarNotificacion(correoCliente, "Aprobación de Crédito", msg);
            }
            case "SOLICITUD_RECHAZADA" -> {
                String msg = mensaje != null ? mensaje 
                    : "Lamentamos informarle que su solicitud de crédito ha sido rechazada.";
                yield notificacionService.enviarNotificacion(correoCliente, "Rechazo de Crédito", msg);
            }
            default -> Mono.empty();
        };
    }
}

// === ARCHIVO: src/test/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCaseTest.java ===
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

// === ARCHIVO: src/test/java/com/creditsystem/solicitudcredito/infrastructure/web/SolicitudCreditoControllerTest.java ===
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
```
