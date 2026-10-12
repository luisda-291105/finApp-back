**Registro de llamada asociada a un bolsillo: quién llama, desde dónde y cuántas veces.**

| Campo         | Tipo Java | Clave | Descripción                                   |
| ------------- | --------- | ----- | --------------------------------------------- |
| idLlamada     | String    | PK    | Identificador único de la llamada (UUID)      |
| idBolsillo    | String    | FK    | Referencia al bolsillo al que pertenece       |
| idContacto    | String    | FK    | Referencia al contacto relacionado (opcional) |
| numero        | String    |       | Número que llama o recibe                     |
| origen        | String    |       | Procedencia de la llamada (operador, app, …)  |
| duracion      | long      |       | Duración en segundos (opcional)               |
| fecha         | LocalDate |       | Fecha de la llamada                           |

**Relaciones:** N:1 con `mBolsillo` (bolsilloId) · N:1 con `mContacto` (contactoId,
opcional)

**Notas:**
- Cada fila es **una llamada**. El "cuántas veces" se obtiene contando filas, no se
  almacena.
- La llamada forma parte del listado de información del bolsillo.
- El contacto asociado es opcional y su identidad se comparte según permiso.
