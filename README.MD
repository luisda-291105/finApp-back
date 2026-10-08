# finApp-back

Backend de finanzas personales (proyecto académico/personal).

## Stack
- Java 25 + Spring Boot 4.1.1 + Maven
- H2 (dev) + MySQL (prod) — JPA/Hibernate
- Arquitectura en capas: Modelo → Repositorio → Servicio → Controlador → DTO

## IAs utilizadas

### Planificación, estudio y conceptos
| IA | Implementación |
|---|---|
| ChatGPT (OpenAI) | Chat / API |
| Claude | Chat / API |
| DeepSeek | Chat / API |
| Gemini | Chat / API |
| Groq | n8n (automatización) |

### Codificación
| IA | Implementación | Instrucciones |
|---|---|---|
| Codex (OpenAI) | Agente CLI | `AGENTS.md` |
| OpenCode | Agente CLI | `AGENTS.md` |
| GitHub Copilot | Chat + Editor | `AGENTS.md` |

## Comandos
- `./mvnw spring-boot:run` — desarrollo
- `./mvnw test` — tests
- `./mvnw package` — build

## Estructura
- `Modelo/` — entidades JPA
- `Repositorio/` — JPA Repositories
- `Servicio/` — lógica de negocio
- `Controlador/` — endpoints REST
- `Dto/` — DTOs
