# Grupos, membresías e invitaciones

## 1. Propósito

Un grupo permite que varias personas organicen información en un contexto común,
por ejemplo una familia, un hogar, un proyecto, un equipo o un grupo de estudio.
No reemplaza la cuenta individual: cada persona conserva su identidad, sus
preferencias y el control de sus datos personales.

El objetivo es compartir únicamente la información y las tareas que sus integrantes
decidan compartir. Pertenecer a un grupo no concede acceso automático a las finanzas,
correos, contactos, calendarios, chats ni otras fuentes personales de sus miembros.

## 2. Cómo participa un usuario

- Una persona tiene una sola cuenta personal y puede pertenecer a varios grupos.
- Su rol se determina por separado en cada grupo. Puede administrar uno y ser miembro
  en otro.
- Cada grupo constituye un contexto independiente. Una relación, regla, categoría o
  dato de un grupo no debe aparecer en otro grupo sin una acción explícita.
- La cuenta individual y los datos privados del usuario existen aunque abandone todos
  sus grupos.
- El usuario puede cambiar de contexto para consultar su espacio personal o un grupo.
- El usuario puede revisar sus grupos, su rol y los permisos vigentes en cada uno.
- Las acciones sobre recursos compartidos deben indicar a qué grupo pertenecen.

Ejemplo:

```text
Ana
├── Hogar: administradora
├── Universidad: miembro
└── Viaje con amigos: miembro
```

Que Ana sea administradora del Hogar no le da privilegios en Universidad ni acceso
general a la información privada de los demás integrantes del Hogar.

## 3. Conceptos y límites

### Grupo

Contenedor de un contexto compartido. Tiene nombre, tipo opcional, estado, creador,
integrantes, roles y recursos compartidos. El tipo puede ayudar a organizar la
experiencia (familia, hogar, trabajo, estudios, proyecto), pero no debe cambiar por sí
mismo los permisos.

### Membresía

Relación entre una cuenta y un grupo. Registra el rol, estado, fecha de ingreso y
cambios relevantes. La membresía es independiente de las membresías que la persona
tenga en otros grupos.

### Rol

Define qué acciones puede realizar el usuario sobre la administración del grupo.
Como conjunto inicial se proponen:

| Rol | Responsabilidades |
|---|---|
| Propietario | Administra el grupo y sus administradores; puede transferir la propiedad o cerrar el grupo. |
| Administrador | Gestiona información básica, invitaciones y membresías según las reglas del grupo. |
| Miembro | Consulta y participa en los recursos compartidos para los que tiene permiso. |

El creador comienza como propietario. Debe existir al menos un propietario mientras el
grupo esté activo. La transferencia de propiedad debe ser una acción explícita y
confirmada; no ocurre automáticamente al salir un propietario.

### Permiso sobre información

Define qué recurso puede consultar o modificar una persona. Se controla aparte del rol:

- ser administrador no otorga acceso implícito a datos privados de otros usuarios
- los recursos personales se mantienen privados por defecto
- compartir una fuente conectada o un dato personal requiere consentimiento explícito
  de su titular
- el consentimiento debe especificar el recurso, el alcance de acceso y el grupo
  destinatario
- aceptar una invitación al grupo no equivale a aceptar todas las solicitudes de datos
- el usuario puede limitar, rechazar o revocar permisos sin tener que abandonar el grupo

## 4. Requisitos funcionales

### Crear y administrar grupos

1. Un usuario autenticado puede crear un grupo e indicar su nombre; la descripción y
   el tipo son opcionales.
2. El creador queda registrado como propietario.
3. Un administrador puede cambiar los datos básicos permitidos y enviar invitaciones.
4. Solo el propietario puede transferir propiedad o cerrar el grupo.
5. Un grupo debe tener al menos un propietario y no puede quedar activo sin uno.
6. Los cambios de rol, membresía, permisos y estado del grupo deben dejar un historial
   auditable con actor, acción y fecha.

### Administrar integrantes

1. Un integrante puede consultar la lista de miembros, sus roles y los estados visibles
   de invitación.
2. Un administrador puede invitar personas y gestionar invitaciones pendientes.
3. El usuario invitado debe aceptar para convertirse en miembro activo.
4. Una persona ya activa no debe recibir una segunda membresía para el mismo grupo.
5. El usuario puede abandonar el grupo. Si es el único propietario, primero debe
   transferir la propiedad o cerrar el grupo.
6. Un propietario o administrador puede retirar a un miembro conforme a las reglas del
   grupo. La retirada debe revocar sus permisos sobre recursos compartidos del grupo.
7. Retirar una membresía no elimina la cuenta individual ni sus datos privados.

### Compartir información

1. Los recursos del grupo son independientes de los espacios personales.
2. La aplicación debe identificar claramente el grupo al crear o consultar un recurso
   compartido.
3. La información personal no debe copiarse ni exponerse al grupo por inferencia,
   coincidencia de correo o pertenencia a una organización.
4. Antes de conceder acceso a una fuente o dato personal, FinApp debe mostrar qué se
   compartirá, con quién, para qué alcance y durante cuánto tiempo, si aplica.
5. El usuario puede aceptar, limitar, rechazar o revocar cada permiso solicitado.
6. Las decisiones de consentimiento y sus cambios deben quedar en el historial.

## 5. Requisitos de invitación

- Solo un propietario o administrador activo puede invitar integrantes.
- La invitación identifica un grupo, una dirección de correo destinataria, el rol
  propuesto, quién invita, cuándo se creó y cuándo vence.
- El rol inicial propuesto es Miembro. Proponer Administrador requiere que quien invita
  tenga permiso para administrar roles; el destinatario debe poder ver y aceptar el rol
  antes de unirse.
- La invitación debe dirigirse a una identidad verificable. Si la persona aún no tiene
  cuenta, debe verificar que controla el correo invitado antes de aceptar.
- El enlace o código de aceptación debe ser impredecible, de un solo uso, asociado a
  una invitación concreta y revocable. No debe contener datos personales ni permisos
  en texto legible.
- El destinatario debe poder revisar el nombre del grupo, quién lo invita y el rol
  propuesto antes de aceptar.
- La invitación no debe incluir permisos sobre fuentes o datos personales como
  aceptación implícita.
- El administrador puede cancelar una invitación pendiente. La persona invitada puede
  rechazarla.
- Al vencer, cancelar, rechazar o aceptar una invitación, el código deja de ser válido.
- Reenviar una invitación pendiente debe mantener un único registro vigente y actualizar
  su fecha de envío/expiración según la política definida.
- Los errores deben distinguir entre invitación inexistente, vencida, revocada, ya
  respondida o incompatible con una membresía activa.

## 6. Estados y ciclo de vida

### Estado de grupo

```text
Activo → Cerrado
```

Un grupo cerrado deja de aceptar nuevas invitaciones y no permite nuevas operaciones
compartidas. El cierre no implica borrar las cuentas personales de sus integrantes.
La política de conservación o eliminación de los datos del grupo debe definirse antes
de implementar persistencia.

### Estado de membresía

```text
Invitada → Activa → Salió
                   └→ Retirada
```

Los estados Invitada y Activa corresponden al flujo de invitación y membresía. Salió
indica que el usuario dejó el grupo; Retirada indica que un administrador revocó su
membresía. En ambos casos, cesa el acceso al contenido compartido del grupo.

### Estado de invitación

```text
Pendiente → Aceptada
          → Rechazada
          → Cancelada
          → Vencida
```

Los estados terminales no se reabren. Para volver a invitar a una persona se crea una
nueva invitación, con un identificador y código nuevos.

## 7. Flujo para crear un grupo

1. El usuario autenticado elige crear grupo.
2. Ingresa el nombre y, opcionalmente, descripción y tipo.
3. Revisa que el grupo es un contexto compartido y que su información personal no se
   comparte automáticamente.
4. Confirma la creación.
5. FinApp crea el grupo y la membresía de propietario para el creador.
6. El propietario puede invitar personas o configurar los recursos compartidos.

## 8. Flujo para invitar a alguien

1. El propietario o administrador elige el grupo y la acción Invitar.
2. Ingresa el correo de la persona y selecciona el rol propuesto; Miembro es el valor
   inicial.
3. FinApp valida que quien invita tenga autoridad, que el correo sea válido, que el
   grupo esté activo y que la persona no tenga ya una membresía activa ni una
   invitación pendiente equivalente.
4. FinApp registra la invitación como Pendiente, con vencimiento, invitador y rol.
5. Se envía al destinatario una notificación con un enlace de aceptación. La
   notificación no concede acceso por sí sola.
6. El destinatario abre el enlace y se autentica o crea una cuenta.
7. Si la cuenta no coincide con el correo invitado, debe verificar el correo invitado
   antes de continuar.
8. FinApp presenta quién invita, el grupo y el rol propuesto. El destinatario elige
   Aceptar o Rechazar.
9. Si acepta, FinApp valida otra vez que la invitación siga pendiente y vigente y que
   el grupo siga activo. Registra la membresía activa y consume el código.
10. Si rechaza, registra la decisión, invalida el código y notifica al invitador sin
    revelar información adicional.
11. La persona invitada recibe confirmación del resultado. La nueva membresía no
    concede acceso a datos personales que no hayan sido compartidos explícitamente.

## 9. Variantes y excepciones

### El destinatario ya tiene cuenta

Inicia sesión con su cuenta, verifica la identidad destinataria cuando sea necesario
y decide si acepta o rechaza. No se crea una cuenta duplicada.

### El destinatario aún no tiene cuenta

Puede iniciar el registro desde la invitación. La invitación solo se acepta después de
completar el registro y verificar el correo destinatario. Si abandona el registro, la
invitación permanece pendiente hasta su vencimiento o cancelación.

### Correo distinto al de la cuenta autenticada

No se transfiere la invitación a la cuenta iniciada. El destinatario debe verificar el
correo invitado y vincularlo de forma segura, o cerrar sesión y entrar con la cuenta
correspondiente.

### La invitación vence o se cancela

El enlace deja de funcionar y no crea membresía. El invitador puede crear otra
invitación. La pantalla informa que ya no está disponible y sugiere contactar a quien
invitó.

### La persona ya pertenece al grupo

No se crea otra membresía. FinApp informa que ya es integrante y muestra su rol actual.
Un cambio de rol se realiza mediante el flujo de administración de roles, no con una
invitación duplicada.

### El grupo se cierra mientras hay invitaciones pendientes

Las invitaciones pendientes se invalidan y no pueden aceptarse.

### El usuario abandona un grupo

Se confirma la acción y se informa que perderá acceso a los recursos compartidos del
grupo. Los datos personales del usuario permanecen en su cuenta. Si es el único
propietario, debe transferir propiedad o cerrar el grupo antes de salir.

## 10. Reglas de privacidad y seguridad

- Denegar por defecto cualquier acceso que no esté concedido explícitamente.
- Validar permisos en cada operación, no solo al iniciar sesión o aceptar la invitación.
- No considerar el correo conocido, la pertenencia al mismo dominio ni la relación
  familiar como consentimiento.
- No incluir el token de invitación en logs, analíticas ni mensajes de error.
- Limitar el uso del token al grupo, destinatario y acción para la que fue creado;
  invalidarlo al responder, cancelar o vencer.
- Evitar revelar si una dirección de correo tiene una cuenta en FinApp en pantallas
  públicas de invitación.
- Registrar quién creó, envió, aceptó, rechazó o canceló la invitación y cuándo.
- Al revocar una membresía, impedir inmediatamente nuevas lecturas o escrituras sobre
  recursos del grupo, sin ampliar el acceso de otros integrantes.
- Mostrar al usuario sus membresías y permisos vigentes de forma comprensible.

## 11. Criterios de aceptación

1. Un usuario puede pertenecer a varios grupos y tener roles distintos en cada uno.
2. La membresía en un grupo no cambia sus roles ni permisos en otro.
3. Una invitación válida requiere autenticación y control de la identidad destinataria
   antes de convertirse en membresía.
4. Aceptar una invitación crea una sola membresía activa y consume el enlace.
5. Rechazar, cancelar o dejar vencer una invitación impide su aceptación posterior.
6. No se puede aceptar una invitación de un grupo cerrado.
7. Administradores y miembros no obtienen acceso automático a datos personales de
   otros integrantes.
8. Los permisos sobre fuentes y datos pueden aceptarse, limitarse, rechazarse y
   revocarse independientemente de la membresía.
9. Las acciones relevantes de invitación, membresía, roles y permisos quedan
   auditables.
10. Un usuario que sale o es retirado pierde acceso a recursos compartidos del grupo,
    pero conserva su cuenta y sus datos personales.

## 12. Decisiones pendientes antes de implementar

Esta definición es funcional; no afirma que los flujos ya existan en el backend. Antes
de implementar se deben aprobar o precisar:

- duración exacta de una invitación y política de reenvío
- canales de notificación disponibles (correo, notificación interna u otros)
- posibilidad de invitar por enlace compartible, además de por correo
- reglas de aprobación para retirar miembros y cambiar roles
- quién puede ver la lista de integrantes y qué datos de perfil se muestran
- tratamiento, exportación y eliminación de los datos compartidos al cerrar un grupo
- catálogo y granularidad de roles y permisos por tipo de recurso
- reglas específicas para grupos de menores o grupos sujetos a políticas organizativas