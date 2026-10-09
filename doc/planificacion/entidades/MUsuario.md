Representa a un usuario registrado en la aplicación.

| Campo         | Tipo Java | Clave | Descripción                            |
| ------------- | --------- | ----- | -------------------------------------- |
| idUsuario     | String    | PK    | Identificador único del usuario (UUID) |
| nombre        | String    |       | Nombre del usuario                     |
| contacto      | String    |       | Correo electrónico (único)             |
| contrasena    | String    |       | Contraseña almacenada como hash        |
| propiedad     | String    |       | Dirección física del usuario           |
| fechaCreacion | LocalDate |       | Fecha de creación del registro         |

**Relaciones:** 1:N con `mContacto` (usuarioId) · 1:N con `mFuente` (usuarioId) ·
1:N con `mGrupo` (usuarioId, como propietario) · 1:N con `mGrupoUsuario` (usuarioId)

**Notas:**
- El usuario tiene un **grupo por defecto** para su contabilidad personal.
- `propiedad` es la dirección física del usuario.
