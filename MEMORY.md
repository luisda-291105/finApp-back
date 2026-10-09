# MEMORY.md — finApp-back

## Estado al 2026-10-09
- Rama local `develop`: 6 commits adelante y 4 atrás de `origin/develop` (último estado
  conocido; no se hizo fetch en esta sesión). No integrar ni publicar sin revisar el árbol.
- Hay cambios locales anteriores sin commit en DTO, servicios, MGrupo, pruebas, agentes y
  documentación. No atribuirlos todos a una sola spec ni descartarlos.
- Documentación reorganizada en subcarpetas dentro de `doc/`; planificación de entidades
  en `doc/planificacion/entidades/`; specs SDD permanecen en `specs/`.
- Spec 003: usuario confirmó siete servicios (5 actuales + SGrupo/SBolsillo), CRUD y todas
  las consultas actuales, y `IllegalStateException` con mensaje y causa dentro de
  try-catch/throw. Añadir headers solo donde falten. Spec revisada y aprobada por reviewer;
  aprobada por el usuario. `plan.md` y `tasks.md` ya están redactados y revisados; esperan
  aprobación formal del usuario. No implementar aún.
- JDK disponible en `/home/daniel/.jdks/openjdk-27`; establecer `JAVA_HOME` para Maven.
- Última validación registrada (2026-10-08): test focalizado, `./mvnw test` (5 pruebas) y
  `./mvnw package` pasaron con ese JDK.

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
- Las specs 001 y 002 aún dicen `Estado: borrador`; reconciliar su estado formal luego.
- Capas: Modelo → Repositorio → Servicio → Controlador; DTOs solo en Controlador.
- Antes de cerrar cambios de código: prueba focalizada, suite completa y package. Commit
  solo con validaciones aprobadas; push requiere package aprobado.
