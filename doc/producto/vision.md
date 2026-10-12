# FinApp — Visión

## Visión

FinApp busca ayudar a una persona a organizar, comprender y gestionar su vida digital
y financiera desde un solo lugar.

La idea parte de un problema cotidiano: ingresos de diferentes fuentes, compras con
distintos medios, deudas, pagos recurrentes, suscripciones, estudios, entretenimiento
y conversaciones, todo distribuido entre correos, chats, cuentas, aplicaciones y
servicios.

FinApp busca centralizar esa información, organizarla y convertirla en datos útiles
para que la persona entienda mejor su propia vida y tome el control de ella.

La aplicación comienza con finanzas personales, pero su visión es más amplia: una
plataforma que organice información relacionada con una persona, sus relaciones y
los grupos a los que pertenece.

## Problema

Una persona puede recibir dinero de varias fuentes, tener salarios e ingresos
adicionales, pagar con tarjeta y efectivo, acumular deudas y pagos recurrentes,
olvidar vencimientos, mantener múltiples suscripciones, recibir cientos de correos
relevantes y no tener visión centralizada de sus actividades.

El problema no es solo registrar información: la información existe, pero está
dispersa, desorganizada y requiere demasiado esfuerzo humano para volverse útil.

## Objetivo

Reducir ese esfuerzo mediante: recopilación, clasificación automática, organización
contextual, priorización, automatización, visualización, personalización, control
mediante permisos, uso de IA e integración progresiva con diferentes fuentes.

## Concepto central

FinApp transforma información desordenada en información estructurada:

```text
Información → Recopilación → Validación → Interpretación → Clasificación
→ Priorización → Registro → Automatización → Presentación
```

La información puede provenir de correos, chats, contactos, servicios externos,
APIs, datos financieros, suscripciones, compras, ingresos, gastos, estudios,
entretenimiento y otras fuentes futuras.

## Finanzas personales

Las finanzas son el punto de partida: ingresos, gastos, deudas, pagos fijos, pagos
automáticos, suscripciones, compras con tarjeta y efectivo, diferentes fuentes de
ingreso, próximos pagos, categorías e historial. Debería visualizarse con gráficos
y tablas.

### Ejemplo de interpretación

Mensaje: "Hoy me gasté $20.000 en un helado de ron con pasas en efectivo."

| Campo | Información |
|---|---|
| Tipo | Gasto |
| Valor | $20.000 |
| Método | Efectivo |
| Concepto | Helado de ron con pasas |
| Categoría | Alimentación / Ocio |
| Fecha | Hoy |
| Origen | Chat |
| Estado | Registrado |

La IA puede extraer estos elementos del contenido.

## Suscripciones y validación de correos

FinApp busca detectar servicios y suscripciones a partir de correos: detectar
servicio → identificar pago → registrar suscripción → categorizar → determinar
próximo pago → asignar prioridad.

Los correos de cobro se contrastan con el contexto: remitente → servicio → buscar
registro relacionado → comprobar suscripción/pago → clasificar. Si no hay
información suficiente, se clasifican como "Desconocido / Requiere revisión", no
como fraude automáticamente.

## Clasificación y conocimiento

La clasificación es fundamental y puede basarse en tipo, categoría, persona, grupo,
organización, servicio, prioridad, fuente, contexto, estado y relación, usando
reglas, contexto, datos del usuario, automatizaciones e IA. No depende solo del
texto del mensaje: también usa remitente, contactos, historial, servicios,
suscripciones, relaciones, grupos, permisos y contexto. El usuario puede corregir
clasificaciones y esas correcciones se convierten en preferencias futuras.

## Usuario, grupos y organizaciones

La entidad principal es el usuario, que puede pertenecer a múltiples grupos
(familia, amigos, trabajo, universidad, proyectos). Un grupo reúne usuarios con
roles, permisos, información compartida, reglas y automatizaciones. Un administrador
es un usuario con privilegios dentro de un grupo, no una entidad aparte: el mismo
usuario puede ser admin en un contexto y miembro en otro. Una organización es un
grupo organizado (empresas, instituciones, equipos, comunidades).

## Permisos y consentimiento

El acceso se controla con permisos: un administrador puede solicitar acceso a
recursos (Gmail, contactos, calendario, finanzas, chats) y el usuario decide:
aceptar, rechazar o limitar. Ser administrador no otorga acceso automático. Las
decisiones quedan registradas en un historial de permisos para trazabilidad.

## Automatización

FinApp busca funcionar parcialmente en segundo plano: detectar, clasificar,
registrar, actualizar, crear registros, detectar próximos pagos, organizar correos
y ejecutar automatizaciones con filtros personalizados.

## Visión de producto

FinApp no busca limitarse a registrar gastos. Busca recopilar, comprender,
organizar, clasificar, priorizar y presentar información relacionada con una
persona para ayudarle a gestionar su vida: finanzas, relaciones y actividades en
un solo lugar, sin controlar su vida, sino dándole una visión centralizada y
organizada de la información que decide conectar.

## Documentación derivada

- [Arquitectura](../arquitectura/arquitectura.md)
- [Permisos y consentimiento](../seguridad/permisos.md)
- [Modelo de datos](../modelo/modelo-datos.md)
- [IA y n8n](../integraciones/ia-n8n.md)
- [Roadmap](roadmap.md)
