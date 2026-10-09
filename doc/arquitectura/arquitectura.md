# Arquitectura conceptual

## Visión general

```text
                 ┌───────────────────┐
                 │      USUARIO      │
                 └─────────┬─────────┘
                           │
             ┌─────────────┼─────────────┐
             │             │             │
          Grupos     Organizaciones   Fuentes
             │             │             │
             └─────────────┼─────────────┘
                           │
                    Permisos / Contexto
                           │
                    ┌──────▼──────┐
                    │    n8n      │
                    └──────┬──────┘
                           │
                    Reglas + IA
                           │
                    Clasificación
                           │
                    ┌──────▼──────┐
                    │  FinApp API │
                    └──────┬──────┘
                           │
                    Base de datos
                           │
                    ┌──────▼──────┐
                    │  Dashboard  │
                    └─────────────┘
```

## Backend

- Java + Spring Boot + base de datos (H2 en desarrollo, MySQL en producción).
- Servidor propio o AWS.
- Responsable progresivamente de: usuarios, autenticación, grupos, organizaciones,
  roles, permisos, relaciones, registros, información financiera, integraciones,
  persistencia y automatizaciones.

## Frontend

- React 18 + Vite 5 + Tailwind CSS 4 + React Router 6.
- Tests con Vitest y Testing Library.
- Estado actual: demo con cifras de ejemplo, sin persistencia real.

## Automatización

n8n se encarga de filtrado de correos, clasificación, detección de contexto,
identificación de remitentes, triggers, actualización de información y
comunicación entre servicios. La app comprueba posteriormente que la automatización
produjo el resultado esperado.

## Flujo de procesamiento

```text
Fuente → n8n → Validaciones → IA → Clasificación → FinApp → Base de datos → Dashboard
```

## Justificación del diseño

El porqué de esta arquitectura (capas, DTOs, proyecciones, modelo de usuario/grupos y
permisos) y su relación con `doc/planificacion/entidades/` se argumenta en
[`justificacion-arquitectura.md`](justificacion-arquitectura.md).
