**MGrupoUsuario — relación N:M entre usuarios y grupos (membresía). Describe a los
miembros distintos al propietario.**

| Campo          | Tipo Java              | Clave | Descripción                                   |
| -------------- | ---------------------- | ----- | --------------------------------------------- |
| idGrupoUsuario | String                 | PK    | Identificador único de la relación (UUID)     |
| idGrupo        | String                 | FK    | Referencia al grupo                           |
| idUsuario      | String                 | FK    | Referencia al usuario miembro                 |
| rol            | Enum (ADMIN, MIEMBRO)  |       | Rol del usuario dentro del grupo              |
| estado         | Enum (INVITADA, ACTIVA, SALIO, RETIRADA) | | Estado de la membresía |

**Relaciones:** N:1 con `mGrupo` (grupoId) · N:1 con `mUsuario` (usuarioId)

**Notas:**
- El **propietario** del grupo no aparece aquí; se identifica por `MGrupo.idUsuario`.
- Esta entidad describe únicamente a los **miembros distintos al propietario** que se
  unieron al grupo.
- En grupos **públicos**, la membresía tiene límites y no aplica transferencia.
- Las reglas detalladas de roles, invitaciones y permisos están en
  [`grupos.md`](./grupos.md). Invitación y permiso se modelan en pasos posteriores.
