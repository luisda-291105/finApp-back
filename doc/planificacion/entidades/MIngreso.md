Movimiento de ingreso dentro de un bolsillo.

| Campo          | Tipo Java | Clave | Descripción                                     |
| -------------- | --------- | ----- | ----------------------------------------------- |
| idIngreso      | String    | PK    | Identificador único del ingreso (UUID)          |
| idBolsillo     | String    | FK    | Referencia al bolsillo al que pertenece         |
| idContabilidad | String    | FK    | Referencia a la contabilidad (opcional)         |
| descripcion    | String    |       | Descripción del ingreso                         |
| valor          | double    |       | Monto del ingreso                               |
| fecha          | LocalDate |       | Fecha en que se registró el ingreso             |

**Relaciones:** N:1 con `mBolsillo` (bolsilloId)

**Notas:**
- La categoría del movimiento se representa mediante el bolsillo
  (ver [`MBolsillo.md`](./MBolsillo.md)); por eso se retira el enum de categoría del
  ingreso. El saldo del bolsillo se deriva de sus movimientos.
- El ingreso cuelga del **bolsillo**; la contabilidad es opcional (para trazabilidad).
