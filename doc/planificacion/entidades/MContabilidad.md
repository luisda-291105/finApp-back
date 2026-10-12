Cuenta o bloque contable de un grupo. Es el **resumen de la clasificación de los datos
obtenidos**, dividido entre los distintos bolsillos.

| Campo          | Tipo Java | Clave | Descripción                                   |
| -------------- | --------- | ----- | --------------------------------------------- |
| idContabilidad | String    | PK    | Identificador único de la contabilidad (UUID) |
| idGrupo        | String    | FK    | Referencia del grupo al que pertenece         |
| idContacto     | String    | FK    | Referencia al contacto asociado (opcional)    |
| nombre         | String    |       | Nombre de la cuenta/contabilidad              |
| valor          | double    |       | Saldo o valor actual de la cuenta             |
| estado         | boolean   |       | Indica si la cuenta está activa               |
| fechaCreacion  | LocalDate |       | Fecha de creación del registro                |

**Relaciones:** N:1 con `mGrupo` (grupoId) · N:1 con `mContacto` (contactoId, opcional)
· 1:N con `mBolsillo` (contabilidadId)

**Notas:**
- La contabilidad pertenece a un **grupo**. Es el resumen de la clasificación de los datos
  obtenidos, dividido entre los distintos bolsillos.
- El contacto asociado es opcional y puede compartirse en el grupo mediante permiso.
- Los reportes/gráficas de la cuenta se calculan, no se persisten.
