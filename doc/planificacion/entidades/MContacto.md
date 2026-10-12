Persona de contacto asociada a un grupo y a un usuario. Puede compartirse dentro del
grupo mediante permiso.

| Campo      | Tipo Java                     | Clave | Descripción                             |
| ---------- | ----------------------------- | ----- | --------------------------------------- |
| idContacto | String                        | PK    | Identificador único del contacto (UUID) |
| idGrupo    | String                        | FK    | Referencia al grupo al que pertenece    |
| idUsuario  | String                        | FK    | Referencia al usuario dueño del contacto |
| nombre     | String                        |       | Nombre del contacto                     |
| telefono   | String                        |       | Número de teléfono del contacto         |
| correo     | String                        |       | Correo electrónico del contacto         |
| tipo       | Enum (FAMILIAR, AMIGO, TRABAJO) |     | Tipo de relación con el contacto        |
| notas      | String                        |       | Notas u observaciones adicionales       |

**Relaciones:** N:1 con `mGrupo` (grupoId) · N:1 con `mUsuario` (usuarioId)

**Notas:**
- El contacto pertenece a un **grupo** y a un **usuario**. Ambos pueden pertenecer al
  mismo grupo.
- Se retira el valor `OTRO` del enum.
- El contacto puede compartirse dentro del grupo mediante permiso explícito.
