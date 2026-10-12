# Plan — Spec 002: Consultas de grupos y bolsillos en repositorios

## Enfoque

Ampliar el acceso a datos con dos repositorios asociados a los modelos actuales y con
consultas derivadas sobre sus campos existentes. Se seguirá el patrón de los repositorios
actuales, que extienden `JpaRepository` y usan métodos `findBy...` con resultados en
colecciones. No se modifica la capa Modelo ni se introducen capas consumidoras.

## Archivos previstos

- `src/main/java/com/luisda/personalapp/springboot/personal_app/Repositorio/GrupoRepository.java`
  — búsqueda de grupos por `idUsuario`.
- `src/main/java/com/luisda/personalapp/springboot/personal_app/Repositorio/BolsilloRepository.java`
  — búsquedas de bolsillos por `idContabilidad` y `MBolsillo.Tipo`.
- `src/test/java/com/luisda/personalapp/springboot/personal_app/Repositorio/GrupoBolsilloRepositoryTest.java`
  — pruebas JPA de los tres filtros y sus resultados sin coincidencias.

## Decisiones justificadas

- La búsqueda de grupos usa `MGrupo.idUsuario`, que el modelo documenta como referencia
  al creador y que está representada como campo escalar. No incluye membresías porque no
  existe `MGrupoUsuario` en el alcance aprobado.
- La búsqueda por contabilidad usa `MBolsillo.idContabilidad`, campo escalar existente.
- La búsqueda por tipo recibe `MBolsillo.Tipo`, que contiene los valores vigentes del
  modelo. Las búsquedas de tipo e id de contabilidad son independientes y no aplican
  condición sobre `estado`, tal como fijan RF-002/RF-003 y los casos límite.
- Los métodos devuelven `List` para representar cero o más resultados, siguiendo los
  repositorios existentes (`ContactoRepository`, `ContabilidadRepository`). Los casos sin
  coincidencias se comprueban como listas vacías (RF-004).
- La spec 001 conserva los identificadores de grupo y contabilidad como columnas simples,
  sin relaciones JPA. Aunque la documentación conceptual enumera relaciones, esta
  implementación usa exclusivamente los campos escalares actuales y no las introduce.
- No se cambia el esquema ni los datos. Según la decisión de la spec 001, la base de datos
  aún no está creada; no se requiere migración para añadir métodos de lectura.

## Alternativas descartadas

- Añadir relaciones JPA entre grupo/usuario o bolsillo/contabilidad: contradice el alcance
  aprobado en la spec 001 y no es necesario para filtrar por los identificadores escalares.
- Buscar grupos por membresía, añadir permisos o filtrar por estado: no está definido en
  esta spec ni en los campos de consulta documentados.
- Crear consultas combinadas por `idContabilidad` y tipo: la documentación enumera dos
  consultas separadas y la spec excluye combinaciones.
- Añadir servicios, controladores o endpoints: explícitamente fuera del alcance.
- Incorporar dependencias: `pom.xml` ya declara JPA y el starter de pruebas JPA.

## Pruebas y validación

- Crear pruebas JUnit con `@DataJpaTest` para ambos repositorios. Usar registros
  sintéticos con distintos `idUsuario`, `idContabilidad` y tipos; comprobar que cada
  consulta devuelve todos y solo los registros que coinciden.
- Incluir consultas sin coincidencias para comprobar que devuelven una colección vacía.
- Incluir registros activos e inactivos coincidentes para verificar que los métodos no
  añaden un filtro implícito por estado.
- Ejecutar la prueba específica mediante `./mvnw test -Dtest=GrupoBolsilloRepositoryTest`,
  la suite completa `./mvnw test` y `./mvnw package`.
- No se requiere `@WebMvcTest`, ya que no se modifica la capa Controlador. Si Java no está
  disponible, registrar las validaciones Maven como no ejecutadas, sin declararlas
  aprobadas.

## Relación entre cambios y requisitos

| Cambio | Requisitos |
| --- | --- |
| Consultar grupos por `idUsuario` | RF-001, RF-004, RF-005 |
| Consultar bolsillos por `idContabilidad` | RF-002, RF-004, RF-005 |
| Consultar bolsillos por tipo | RF-003, RF-004, RF-005 |
| Verificar filtros, ausencia de coincidencias y estado | RF-001–RF-005 |
