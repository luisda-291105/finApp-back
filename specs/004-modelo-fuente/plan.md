# Plan — Modelo de fuente (`MFuente`)

## Objetivo y alcance

Implementar únicamente `MFuente` como entidad del modelo, conforme a la spec 004
aprobada. No se crearán `MCorreo`, `MLlamada` ni `MGrupoUsuario`, ni repositorios,
servicios, controladores, DTO o reglas de negocio. La entidad será un registro JPA con
los seis campos especificados; no realizará conexión ni ingestión de fuentes externas.

## Arquitectura y archivos

La entidad pertenece a la capa `Modelo`, bajo el paquete base existente
`com.luisda.personalapp.springboot.personal_app.Modelo`. Seguirá el patrón de entidades
JPA ya usado: `@Entity`, tabla con nombre de clase (`MFuente`), clave primaria escalar y
referencias escalares. No se introducirán dependencias hacia otras capas.

Archivos previstos:

- `src/test/java/com/luisda/personalapp/springboot/personal_app/Modelo/MFuenteTest.java`
  — pruebas JUnit añadidas primero para el modelo y su mapeo JPA.
- `src/main/java/com/luisda/personalapp/springboot/personal_app/Modelo/MFuente.java`
  — nueva entidad y enum `Tipo` con `CORREO`, `CHAT`, `TELEFONO` y `OTRO`, implementada
  después de confirmar el fallo esperado de las pruebas.

No se prevén cambios a otros archivos. La base ya declara JPA, H2 y dependencias de
pruebas JPA en `pom.xml`; no se requieren nuevas dependencias.

## Decisiones de implementación

- **Tabla y clave:** declarar `@Entity` y `@Table(name = "MFuente")`, en línea con
  `MUsuario`, `MGrupo`, `MContacto` y `MBolsillo`. `idFuente` será `String` y la clave
  primaria asignada por quien construya la entidad. No usar `@GeneratedValue`, inicializar
  un UUID automáticamente ni agregar validación de formato no exigida por la spec.
- **Referencia al usuario:** almacenar `idUsuario` como `String` simple. No declarar
  `@ManyToOne`, `@JoinColumn`, clave foránea ni asociación de navegación; se respeta la
  decisión explícita del usuario aunque el documento de diseño previo la describa como
  relación/FK.
- **Tipo:** declarar un enum de entidad con los cuatro valores aprobados y mapearlo con
  `@Enumerated(EnumType.STRING)`, consistente con `MBolsillo` y `MContacto`. No conservar
  `GMAIL` ni `WHATSAPP` ni inventar una transformación entre esos valores y los aprobados.
- **Otros campos:** `identificador` será `String`, `estado` será `boolean` y
  `fechaCreacion` será `LocalDate`, como especifica la spec. No agregar unicidad,
  normalización, reglas de transición ni restricciones de negocio.
- **Columnas:** respetar el patrón de tabla y clave de las entidades actuales. No añadir
  longitudes, nulabilidad u otras restricciones para campos que la spec no define, salvo
  los requisitos inherentes a la clave primaria.
- **Encapsulamiento:** proporcionar constructor sin argumentos requerido por JPA,
  construcción con los datos especificados y acceso a los campos siguiendo el estilo
  JavaBean de los modelos existentes. No generar el identificador dentro de la entidad.
- **Persistencia/ciclo de vida:** la entidad permite que JPA represente y persista los
  campos; no se incorpora una API de persistencia ni lógica de ciclo de vida fuera del
  modelo. El alcance aprobado no especifica migración ni política de compatibilidad de
  esquema, por lo que no se añadirá una.

### Alternativas descartadas

- Generar el UUID automáticamente o anotar generación de clave: contradice que el
  identificador sea proporcionado al construir la entidad.
- Mapear `idUsuario` como asociación JPA o generar una FK: contradice la decisión
  explícita del usuario.
- Guardar el enum por ordinal o mantener los tipos específicos `GMAIL` y `WHATSAPP`:
  contradice el formato textual y los valores generales aprobados.
- Crear un repositorio o CRUD para probar persistencia: excede el alcance; el mapeo se
  verificará directamente en el slice JPA.
- Incluir otras entidades o comportamiento de conexión, ingestión, autorización o negocio:
  no están requeridos por la spec.

## Pruebas y validación

Seguir TDD dentro de una única tarea cohesiva de implementación: primero añadir las pruebas
JUnit focalizadas y ejecutarlas antes de crear la entidad. En esa primera ejecución se
espera un fallo de compilación por la ausencia de `MFuente` (o sus miembros requeridos);
confirmar que corresponde a esa ausencia, no a un defecto de configuración. A continuación,
en la misma tarea, implementar la entidad y repetir la prueba focalizada hasta que pase.
Para cerrar esa tarea, ejecutar además la suite completa y `package`; la tarea no se
considera completa si alguna de esas validaciones falla.

Las pruebas comprobarán que:

- el constructor recibe y conserva el `idFuente` suministrado, sin generación interna;
- los campos y el enum exponen los valores especificados;
- un `@DataJpaTest` puede persistir, limpiar el contexto y recuperar una fuente, verificando
  que el enum se conserva por nombre como texto;
- el guardado de una fuente con un `idUsuario` que no identifica un usuario existente
  funciona, confirmando que no se impone una FK.

`@DataJpaTest` es pertinente porque se agrega una entidad y un mapeo persistente. No se
requiere `@WebMvcTest`: no se modifica la capa Controlador. Dentro del mismo slice de
trabajo, ejecutar primero la prueba focalizada para confirmar el fallo esperado; tras
implementar, repetirla hasta que pase y solo entonces ejecutar la suite y `package`.
Comandos:

1. Antes de implementar: `JAVA_HOME=/home/daniel/.jdks/openjdk-27 ./mvnw test -Dtest=MFuenteTest`
   — confirmar el fallo esperado por ausencia de `MFuente`.
2. Después de implementar: `JAVA_HOME=/home/daniel/.jdks/openjdk-27 ./mvnw test -Dtest=MFuenteTest`
   — confirmar que las pruebas focalizadas pasan.
3. `JAVA_HOME=/home/daniel/.jdks/openjdk-27 ./mvnw test`
4. `JAVA_HOME=/home/daniel/.jdks/openjdk-27 ./mvnw package`

El JDK citado está registrado en `MEMORY.md` como disponible; si ya no estuviera disponible,
reportar las validaciones como no ejecutadas, sin declararlas aprobadas.

## Relación entre requisitos y cambios

| Requisito | Cambio previsto | Verificación |
| --- | --- | --- |
| RF-001 | En una misma tarea TDD, añadir primero aserciones de construcción/acceso y después campo `String idFuente` como clave primaria, recibido por construcción y sin generación automática. | Fallo inicial por ausencia del modelo; prueba focalizada pasa después de implementar, seguida de suite y package aprobados. |
| RF-002 | En una misma tarea TDD, añadir primero prueba de persistencia con `idUsuario` inexistente y después campo escalar `String`, sin asociación ni FK. | Tras confirmar fallo por ausencia de `MFuente`, el caso persiste sin exigir usuario relacionado; suite y package aprobados. |
| RF-003 | En una misma tarea TDD, añadir primero aserciones del enum/round-trip y después enum `Tipo` con valores aprobados y mapeo textual. | Tras confirmar fallo por ausencia de `MFuente`, `@DataJpaTest` verifica persistencia/recuperación textual; suite y package aprobados. |
| RF-004 | En la misma tarea, cubrir primero con pruebas y después implementar campo `String identificador`. | Prueba focalizada, suite y package aprobados. |
| RF-005 | En la misma tarea, cubrir primero con pruebas y después implementar campo `boolean estado`. | Prueba focalizada, suite y package aprobados. |
| RF-006 | En la misma tarea, cubrir primero con pruebas y después implementar campo `LocalDate fechaCreacion`. | Prueba focalizada, suite y package aprobados. |
| RF-007 | Mantener pruebas y código limitados a `MFuente`; no tocar otros modelos ni capas. | Revisión del diff y validaciones focalizada, suite y package aprobados. |

## Gobierno documental y discrepancias

Se consultaron `AGENTS.md`, `doc/constitution.md`, `.agents/rules/rules.md`,
`MEMORY.md` y `pom.xml`; esos documentos están presentes y legibles. No se detectó una
limitación por ausencia de documentos de gobierno. `doc/planificacion/entidades/MFuente.md`
describe los valores específicos `GMAIL`/`WHATSAPP` y una relación/FK a usuario, en
contradicción con la spec aprobada. Prevalecen las decisiones explícitas del usuario
registradas en la spec: enum general persistido como texto e identificador de usuario
escalar, sin relación JPA ni FK. No se editan los documentos de planificación ni `MEMORY.md`.
