# Modelo de datos (conceptual)

Estructura vigente: **Grupo → Contabilidad → Bolsillo → movimientos**. Los saldos y las
gráficas se **calculan** (datos dinámicos); no se persisten como entidad.

## Entidades principales

- **Usuario** — persona real; tiene un grupo por defecto y puede pertenecer a varios
  grupos.
- **Contacto** — persona de contacto; pertenece a un grupo y a un usuario.
- **Grupo** — espacio que agrupa usuarios, contabilidad, correos e información
  compartida. Las gráficas se calculan aquí. Puede ser **público** (membresía con
  límites, chat de grupo) o **privado** (solo invitación, transferencia de propiedad).
- **GrupoUsuario** — membresía de usuarios distintos al propietario, con rol
  (ADMIN/MIEMBRO) y estado.
- **Contabilidad** — resumen de la clasificación de datos, dividido entre bolsillos.
  Pertenece a un grupo.
- **Bolsillo** — espacio de información (listas, conteos, correos, llamadas); su saldo es
  derivado. `AHORRO` y `OTRO` son valores del tipo de bolsillo.
- **Gasto / Ingreso** — movimientos; la categoría se representa mediante el bolsillo.
- **Fuente** — conexión del usuario con un servicio externo (Gmail, WhatsApp).
- **Correo** — correo recibido e interpretado; vive en el grupo y puede vincularse
  opcionalmente a un bolsillo. Tiene nivel de registro (principal, secundario, estudio,
  trabajo).
- **Llamada** — registro de llamada asociada a un bolsillo (quién, desde dónde, cuántas
  veces).

## Pendiente de modelar

- Invitación (invitación directa y solicitud pública).
- Permiso (recurso, titular, grupo destinatario, alcance, historial).

## Notas de planificación

Para detalles específicos de cada entidad ver:

- [`doc/planificacion/entidades/Modelo.md`](../planificacion/entidades/Modelo.md)
- [`doc/planificacion/entidades/datos_usuario.md`](../planificacion/entidades/datos_usuario.md)
- [`doc/planificacion/entidades/grupos.md`](../planificacion/entidades/grupos.md)

Principios: datos personales privados por defecto, no convertir el rol de grupo en
propiedad global del usuario, no almacenar credenciales en texto plano, y mantener la
información y las fuentes en sus entidades correspondientes.
