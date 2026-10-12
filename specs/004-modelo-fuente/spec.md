# Spec 004 — Modelo de fuente

**Estado:** borrador

## Propósito

El modelo planificado contempla una fuente como conexión de un usuario con un servicio
externo. Esta spec delimita la creación de `MFuente` con los datos definidos para la
entidad y las decisiones explícitas sobre sus tipos, identificador y referencia al usuario,
sin adelantar el procesamiento de correos ni el acceso compartido por grupos.

## Alcance

### Dentro del alcance

- Crear únicamente la entidad de modelo `MFuente`.
- Representar sus campos: identificador, usuario dueño, tipo general de fuente,
  identificador de la cuenta, estado de la conexión y fecha de creación.
- Representar el tipo como enum con los valores `CORREO`, `CHAT`, `TELEFONO` y `OTRO`,
  persistido como texto.
- Usar `String` en formato UUID para `idFuente`, proporcionado al construir la entidad y
  no generado dentro de ella.
- Mantener `idUsuario` como referencia escalar, sin relación JPA ni restricción FK en la
  base de datos.

### Fuera del alcance

- Crear o modificar `MLlamada` y `MGrupoUsuario`; se posponen a specs posteriores.
- Crear `MCorreo` u otras entidades, repositorios, servicios, controladores o DTO.
- Implementar conexión, autenticación, sincronización o ingestión de datos de servicios
  externos.
- Definir permisos o acceso de grupos a fuentes.
- Añadir reglas de negocio, validaciones o restricciones no descritas en la fuente de
  datos indicada.
- Cambiar el esquema o formato de otros modelos o datos existentes.

## Casos límite

- **Tipo de fuente:** la planificación original enumera `GMAIL`, `WHATSAPP` y `OTRO`,
  pero la decisión explícita del usuario la sustituye por el enum general
  `CORREO`, `CHAT`, `TELEFONO` y `OTRO`; se persiste como texto. No se establecen
  correspondencias automáticas entre los valores anteriores y los nuevos.
- **Identificador UUID:** `idFuente` sigue siendo `String` y debe tener formato UUID. El
  valor se proporciona al construir la entidad; la entidad no lo genera. Esta spec no
  define validación adicional del formato.
- **Estado:** el estado es un booleano que indica si la conexión está activa; esta spec
  no define transiciones ni efectos de cambiarlo.
- **Fecha:** la fecha de creación se representa como `LocalDate`, según la planificación;
  no se añade hora ni zona horaria.
- **Identificador de cuenta:** representa la cuenta o dirección conectada. No se define
  formato, normalización ni unicidad adicional.
- **Referencia al usuario:** la planificación describe una relación N:1 y marca
  `idUsuario` como FK; la decisión explícita del usuario mantiene solo la referencia
  escalar y descarta tanto la relación JPA como la restricción FK en la base de datos.
- **Alcance futuro:** la referencia planificada de fuentes a correos no implica crear
  `MCorreo` ni establecer comportamiento de acceso o procesamiento en esta spec.

## Requisitos funcionales (EARS)

- **RF-001 — Identidad de la fuente.** Cuando se construya una instancia de `MFuente`,
  deberá recibir `idFuente` de tipo `String` en formato UUID como clave primaria; la
  entidad no deberá generar este identificador.
- **RF-002 — Usuario dueño.** Cuando se represente una fuente, deberá incluir `idUsuario`
  de tipo `String` como referencia escalar al usuario dueño, sin relación JPA ni
  restricción FK en la base de datos.
- **RF-003 — Tipo de fuente.** Cuando se represente el tipo de una fuente, deberá ser un
  enum con uno de los valores `CORREO`, `CHAT`, `TELEFONO` u `OTRO`, y deberá persistirse
  como texto.
- **RF-004 — Cuenta conectada.** Cuando se represente una fuente, deberá incluir
  `identificador` de tipo `String` para la cuenta o dirección conectada.
- **RF-005 — Estado de conexión.** Cuando se represente una fuente, deberá incluir
  `estado` de tipo `boolean` para indicar si la conexión está activa o inactiva.
- **RF-006 — Fecha de creación.** Cuando se represente una fuente, deberá incluir
  `fechaCreacion` de tipo `LocalDate`.
- **RF-007 — Alcance limitado al modelo.** Al completar esta spec, los modelos distintos
  de `MFuente` y las capas de repositorio, servicio y controlador deberán conservar su
  estado previo.
