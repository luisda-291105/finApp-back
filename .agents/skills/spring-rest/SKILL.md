---
name: spring-rest
description: Convenciones para endpoints REST en la capa Controlador de finApp-back (Spring Boot).
---

# SKILL: spring-rest

Endpoints REST con Spring Boot.

## reglas
- Controladores en `Controlador/` con prefijo C y anotación `@RestController`
- Entrada/salida solo con DTOs de `Dto/`; nunca exponer entidades de `Modelo/`
- Controlador delgado: valida entrada y delega la lógica en el Servicio
- Códigos de estado coherentes (200/201/400/404) y mensajes de error claros
- Rutas por recurso, en español y consistentes con el dominio

## estado
Capa Controlador pendiente de implementar.
