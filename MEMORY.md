# MEMORY.md — finApp-back

## Estado al 2026-10-09
- Rama local `develop`: 8 commits adelante y 4 atrás de `origin/develop` (último estado
  conocido; no se hizo fetch en esta sesión). No integrar ni publicar sin revisar el árbol.
- Commits recientes: `97db95a` reorganiza documentación y registra la spec 003;
  `60a91bd` cambia `MGrupo.estado` a `boolean` y adapta el acceso a `isEstado()`.
- Hay cambios locales anteriores sin commit en DTO, servicios, MGrupo, pruebas, agentes y
  documentación. No atribuirlos todos a una sola spec ni descartarlos.
- Documentación reorganizada en subcarpetas dentro de `doc/`; planificación de entidades
  en `doc/planificacion/entidades/`; specs SDD permanecen en `specs/`.
- Spec 003: alcance confirmado de siete servicios, CRUD/consultas, try-catch/throw con
  `IllegalStateException` y causa, headers solo donde falten. Tras aplicar `spring-rest`,
  la spec delimita DTO/API HTTP, rutas, seguridad de endpoints, paginación y docs API como
  futuras specs de Controlador. Usuario aprobó la spec; planner confirmó y reviewer aprobó
  el plan/tasks bajo la versión actual. Implementación espera aprobación explícita del plan.
- JDK disponible en `/home/daniel/.jdks/openjdk-27`; establecer `JAVA_HOME` para Maven.
- Última validación registrada (2026-10-08): test focalizado, `./mvnw test` (5 pruebas) y
  `./mvnw package` pasaron con ese JDK.
- Para `MGrupo`: `JAVA_HOME=/home/daniel/.jdks/openjdk-27 ./mvnw clean test` (5 pruebas)
  y `./mvnw package` pasaron el 2026-10-09 antes del commit `60a91bd`.

## Decisiones de dominio registradas
- MGrupo.idUsuario identifica propietario; MGrupoUsuario representa miembros con rol
  ADMIN/MIEMBRO y estado INVITADA/ACTIVA/SALIO/RETIRADA.
- Grupo público/privado y membresías; contacto pertenece a grupo y usuario (propuesta de
  planificación); gastos/ingresos vinculados a bolsillos en el modelo futuro.
- MUsuario.propiedad se decidió como dirección física. No hay flujo de autenticación
  implementado que garantice hash de contraseña.
- Tablas JPA usan nombre de clase con prefijo M. MFuente, MCorreo, MLlamada y
  MGrupoUsuario están planificadas, no implementadas.

## Specs y validación
- Spec 001 (paquetes/modelos) aprobada y completada en 2026-10-07; pruebas y package
  pasaron. Spec 002 (consultas de grupo/bolsillo) completada en 2026-10-08; suite de
  5 pruebas y package pasaron.
- Spec 004: implementada únicamente `MFuente` el 2026-10-12; prueba focalizada (3) y suite
  (23) pasaron, al igual que `package`, con `/home/daniel/.jdks/openjdk-27`. Enum `Tipo`
  textual y `idUsuario` escalar, sin FK; `MLlamada`/`MGrupoUsuario` siguen pospuestos.
- Las specs 001 y 002 aún dicen `Estado: borrador`; reconciliar su estado formal luego.
- Capas: Modelo → Repositorio → Servicio → Controlador; DTOs solo en Controlador.
- Antes de cerrar cambios de código: prueba focalizada, suite completa y package. Commit
  solo con validaciones aprobadas; push requiere package aprobado.
