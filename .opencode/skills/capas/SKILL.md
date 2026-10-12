---
name: capas
description: Arquitectura en capas de finApp-back y dirección de dependencias permitida.
---

# SKILL: capas

Arquitectura en capas.

## reglas
- Dependencias solo hacia abajo: Controlador → Servicio → Repositorio → Modelo
- DTOs solo en Controlador; no propagar a Servicio ni Repositorio
- Entidades JPA solo en Modelo; no exponerlas en la API
- Prefijos por capa: M (Modelo), S (Servicio), C (Controlador), sufijo DTO
- Paquete base: `com.luisda.personalapp.springboot.personal_app`

## uso
Aplicar a toda clase nueva y a cualquier cambio que cruce capas.
