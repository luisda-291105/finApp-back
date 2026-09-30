# AGENTS.md

## Stack
- Java 25 + Spring Boot 4.1.1 + Maven
- H2 (dev) + MySQL (prod) — JPA/Hibernate
- Arquitectura en capas: Modelo → Repositorio → Servicio → Controlador → DTO
- Tests: spring-boot-starter-data-jpa-test + webmvc-test

## Comandos
- `./mvnw spring-boot:run` — levantar la app
- `./mvnw test` — ejecutar tests
- `./mvnw test -Dtest=NombreClase` — un solo test
- `./mvnw package` — build de producción

## Estructura
- `Modelo/` — entidades JPA (MUsuario, MGasto, MIngreso, etc.)
- `Repositorio/` — interfaces JPA Repository
- `Servicio/` — lógica de negocio (SUsuario, SGasto, etc.)
- `Controlador/` — endpoints REST (a implementar)
- `Dto/` — objetos de transferencia (XxxDTO, UsuarioXxxDTO)

## Reglas de codificación
- Nombres de clases con prefijo: M (Modelo), S (Servicio), C (Controlador), DTO
- Todo en español (nombres, commits)
- Paquete base: `com.luisda.personalapp.springboot.personal_app`

## Reglas de arquitectura
- Dependencias solo hacia abajo: Controlador → Servicio → Repositorio → Modelo
- DTOs solo en la capa de Controlador (no propagar a Servicio/Repositorio)
- Entidades JPA solo en Modelo (no exponer en API)

## Reglas de proceso
- Commits: mensaje corto, imperativo, en español
- Tests antes de commit
- Build debe pasar antes de push

### Flujo de trabajo por tareas
1. **Plan** — lista breve de tareas antes de escribir código
2. **Código** — implementar siguiendo arquitectura y convenciones
3. **Tests** — ejecutar `./mvnw test` y verificar que pasa
4. **Commit** — solo si tests pasan; mensaje corto, imperativo, en español
5. **Push** — solo si `./mvnw package` pasa

### Memoria
- **Antes de terminar la sesión o agotar tokens:** actualizar `MEMORY.md` con tareas a medias, procesos faltantes y contexto relevante
- **Mantener MEMORY.md ligero:** eliminar lo irrelevante o sin valor; máximo ~50 líneas
