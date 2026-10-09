---
name: spring-rest
description: Convenciones, pasos de construcción y requisitos para crear la capa Controlador de finApp-back (Spring Boot) siguiendo el flujo de specs (spec, plan y tasks) con subagentes, con arquitectura limpia y diseño escalable y seguro. Úsala siempre que el usuario pida crear, modificar o revisar un controlador, un endpoint o la seguridad de la API en finApp-back, o cuando se redacte o ejecute la spec, el plan o las tareas de un controlador, aunque no mencione "REST" explícitamente.
---

# SKILL: spring-rest

Guía para construir la capa Controlador de finApp-back de forma consistente, limpia, escalable y segura.

## Estado
- Capa Controlador pendiente de implementar. Los Servicios (`SUsuario`, `SContacto`, `SContabilidad`, `SGasto`, `SIngreso`, `SGrupo`, `SBolsillo`) se definen en la Spec 003 y son la única dependencia permitida de un controlador.
- **DTOs aún no definidos.** Solo está definido su destino: `Dto/`. Su contenido (campos, cómo se reciben o generan los ids, validaciones) se define en la spec de cada recurso. No inventarlos.
- **Errores HTTP aún no definidos.** Códigos, formato y manejador de errores se definirán en una spec. Esta skill no los fija.
- **La web aún no está creada.** No hay pruebas de controlador por ahora ni dependencias de ella.

## 1. Flujo de trabajo con specs y subagentes
Todo controlador se construye a partir de una spec. Cada spec vive en su propia carpeta:

```
specs/
└── 001-nombre-de-la-spec/
    ├── spec.md     ← qué se hace y por qué
    ├── plan.md     ← cómo se hace
    └── tasks.md    ← tareas para los subagentes
```

- **Nomenclatura:** número de tres dígitos consecutivo + nombre en `kebab-case` (`004-controlador-gasto`). Una spec por unidad de entrega (un recurso o un controlador).
- **spec.md:** sigue el formato de las specs existentes: estado, propósito, alcance (dentro/fuera), decisiones, casos límite y requisitos EARS (`RF-NNN`). Incluye un requisito de "sin cambios fuera del alcance".
- **plan.md:** decisiones técnicas apoyadas en esta skill (capas, rutas, seguridad) y lista de archivos a crear o modificar.
- **tasks.md:** tareas pequeñas, ordenadas por dependencia. Cada tarea indica los archivos que toca y su criterio de terminado, de modo que un subagente la ejecute sin contexto adicional.
- **Reglas para el subagente:**
  - Ejecuta solo la tarea asignada y no toca archivos fuera de su alcance.
  - Lo que la spec no define (DTOs, errores HTTP) no se inventa: se deja como `Pendiente` y se informa.
  - Al terminar, marca la tarea como completada en `tasks.md`.

## 2. Arquitectura y dirección de dependencias

```
Controlador  →  Servicio  →  Repositorio  →  Modelo (entidad)
     ↕ Dto (solo en el borde HTTP)
```

- Las dependencias van hacia adentro. Una capa interna nunca importa una externa (el Servicio no conoce `ResponseEntity`, ni DTOs HTTP, ni anotaciones web).
- El Controlador depende del Servicio, jamás del Repositorio ni de `EntityManager`.
- Las entidades de `Modelo/` no salen hacia el cliente: el controlador trabaja con DTOs de `Dto/`.
- Inyección por constructor (campos `final`); prohibido `@Autowired` en campos.
- Una clase, una responsabilidad: si un controlador crece de forma notable, divídelo por subrecurso.

## 3. Reglas de la capa Controlador
- Ubicación `Controlador/`, prefijo `C` (ej. `CGasto`), anotación `@RestController`.
- Entrada/salida solo con DTOs de `Dto/`; nunca exponer entidades de `Modelo/`. La estructura de cada DTO la fija la spec.
- Controlador delgado: valida la entrada y delega en el Servicio. Sin reglas de negocio, sin acceso a datos.
- Rutas por recurso, en español, en plural y consistentes con el dominio (`/api/v1/gastos`, `/api/v1/contabilidades/{id}/gastos`). Sin verbos en la ruta.
- Verbos HTTP semánticos: `GET` consulta, `POST` crea, `PUT` reemplaza, `PATCH` modifica parcialmente, `DELETE` elimina.
- Códigos de éxito:

| Caso | Código |
|---|---|
| Consulta o actualización correcta | 200 |
| Creación (con cabecera `Location`) | 201 |
| Borrado sin cuerpo | 204 |

Los códigos de error no se definen aquí (ver sección 5).

## 4. Pasos de construcción de un endpoint
1. **Abrir la spec** (`specs/NNN-nombre/`) y leer `spec.md`, `plan.md` y `tasks.md`. Si no existe, crearla primero.
2. **Confirmar el contrato del Servicio.** Revisa qué operaciones expone (CRUD y consultas de la Spec 003). No inventes operaciones que no existan.
3. **Ubicar los DTOs.** Usa los que la spec defina en `Dto/`. Si aún no están definidos, detente y regístralo como pendiente en lugar de crearlos por tu cuenta.
4. **Escribir el controlador** con constructor, rutas y códigos de éxito de la sección 3.
5. **Aplicar seguridad** (sección 6): autenticación, autorización por recurso, límites de entrada.
6. **Documentar** el endpoint: Javadoc de cabecera según la plantilla del proyecto y, si procede, documentación explicativa en `doc/` (skill `doc-explicativa`).
7. **Cerrar la tarea** marcándola en `tasks.md`.

## 5. Uso de excepciones
**Principio:** una excepción se lanza donde ocurre el fallo y se traduce a HTTP en un solo lugar.

- **Servicio** (Spec 003): captura el fallo del repositorio y relanza `IllegalStateException` con un mensaje claro de la operación y la causa original. No traga excepciones ni devuelve `null` para ocultar errores.
- **Resultados vacíos:** el servicio conserva `Optional.empty()` o la colección vacía del repositorio. Cómo responde el controlador en ese caso no está definido y se decide en la spec.
- **Controlador:** no usa `try-catch` para formatear respuestas; deja propagar las excepciones.
- **Errores HTTP: pendiente de definir.** No crear un manejador global, códigos ni formato de error sin una spec que lo defina. Cuando se defina, debe ser un único lugar y un único formato para todos los errores.
- **Nunca** exponer al cliente el stack trace, el mensaje de una excepción interna, nombres de tablas ni consultas SQL. Registrarlos en el log con su causa.
- Excepciones propias (si una spec las define): heredan de `RuntimeException`, llevan mensaje y causa, y representan una condición de negocio, no un detalle técnico.

## 6. Seguridad
- **Autenticación y autorización** con Spring Security. Todo endpoint es privado por defecto; las rutas públicas se declaran de forma explícita y mínima.
- **Autorización por recurso**: verifica que el usuario autenticado es dueño o miembro del recurso (contabilidad, grupo, bolsillo). Evita IDOR: nunca confíes solo en el `id` de la URL.
- **Validación en el borde**: todo dato de entrada es no confiable. Valida tipo, longitud, rango y formato.
- **Mass assignment**: acepta solo los campos del DTO; nunca enlaces directamente el cuerpo a una entidad.
- **Datos sensibles**: no expongas contraseñas, hashes, tokens ni datos financieros más allá de lo necesario; no los escribas en logs. Contraseñas con `BCryptPasswordEncoder` (o Argon2).
- **Consultas seguras**: solo consultas parametrizadas / Spring Data; nada de concatenar SQL con entrada del usuario.
- **CORS** restringido a los orígenes que se definan cuando exista la web; nunca `*` con credenciales. HTTPS en producción.
- **Cabeceras de seguridad** (`X-Content-Type-Options`, `Strict-Transport-Security`, `Content-Security-Policy` donde aplique) y CSRF según el tipo de autenticación.
- **Secretos** (claves, credenciales de BD) por variables de entorno o gestor de secretos, jamás en el repositorio.
- **Límite de peticiones** (rate limiting) en login y endpoints costosos.
- **Auditoría**: registra quién hizo qué en operaciones financieras (crear/editar/eliminar gastos e ingresos).

## 7. Diseño escalable
- **API sin estado**: nada de sesiones en memoria del servidor; la identidad viaja en el token.
- **Versionado** de la API desde el inicio (`/api/v1/…`).
- **Paginación** en toda colección, con tope de tamaño. Ordenamiento solo por campos permitidos.
- **Evita N+1** y cargas completas innecesarias.
- **Idempotencia**: `PUT` y `DELETE` idempotentes; para `POST` sensibles considera una clave de idempotencia.
- **Transacciones** en el Servicio (`@Transactional`), no en el controlador.
- **Contratos estables**: añade campos opcionales en lugar de cambiar o eliminar los existentes; los cambios incompatibles van en una nueva versión.
- **Observabilidad**: logs estructurados, métricas (Actuator) y health checks.
- **Configuración externalizada** por perfil (`dev`, `prod`), sin valores fijos en el código.

## 8. Principios de diseño
- **SOLID**: responsabilidad única por clase; dependencia de abstracciones solo cuando aporta (más de una implementación o necesidad real de sustituir).
- **DRY** en lo que importa, sin abstraer de forma prematura.
- **Fail fast**: valida al entrar y falla con un mensaje claro.
- **Mínimo privilegio** en permisos, CORS y datos expuestos.
- **Consistencia sobre ingenio**: sigue las convenciones existentes del proyecto antes de introducir un patrón nuevo.

## 9. Checklist antes de dar una tarea por terminada
- [ ] Existe la carpeta `specs/NNN-nombre/` con `spec.md`, `plan.md` y `tasks.md`, y la tarea está marcada.
- [ ] Controlador en `Controlador/`, prefijo `C`, `@RestController`, inyección por constructor.
- [ ] Solo DTOs de `Dto/` en la firma; ninguna entidad expuesta; ningún DTO inventado fuera de la spec.
- [ ] Solo depende del Servicio; sin lógica de negocio.
- [ ] Códigos de éxito correctos y `Location` en 201.
- [ ] Sin manejo de errores HTTP inventado (queda pendiente de su spec).
- [ ] Autenticación y autorización por recurso verificadas.
- [ ] Colecciones paginadas con tope de tamaño.
- [ ] Ruta versionada, en español y consistente.
- [ ] Sin cambios fuera del alcance de la spec.
- [ ] Documentación del endpoint.

## Anti-patrones (no hacer)
- Inventar DTOs, códigos de error o un manejador de errores que la spec no define.
- Devolver entidades JPA o `Map<String, Object>` desde un controlador.
- `catch (Exception e) { return null; }` o `e.printStackTrace()`.
- Lógica de negocio, cálculos o consultas dentro del controlador.
- Llamar a repositorios desde el controlador.
- Mostrar `e.getMessage()` de una excepción interna al cliente.
- Endpoints que devuelven colecciones completas sin paginar.
- Confiar en que el cliente envía el `usuarioId` correcto en lugar de tomarlo del token.
- Que un subagente modifique archivos fuera del alcance de su tarea.