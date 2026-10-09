---
name: doc-explicativa
description: Cómo crear documentación explicativa (el qué, el porqué y el cómo) dentro de la carpeta doc/ del proyecto, ubicándola en la subcarpeta correcta y con una plantilla por tipo de documento. Úsala siempre que el usuario pida documentar, explicar, redactar o actualizar documentación de arquitectura, modelo de datos, integraciones, entidades planificadas, producto, proyecto o seguridad, o cuando diga "documenta esto", "explica cómo funciona", "crea el doc de…", aunque no mencione la carpeta doc/.
---

# SKILL: doc-explicativa

Guía para escribir documentación que explique, no que solo liste. Un lector nuevo debe entender qué es una pieza, por qué existe y cómo encaja, sin abrir el código.

## 1. Estructura de `doc/`

```
doc/
├── constitution.md        ← principios y reglas del proyecto (leer primero)
├── arquitectura/
├── integraciones/
├── modelo/
├── planificacion/
│   └── entidades/
├── producto/
├── proyecto/
└── seguridad/
```

Qué va en cada carpeta (deducido de los nombres; ajusta si el proyecto lo usa distinto):

| Carpeta | Contenido |
|---|---|
| `arquitectura/` | Capas, dependencias, decisiones de diseño y sus motivos, flujos entre componentes |
| `integraciones/` | Sistemas externos y comunicación entre apps (ej. back ⇄ web, APIs de terceros): contratos, autenticación, errores |
| `modelo/` | Entidades vigentes: campos, relaciones, reglas e invariantes |
| `planificacion/entidades/` | Entidades planeadas que aún no existen en el código |
| `producto/` | Qué problema resuelve, usuarios, casos de uso, alcance funcional |
| `proyecto/` | Visión general, glosario, cómo ejecutarlo, convenciones, specs y estado |
| `seguridad/` | Amenazas, controles, autenticación/autorización, manejo de datos sensibles |

`constitution.md` es la fuente de verdad de las reglas del proyecto: cualquier documento debe ser coherente con ella y no contradecirla. Si hay conflicto, señálalo al usuario en lugar de resolverlo en silencio.

## 2. Pasos para crear un documento

1. **Leer el contexto.** Abre `constitution.md` y los documentos existentes de la carpeta destino para respetar tono, términos y no duplicar.
2. **Definir audiencia y propósito.** ¿Lo leerá alguien nuevo, quien mantiene el código, o quien evalúa riesgos? Una frase: "Este documento explica X a Y para que pueda Z".
3. **Elegir la carpeta** según la tabla anterior. Si encaja en dos, escribe en una y enlaza desde la otra.
4. **Leer la fuente real** (código, specs, configuración) antes de escribir. No inventes campos, rutas ni comportamientos; si algo no se puede verificar, márcalo como `Pendiente de confirmar`.
5. **Escribir con la plantilla** del tipo de documento (sección 3).
6. **Enlazar** con rutas relativas a documentos relacionados (modelo ⇄ arquitectura ⇄ seguridad).
7. **Revisar con el checklist** (sección 5).

## 3. Plantillas por tipo

Todo documento empieza con:

```markdown
# Título claro

> **Estado:** vigente | borrador | planificado · **Actualizado:** AAAA-MM-DD
> **Resumen:** una o dos frases que explican de qué trata y para quién.
```

### Arquitectura
`Contexto` → `Decisión` → `Por qué (alternativas descartadas)` → `Diagrama` → `Consecuencias y límites` → `Relacionado`.

### Modelo
`Propósito de la entidad` → `Campos` (tabla: nombre, tipo, obligatorio, descripción) → `Relaciones` → `Reglas e invariantes` → `Ejemplo de registro` → `Repositorio/servicio que la usa`.

### Integraciones
`Qué conecta y por qué` → `Contrato` (rutas, formatos, ejemplos de petición/respuesta) → `Autenticación` → `Errores y reintentos` → `Cómo probarla`.

### Planificación de entidades
`Qué es y qué problema resuelve` → `Campos y relaciones previstos` → `Qué falta para implementarla` → `Dependencias`. Marca siempre **Estado: planificado** y no la describas como existente. Al implementarse, muévela a `modelo/`.

### Producto
`Problema` → `Usuarios` → `Casos de uso` (con un ejemplo concreto) → `Fuera de alcance` → `Criterios de éxito`.

### Proyecto
`Visión` → `Glosario` → `Cómo ejecutarlo` → `Convenciones` → `Estado actual y siguientes pasos`.

### Seguridad
`Activos a proteger` → `Amenazas` → `Controles aplicados` (dónde se implementan) → `Riesgos pendientes` → `Qué NO se registra ni expone`.

## 4. Cómo escribir para explicar

- **Explica el porqué**, no solo el qué. Cada decisión lleva su motivo y, si aplica, la alternativa descartada.
- **Primero el panorama, luego el detalle**: resumen arriba, profundidad abajo.
- **Un ejemplo concreto** vale más que un párrafo abstracto (una petición real, un registro de ejemplo).
- **Diagramas con Mermaid** para flujos, capas y relaciones; acompáñalos de texto que los explique.
- **Define cada término** la primera vez que aparece o enlaza al glosario.
- **Español claro**, frases cortas, voz activa. Mismo término para la misma cosa en todo el documento.
- **No dupliques**: si algo ya está documentado, enlázalo.
- **Distingue lo vigente de lo planificado** siempre que hablen de entidades o funciones.
- **Archivos en `kebab-case.md`**, un tema por archivo, con título que coincida con el nombre.
- **Nada sensible**: sin contraseñas, tokens, claves, URLs internas ni datos reales de usuarios.

## 5. Checklist antes de entregar
- [ ] Está en la carpeta correcta y no contradice `constitution.md`.
- [ ] Tiene estado, fecha y resumen al inicio.
- [ ] Todo lo afirmado se verificó en el código o en una spec; lo dudoso está marcado.
- [ ] Explica el porqué de las decisiones clave.
- [ ] Incluye al menos un ejemplo concreto.
- [ ] Los diagramas están explicados en texto.
- [ ] Lo planificado está separado de lo vigente.
- [ ] Enlaces relativos funcionan y no hay contenido duplicado.
- [ ] Sin secretos ni datos sensibles.

## Anti-patrones
- Copiar el código en prosa sin explicar su propósito.
- Documentar entidades planificadas como si ya existieran.
- Documentos enormes que mezclan arquitectura, seguridad y producto.
- Describir comportamientos supuestos sin revisar el código.
- Dejar el documento sin estado ni fecha, de modo que no se sabe si sigue vigente.
- Diagramas sin texto o texto que repite el diagrama sin aportar nada.