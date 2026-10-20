# RTBC Orders E2E

Pruebas de aceptación de punta a punta de [rtbc-orders-api](https://github.com/NataliaGarciaB/rtbc-orders-api) con Serenity BDD y el patrón Screenplay.

## Cómo ejecutarlas

Requisitos: Java 17 y la API corriendo.

```bash
# 1. Levantar la API (cualquiera de las dos opciones)
docker run -d -p 8080:8080 ghcr.io/nataliagarciab/rtbc-orders-api:1.0.0
# o, desde el repo de la API:  ./mvnw spring-boot:run

# 2. Ejecutar los escenarios y generar el reporte
./mvnw verify
```

El reporte queda en `target/site/serenity/index.html`. En el CI se publica como artifact `reporte-serenity`.

La URL de la API se configura en `src/test/resources/serenity.conf` (`restapi.baseurl`). Para otro entorno, se agrega un bloque en `environments` y se ejecuta con `-Denvironment=<nombre>`.

## Escenarios

| Tag | Escenario | Resultado esperado |
|---|---|---|
| `@crear @RN-03` | Crear una orden | 201, estado `CREADA`, total calculado |
| `@consultar` | Consultar una orden existente | 200 |
| `@actualizar-estado @RN-06` | Camino feliz hasta `ENTREGADA` | 200, estado `ENTREGADA` |
| `@cancelar` | Cancelar una orden pagada | 200, estado `CANCELADA` |
| `@RN-05` | Enviar una orden sin pagar | 400, `ORDEN_NO_PAGADA` |
| `@RN-04` | Cancelar una orden entregada | 409, `TRANSICION_INVALIDA`, la orden sigue `ENTREGADA` |

Para ejecutar solo algunos: `./mvnw verify -Dcucumber.filter.tags="@RN-04"`.

## Diseño (Screenplay + SOLID)

```
src/test/java/com/rtbc/orders/e2e
├── tasks/            Acciones de negocio del actor (crear, cambiar estado, cancelar, consultar)
├── questions/        Lo que el actor observa (código HTTP, código de error, estado, total)
├── models/           Datos de la solicitud y estados de la orden
├── utils/            Rutas de la API y claves de la memoria del actor
├── stepdefinitions/  Traducción del Gherkin a tareas y preguntas
└── runners/          Suite de Cucumber sobre JUnit Platform
```

- **Responsabilidad única:** cada tarea modela una sola acción de negocio y cada pregunta una sola observación.
- **Abierto/cerrado:** `LlevarUnaOrdenAlEstado` compone tareas existentes para preparar cualquier estado; un estado nuevo es una entrada más en su mapa de caminos, sin tocar las otras tareas.
- **Inversión de dependencias:** los step definitions no conocen HTTP; dependen de tareas y preguntas. Las rutas viven en `RecursosDeLaApi` y la URL base en `serenity.conf`.
- **Memoria del actor:** el id de la orden creada se recuerda (`actor.remember`) y lo usan las tareas siguientes, sin estado compartido entre clases.
- **Montos exactos:** el total se lee como `BigDecimal` y se compara con `comparesEqualTo`, sin redondeos de punto flotante.
- **Sin falsos verdes:** Failsafe no ve los resultados de Cucumber, así que el goal `serenity:check` rompe el build si algún escenario falla.
