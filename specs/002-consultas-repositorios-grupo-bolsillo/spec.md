# Spec 002 — Consultas de grupos y bolsillos en repositorios

**Estado:** borrador

## Propósito

Los modelos `MGrupo` y `MBolsillo` ya contienen las referencias escalares necesarias para
localizar grupos por el usuario creador y bolsillos por su contabilidad o tipo. Sin
consultas de repositorio para esos campos, las capas que consumen los repositorios no
pueden recuperar los registros según el diseño documentado. Esta spec incorpora esas
consultas acotadas para que los modelos creados puedan utilizarse por sus referencias
definidas.

## Alcance

### Dentro del alcance

- Consultar los grupos cuyo `idUsuario` corresponde al usuario creador registrado en el
  grupo.
- Consultar los bolsillos asociados al valor `idContabilidad` indicado.
- Consultar los bolsillos cuyo `tipo` coincide con uno de los valores definidos por el
  modelo de bolsillo.
- Mantener las consultas alineadas con los campos escalares existentes y sin extender el
  alcance a membresías, permisos u otras relaciones del modelo.

### Fuera del alcance

- Crear, modificar o ampliar los modelos `MGrupo` y `MBolsillo`.
- Consultar grupos por membresía de usuario distinta del campo `idUsuario` que identifica
  al creador.
- Consultar bolsillos combinando filtros o aplicando filtros por estado, salvo las
  búsquedas individuales indicadas en esta spec.
- Crear entidades de asociación, relaciones JPA, servicios, controladores o endpoints.
- Modificar el esquema, los datos persistidos o la forma de los datos.

## Casos límite

- **Sin coincidencias:** una consulta por usuario, contabilidad o tipo que no encuentre
  registros deberá producir una colección vacía; no deberá fallar ni devolver `null`.
- **Identificador de usuario:** la búsqueda por `idUsuario` identifica grupos por su
  creador según el modelo actual; no representa todos los grupos a los que el usuario
  pudiera pertenecer.
- **Estado del registro:** las consultas descritas no filtran por `estado`; incluyen
  registros activos e inactivos que coincidan con el criterio solicitado.
- **Tipo de bolsillo:** la búsqueda acepta únicamente los tipos definidos actualmente
  por `MBolsillo.Tipo`; no introduce ni traduce valores de tipo.
- **Relaciones documentales:** la documentación conceptual describe relaciones de grupo
  con usuario y de bolsillo con contabilidad. La spec 001 establece que sus identificadores
  se mantienen como columnas simples, sin relaciones JPA; estas consultas usan esos
  identificadores y no alteran esa decisión.
- **Persistencia:** conforme a la decisión registrada en la spec 001, la base de datos aún
  no se ha creado. Esta spec solo define consultas y no necesita migración ni cambio de
  formato de datos.

## Requisitos funcionales (EARS)

- **RF-001 — Grupos por creador.** Cuando se soliciten grupos por `idUsuario`, el
  repositorio deberá devolver todos los grupos cuyo campo `idUsuario` coincida con el
  identificador recibido.

- **RF-002 — Bolsillos por contabilidad.** Cuando se soliciten bolsillos por
  `idContabilidad`, el repositorio deberá devolver todos los bolsillos cuyo campo
  `idContabilidad` coincida con el identificador recibido.

- **RF-003 — Bolsillos por tipo.** Cuando se soliciten bolsillos por tipo, el repositorio
  deberá devolver todos los bolsillos cuyo tipo coincida con el valor definido por el
  modelo y recibido en la consulta.

- **RF-004 — Resultado sin coincidencias.** Cuando una consulta de esta spec no encuentre
  registros coincidentes, el repositorio deberá devolver una colección vacía.

- **RF-005 — Sin cambios al modelo ni a los datos.** Al incorporar estas consultas, el
  modelo y el formato de los datos persistidos deberán permanecer sin cambios; las
  referencias usadas por las búsquedas seguirán siendo los campos simples ya existentes.
