# Plan — Repositorio de fuente

## Alcance

Implementar exclusivamente el acceso CRUD a `MFuente` requerido por la spec 005. Se
añadirá el repositorio y su prueba de integración JPA; no se modificarán la entidad, su
esquema ni otras capas.

## Arquitectura y archivos

- `src/main/java/com/luisda/personalapp/springboot/personal_app/Repositorio/FuenteRepository.java`
  - Interfaz en la capa Repositorio, dentro del paquete existente.
  - Extiende `JpaRepository<MFuente, String>` y sigue la estructura de
    `UsuarioRepository`: `@Repository`, tipo de entidad e identificador.
  - Declara los alias CRUD nominales en español del patrón de `UsuarioRepository`:
    `adicionarFuente(MFuente)`, `eliminarFuente(String)`, `actualizarFuente(MFuente)`,
    `consultarTodasFuentes()` y `consultarFuente(String)`.
  - Cada alias delega al método correspondiente heredado de `JpaRepository`; conserva
    el retorno `MFuente`, `void`, `List<MFuente>`, o `Optional<MFuente>` según el
    contrato, sin consultas personalizadas.
- `src/test/java/com/luisda/personalapp/springboot/personal_app/Repositorio/FuenteRepositoryTest.java`
  - Pruebas JUnit 5 con `@DataJpaTest`, siguiendo el slice y la ubicación de las pruebas
    de repositorios existentes.
  - Comprueba los cinco alias públicos y sus resultados, incluidos colección vacía y
    `Optional.empty()`, así como la consulta completa sin filtrar por usuario o estado.

## Decisiones y alternativas

- **Exponer los alias CRUD nominales del patrón de `UsuarioRepository`.** La petición
  define el CRUD usando ese patrón, que expone los cinco métodos en español. Se
  implementarán como métodos `default` que delegan a `save`, `deleteById`, `save`,
  `findAll` y `findById`, respectivamente. Se mantienen las firmas y tipos compatibles
  con `JpaRepository<MFuente, String>` y no se agregan consultas personalizadas.
- **Aplicar TDD.** Primero se crea la prueba `@DataJpaTest` usando los nombres públicos
  de los alias y se confirma su fallo esperado porque `FuenteRepository` aún no existe;
  después se implementa la interfaz y se repite la prueba focalizada para confirmar el
  verde. De esta forma se valida que las operaciones quedan accesibles con el patrón
  solicitado, además de comprobar su persistencia real.
- **Conservar la forma del repositorio existente.** Se mantiene el paquete, el tipo
  `@Repository` y la interfaz `JpaRepository<..., String>` usados por
  `UsuarioRepository`; la clave coincide con `MFuente.idFuente`.
- **Aplicar `@DataJpaTest`, no `@WebMvcTest`.** El cambio solo incorpora persistencia y
  no crea controladores ni endpoints. `@WebMvcTest` probaría una capa fuera de alcance.
  Las dependencias de prueba JPA ya están declaradas en `pom.xml`; no se requiere añadir
  dependencias.
- **No tocar `MFuente` ni su esquema.** El modelo existente ya define la entidad, sus
  seis campos y el ID `String`. Se reutiliza tal cual, de acuerdo con RF-006.

## Pruebas y trazabilidad de requisitos

Todas las pruebas nuevas estarán en `FuenteRepositoryTest` y usarán el contexto aislado
de `@DataJpaTest`.

| Requisito | Cambio | Verificación prevista |
| --- | --- | --- |
| RF-001 | `adicionarFuente(MFuente)` → `save` | Guardar una fuente nueva; comprobar el valor devuelto y recuperarla por ID con sus datos. |
| RF-002 | `actualizarFuente(MFuente)` → `save` | Guardar una fuente, cambiar datos de esa misma entidad, volver a guardar y comprobar los cambios persistidos. |
| RF-003 | `eliminarFuente(String)` → `deleteById` | Guardar y eliminar una fuente; comprobar que `consultarFuente` ya no la devuelve. |
| RF-004 | `consultarTodasFuentes()` → `findAll` | Verificar que sin filas devuelve colección vacía y que con fuentes de distintos usuarios y estados las devuelve todas. |
| RF-005 | `consultarFuente(String)` → `findById` | Comprobar `Optional` presente para un ID existente y vacío para uno inexistente. |
| RF-006 | Reutilización de `MFuente`, sin cambio de modelo | En las operaciones de escritura, comprobar que los seis campos conservan sus valores, excepto los modificados expresamente en la actualización. |

No se planifican cambios a servicios, controladores, DTOs, endpoints, entidades,
restricciones ni formatos de datos.

## Validación

La única tarea de implementación cubre el ciclo TDD completo y el cierre de validación:
primero crear `FuenteRepositoryTest` y confirmar la fase roja por ausencia de
`FuenteRepository`; luego implementar la interfaz con los cinco alias CRUD y confirmar
la fase verde con `./mvnw test -Dtest=FuenteRepositoryTest`; finalmente ejecutar
`./mvnw test` y `./mvnw package`. La tarea solo se considera completa cuando pasan la
prueba focalizada, la suite y el paquete.
