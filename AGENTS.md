# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Diseño y validación de flujo E2E para solicitud de crédito**.

| | |
|---|---|
| Tema | E2E inval |
| Nivel | senior-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean con CQRS y manejo reactivo de eventos |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-webflux 3.5.6
- org.springframework.boot:spring-boot-starter-data-r2dbc 3.5.6
- org.springframework.boot:spring-boot-starter-validation n/a
- io.r2dbc:r2dbc-postgresql 1.0.5.RELEASE
- org.springframework.kafka:spring-kafka 3.5.6
- io.github.resilience4j:resilience4j-spring-boot3 2.2.0
- org.springdoc:springdoc-openapi-starter-webflux-ui 2.9.1
- org.springframework.boot:spring-boot-starter-test n/a
- org.testcontainers:postgresql 1.20.1
- org.junit.jupiter:junit-jupiter-api n/a

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Definición de requisitos y validación de entrada**: Documento de requisitos funcionales y no funcionales para el flujo de solicitud de crédito.
- **Fase 2 — Diseño del flujo E2E**: Diagrama del flujo E2E y documento de decisiones de diseño.
- **Fase 3 — Validación y optimización del flujo**: Reporte de validación y optimización del flujo E2E.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/config/Resilience4jConfig.java` — El topic pide resiliencia: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (136)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/creditsystem/solicitudcredito/Application.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.models.OpenAPI pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/creditsystem/solicitudcredito/domain/ports/SolicitudCreditoRepository.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/creditsystem/solicitudcredito/domain/ports/ValidacionCreditoService.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/creditsystem/solicitudcredito/domain/ports/NotificacionService.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/ValidacionCreditoServiceAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/ValidacionCreditoServiceAdapter.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/config/KafkaConfig.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/web/SolicitudCreditoController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/web/SolicitudCreditoController.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Flux pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/events/SolicitudCreditoEventListener.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/events/SolicitudCreditoEventListener.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCaseTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/creditsystem/solicitudcredito/infrastructure/web/SolicitudCreditoControllerTest.java` — `reactor.core.publisher`
      El import reactor.core.publisher.Mono pertenece a reactor.core.publisher, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setId`
      Se invoca `setId` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.id`
      Se invoca `id` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setTipoCredito`
      Se invoca `setTipoCredito` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.tipoCredito`
      Se invoca `tipoCredito` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setMonto`
      Se invoca `setMonto` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.monto`
      Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setPlazoMeses`
      Se invoca `setPlazoMeses` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.plazoMeses`
      Se invoca `plazoMeses` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setCorreoSolicitante`
      Se invoca `setCorreoSolicitante` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.correoSolicitante`
      Se invoca `correoSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setNombreSolicitante`
      Se invoca `setNombreSolicitante` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.nombreSolicitante`
      Se invoca `nombreSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setIdentificacion`
      Se invoca `setIdentificacion` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.identificacion`
      Se invoca `identificacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setEstado`
      Se invoca `setEstado` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.estado`
      Se invoca `estado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setMotivoRechazo`
      Se invoca `setMotivoRechazo` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.motivoRechazo`
      Se invoca `motivoRechazo` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setIdempotenciaKey`
      Se invoca `setIdempotenciaKey` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.idempotenciaKey`
      Se invoca `idempotenciaKey` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setFechaCreacion`
      Se invoca `setFechaCreacion` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.fechaCreacion`
      Se invoca `fechaCreacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setFechaActualizacion`
      Se invoca `setFechaActualizacion` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.fechaActualizacion`
      Se invoca `fechaActualizacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCreditoEntity.setVersion`
      Se invoca `setVersion` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java` — `SolicitudCredito.version`
      Se invoca `version` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `CrearSolicitudCommand.correo`
      Se invoca `correo` sobre `CrearSolicitudCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.id`
      Se invoca `id` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.estado`
      Se invoca `estado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCreditoRepository.findByIdempotenciaKey`
      Se invoca `findByIdempotenciaKey` sobre `SolicitudCreditoRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `CrearSolicitudCommand.id`
      Se invoca `id` sobre `CrearSolicitudCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `CrearSolicitudCommand.tipoCredito`
      Se invoca `tipoCredito` sobre `CrearSolicitudCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `CrearSolicitudCommand.monto`
      Se invoca `monto` sobre `CrearSolicitudCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `CrearSolicitudCommand.plazoMeses`
      Se invoca `plazoMeses` sobre `CrearSolicitudCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `CrearSolicitudCommand.nombre`
      Se invoca `nombre` sobre `CrearSolicitudCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `CrearSolicitudCommand.identificacion`
      Se invoca `identificacion` sobre `CrearSolicitudCommand`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.correoSolicitante`
      Se invoca `correoSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java` — `SolicitudCredito.monto`
      Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.id`
      Se invoca `id` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoR2dbcRepository.save`
      Se invoca `save` sobre `SolicitudCreditoR2dbcRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getId`
      Se invoca `getId` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoR2dbcRepository.findById`
      Se invoca `findById` sobre `SolicitudCreditoR2dbcRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `EstadoSolicitud.name`
      Se invoca `name` sobre `EstadoSolicitud`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setId`
      Se invoca `setId` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setTipoCredito`
      Se invoca `setTipoCredito` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.tipoCredito`
      Se invoca `tipoCredito` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setMontoSolicitado`
      Se invoca `setMontoSolicitado` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.montoSolicitado`
      Se invoca `montoSolicitado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setPlazoMeses`
      Se invoca `setPlazoMeses` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.plazoMeses`
      Se invoca `plazoMeses` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setNombreSolicitante`
      Se invoca `setNombreSolicitante` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.nombreSolicitante`
      Se invoca `nombreSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setCorreoSolicitante`
      Se invoca `setCorreoSolicitante` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.correoSolicitante`
      Se invoca `correoSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setTelefono`
      Se invoca `setTelefono` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.telefono`
      Se invoca `telefono` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setDireccion`
      Se invoca `setDireccion` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.direccion`
      Se invoca `direccion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setEdad`
      Se invoca `setEdad` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.edad`
      Se invoca `edad` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setScoreBuro`
      Se invoca `setScoreBuro` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.scoreBuro`
      Se invoca `scoreBuro` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setTieneHistorialCrediticio`
      Se invoca `setTieneHistorialCrediticio` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.tieneHistorialCrediticio`
      Se invoca `tieneHistorialCrediticio` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setIngresoMensual`
      Se invoca `setIngresoMensual` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.ingresoMensual`
      Se invoca `ingresoMensual` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setOtrosIngresos`
      Se invoca `setOtrosIngresos` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.otrosIngresos`
      Se invoca `otrosIngresos` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setEstado`
      Se invoca `setEstado` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.estado`
      Se invoca `estado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setFechaSolicitud`
      Se invoca `setFechaSolicitud` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.fechaSolicitud`
      Se invoca `fechaSolicitud` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setFechaActualizacion`
      Se invoca `setFechaActualizacion` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.fechaActualizacion`
      Se invoca `fechaActualizacion` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setIdempotenciaKey`
      Se invoca `setIdempotenciaKey` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.idempotenciaKey`
      Se invoca `idempotenciaKey` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.setMotivoRechazo`
      Se invoca `setMotivoRechazo` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCredito.motivoRechazo`
      Se invoca `motivoRechazo` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getTipoCredito`
      Se invoca `getTipoCredito` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getMontoSolicitado`
      Se invoca `getMontoSolicitado` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getPlazoMeses`
      Se invoca `getPlazoMeses` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getNombreSolicitante`
      Se invoca `getNombreSolicitante` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getCorreoSolicitante`
      Se invoca `getCorreoSolicitante` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getTelefono`
      Se invoca `getTelefono` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getDireccion`
      Se invoca `getDireccion` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getEdad`
      Se invoca `getEdad` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getScoreBuro`
      Se invoca `getScoreBuro` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getTieneHistorialCrediticio`
      Se invoca `getTieneHistorialCrediticio` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getIngresoMensual`
      Se invoca `getIngresoMensual` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getOtrosIngresos`
      Se invoca `getOtrosIngresos` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getEstado`
      Se invoca `getEstado` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getFechaSolicitud`
      Se invoca `getFechaSolicitud` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getFechaActualizacion`
      Se invoca `getFechaActualizacion` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getIdempotenciaKey`
      Se invoca `getIdempotenciaKey` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java` — `SolicitudCreditoEntity.getMotivoRechazo`
      Se invoca `getMotivoRechazo` sobre `SolicitudCreditoEntity`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/ValidacionCreditoServiceAdapter.java` — `SolicitudCredito.id`
      Se invoca `id` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/ValidacionCreditoServiceAdapter.java` — `SolicitudCredito.correoSolicitante`
      Se invoca `correoSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/ValidacionCreditoServiceAdapter.java` — `SolicitudCredito.monto`
      Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/ValidacionCreditoServiceAdapter.java` — `SolicitudCredito.plazoMeses`
      Se invoca `plazoMeses` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/ValidacionCreditoServiceAdapter.java` — `SolicitudCredito.tipoCredito`
      Se invoca `tipoCredito` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/ValidacionCreditoServiceAdapter.java` — `SolicitudCredito.ingresoMensual`
      Se invoca `ingresoMensual` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java` — `SolicitudCredito.id`
      Se invoca `id` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java` — `SolicitudCredito.correoSolicitante`
      Se invoca `correoSolicitante` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java` — `SolicitudCredito.estado`
      Se invoca `estado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java` — `SolicitudCredito.tipoCredito`
      Se invoca `tipoCredito` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java` — `SolicitudCredito.monto`
      Se invoca `monto` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java` — `SolicitudCredito.plazoMeses`
      Se invoca `plazoMeses` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java` — `SolicitudCredito.motivoRechazo`
      Se invoca `motivoRechazo` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/web/SolicitudCreditoController.java` — `ProcesarSolicitudCreditoUseCase.ejecutar`
      Se invoca `ejecutar` sobre `ProcesarSolicitudCreditoUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/web/SolicitudCreditoController.java` — `ObtenerEstadoSolicitudQuery.buscarPorCorreo`
      Se invoca `buscarPorCorreo` sobre `ObtenerEstadoSolicitudQuery`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/web/SolicitudCreditoController.java` — `ObtenerEstadoSolicitudQuery.buscarPorEstado`
      Se invoca `buscarPorEstado` sobre `ObtenerEstadoSolicitudQuery`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/creditsystem/solicitudcredito/infrastructure/events/SolicitudCreditoEventListener.java` — `NotificacionService.enviarNotificacion`
      Se invoca `enviarNotificacion` sobre `NotificacionService`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCaseTest.java` — `ProcesarSolicitudCreditoUseCase.ejecutar`
      Se invoca `ejecutar` sobre `ProcesarSolicitudCreditoUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCaseTest.java` — `SolicitudCredito.estado`
      Se invoca `estado` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCaseTest.java` — `SolicitudCredito.motivoRechazo`
      Se invoca `motivoRechazo` sobre `SolicitudCredito`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `pom.xml` — `io.r2dbc:r2dbc-postgresql@1.0.5.RELEASE`
      io.r2dbc:r2dbc-postgresql declara la version 1.0.5.RELEASE, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

### Presentes (21)

- `pom.xml`
- `src/main/java/com/creditsystem/solicitudcredito/Application.java`
- `src/main/resources/application.yml`
- `src/main/java/com/creditsystem/solicitudcredito/domain/model/SolicitudCredito.java`
- `src/main/java/com/creditsystem/solicitudcredito/domain/ports/SolicitudCreditoRepository.java`
- `src/main/java/com/creditsystem/solicitudcredito/domain/ports/ValidacionCreditoService.java`
- `src/main/java/com/creditsystem/solicitudcredito/domain/ports/NotificacionService.java`
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories/SolicitudCreditoR2dbcRepository.java`
- `src/main/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCase.java`
- `src/main/java/com/creditsystem/solicitudcredito/application/commands/CrearSolicitudCommand.java`
- `src/main/java/com/creditsystem/solicitudcredito/application/queries/ObtenerEstadoSolicitudQuery.java`
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/SolicitudCreditoRepositoryAdapter.java`
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/ValidacionCreditoServiceAdapter.java`
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters/NotificacionServiceAdapter.java`
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/config/KafkaConfig.java`
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/config/Resilience4jConfig.java`
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/web/SolicitudCreditoController.java`
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/events/SolicitudCreditoEvent.java`
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/events/SolicitudCreditoEventListener.java`
- `src/test/java/com/creditsystem/solicitudcredito/application/usecases/ProcesarSolicitudCreditoUseCaseTest.java`
- `src/test/java/com/creditsystem/solicitudcredito/infrastructure/web/SolicitudCreditoControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/creditsystem/solicitudcredito`
- `src/main/java/com/creditsystem/solicitudcredito/domain`
- `src/main/java/com/creditsystem/solicitudcredito/domain/model`
- `src/main/java/com/creditsystem/solicitudcredito/domain/ports`
- `src/main/java/com/creditsystem/solicitudcredito/application`
- `src/main/java/com/creditsystem/solicitudcredito/application/usecases`
- `src/main/java/com/creditsystem/solicitudcredito/application/commands`
- `src/main/java/com/creditsystem/solicitudcredito/application/queries`
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure`
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/adapters`
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/config`
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/events`
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/repositories`
- `src/main/java/com/creditsystem/solicitudcredito/infrastructure/web`
- `src/main/resources`
- `src/test/java/com/creditsystem/solicitudcredito`

## Verificacion

```bash
mvn clean compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean con CQRS y manejo reactivo de eventos**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Spring, Tecnología Java, Senior L2
- Brecha que el reto ataca: x

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
