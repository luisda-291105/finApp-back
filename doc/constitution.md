# Constitución del proyecto — finApp-back

Principios que deben respetar las solicitudes, especificaciones, planes, cambios y
revisiones del proyecto. Para herramientas y procedimientos concretos, consulta
[`AGENTS.md`](../AGENTS.md) y [las reglas de arquitectura](../.agents/rules/rules.md).

## 1. Identidad y stack

- finApp-back es la API REST de FinApp, una aplicación de finanzas personales en
  desarrollo. Su frontend es el proyecto finApp-web (React + Vite).
- La base técnica es Java 25, Spring Boot 4.1.1 y Maven, con JPA/Hibernate sobre H2 en
  desarrollo y MySQL en producción. No la describas ni la conviertas en otra cosa.
- No añadas dependencias ni herramientas de build sin que el cambio esté justificado y
  aprobado; consulta `pom.xml` antes de proponer comandos o librerías.
- No presentes endpoints, respuestas o datos de prueba como operaciones financieras
  reales si la persistencia correspondiente no está implementada y verificada.

## 2. Requisitos y alcance

- Implementa únicamente el comportamiento solicitado y aprobado. No inventes
  funcionalidades ni amplíes el alcance durante la implementación.
- En el flujo SDD, la spec aprobada es el contrato del cambio. Si un requisito o una
  decisión necesaria no está definido, detente y pide aclaración antes de implementarlo.
- Los cambios pequeños pueden seguir una solicitud directa sin crear una spec nueva,
  siempre que el alcance y el comportamiento esperado estén claros.
- No crees archivos ni cambies formatos de datos persistidos sin que el alcance lo
  contemple.

## 3. Arquitectura y legibilidad

- Respeta la arquitectura en capas con dependencias solo hacia abajo:
  Controlador → Servicio → Repositorio → Modelo.
- Los DTOs pertenecen a la capa de Controlador: no los propagues a Servicio ni
  Repositorio. Las entidades JPA pertenecen a Modelo: no las expongas en la API.
- Prefiere clases pequeñas y una solución que el equipo pueda mantener; evita
  abstracciones o dependencias que no resuelvan una necesidad concreta.
- Mantén los nombres en español y los prefijos de capa (M, S, C, sufijo DTO), de forma
  consistente con el código existente. La documentación del proyecto también va en
  español.
- Las clases nuevas deben seguir la plantilla de `.agents/skills/file-header-template/SKILL.md`.
  Documenta los métodos públicos con Javadoc breve cuando su contrato no sea evidente.

## 4. Datos y servicios

- No afirmes que la API guarda o recupera datos financieros si esa operación no está
  implementada y verificada con pruebas.
- Si una spec incorpora persistencia, debe definir el origen, la forma y el ciclo de
  vida de los datos, y contemplar la compatibilidad o migración antes de modificar un
  formato existente.
- Trata los datos financieros y las credenciales como sensibles: no incluyas secretos
  en código, memoria del proyecto, logs o ejemplos. Las credenciales de base de datos se
  configuran fuera del repositorio.
- Si una operación falla, devuelve el error con claridad (código de estado y mensaje
  coherentes); no ocultes errores detrás de respuestas que parezcan confirmar una
  operación real.

## 5. Calidad y validación

- Añade o ajusta pruebas para el comportamiento modificado, con JUnit y los slices de
  Spring (`@DataJpaTest`, `@WebMvcTest`) según la capa afectada.
- Antes de cerrar un cambio de código, ejecuta las pruebas relevantes, `./mvnw test` y
  `./mvnw package`. No declares completo un cambio si una validación falla.
- Si Java no está disponible en el entorno, informa explícitamente de que la validación
  no pudo realizarse.
- Distingue entre validaciones ejecutadas y pendientes; nunca informes una comprobación
  no realizada como aprobada.
