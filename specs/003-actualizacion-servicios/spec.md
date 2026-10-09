# Spec 003 — Actualización de servicios según los modelos vigentes

**Estado:** borrador

## Propósito

Los modelos y repositorios vigentes definen las entidades disponibles y las operaciones
que pueden realizarse sobre ellas. Los servicios actuales no cubren todavía los modelos
de grupo y bolsillo, y todos los servicios deben presentar un manejo coherente de errores
y documentación de cabecera. Esta spec delimita la actualización de los servicios para
que sus operaciones correspondan a los modelos y repositorios que existen hoy.

## Alcance

### Dentro del alcance

- Actualizar `SUsuario`, `SContacto`, `SContabilidad`, `SGasto` y `SIngreso`, y crear
  `SGrupo` y `SBolsillo`, tomando como referencia sus modelos y repositorios actuales.
- Proveer el CRUD básico y todas las consultas personalizadas ya expuestas por cada uno de
  esos repositorios.
- Asegurar que los fallos de las operaciones de servicio no se oculten y se comuniquen a
  quien invocó la operación.
- Añadir el encabezado Javadoc de seis campos a cada clase de servicio incluida que no
  tenga ya un encabezado conforme a la plantilla del proyecto.
- Usar como fuente de verdad los modelos y repositorios presentes al redactar esta spec.

### Fuera del alcance

- Crear servicios para entidades planificadas que aún no existen en el código (`MFuente`,
  `MCorreo`, `MLlamada` y `MGrupoUsuario`).
- Añadir reglas de negocio, validaciones, filtros, consultas u operaciones que no estén
  definidas por los repositorios vigentes.
- Cambiar modelos, repositorios, DTO, controladores, esquema o formato de datos.
- Definir contratos de DTO, traducción de excepciones a HTTP o un manejador de errores,
  rutas, seguridad y autenticación de endpoints, paginación de respuestas o documentación
  de la API. Estos asuntos se concretarán en futuras specs de Controlador; no forman parte
  del contrato de Servicio.
- Rehacer ni incorporar otros cambios previos del árbol de trabajo que no estén
  especificados aquí.

## Decisiones de alcance confirmadas

- Se incluyen los cinco servicios existentes (`SUsuario`, `SContacto`, `SContabilidad`,
  `SGasto` y `SIngreso`) y se crean `SGrupo` y `SBolsillo` para los modelos/repositorios
  vigentes correspondientes.
- Cada uno de los siete servicios ofrece el CRUD básico y todas las consultas
  personalizadas que ya expone su repositorio. No se agrega una política adicional sobre
  registros activos/inactivos ni se cambia el significado de resultados opcionales o
  colecciones.
- Cada llamada al repositorio se ejecuta dentro de `try-catch`; ante una excepción, el
  servicio la relanza como `IllegalStateException`, usando `throw`, un mensaje claro que
  identifica la operación y la excepción capturada como causa.
- En el árbol de trabajo hay modificaciones y eliminaciones previas sin commit. Entre
  ellas, la spec 001 RF-013 indica conservar los DTO simples, aunque actualmente esos DTO
  aparecen eliminados en el árbol. Esta spec no decide ni incorpora esos cambios; su
  fuente de verdad se limita a los siete modelos y sus repositorios vigentes.

## Casos límite

- **Búsqueda sin resultados:** cada servicio conserva el resultado vacío de una colección
  o el `Optional.empty()` devuelto por el repositorio; no lo convierte en excepción.
- **Fallo del repositorio:** cuando una llamada al repositorio lanza una excepción, el
  servicio relanza `IllegalStateException` con un mensaje que identifica la operación y
  conserva la excepción original como causa.
- **Borrado de identificador inexistente:** el servicio comunica mediante la excepción
  relanzada cualquier fallo reportado por el repositorio; no define una respuesta de
  negocio nueva.
- **Consultas por estado o tipo:** se usan únicamente los parámetros y los resultados
  definidos por los repositorios/modelos vigentes; no se agregan filtros implícitos.
- **Modelos futuros:** la ausencia de servicios para las entidades enumeradas fuera del
  alcance no impide completar esta spec.
- **Cambios preexistentes:** las modificaciones ajenas a este alcance, incluidas las
  eliminaciones de DTO, no se atribuyen a la spec ni se deben revertir como parte de ella.

## Requisitos funcionales (EARS)

- **RF-001 — Servicios para modelos vigentes.** Cuando una capa consumidora solicite una
  operación de usuario, contacto, contabilidad, gasto, ingreso, grupo o bolsillo, deberá
  existir el servicio correspondiente (`SUsuario`, `SContacto`, `SContabilidad`, `SGasto`,
  `SIngreso`, `SGrupo` o `SBolsillo`) y delegar en su repositorio vigente.

- **RF-002 — CRUD básico.** Cuando se solicite crear, consultar, actualizar o eliminar un
  registro, cada uno de los siete servicios deberá delegar en la operación CRUD disponible
  en su repositorio y conservar el tipo de resultado que esta defina.

- **RF-003 — Consultas personalizadas.** Cuando se solicite una búsqueda personalizada,
  el servicio deberá ofrecer y delegar las consultas vigentes declaradas por su
  repositorio: usuario por contacto/nombre; contacto por usuario/nombre/teléfono/correo;
  contabilidad por grupo/estado; gasto e ingreso por contabilidad; grupo por usuario; y
  bolsillo por contabilidad/tipo.

- **RF-004 — Resultados de consultas.** Cuando una consulta incluida no encuentre
  coincidencias, el servicio deberá conservar el tipo de resultado vacío (`Optional.empty()`
  o colección vacía) que entregue el repositorio.

- **RF-005 — Manejo de errores.** Cuando una llamada al repositorio falle, el servicio
  deberá capturar la excepción con `try-catch` y relanzarla con `throw` como
  `IllegalStateException`, con un mensaje claro sobre la operación y preservando la
  excepción capturada como causa.

- **RF-006 — Encabezados faltantes.** Cuando una clase de servicio incluida carezca del
  encabezado Javadoc de seis campos establecido por la plantilla del proyecto, deberá
  incorporar ese encabezado con información correspondiente a la clase. Las clases que
  ya tengan un encabezado conforme no requieren cambios por este requisito.

- **RF-007 — Sin cambios fuera de servicios.** Al completar esta spec, los modelos,
  repositorios, DTO, controladores y datos persistidos deberán conservar su estado previo
  a la implementación de esta spec.
