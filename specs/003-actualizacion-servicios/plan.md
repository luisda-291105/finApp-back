# Plan — Spec 003: Actualización de servicios

## Objetivo

Actualizar los cinco servicios existentes y crear `SGrupo` y `SBolsillo`, delegando el
CRUD y las consultas personalizadas vigentes en sus repositorios. Cada llamada de
repositorio deberá quedar en `try-catch`; las excepciones se relanzarán con `throw` como
`IllegalStateException`, con un mensaje claro sobre la operación y la causa original.
Añadir el encabezado Javadoc de seis campos a las clases de servicio incluidas que no
tengan uno conforme.

## Diseño

- Mantener la dirección de dependencias `Servicio → Repositorio → Modelo`; los servicios
  reciben sus repositorios por constructor y trabajan con entidades y parámetros del
  modelo. No introducir DTO ni dependencias nuevas.
- Conservar las operaciones y firmas de los cinco servicios existentes, salvo los ajustes
  necesarios para corresponder a los modelos y métodos disponibles en sus repositorios.
- Implementar `SGrupo` y `SBolsillo` usando el mismo estilo de los servicios actuales:
  operaciones CRUD básicas delegadas al repositorio y consultas `findByIdUsuario`,
  `findByIdContabilidad` y `findByTipo` donde corresponda.
- Mantener el resultado definido por el repositorio, incluidos `Optional.empty()` y las
  listas vacías. No añadir filtros por estado ni otras reglas de negocio.
- En cada operación que invoque un repositorio, capturar los fallos de ejecución en
  `try-catch` y relanzarlos con `throw` como `IllegalStateException`, con un mensaje que
  identifique la operación y la excepción original como causa, de acuerdo con RF-005.
- Añadir encabezados solo donde falten o no cumplan los seis campos de la plantilla. Los
  headers describirán la responsabilidad real, datos, dependencias y consumidores de cada
  clase.

## Archivos de producción

- Modificar `src/main/java/com/luisda/personalapp/springboot/personal_app/Servicio/SUsuario.java`
- Modificar `src/main/java/com/luisda/personalapp/springboot/personal_app/Servicio/SContacto.java`
- Modificar `src/main/java/com/luisda/personalapp/springboot/personal_app/Servicio/SContabilidad.java`
- Modificar `src/main/java/com/luisda/personalapp/springboot/personal_app/Servicio/SGasto.java`
- Modificar `src/main/java/com/luisda/personalapp/springboot/personal_app/Servicio/SIngreso.java`
- Crear `src/main/java/com/luisda/personalapp/springboot/personal_app/Servicio/SGrupo.java`
- Crear `src/main/java/com/luisda/personalapp/springboot/personal_app/Servicio/SBolsillo.java`

## Pruebas

Crear pruebas unitarias JUnit para los siete servicios, con repositorios simulados usando
las dependencias de prueba ya declaradas en `pom.xml`; no añadir dependencias. Por cada
servicio, verificar delegación de CRUD y consultas personalizadas, conservación de
resultados sin coincidencias y envoltura de fallos como `IllegalStateException` con mensaje
de operación y causa original. Los servicios no requieren `@DataJpaTest` porque no se
cambia persistencia ni comportamiento del repositorio; `@WebMvcTest` no aplica porque no
se modifica la capa Controlador.

Archivos de prueba previstos bajo
`src/test/java/com/luisda/personalapp/springboot/personal_app/Servicio/`:

- `SUsuarioTest.java`
- `SContactoTest.java`
- `SContabilidadTest.java`
- `SGastoTest.java`
- `SIngresoTest.java`
- `SGrupoTest.java`
- `SBolsilloTest.java`

## Relación entre tareas y requisitos

Las tareas se agrupan por servicio. Cada una cubre RF-001 y RF-002; las consultas
personalizadas correspondientes cubren RF-003; los resultados sin coincidencias cubren
RF-004; las excepciones cubren RF-005; y los encabezados faltantes cubren RF-006. RF-007
se verifica revisando el diff y confirmando que no se hayan modificado modelos,
repositorios, DTO, controladores ni datos persistidos.

## Validación

Por cada servicio, ejecutar su prueba unitaria focalizada. Al finalizar todas las tareas,
ejecutar `./mvnw test` y `./mvnw package` con el JDK 27 disponible en
`/home/daniel/.jdks/openjdk-27`, configurando `JAVA_HOME`. Revisar el diff para comprobar
que los únicos cambios de código estén en `Servicio/` y en las pruebas de servicio.

## Alternativas descartadas

- **Crear reglas de negocio o filtros adicionales:** descartado porque la spec limita las
  operaciones al CRUD y las consultas que los repositorios ya exponen.
- **Agregar pruebas JPA o web MVC:** descartado porque no se cambia el comportamiento de
  persistencia ni se implementan controladores; las pruebas unitarias enfocan delegación y
  manejo de errores del servicio.
- **Introducir una excepción personalizada o una dependencia nueva:** descartado porque
  la spec establece `IllegalStateException` y el proyecto ya declara dependencias de
  prueba.
