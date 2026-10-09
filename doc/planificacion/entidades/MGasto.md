Movimiento de gasto dentro de un bolsillo.

| Campo          | Tipo Java                                             | Clave | Descripción                                     |
| -------------- | ----------------------------------------------------- | ----- | ----------------------------------------------- |
| idGasto        | String                                                | PK    | Identificador único del gasto (UUID)            |
| idBolsillo     | String                                                | FK    | Referencia al bolsillo al que pertenece         |
| idContabilidad | String                                                | FK    | Referencia a la contabilidad (opcional)         |
| descripcion    | String                                                |       | Descripción del gasto                           |
| valor          | double                                                |       | Monto del gasto                                 |
| fecha          | LocalDate                                             |       | Fecha en que se realizó el gasto                |

**Relaciones:** N:1 con `mBolsillo` (bolsilloId)

**Notas:**
- La categoría del movimiento se representa mediante el bolsillo
  (ver [`MBolsillo.md`](./MBolsillo.md)); por eso se retira el enum de categoría del
  gasto. El saldo del bolsillo se deriva de sus movimientos.
- El gasto cuelga del **bolsillo**; la contabilidad es opcional (para trazabilidad).
