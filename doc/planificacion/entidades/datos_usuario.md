# Datos necesarios para el usuario

## 1. Propósito

Definir qué información pertenece a la cuenta individual de una persona en FinApp y
separarla de los datos financieros, las membresías de grupo, los permisos y las
fuentes conectadas.

Este documento es una propuesta funcional para planificar la entidad. No implica que
el esquema o los flujos de autenticación ya estén implementados.

## 2. Principios

- Solicitar solo los datos necesarios para crear y proteger la cuenta.
- Mantener los datos personales privados por defecto.
- No convertir el rol de grupo en una propiedad global del usuario.
- No almacenar contraseñas en texto plano.
- No exponer credenciales ni datos de seguridad en respuestas de la API.
- Mantener los datos financieros y las fuentes conectadas en sus entidades y flujos
  correspondientes, no como columnas de la cuenta.
- Permitir que una cuenta exista independientemente de su pertenencia a grupos.

## 3. Datos mínimos de la cuenta

| Dato | Requerido | Uso | Regla propuesta |
|---|---:|---|---|
| Identificador | Sí | Identificar la cuenta de manera estable en el sistema. | Generado por el sistema; no usar el correo como clave primaria. |
| Nombre visible | Sí | Mostrar quién es el usuario en la experiencia personal y en contextos compartidos. | Texto no vacío, con longitud máxima definida por validación. |
| Correo electrónico | Sí, si se usa para autenticación e invitaciones | Inicio/verificación de cuenta y recepción de invitaciones. | Normalizar para comparación, verificar control del buzón y aplicar unicidad. |
| Credencial de autenticación | Sí, si FinApp administra el acceso | Autenticar a la persona. | Guardar únicamente un hash seguro; nunca guardar ni devolver la contraseña original. |
| Fecha de creación | Sí | Trazabilidad de cuándo se creó la cuenta. | Asignada por el sistema, no por el usuario. |
| Estado de cuenta | Recomendado | Distinguir cuenta pendiente de verificación, activa o deshabilitada. | Transiciones controladas por el sistema y auditables. |

El correo se considera la dirección principal para identificar invitaciones solo si esa
decisión se mantiene en el flujo de grupos. Si FinApp permite proveedores externos de
autenticación, las identidades externas deben modelarse aparte; no se debe asumir que
el correo siempre será el nombre de usuario.

## 4. Datos opcionales del perfil

Estos datos no son necesarios para crear la cuenta y solo deben solicitarse cuando
habiliten una función concreta:

| Dato | Uso posible | Consideración |
|---|---|---|
| Foto de perfil | Personalizar la cuenta e identificar miembros visualmente. | Opcional; almacenar referencia segura, no exigirla. |
| Preferencia de idioma | Mostrar textos y notificaciones en el idioma elegido. | Puede tener un valor inicial y ser editable. |
| Zona horaria | Presentar fechas, vencimientos y recordatorios localmente. | Relevante cuando haya eventos y notificaciones programadas. |
| Moneda preferida | Mostrar importes en una moneda de referencia. | No reemplaza la moneda original de cada operación financiera. |
| Preferencias de notificación | Controlar avisos por tipo o canal. | No equivale al consentimiento para compartir información. |

Fecha de nacimiento, género, dirección física, teléfono, documento de identidad y
datos bancarios no se consideran requisitos de la cuenta con la información disponible.
No deben recopilarse hasta que exista una necesidad funcional y una decisión de
privacidad que lo justifiquen.

## 5. Información que no pertenece directamente a la cuenta

| Información | Lugar conceptual | Motivo |
|---|---|---|
| Rol, estado de membresía y grupo | Membresía entre usuario y grupo | Una misma persona puede tener roles distintos en grupos diferentes. |
| Solicitudes y decisiones de acceso | Permiso/consentimiento y su historial | El permiso se otorga sobre un recurso y un contexto concretos, no globalmente. |
| Gastos, ingresos, deudas y suscripciones | Entidades financieras correspondientes | Son información de dominio, sensible y con ciclo de vida propio. |
| Contactos de la persona | Entidad de contactos | No confundir los contactos personales con los datos para iniciar sesión. |
| Cuentas de correo, calendarios u otros servicios conectados | Fuente/conexión de usuario | Cada conexión requiere autorización, estado y ciclo de vida propios. |
| Preferencias de clasificación y correcciones de IA | Preferencias o historial de clasificación | Pueden cambiar independientemente de los datos básicos de identidad. |
| Roles globales de administración | No asumirlos en el usuario | Ser administrador de un grupo no concede autoridad en otros grupos. |

Consultar [grupos.md](./grupos.md) para el comportamiento de membresías, roles,
invitaciones y consentimiento en los grupos.

## 6. Requisitos de validación y ciclo de vida

1. El identificador de usuario no cambia cuando se actualiza el nombre o el correo.
2. El correo debe verificarse antes de utilizarse para aceptar una invitación o como
   canal de recuperación de cuenta.
3. La unicidad del correo debe tratarse de forma consistente, incluyendo normalización
   para evitar duplicados por diferencias de mayúsculas o espacios.
4. Cambiar el correo requiere verificar el nuevo correo y conservar trazabilidad del
   cambio; no debe transferir invitaciones pendientes sin validar la identidad.
5. El nombre visible puede cambiar sin modificar la identidad o la membresía.
6. La credencial debe procesarse mediante el mecanismo de autenticación aprobado. No
   registrar contraseñas, hashes ni tokens en logs o respuestas.
7. Deshabilitar una cuenta debe impedir autenticaciones nuevas y debe definir qué pasa
   con sus membresías, permisos y datos antes de implementarse.
8. La eliminación de una cuenta requiere una política explícita de conservación,
   anonimización o eliminación de los datos relacionados.
9. Las operaciones sobre la cuenta deben protegerse mediante autenticación y
   autorización; un usuario solo puede modificar los datos de cuenta permitidos para
   su propia identidad.

## 7. Comparación con la entidad actual

La entidad `MUsuario` existente contiene actualmente:

| Campo existente | Evaluación para esta propuesta |
|---|---|
| `idUsuario` | Corresponde al identificador estable. |
| `nombre` | Corresponde al nombre visible. |
| `contacto` | Su propósito y formato deben aclararse. Si representa el correo de acceso e invitación, conviene documentarlo como correo verificado; si representa otro canal, debe separarse del correo de cuenta. |
| `contrasena` | Es información de autenticación sensible. Verificar que el valor persistido sea un hash seguro y que nunca se exponga; no migrar ni asumir un mecanismo sin revisar autenticación existente. |
| `propiedad` | El significado no está definido en la documentación funcional. No conservarlo ni reemplazarlo hasta determinar su uso y datos existentes. |
| `fechaCreacion` | Corresponde a la fecha de creación asignada por el sistema. |

Esta comparación no propone modificar el esquema existente. Cualquier cambio de nombres,
tipos, restricciones o datos persistidos requiere revisar compatibilidad y definir una
migración antes de implementarlo.

## 8. Propuesta mínima para el primer registro

Si FinApp administra autenticación propia, el registro inicial solo debería pedir:

1. Nombre visible.
2. Correo electrónico.
3. Contraseña, procesada y almacenada mediante un mecanismo seguro.
4. Aceptación de los términos y aviso de privacidad aplicables.

El sistema genera el identificador, la fecha de creación y el estado inicial de
verificación. La persona completa la verificación del correo para activar las funciones
que dependen de una identidad confirmada. La zona horaria, idioma, moneda y demás
preferencias se pueden configurar posteriormente.

Si se elige autenticación externa, no se debe pedir una contraseña local salvo que
exista un requisito separado. Debe definirse primero cómo se vincula la identidad del
proveedor con la cuenta de FinApp.

## 9. Decisiones pendientes antes de implementación

- Confirmar si `contacto` representa un correo electrónico, un teléfono u otro dato.
- Confirmar si FinApp manejará contraseñas propias o autenticación mediante proveedor.
- Definir los estados de cuenta y las reglas de verificación y recuperación.
- Definir normalización, cambio y unicidad del correo.
- Aclarar el significado y destino de `propiedad`.
- Aprobar si idioma, zona horaria, moneda y preferencias de notificación se incluyen
  desde la primera versión.
- Definir el tratamiento de la cuenta y de los datos relacionados cuando se deshabilite
  o elimine.
