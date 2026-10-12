**Fuente o conexión de un usuario con un servicio externo (ej. Gmail, WhatsApp).**

| Campo         | Tipo Java                          | Clave | Descripción                          |
| ------------- | ---------------------------------- | ----- | ------------------------------------ |
| idFuente      | String                             | PK    | Identificador único de la fuente (UUID) |
| idUsuario     | String                             | FK    | Referencia al usuario dueño de la conexión |
| tipo          | Enum (GMAIL, WHATSAPP, OTRO)       |       | Servicio de la fuente                |
| identificador | String                             |       | Cuenta/dirección conectada           |
| estado        | boolean                            |       | Indica si la conexión está activa    |
| fechaCreacion | LocalDate                          |       | Fecha de creación del registro       |

**Relaciones:** N:1 con `mUsuario` (usuarioId) · 1:N con `mCorreo` (fuenteId)

**Notas:** pertenece al usuario, no al grupo. El grupo accede a la información obtenida
mediante permiso. La ingesta de Gmail es el primer foco de la aplicación.
