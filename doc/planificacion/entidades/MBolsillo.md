**Bolsillo o subcuenta de una cuenta contable (ej. ahorro, gastos, metas). Es un
espacio de información, no solo un número.**

| Campo          | Tipo Java | Clave | Descripción                               |
| -------------- | --------- | ----- | ----------------------------------------- |
| idBolsillo     | String    | PK    | Identificador único del bolsillo (UUID)   |
| idContabilidad | String    | FK    | Referencia a la contabilidad/cuenta dueña |
| nombre         | String    |       | Nombre del bolsillo                       |
| tipo           | Enum (GASTO, INGRESO, AHORRO, OTRO) |  | Tipo de bolsillo                 |
| estado         | boolean   |       | Indica si el bolsillo está activo         |
| fechaCreacion  | LocalDate |       | Fecha de creación del registro            |

**Relaciones:** N:1 con `mContabilidad` (contabilidadId) · 1:N con `mGasto`
(bolsilloId) · 1:N con `mIngreso` (bolsilloId) · 1:N con `mLlamada` (bolsilloId) ·
1:N con `mCorreo` (bolsilloId, opcional)

**Notas:**
- El saldo es **derivado** de los movimientos (dato dinámico para el dashboard),
  no una columna. El bolsillo agrupa listas de movimientos, correos y llamadas.
- `AHORRO` y `OTRO` son valores del enum `Tipo` de este bolsillo.
- El bolsillo es una **división** de la contabilidad: la contabilidad es el resumen,
  los bolsillos son las partes.
