# Spec 006 — Servicios de bolsillo, fuente y grupo

**Estado:** aprobado por el usuario

## Propósito

Los modelos y repositorios actuales permiten operar bolsillos, fuentes y grupos, pero
todavía no hay servicios para delegar esas operaciones. Esta spec concreta el contrato
de esos tres servicios usando únicamente las operaciones que ofrecen sus repositorios y
extiende a fuentes las decisiones SDD ya establecidas para manejo de errores y
encabezados de servicio.

## Alcance

### Dentro del alcance

- Proveer el CRUD disponible para bolsillos, fuentes y grupos, conservando los tipos de
  resultado que ofrecen sus repositorios.
- Consultar grupos por el identificador del usuario creador.
- Consultar bolsillos por identificador de contabilidad o por tipo.
- Mantener para fuentes únicamente el CRUD existente; no se dispone de consultas
  personalizadas para esa entidad.
- Ante fallos de una operación del repositorio, comunicar el error mediante
  `IllegalStateException`, con un mensaje que identifique la operación y conservando la
  excepción original como causa.
- Incorporar el encabezado Javadoc de seis campos a cada servicio incluido que no lo
  tenga ya conforme a la plantilla del proyecto.

### Fuera del alcance

- Crear o modificar modelos, repositorios, DTO, controladores, endpoints o datos
  persistidos.
- Añadir consultas para fuentes por usuario, tipo, identificador de cuenta, estado o
  fecha, ni consultas adicionales para bolsillos o grupos.
- Filtrar resultados implícitamente por estado, propietario, membresía o permisos; añadir
  reglas de negocio, validaciones o políticas de acceso.
- Modificar los otros servicios ni las decisiones generales de la spec 003.

## Casos límite

- **Sin coincidencias:** las consultas que devuelven colecciones conservan la colección
  vacía del repositorio; la consulta por identificador conserva `Optional.empty()`. No se
  convierten en excepciones.
- **Grupos por usuario:** la búsqueda identifica grupos mediante `idUsuario`, que
  representa al creador. No representa grupos accesibles por membresía.
- **Bolsillos:** las consultas por contabilidad y tipo son independientes; no combinan
  criterios ni excluyen bolsillos por estado. Los tipos son los definidos actualmente
  por el modelo.
- **Fuentes:** consultar todas las fuentes no filtra por usuario ni estado. No hay una
  búsqueda por usuario ni otras consultas personalizadas disponibles en el repositorio.
- **Identificador inexistente en escritura:** el servicio conserva el comportamiento del
  repositorio para actualizar o eliminar un identificador sin coincidencia; no define un
  resultado de negocio adicional.
- **Fallo del repositorio:** el servicio relanza el fallo como `IllegalStateException`,
  identifica la operación en el mensaje y conserva la excepción original como causa.
- **Encabezados:** solo se añaden a servicios incluidos que carezcan del encabezado
  Javadoc requerido; no se alteran encabezados que ya sean conformes.

## Requisitos funcionales (EARS)

- **RF-001 — CRUD de los tres servicios.** Cuando se solicite crear, consultar, actualizar
  o eliminar un bolsillo, fuente o grupo, el servicio correspondiente deberá delegar la
  operación disponible en su repositorio y conservar su tipo de resultado.
- **RF-002 — Consultas de grupo.** Cuando se soliciten grupos por usuario, el servicio
  deberá devolver los grupos cuyo identificador de creador coincida con el recibido, sin
  interpretar el resultado como una búsqueda por membresía.
- **RF-003 — Consultas de bolsillo.** Cuando se soliciten bolsillos por contabilidad o
  tipo, el servicio deberá delegar cada criterio en la consulta correspondiente del
  repositorio y conservar los resultados sin aplicar otros filtros.
- **RF-004 — Alcance de consultas de fuente.** Cuando se soliciten fuentes, el servicio
  deberá limitarse a las operaciones CRUD existentes; no deberá ofrecer consultas
  personalizadas que el repositorio no expone.
- **RF-005 — Resultados vacíos.** Cuando una consulta no encuentre coincidencias, el
  servicio deberá conservar la colección vacía o `Optional.empty()` que devuelva el
  repositorio.
- **RF-006 — Manejo de errores.** Cuando una operación del repositorio lance una
  excepción, el servicio deberá capturarla y relanzarla como `IllegalStateException`, con
  un mensaje claro que identifique la operación y la excepción original como causa.
- **RF-007 — Encabezados faltantes.** Cuando un servicio incluido no tenga el encabezado
  Javadoc de seis campos definido por la plantilla del proyecto, deberá incorporarlo con
  información correspondiente al servicio.
- **RF-008 — Sin cambios ajenos al servicio.** Al completar esta spec, los modelos,
  repositorios, otros servicios, DTO, controladores y datos persistidos deberán conservar
  su estado previo.
