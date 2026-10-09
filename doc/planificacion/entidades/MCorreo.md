**Correo electrónico recibido desde una fuente, interpretado y clasificado. Vive en el
grupo.**

| Campo         | Tipo Java                                                         | Clave | Descripción                                   |
| ------------- | ----------------------------------------------------------------- | ----- | --------------------------------------------- |
| idCorreo      | String                                                            | PK    | Identificador único del correo (UUID)         |
| idGrupo       | String                                                            | FK    | Referencia al grupo al que pertenece          |
| idFuente      | String                                                            | FK    | Referencia a la fuente (conexión) de origen   |
| idBolsillo    | String                                                            | FK    | Bolsillo asociado al clasificar (opcional)    |
| remitente     | String                                                            |       | Remitente del correo                          |
| asunto        | String                                                            |       | Asunto del correo                             |
| resumen       | String                                                            |       | Resumen/contenido interpretado                |
| fecha         | LocalDate                                                         |       | Fecha del correo                              |
| estado        | Enum (RECIBIDO, INTERPRETADO, REGISTRADO, REQUIERE_REVISION)      |       | Estado de procesamiento                       |
| nivelRegistro | Enum (PRINCIPAL, SECUNDARIO, ESTUDIO, TRABAJO)                    |       | Nivel de registro del correo                  |

**Relaciones:** N:1 con `mGrupo` (grupoId) · N:1 con `mFuente` (fuenteId) · N:1 con
`mBolsillo` (bolsilloId, opcional)

**Notas:**
- El vínculo con el bolsillo es opcional y se resuelve al interpretar el correo.
- `nivelRegistro` clasifica el correo según su nivel de registro: principal, secundario,
  estudio, trabajo, etc.
- Sigue el flujo `recibido → interpretado → registrado / requiere revisión`.
