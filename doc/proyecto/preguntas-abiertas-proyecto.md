# Estado y preguntas abiertas del proyecto

**Revisado:** 2026-10-09

**Propósito:** separar el estado observable del código de las decisiones de producto y
arquitectura que todavía no puedo deducir. No es una aprobación de cambios ni una
especificación de implementación.

## Lo que entiendo del estado actual

- Backend Java con Spring Boot, Maven y JPA; la arquitectura prevista es
  Controlador → Servicio → Repositorio → Modelo. Los controladores aún no están
  implementados.
- El código visible tiene siete modelos (`MUsuario`, `MContacto`, `MGrupo`,
  `MContabilidad`, `MBolsillo`, `MGasto`, `MIngreso`), siete repositorios y cinco
  servicios (`SUsuario`, `SContacto`, `SContabilidad`, `SGasto`, `SIngreso`).
- Los cinco servicios actuales contienen `try-catch` de `RuntimeException` y relanzan
  `IllegalStateException` conservando la causa. No todos tienen encabezado Javadoc.
- `MGrupo` y `MBolsillo` tienen repositorios, pero no servicios actuales. `MGrupoUsuario`,
  `MFuente`, `MCorreo` y `MLlamada` están descritos en la planificación, no en el código.
- Los identificadores entre modelos se manejan principalmente como campos `String`; las
  relaciones JPA mostradas en documentos no están implementadas en varios modelos.
- El árbol de trabajo tiene cambios sin commit anteriores a este análisis. Entre ellos
  aparecen eliminaciones de DTO y modificaciones de servicios/modelos. Este documento no
  atribuye esos cambios a una spec ni autoriza descartarlos.
- La base de datos no se ha creado, según la decisión registrada para la spec 001.

## Decisiones confirmadas para spec 003

- Se cubren los cinco servicios existentes y se crean `SGrupo` y `SBolsillo`.
- Los siete servicios ofrecen CRUD y todas las consultas personalizadas actuales de sus
  repositorios, sin añadir reglas de negocio.
- Cada llamada al repositorio va dentro de `try-catch`; los fallos se relanzan con
  `throw` como `IllegalStateException`, con un mensaje de operación y la causa original.
- Se agregan encabezados Javadoc solo a los servicios incluidos que aún no tengan uno
  conforme.

## Preguntas abiertas de dominio

1. **Modelo de referencia para futuras specs.** La spec 003 usa los modelos y repositorios
   actuales como referencia. Para cambios posteriores, ¿qué partes de la planificación
   futura requieren una spec de actualización del modelo antes de implementarse?
2. **Grupo y bolsillo.** La documentación propone `Grupo.tipo` (`PUBLICO`/`PRIVADO`), pero
   `MGrupo` no contiene ese campo. ¿Es un requisito aprobado o solo una idea de diseño?
   ¿“Público/privado” describe acceso, mientras que familia/trabajo/estudio describe una
   categoría distinta?
3. **Propiedad de contactos.** `MContacto` actualmente contiene `idUsuario`; su referencia
   JPA a grupo está comentada. La planificación dice que el contacto pertenece a un
   usuario y a un grupo. ¿El grupo debe añadirse ahora, y debe ser una columna simple o
   una relación JPA?
4. **Movimientos y bolsillos.** El código actual vincula `MGasto` y `MIngreso` con
   `idContabilidad` y conserva sus enums `Categoria`. La planificación los vincula con
   `idBolsillo` y dice que la categoría se representa con el bolsillo. ¿Cuándo debe
   hacerse ese cambio y qué campos/enums se conservarán?
5. **Saldo de contabilidad.** `MContabilidad` persiste `valor`, mientras los documentos
   describen saldos derivados de movimientos/bolsillos. ¿`valor` es un dato persistido,
   un resumen recalculable o debe retirarse en una spec futura?
6. **Constructor de grupo.** `MGrupo` tiene un constructor de cuatro parámetros que no
   asigna `idUsuario` ni `fechaCreacion`, aunque ambos campos son obligatorios en JPA.
   ¿Se conserva por compatibilidad, se elimina o se completa con valores definidos?
7. **Usuario y credenciales.** `MUsuario.md` llama dirección física a `propiedad`, pero
   `datos_usuario.md` dice que el significado está pendiente. Además, la documentación
   propone una contraseña hasheada, pero no se observa un flujo de autenticación que
   garantice ese hash. ¿Cuál es el significado aprobado de `propiedad`, qué representa
   `contacto` y qué política de autenticación/contraseña se usará?
8. **DTO simples.** La spec 001 dice conservar `UsuarioDTO`, `ContactoDTO`, `GastoDTO`,
    `IngresoDTO` y `ContabilidadDTO`; el árbol de trabajo muestra eliminaciones de esos
    DTO junto con los DTO de dashboard. ¿Se revisó/aprobó una modificación de la spec o
    deben preservarse los cinco DTO simples?
9. **Membresías y roles.** La planificación excluye al propietario de `MGrupoUsuario` y
    asigna a esa entidad los roles `ADMIN`/`MIEMBRO`; el propietario también tiene
    facultades exclusivas. ¿Se aprueba este modelo de roles y estados antes de crear la
    entidad y sus servicios?
10. **Permisos e integraciones.** Los documentos describen permisos, invitaciones, IA y
    n8n como capacidades, pero el roadmap las presenta como futuras y no hay modelos de
    permiso/invitación en el código. ¿Qué documentos son visión y cuáles describen
    comportamiento ya aprobado para implementar?
11. **Estado de las specs.** Las specs 001 y 002 se marcan `borrador`, aunque sus tareas y
    el roadmap las presentan como completadas. ¿Deben actualizarse sus estados formales?

## Diferencias documentales que requieren reconciliación

- `doc/planificacion/entidades/Modelo.md` ya clasifica `MGrupoUsuario` como pendiente y
  etiqueta las relaciones como diseño objetivo, no como implementación actual.
- Las fichas `MGrupo.md`, `MContacto.md`, `MGasto.md` y `MIngreso.md` presentan campos o
  relaciones que no existen en los modelos Java actuales.
- `doc/planificacion/entidades/Servicio.md` usa nombres como `UsuarioService` y atribuye
  autenticación/grupo por defecto a usuario; el código usa clases `S...` y no muestra esa
  lógica.
- `doc/modelo/modelo-datos.md` se titula conceptual, pero llama “vigente” al flujo de bolsillos y
  representa como actuales asociaciones que aún son diseño.
- `doc/seguridad/permisos.md` habla de permisos e historial en presente, mientras el roadmap deja
  el modelo de permisos para después.
- `AGENTS.md`, `doc/constitution.md` y `.agents/rules/rules.md` apuntan a la skill actual
  de encabezado; AGENTS también apunta a la skill de respuesta.
- `doc/planificacion/entidades/MContacto.md` no incluye `PERSONAL` en su enum; el modelo actual
  sí lo incluye. Debe definirse si esa ficha es propuesta o si refleja el enum deseado.

## Seguimiento de esta revisión (2026-10-09)

- La documentación general se organizó en subcarpetas dentro de `doc/`; la planificación
  de entidades quedó en `doc/planificacion/entidades/`. Las specs permanecen en `specs/`.
- El usuario confirmó el alcance, las operaciones, el contrato de errores y los
  encabezados de spec 003; luego aprobó la spec. El planner ya redactó `plan.md` y
  `tasks.md`; esperan aprobación formal antes de implementar.
- No hay plan aprobado ni implementación autorizada por el flujo SDD.
- La revisión de enlaces Markdown locales encontró cero enlaces rotos tras corregir las
  rutas afectadas por la reorganización.
- Sigue pendiente reconciliar el árbol de trabajo extenso y la divergencia de Git antes
  de crear commits selectivos o integrar cambios remotos.

## Estado de Git observado

Al revisar, la rama local `develop` estaba 6 commits adelante y 4 atrás de
`origin/develop`. Había modificaciones y eliminaciones sin commit en documentación,
servicios, modelos y DTO. No se revisó ni resolvió aquí la integración con remoto. Antes
de implementar una spec o crear otro commit, hay que confirmar qué cambios del árbol
pertenecen a cada tarea.
