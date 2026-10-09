# Permisos y consentimiento

## Principio

El acceso a la información está controlado mediante permisos. Ser administrador de
un grupo no otorga acceso automático a toda la información de una persona: se
requiere una relación válida y el nivel de acceso correspondiente.

## Solicitud de permisos

Un administrador puede registrar o invitar usuarios relacionados y solicitar
permisos sobre recursos:

| Recurso | Solicitud | Decisión posible |
|---|---|---|
| Gmail | Leer correos | Aceptar |
| Contactos | Consultar contactos | Limitar |
| Calendario | Consultar eventos | Rechazar |
| Finanzas | Consultar gastos | Aceptar |
| Chats | Consultar mensajes | Rechazar |

El usuario puede aceptar, rechazar o limitar.

## Historial de permisos

Las decisiones quedan registradas para trazabilidad:

```text
12/10  Gmail solicitado          → Rechazado
20/10  Gmail solicitado de nuevo → Limitado
25/10  Gmail actualizado         → Aceptado
```

## Fuentes de información

Cada usuario controla qué fuentes conecta (email, contactos, calendarios, APIs,
servicios externos, información financiera, chats) y qué puede hacer FinApp con esa
información.
