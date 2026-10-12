# AGENTS.md — finApp-back

Guía operativa del repositorio. Los principios del proyecto están en
[`doc/constitution.md`](doc/constitution.md); las reglas detalladas de arquitectura están
en [`.agents/rules/rules.md`](.agents/rules/rules.md). El formato de trabajo y respuesta
está en [`.agents/skills/response-rules/SKILL.md`](.agents/skills/response-rules/SKILL.md).

## Stack y comandos

- Java 25 + Spring Boot 4.1.1 + Maven.
- H2 (dev) + MySQL (prod) — JPA/Hibernate.
- Arquitectura en capas: Modelo → Repositorio → Servicio → Controlador → DTO.
- Tests: spring-boot-starter-data-jpa-test + webmvc-test.
- `./mvnw spring-boot:run` — levantar la app.
- `./mvnw test` — ejecutar tests.
- `./mvnw test -Dtest=NombreClase` — un solo test.
- `./mvnw package` — build de producción.

## Estructura actual

- `Modelo/` — entidades JPA (MUsuario, MGasto, MIngreso, etc.).
- `Repositorio/` — interfaces JPA Repository.
- `Servicio/` — lógica de negocio (SUsuario, SGasto, etc.).
- `Controlador/` — endpoints REST (a implementar).
- `Dto/` — objetos de transferencia (XxxDTO, UsuarioXxxDTO).

## Reglas de trabajo

- Antes de cambiar código, lee esta guía, `doc/constitution.md`, las reglas pertinentes
  de `.agents/rules/rules.md` y `MEMORY.md`. Si existe una spec activa en `specs/`,
  léela también; no presupongas que esa carpeta existe para cada cambio.
- Implementa solo el alcance solicitado. Si falta una decisión que cambie el
  comportamiento esperado, pregunta antes de asumir.
- Nombres de clases con prefijo: M (Modelo), S (Servicio), C (Controlador), sufijo DTO.
  Todo en español (nombres, commits).
- Paquete base: `com.luisda.personalapp.springboot.personal_app`.
- Las clases nuevas deben seguir `.agents/skills/file-header-template/SKILL.md`. No se requiere ese
  header en documentos Markdown ni en archivos de pruebas.
- Dependencias solo hacia abajo: Controlador → Servicio → Repositorio → Modelo.
- DTOs solo en la capa de Controlador (no propagar a Servicio/Repositorio).
- Entidades JPA solo en Modelo (no exponer en API).

## Pruebas y cierre

1. **Plan** — lista breve de tareas antes de escribir código.
2. **Terminal** — ejecuta las pruebas relevantes (`./mvnw test -Dtest=NombreClase`),
   `./mvnw test` y, para cerrar, `./mvnw package`. Si Java no está disponible en el
   entorno, reporta la validación como no realizada.
3. **Commit** — solo si los tests pasan; limita el staging a los archivos del cambio y
   usa un mensaje corto, imperativo, en español.
4. **Push** — solo si `./mvnw package` pasa.

## Memoria

- Antes de terminar la sesión o agotar tokens: actualizar `MEMORY.md` con tareas a
  medias, procesos faltantes y contexto relevante.
- Mantener `MEMORY.md` ligero: eliminar lo irrelevante o sin valor; máximo ~50 líneas.
