---
name: jpa-persistence
description: Reglas de persistencia JPA/Hibernate con H2 en desarrollo y MySQL en producción.
---

# SKILL: jpa-persistence

Persistencia con JPA/Hibernate.

## reglas
- Entidades JPA solo en `Modelo/`, con prefijo M; no exponerlas en la API
- Repositorios como interfaces en `Repositorio/` que extienden JpaRepository
- H2 para desarrollo y MySQL para producción
- Credenciales y conexiones fuera del repositorio; nada hardcodeado
- Cambios de esquema o formato de datos requieren spec con migración y compatibilidad

## estado
Modelo y Repositorio implementados; conector MySQL ya presente en pom.xml.
