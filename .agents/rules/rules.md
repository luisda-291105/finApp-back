# Reglas de arquitectura — finApp-back

Estas reglas desarrollan los principios de [`doc/constitution.md`](../../doc/constitution.md).
Si una regla parece contradecir el código existente, conserva el alcance aprobado y evita
extender el patrón problemático; no hagas migraciones generales como parte de un cambio
ajeno.

## Stack y arquitectura

- Stack vigente: Java 25, Spring Boot 4.1.1 y Maven; consulta `pom.xml` antes de proponer
  comandos o dependencias.
- Arquitectura en capas con dependencias solo hacia abajo:
  Controlador → Servicio → Repositorio → Modelo.
- Mantén las entidades en `Modelo/`, los repositorios en `Repositorio/`, la lógica de
  negocio en `Servicio/`, los endpoints en `Controlador/` y los objetos de transferencia
  en `Dto/`, bajo el paquete base `com.luisda.personalapp.springboot.personal_app`.
- Los DTOs solo se usan en la capa de Controlador; no los propagues a Servicio ni
  Repositorio.
- Las entidades JPA solo viven en Modelo; no las expongas en la API. La entrada y salida
  REST se hace con DTOs.
- Nombra clases con el prefijo de su capa: M (Modelo), S (Servicio), C (Controlador);
  objetos de transferencia con sufijo DTO. Todo en español, coherente con el código
  existente.
- Las clases nuevas deben incluir el header de `.agents/skills/file-header-template/SKILL.md`. Los
  métodos públicos con contrato no evidente llevan Javadoc breve.

## Persistencia y datos

- H2 en desarrollo y MySQL en producción; no hardcodees credenciales ni conexiones en el
  código ni en el repositorio.
- No supongas que una operación persiste datos si no está implementada y verificada con
  pruebas.
- Si un cambio añade persistencia o modifica datos existentes, la spec debe contemplar
  formato, privacidad, compatibilidad/migración y comportamiento ante errores.
- No guardes credenciales, tokens ni datos financieros sensibles en el repositorio, en
  logs ni en `MEMORY.md`.

## API REST

- Los controladores son delgados: validan entrada, delegan en el Servicio y devuelven
  DTOs; la lógica de negocio vive en Servicio.
- Devuelve códigos de estado coherentes y errores claros; no ocultes fallos detrás de
  respuestas que parezcan confirmar una operación real.

## Pruebas

- Usa JUnit con los slices de Spring: `@DataJpaTest` para repositorios y `@WebMvcTest`
  para controladores, siguiendo las dependencias ya declaradas en `pom.xml`.
- Coloca las pruebas en `src/test/` reflejando la estructura de paquetes del código
  probado.
- `./mvnw test` debe pasar para cambios de código antes del commit, y `./mvnw package`
  antes del push.
- Si Java no está instalado en el entorno, reporta la validación como no realizada; no
  la declares aprobada.
