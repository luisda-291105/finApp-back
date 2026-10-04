# MEMORY.md — finApp-back

## Sesión 2026-10-03
- Añadida configuración de agentes replicando finApp-web: `.opencode/agents/`
  (coordinator, planner, implementer, reviewer), `.agents/` (response-rules, rules,
  skills capas/spring-rest/jpa-persistence, file-header-template) y
  `doc/constitution.md`
- AGENTS.md reorganizado con referencias a constitution, rules y response-rules
- Java sigue sin estar instalado en el entorno: validaciones con `./mvnw` pendientes

## Sesión 2026-09-29
- Repositorio renombrado de perosnal-app a finApp-back en GitHub
- Remote actualizado a https://github.com/luisda-291105/finApp-back.git
- Rama actual: develop (último commit: f640dbb)
- Creados AGENTS.md y MEMORY.md
- README.MD actualizado con stack, IAs utilizadas, comandos y estructura
- Push exitoso a origin/develop

## Estado del proyecto
- Capas implementadas: Modelo, Repositorio, Servicio, DTO
- Capa pendiente: Controlador (endpoints REST)
- Sin tests todavía

## Tareas a medias / pendientes
- Implementar capa Controlador con endpoints REST
- Crear tests para las capas existentes
- Configurar MySQL para producción (conector ya presente en pom.xml)

## Notas
- Proyecto académico/personal de finanzas personales
- App frontend relacionada: finApp (React + Vite)
- IAs: ChatGPT, Claude, DeepSeek, Gemini, Groq (planificación); Codex, OpenCode, Copilot (codificación)
- Java no instalado en entorno actual — no se pudieron ejecutar tests
