# MEMORY.md — finApp-back

## Sesión 2026-09-29
- Repositorio renombrado de perosnal-app a finApp-back en GitHub
- Creados AGENTS.md y MEMORY.md
- README.MD actualizado con stack, IAs utilizadas, comandos y estructura
- Reestructuración de directorios completada

## Estado del proyecto
- Capas implementadas: Modelo, Repositorio, Servicio, DTO
- Capa pendiente: Controlador (endpoints REST)
- Sin tests todavía

## Estructura de directorios
```
/home/luisda/homelab/proyectos/
└── finApp/
    ├── finApp-web/   → frontend React (origin: finApp-web)
    └── finApp-back/  → backend Spring Boot (origin: finApp-back)
```

## Tareas a medias / pendientes
- Implementar capa Controlador con endpoints REST
- Crear tests para las capas existentes
- Configurar MySQL para producción (conector ya presente en pom.xml)

## Notas
- Proyecto académico/personal de finanzas personales
- IAs: ChatGPT, Claude, DeepSeek, Gemini, Groq (planificación); Codex, OpenCode, Copilot (codificación)
- Java no instalado en entorno actual — no se pudieron ejecutar tests
