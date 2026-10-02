# Diseño y validación de flujo E2E para solicitud de crédito

El sistema de solicitud de crédito en línea debe manejar solicitudes de clientes, validar la información proporcionada y notificar el resultado al cliente. Los actores involucrados son el cliente, el sistema de validación de crédito y el sistema de notificación. El sistema debe asegurar que las solicitudes sean idempotentes y manejar adecuadamente los errores de validación. La tasa de solicitudes es de 1 500 por segundo en hora pico.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | E2E inval |
| **Nivel** | senior-l2 |
| **Tipo** | mixed |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Definición de requisitos y validación de entrada

**Objetivo:** Establecer los requisitos funcionales y no funcionales para el flujo de solicitud de crédito.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar y documentar los requisitos funcionales y no funcionales para el flujo de solicitud de crédito.
- Definir los criterios de aceptación para la validación de entrada de las solicitudes de crédito.

**Entregable:** Documento de requisitos funcionales y no funcionales para el flujo de solicitud de crédito.

<details>
<summary>Pistas de conocimiento</summary>

- Considerar la idempotencia en el diseño del flujo.
- Evaluar los posibles errores de validación y cómo manejarlos.

</details>

### Fase 2: Diseño del flujo E2E

**Objetivo:** Diseñar el flujo completo de solicitud de crédito desde la entrada hasta la notificación del resultado.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Diseñar el flujo E2E para la solicitud de crédito, incluyendo la validación de entrada, la interacción con el sistema de validación de crédito y la notificación al cliente.
- Identificar y documentar las decisiones de diseño tomadas y las consecuencias de estas decisiones.

**Entregable:** Diagrama del flujo E2E y documento de decisiones de diseño.

<details>
<summary>Pistas de conocimiento</summary>

- Considerar la consistencia y la disponibilidad en el diseño del flujo.
- Evaluar las opciones para la notificación del resultado al cliente.

</details>

### Fase 3: Validación y optimización del flujo

**Objetivo:** Validar el diseño del flujo y optimizar su rendimiento y robustez.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Validar el diseño del flujo E2E contra los requisitos funcionales y no funcionales.
- Identificar y documentar las áreas de optimización y las decisiones tomadas para mejorar el rendimiento y la robustez del flujo.

**Entregable:** Reporte de validación y optimización del flujo E2E.

<details>
<summary>Pistas de conocimiento</summary>

- Considerar la latencia y el throughput en la validación y optimización del flujo.
- Evaluar las opciones para mejorar la robustez del flujo ante posibles fallos.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es el flujo E2E para la solicitud de crédito y cuáles son sus componentes?
- **paraQueSirve**: ¿Para qué sirve el flujo E2E en el contexto de la solicitud de crédito?
- **erroresComunes**: ¿Cuáles son los errores comunes en el flujo E2E de solicitud de crédito y cómo se manejan?
- **queDecisionesImplica**: ¿Qué decisiones de diseño implica el flujo E2E y cuáles son sus consecuencias?

## Criterios de Evaluacion

- Definición clara de los requisitos funcionales y no funcionales para el flujo de solicitud de crédito.
- Diseño completo y documentado del flujo E2E, incluyendo la validación de entrada, la interacción con el sistema de validación de crédito y la notificación al cliente.
- Identificación y documentación de las decisiones de diseño y sus consecuencias.
- Validación del diseño del flujo contra los requisitos funcionales y no funcionales.
- Identificación y documentación de las áreas de optimización y las decisiones tomadas para mejorar el rendimiento y la robustez del flujo.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
