# Justificación de la arquitectura — finApp-back

Este documento explica **por qué** la arquitectura que finApp-back está adoptando es
correcta para el problema que FinApp quiere resolver. Complementa
[`arquitectura.md`](arquitectura.md) (que describe *qué* hay) y
[`constitution.md`](../constitution.md) (que fija *principios*): aquí se argumenta el
*diseño* y su relación con la planificación de entidades en `doc/planificacion/entidades/`.

No describe funcionalidades ya implementadas; es un documento de diseño y justificación.

## 1. El problema condiciona la arquitectura

La visión de FinApp no es "registrar gastos" sino transformar información dispersa
(finanzas, correos, chats, grupos, relaciones, permisos) en información estructurada y
accionable ([`vision.md`](../producto/vision.md), [`usuario.md`](../planificacion/entidades/usuario.md)).

Ese objetivo impone tres restricciones que cualquier arquitectura válida debe cumplir:

1. **La información tiene dueño y contexto.** Cada dato pertenece a una persona, a un
   grupo o a una organización, y el acceso se controla aparte
   ([`permisos.md`](../seguridad/permisos.md), [`grupos.md`](../planificacion/entidades/grupos.md)).
2. **La información se interpreta automáticamente.** n8n e IA procesan datos externos y
   los convierten en registros ([`ia-n8n.md`](../integraciones/ia-n8n.md)).
3. **La información cambia de forma y de origen.** El modelo de datos evolucionará
   (más fuentes, más entidades) sin romper la API ni la persistencia.

La arquitectura en capas que se está adoptando responde directamente a esas
restricciones. Las secciones siguientes justifican cada decisión.

## 2. Separación en capas: dependencias solo hacia abajo

La regla central es `Controlador → Servicio → Repositorio → Modelo`
([`.agents/rules/rules.md`](../../.agents/rules/rules.md)). No es una formalidad: es lo que
permite que cada restricción anterior se cumpla.

### Por qué cada capa existe

| Capa | Responsabilidad | Problema que evita |
|---|---|---|
| `Modelo/` | Entidades JPA y su mapeo a la base de datos. | Que la forma de persistir se mezcle con la lógica o con la API. |
| `Repositorio/` | Acceso a datos (consultas, proyecciones). | Que la lógica de negocio dependa de SQL o de Hibernate. |
| `Servicio/` | Reglas de negocio y orquestación. | Que los controladores acumulen lógica y se vuelvan intestables. |
| `Controlador/` | Entrada/salida REST, validación y DTOs. | Que detalles HTTP (códigos, JSON) contaminen el dominio. |
| `Dto/` | Contratos de entrada/salida. | Exponer entidades internas y acoplar la API al esquema. |

### Por qué la dirección es "hacia abajo"

- **Sustituibilidad.** Si el acceso a datos cambia (H2 → MySQL, consulta derivada →
  nativa), solo se toca `Repositorio/`. El `Servicio/` no se enteró, como exige la
  constitución al separar persistencia de lógica.
- **Testabilidad.** Un `Servicio/` que depende de una interfaz de repositorio se prueba
  con dobles, sin base de datos. Un `Controlador/` que depende de `Servicio/` se prueba
  con `@WebMvcTest` sin levantar persistencia.
- **Control del alcance.** Cuando la IA o n8n creen registros automáticamente, deben
  pasar por las mismas reglas de negocio. Al vivir en `Servicio/`, cualquier origen
  (REST, automatización, futura importación) reutiliza la misma lógica en vez de
  duplicarla.

Depender hacia arriba (por ejemplo, un repositorio que conoce un DTO de controlador)
volvería imposible probar y aislar el cambio de cualquier capa.

## 3. DTOs y entidades: por qué no se mezclan

La regla "DTOs solo en Controlador, entidades JPA solo en Modelo" tiene justificación
concreta en este dominio:

- **Seguridad.** `MUsuario` contiene credenciales (`contrasena`, `contacto`). Devolver la
  entidad directamente expondría esos campos; `datos_usuario.md` lo prohíbe
  explícitamente. El DTO es el punto donde se decide qué se publica.
- **Estabilidad del contrato.** El esquema JPA puede cambiar (renombrar una columna,
  añadir una relación) sin alterar la respuesta JSON si el DTO se mantiene.
- **Privacidad por defecto.** Al obligar a construir un DTO explícito, ningún campo nuevo
  de la entidad se filtra "por inercia" a la API.
- **Separación de contextos.** Un mismo registro (`MGasto`) se expone distinto según el
  consumidor (por usuario, por contabilidad, compartido con un grupo). Eso se modela con
  DTOs específicos, no con la entidad.

La `doc/planificacion/entidades/` refuerza esta línea al separar *qué datos pertenecen a la
cuenta* de *qué datos son dominio financiero o de grupo*
([`datos_usuario.md`](../planificacion/entidades/datos_usuario.md) §5).

## 4. Proyecciones y mapper: consultas complejas sin cargar todo

Para consultas que cruzan varias tablas (usuario → contabilidad → gasto, etc.), la
documentación técnica de [`doc.md`](../planificacion/entidades/doc.md) propone:

`consulta nativa/JPQL → proyección → mapper (MapStruct) → DTO record`.

Es una decisión correcta por:

- **Rendimiento.** Se traen solo las columnas necesarias, no la entidad completa.
- **Seguridad.** La proyección es un contrato de lectura explícito; no arrastra campos
  sensibles.
- **Compilación segura.** MapStruct genera el mapeo en tiempo de compilación y avisa si
  un campo deja de coincidir, en lugar de fallar en producción.
- **Inmutabilidad.** El DTO como `record` evita mutaciones accidentales en datos de
  salida.

Este patrón es la forma de mantener la regla de capas incluso cuando la consulta es
compleja: la proyección vive en `Repositorio/`, la conversión en una capa de `Mapper/`,
y el `Servicio/` solo orquesta. Ninguna capa invade a la otra.

## 5. El modelo de dominio: por qué la planificación es coherente

La planificación en `doc/planificacion/entidades/` no inventa entidades; resuelve correctamente
los límites del dominio.

### Usuario como entidad principal, con identidad estable

`datos_usuario.md` fija un identificador generado por el sistema y no usa el correo como
clave. Esto es correcto porque el correo puede cambiar sin que cambie la identidad de la
persona, y porque las invitaciones y membresías deben referenciar una identidad estable,
no una dirección.

### Rol y grupo en la membresía, no en el usuario

La decisión clave de `grupos.md` — *el rol pertenece a la relación usuario–grupo, no al
usuario* — es la que evita el error más común en sistemas multiusuario: tratar el rol como
una propiedad global. En FinApp una misma persona es administradora en un contexto y
miembro en otro; modelarlo en la membresía es lo que hace posible `Permisos` granulares
y correctos.

### Permiso separado del rol

`grupos.md` §10 y `permisos.md` establecen que ser administrador **no** concede acceso a
datos personales. Al separar "rol administrativo" de "permiso sobre información", el
sistema puede denegar por defecto y exigir consentimiento explícito por recurso, lo que
es exactamente lo que exige una app que tocará correos, finanzas y chats.

### Datos financieros y fuentes en sus propias entidades

`datos_usuario.md` §5 prohíbe meter gastos, ingresos o fuentes conectadas como columnas
de la cuenta. La razón es de ciclo de vida: una cuenta existe aunque no tenga movimientos
ni fuentes, y una fuente puede revocarse sin tocar la identidad.

En conjunto, la planificación es coherente con el principio rector de la constitución:
privacidad por defecto, separación de responsabilidades y dependencias hacia abajo.

## 6. Cómo la arquitectura sostiene la visión (n8n, IA, fuentes)

La visión incluye automatización en segundo plano y IA como intérprete
([`ia-n8n.md`](../integraciones/ia-n8n.md)). La arquitectura ya prepara ese camino:

- **Un único punto de entrada a las reglas.** n8n/IA crearán registros llamando al
  `Servicio/`, no insertando en la base de datos. Así las mismas validaciones aplican a
  orígenes automáticos y manuales.
- **Contratos explícitos.** Los DTOs/records definen qué espera y qué devuelve el sistema;
  un flujo de n8n puede consumirlos sin conocer las entidades JPA.
- **Clasificación como dato de dominio.** Prioridad, categoría, estado y relación pueden
  modelarse como entidades propias y evolucionar sin romper la API, gracias a la
  separación entre Modelo y DTO.

Es decir: la arquitectura no solo ordena el código actual, también habilita la parte de
la visión que todavía no está implementada.

## 7. Relación con la documentación de planificación

| Decisión de arquitectura | Respaldo en la planificación |
|---|---|
| Identificador estable de usuario | [`datos_usuario.md`](../planificacion/entidades/datos_usuario.md) §3, §6 |
| Rol en la membresía, no en el usuario | [`grupos.md`](../planificacion/entidades/grupos.md) §2, §3 |
| Permiso independiente del rol | [`grupos.md`](../planificacion/entidades/grupos.md) §10, [`permisos.md`](../seguridad/permisos.md) |
| Datos sensibles fuera de la API | [`datos_usuario.md`](../planificacion/entidades/datos_usuario.md) §5, [`constitution.md`](../constitution.md) §4 |
| Proyecciones + mapper + DTO record | [`doc.md`](../planificacion/entidades/doc.md) |
| Capas con dependencias hacia abajo | [`constitution.md`](../constitution.md) §3, [`rules.md`](../../.agents/rules/rules.md) |

## 8. Qué confirma que el diseño es correcto

El diseño es sólido si se mantienen estas invariantes:

1. Ninguna capa conoce a una capa superior.
2. La API nunca expone entidades ni campos sensibles.
3. El rol y el permiso se evalúan por contexto (grupo/recurso), no globalmente.
4. Los datos financieros y las fuentes tienen entidades y ciclo de vida propios.
5. Un cambio de persistencia o de esquema no obliga a reescribir la lógica de negocio.

## 9. Decisiones pendientes

Este documento justifica el diseño, no lo declara implementado. Siguen abiertas, entre
otras: cómo se materializa el `Mapper/` en el proyecto, la política de migración del
esquema actual, y los puntos listados al final de
[`datos_usuario.md`](../planificacion/entidades/datos_usuario.md) §9 y
[`grupos.md`](../planificacion/entidades/grupos.md) §12.
