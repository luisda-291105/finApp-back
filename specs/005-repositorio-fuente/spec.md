# Spec 005 — Repositorio de fuente

**Estado:** borrador

## Propósito

`MFuente` representa una fuente de un usuario, pero todavía no se han especificado las
operaciones básicas para persistirla y consultarla. Esta spec define el CRUD básico de
fuentes siguiendo el patrón de operaciones existente para usuarios, sin extenderlo a
búsquedas especializadas ni a otras capas.

## Alcance

### Dentro del alcance

- Agregar una fuente.
- Actualizar una fuente existente.
- Eliminar una fuente por su identificador.
- Consultar todas las fuentes.
- Consultar una fuente por su identificador.
- Mantener los datos y decisiones de `MFuente` definidos en la spec 004.

### Fuera del alcance

- Modificar `MFuente`, su esquema, sus campos o sus restricciones.
- Agregar búsquedas por usuario, tipo, identificador de cuenta, estado o fecha.
- Incorporar reglas de negocio, filtros automáticos, validaciones adicionales o políticas
  de permisos.
- Crear o modificar servicios, controladores, DTO, endpoints o integraciones con fuentes
  externas.
- Crear o modificar otras entidades o repositorios.

## Casos límite

- **Colección sin fuentes:** consultar todas las fuentes sin registros deberá producir
  una colección vacía.
- **Identificador sin coincidencia:** consultar una fuente inexistente deberá representar
  explícitamente que no hay resultado, sin devolver un valor nulo.
- **Consulta sin filtros:** consultar todas las fuentes no deberá excluir registros por
  estado ni por usuario.
- **Identificadores en operaciones de escritura:** no se agregan reglas especiales de
  negocio para una actualización o eliminación cuyo identificador no corresponda a un
  registro; se conserva el comportamiento del patrón de operaciones CRUD existente.
- **Identidad y datos:** agregar, consultar, actualizar y eliminar no cambian el formato
  ni las decisiones de identidad o campos establecidas para `MFuente` en la spec 004.

## Requisitos funcionales (EARS)

- **RF-001 — Agregar fuente.** Cuando se solicite agregar una fuente, la operación deberá
  persistir los datos recibidos y devolver la fuente persistida.
- **RF-002 — Actualizar fuente.** Cuando se solicite actualizar una fuente existente, la
  operación deberá persistir los cambios recibidos y devolver la fuente actualizada.
- **RF-003 — Eliminar fuente.** Cuando se solicite eliminar una fuente por su
  identificador, la operación deberá eliminar la fuente correspondiente.
- **RF-004 — Consultar todas las fuentes.** Cuando se soliciten todas las fuentes, la
  operación deberá devolver la colección de fuentes sin filtrar por usuario ni estado;
  si no existen fuentes, deberá devolver una colección vacía.
- **RF-005 — Consultar fuente por identificador.** Cuando se solicite una fuente por su
  identificador, la operación deberá devolverla si existe y representar explícitamente
  la ausencia de resultado si no existe.
- **RF-006 — Conservar el modelo.** Al proporcionar estas operaciones, la identidad,
  los campos y el formato de datos de `MFuente` deberán permanecer conforme a la spec 004.
