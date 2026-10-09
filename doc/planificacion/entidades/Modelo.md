# Modelo de datos (planificación)

Estructura objetivo del dominio: **Grupo → Contabilidad → Bolsillo → movimientos**.
Las gráficas y saldos se **calculan** (datos dinámicos); no se persisten como entidad.

## Entidades implementadas

| Clase            | Anotación principal               | Tabla         | Relaciones del diseño objetivo                                            |
| ---------------- | --------------------------------- | ------------- | -------------------------------------------------------------------------- |
| Usuario          | @Entity, @Table(name="MUsuario")  | MUsuario      | 1:N Contacto · 1:N Fuente · 1:N Grupo (propietario) · 1:N GrupoUsuario     |
| Contacto         | @Entity, @Table(name="MContacto") | MContacto     | N:1 Grupo · N:1 Usuario                                                    |
| Grupo            | @Entity, @Table(name="MGrupo")    | MGrupo        | N:1 Usuario (propietario) · 1:N GrupoUsuario · 1:N Contabilidad · 1:N Correo |
| Contabilidad     | @Entity, @Table(name="MContabilidad") | MContabilidad | N:1 Grupo · N:1 Contacto · 1:N Bolsillo                                   |
| Bolsillo         | @Entity, @Table(name="MBolsillo") | MBolsillo     | N:1 Contabilidad · 1:N Gasto · 1:N Ingreso · 1:N Llamada · 1:N Correo     |
| Gasto            | @Entity, @Table(name="MGasto")    | MGasto        | N:1 Bolsillo (± N:1 Contabilidad)                                          |
| Ingreso          | @Entity, @Table(name="MIngreso")  | MIngreso      | N:1 Bolsillo (± N:1 Contabilidad)                                          |

## Entidades pendientes de implementar

| Clase            | Anotación principal               | Tabla         | Relaciones del diseño objetivo                                            |
| ---------------- | --------------------------------- | ------------- | -------------------------------------------------------------------------- |
| Fuente           | @Entity, @Table(name="MFuente")   | MFuente       | N:1 Usuario (Gmail, WhatsApp, …) · 1:N Correo                              |
| Correo           | @Entity, @Table(name="MCorreo")   | MCorreo       | N:1 Grupo · N:1 Fuente · N:1 Bolsillo (opcional)                           |
| Llamada          | @Entity, @Table(name="MLlamada")  | MLlamada      | N:1 Bolsillo · N:1 Contacto (opcional)                                     |
| GrupoUsuario     | @Entity, @Table(name="MGrupoUsuario") | MGrupoUsuario | N:1 Grupo · N:1 Usuario (rol ADMIN/MIEMBRO)                            |

## Notas de diseño

- **El bolsillo es un espacio de información**, no solo un número: contiene listas de
  movimientos, conteos, correos y llamadas asociadas. El saldo es **derivado** de sus
  movimientos (dato dinámico para el dashboard), no una columna fija.
- **La contabilidad es el resumen** de la clasificación de los datos obtenidos, dividido
  entre los distintos bolsillos.
- `AHORRO` y `OTRO` son valores del tipo de bolsillo descrito en
  [`MBolsillo.md`](./MBolsillo.md).
- **Cada categoría de movimiento** (ALIMENTACION, TRANSPORTE, SALARIO, …) puede
  materializarse como un bolsillo dentro de la contabilidad.
- **Grupos públicos y privados:** el público tiene membresía con límites y chat de grupo;
  el privado solo por invitación y con transferencia de propiedad.
- **Contacto** pertenece a un grupo y a un usuario; ambos pueden pertenecer al mismo grupo.
- **Correos y contactos** son del usuario; los grupos son zonas para mostrar datos a otros.
- **Fuente** pertenece al usuario (su conexión personal: Gmail, WhatsApp); el grupo
  accede a la información por permiso.
- **Correo** vive en el grupo y puede vincularse opcionalmente a un bolsillo cuando se
  interpreta. La ingesta de Gmail es el primer foco de la aplicación.

## Pendiente de modelar (pasos posteriores)

- **Invitación** (invitación directa de un admin y solicitud pública del usuario).
- **Permiso** (recurso, titular, grupo destinatario, alcance, historial) para compartir
  contactos y fuentes.
- Roles finos / reglas de administración de grupos (ver [`grupos.md`](./grupos.md)).
