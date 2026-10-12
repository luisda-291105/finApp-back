# Plan — Spec 006: Servicios de bolsillo, fuente y grupo

## Objetivo y alcance

Crear `SBolsillo`, `SFuente` y `SGrupo`, cada uno como una capa delgada sobre el
repositorio existente. Delegar únicamente su CRUD disponible y las consultas aprobadas
para bolsillos y grupos; envolver los fallos de cada llamada al repositorio en
`IllegalStateException` con un mensaje que identifique la operación y conserve la causa.
Añadir el encabezado de seis campos a cada clase nueva.

No se modificarán modelos, repositorios, otros servicios, DTO, controladores ni datos
persistidos. Las clases `SGrupo`, `SBolsillo` y `SFuente` no existen actualmente en
`Servicio/`. `SGrupo` y `SBolsillo` ya se habían previsto en la spec 003, pero aún faltan;
`SFuente` es la incorporación nueva de esta spec. Por tanto, las tres requieren
implementación y prueba, sin reabrir las tareas de la spec 003 ni alterar servicios
existentes.

## Diseño y decisiones

- Mantener la arquitectura `Servicio → Repositorio → Modelo`, inyectar cada repositorio
  por constructor y exponer entidades de modelo y tipos de resultado del repositorio; no
  añadir DTO ni dependencias.
- `SBolsillo` usará `BolsilloRepository.save`, `findById`, `findAll` y `deleteById` para
  crear, consultar por identificador, listar y actualizar/eliminar, además de delegar
  `findByIdContabilidad` y `findByTipo` sin combinar criterios ni filtrar por estado.
  Las operaciones públicas seguirán el vocabulario existente: `adicionarBolsillo`,
  `consultarBolsillo`, `consultarTodosBolsillos`, `actualizarBolsillo`,
  `eliminarBolsillo`, `consultarBolsillosPorContabilidad` y
  `consultarBolsillosPorTipo`.
- `SFuente` delegará el CRUD en los métodos expresos de `FuenteRepository`:
  `adicionarFuente`, `consultarFuente`, `consultarTodasFuentes`,
  `actualizarFuente` y `eliminarFuente`. No ofrecerá consultas personalizadas, pues el
  repositorio no define ninguna. Sus métodos públicos conservarán esos nombres.
- `SGrupo` usará `GrupoRepository.save`, `findById`, `findAll` y `deleteById` para CRUD,
  y `findByIdUsuario` para consultar los grupos por su creador. No interpretará ese
  criterio como membresía. Los métodos públicos serán `adicionarGrupo`,
  `consultarGrupo`, `consultarTodosGrupos`, `actualizarGrupo`, `eliminarGrupo` y
  `consultarGruposPorUsuario`.
- Cada método público que llame al repositorio tendrá su propio `try-catch` de
  `RuntimeException` y relanzará con `throw new IllegalStateException(mensaje, causa)`,
  siguiendo los servicios existentes y el requisito RF-006. El mensaje describirá la
  operación concreta. Esto conserva `Optional.empty()` y las colecciones vacías cuando
  son resultados normales, sin confundirlas con fallos.
- Las pruebas de servicio usarán JUnit 5 y mocks de repositorio con Mockito, ya presente
  en las pruebas de servicio existentes y en las dependencias de prueba declaradas en
  `pom.xml`. Para cada llamada se comprobará la delegación y el resultado conservado,
  incluidos resultados vacíos, y que un fallo produce la excepción, mensaje y causa
  esperados. Se comprobará manejo de error para cada operación que delega al repositorio.
- Los slices se aplican según la capa: las pruebas existentes
  `GrupoBolsilloRepositoryTest` y `FuenteRepositoryTest` usan `@DataJpaTest` y cubren la
  persistencia/consultas de los repositorios que estos servicios consumirán; no se
  cambiarán porque esta spec no modifica repositorios. No corresponde `@WebMvcTest`,
  ya que no hay cambios de controlador. Las nuevas pruebas unitarias de servicio no
  necesitan un slice de Spring.
- Las clases nuevas incorporarán el header Javadoc de seis campos definido en
  `.agents/skills/file-header-template/SKILL.md`. No se modificarán encabezados de otras
  clases.

## Archivos previstos

Producción:

- Crear `src/main/java/com/luisda/personalapp/springboot/personal_app/Servicio/SBolsillo.java`
- Crear `src/main/java/com/luisda/personalapp/springboot/personal_app/Servicio/SFuente.java`
- Crear `src/main/java/com/luisda/personalapp/springboot/personal_app/Servicio/SGrupo.java`

Pruebas:

- Crear `src/test/java/com/luisda/personalapp/springboot/personal_app/Servicio/SBolsilloTest.java`
- Crear `src/test/java/com/luisda/personalapp/springboot/personal_app/Servicio/SFuenteTest.java`
- Crear `src/test/java/com/luisda/personalapp/springboot/personal_app/Servicio/SGrupoTest.java`

Los archivos `BolsilloRepository.java`, `FuenteRepository.java`,
`GrupoRepository.java`, los tests JPA existentes y cualquier cambio preexistente del
árbol se conservan sin modificaciones atribuibles a esta spec.

## Alternativas descartadas

- **Crear o cambiar métodos en los repositorios:** descartado porque el CRUD requerido ya
  está expuesto mediante `JpaRepository` o los métodos existentes de `FuenteRepository`,
  y RF-008 prohíbe cambios de repositorio.
- **Añadir consultas a fuentes o consultas adicionales a bolsillos/grupos:** descartado
  por RF-004 y el alcance cerrado de la spec.
- **Filtrar por estado, propietario, membresía o permisos:** descartado porque no forma
  parte del contrato; la búsqueda de grupo es solo por el identificador de creador
  `idUsuario`.
- **Usar pruebas de integración de servicio, añadir dependencias o crear
  `@WebMvcTest`:** descartado porque Mockito ya está disponible para aislar llamadas al
  repositorio, el comportamiento de persistencia se cubre en los `@DataJpaTest`
  existentes, no cambia ningún controlador y `pom.xml` no requiere cambios.
- **Modificar o reutilizar los servicios existentes:** descartado porque la spec limita
  el trabajo a los tres servicios indicados y excluye otros servicios.

## Tarea cohesiva y relación con requisitos funcionales

La implementación se cerrará como una sola tarea ordenada: completar en secuencia los
ciclos TDD de `SBolsillo`, `SFuente` y `SGrupo` (prueba roja, implementación y prueba
focalizada verde por servicio); después ejecutar en conjunto las pruebas focalizadas y
las pruebas JPA relevantes, la suite completa y el paquete. Ninguno de esos pasos se
considera cierre parcial: si falla una validación, la tarea sigue incompleta.

- **RF-001:** las pruebas y métodos CRUD de los tres servicios comprueban delegación y
  conservación del tipo/valor de resultado.
- **RF-002:** `SGrupoTest` verifica el uso de `findByIdUsuario` y la correspondencia con
  el creador del grupo.
- **RF-003:** `SBolsilloTest` verifica llamadas independientes a `findByIdContabilidad` y
  `findByTipo`, con sus parámetros y resultados preservados.
- **RF-004:** `SFuente` solo expondrá el CRUD del repositorio; su prueba verifica esas
  operaciones y no añadirá búsquedas específicas.
- **RF-005:** las pruebas de consultas comprueban que se propagan sin transformación las
  colecciones vacías y los `Optional.empty()` que devuelva el repositorio.
- **RF-006:** las pruebas de error cubren cada método que delega: tipo
  `IllegalStateException`, texto identificador de operación y causa original.
- **RF-007:** el header de seis campos se incorpora a las tres clases nuevas.
- **RF-008:** se verifica en la revisión final que solo se creen los seis archivos
  indicados (tres servicios y tres pruebas), sin cambios a entidades, repositorios,
  otros servicios, DTO, controladores o datos.

## Validación prevista

Dentro de la tarea única, para cada servicio implementar primero su prueba, confirmar la
fase roja por comportamiento esperado e implementar hasta que el test focalizado pase:

- `JAVA_HOME=/home/daniel/.jdks/openjdk-27 ./mvnw test -Dtest=SBolsilloTest`
- `JAVA_HOME=/home/daniel/.jdks/openjdk-27 ./mvnw test -Dtest=SFuenteTest`
- `JAVA_HOME=/home/daniel/.jdks/openjdk-27 ./mvnw test -Dtest=SGrupoTest`

Al finalizar, correr en conjunto las pruebas relevantes de servicio y persistencia,
después la suite completa y el paquete:

- `JAVA_HOME=/home/daniel/.jdks/openjdk-27 ./mvnw test -Dtest=SBolsilloTest,SFuenteTest,SGrupoTest,GrupoBolsilloRepositoryTest,FuenteRepositoryTest`
- `JAVA_HOME=/home/daniel/.jdks/openjdk-27 ./mvnw test`
- `JAVA_HOME=/home/daniel/.jdks/openjdk-27 ./mvnw package`

La verificación final del diff comprobará RF-008. Esta planificación no ejecuta pruebas
ni modifica código.
