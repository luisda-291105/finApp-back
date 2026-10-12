**Grupo o espacio compartido de información (ej. familia, pareja, equipo). Agrupa
usuarios, contabilidad, correos y demás información; las gráficas se calculan aquí.**

| Campo         | Tipo Java | Clave | Descripción                                   |
| ------------- | --------- | ----- | --------------------------------------------- |
| idGrupo       | String    | PK    | Identificador único del grupo (UUID)          |
| idUsuario     | String    | FK    | Referencia al **propietario** (creador) del grupo |
| nombre        | String    |       | Nombre del grupo                              |
| descripcion   | String    |       | Descripción o propósito del grupo             |
| tipo          | Enum (PUBLICO, PRIVADO) | | Tipo de grupo                      |
| estado        | boolean   |       | Indica si el grupo está activo                |
| fechaCreacion | LocalDate |       | Fecha de creación del registro                |

**Relaciones:** N:1 con `mUsuario` (usuarioId, propietario) · 1:N con `mGrupoUsuario`
(grupoId, miembros distintos al propietario) · 1:N con `mContabilidad` (grupoId) ·
1:N con `mCorreo` (grupoId)

**Notas:**
- El **propietario** es el usuario creador, identificado por `idUsuario`. No aparece en
  `MGrupoUsuario`.
- `MGrupoUsuario` describe a los **miembros distintos al propietario** que se unieron al
  grupo.
- **Grupo público:** cualquier usuario puede unirse; membresía con límites; no aplica
  transferencia de propiedad; incluye chat de grupo.
- **Grupo privado:** solo por invitación; aplica transferencia de propiedad.
- **No existe una entidad de gráficas**: los reportes se calculan desde los movimientos
  del grupo.
- Invitación y permiso se modelan en pasos posteriores.
