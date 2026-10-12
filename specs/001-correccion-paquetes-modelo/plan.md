# Plan — Spec 001: Corrección de paquetes y limpieza mínima del modelo

## Enfoque

Aplicar un cambio acotado al código y a la documentación existentes. Primero se unifica
la identidad del paquete; después se corrigen las clases de modelo y las consultas que
dependen de ellas; finalmente se eliminan los conceptos y DTO retirados y se revisa la
coherencia documental. Las dependencias existentes se conservan: Modelo → Repositorio →
Servicio. No se crean relaciones JPA ni endpoints.

## Áreas y archivos previstos

- `src/main/java/**`: declaraciones de paquete, imports y nombres cualificados JPQL.
- `src/main/java/**/Modelo/`: `MContabilidad`, `MGrupo`, `MBolsillo`, `MGasto`, `MIngreso`,
  `MContacto`, `MUsuario` y retirada de modelos obsoletos.
- `src/main/java/**/Repositorio/`: consulta de contabilidad por grupo, referencias de
  `UsuarioRepository` y retirada de repositorios obsoletos.
- `src/main/java/**/Servicio/`: métodos de contabilidad por grupo y limpieza de métodos
  de usuario obsoletos.
- `src/main/java/**/Dto/`: retirada de los seis DTO de dashboard no definidos y de DTO
  específicos de Ahorro/Otro; conservación de los cinco DTO simples señalados en RF-013.
- `doc/planificacion/entidades/**` y `doc/modelo/modelo-datos.md`: coherencia con la limpieza solicitada.
- `src/test/**`: prueba JUnit `@DataJpaTest` de la consulta de contabilidad por grupo y
  carga del contexto con los mapeos resultantes.

La lista exacta se confirma contra los archivos presentes durante la implementación; no
se deben revertir ni sobrescribir cambios preexistentes ajenos a la spec.

## Decisiones y justificación

- Mantener `idGrupo` e `idUsuario` como columnas simples en las entidades indicadas, sin
  relaciones JPA, porque RF-005–RF-008 lo especifican expresamente.
- Consultar contabilidades por `idGrupo`: `MContabilidad` no tiene `idUsuario` (RF-006).
- Mantener `OTRO` únicamente en el tipo de `MBolsillo`; `OTRO` en categorías de movimiento
  y tipo de contacto se elimina según RF-010–RF-011.
- Eliminar los DTO `UsuarioContactoDTO`, `UsuarioContabilidadDTO`, `UsuarioGastoDTO`,
  `UsuarioIngresoDTO`, `UsuarioAhorroDTO` y `UsuarioOtroDTO`, junto con las consultas y
  métodos productores. Conservar `UsuarioDTO`, `ContactoDTO`, `GastoDTO`, `IngresoDTO` y
  `ContabilidadDTO` sin redefinirlos, como exige RF-012–RF-013.
- Mantener la convención de tabla ya usada por el código (`@Table(name = "<NombreClase>")`)
  donde se creen o modifiquen entidades (RF-009).
- La base de datos aún no está creada, según decisión explícita del usuario. El cambio
  establece el esquema inicial de las entidades descritas y elimina los valores enum
  indicados sin migración ni conversión de registros; no se planifican migraciones.
- Los enums de gasto e ingreso se almacenan como texto. Al crear la base posteriormente,
  el esquema inicial debe reflejar los literales resultantes, sin `OTRO` en esos enums y
  conservando `OTRO` en el tipo de bolsillo.

## Alternativas descartadas

- Crear relaciones JPA o `MGrupoUsuario` en este cambio: la spec las excluye.
- Mover `MGasto`/`MIngreso` a `MBolsillo`, crear Fuente/Correo/Llamada o definir DTO de
  dashboard: pertenecen a trabajo posterior y no son requisitos de esta spec.
- Mantener la búsqueda de contabilidad por usuario: apuntaría a un campo inexistente.
- Añadir dependencias de pruebas o producción: `pom.xml` ya contiene los starters JPA y
  Web MVC de prueba y la spec no requiere nuevas dependencias.

## Validación y pruebas

- Ejecutar `./mvnw test -Dtest=NombreClase` para cada prueba específica añadida o ajustada.
- Añadir y ejecutar una prueba JUnit `@DataJpaTest` para persistir una `MContabilidad`
  sintética y verificar que la consulta por `idGrupo` devuelve el registro esperado; el
  slice también deberá confirmar que el contexto JPA arranca con las entidades y enums
  resultantes (RF-016, CA-09).
- No se prevé `@WebMvcTest`: no se modifica ni implementa la capa Controlador.
- Ejecutar la batería completa `./mvnw test` y luego `./mvnw package`.
- Comprobar además que no queden referencias `com.Cesde.concesionario` bajo `src/`, que
  los DTO retirados no tengan referencias y que la documentación cumpla RF-015.
- Si Java no está disponible, registrar las validaciones Maven como no ejecutadas; no
  declarar la spec cerrada hasta ejecutarlas en un entorno con Java.

## Relación entre cambios y requisitos

| Área de cambio | Requisitos |
| --- | --- |
| Paquetes, imports y JPQL | RF-001, RF-002, RF-003, RF-014 |
| Compilación, suite y empaquetado | RF-004, RF-014 |
| Entidad y consulta de contabilidad | RF-005, RF-006, RF-009, RF-014 |
| Campos de grupo y bolsillo | RF-007, RF-008, RF-009, RF-011, RF-014 |
| Retirada Ahorro/Otro y enums | RF-010, RF-011, RF-014 |
| DTO de dashboard y métodos productores | RF-012, RF-013, RF-014 |
| Documentación | RF-015 |
| Prueba de consulta JPA por grupo | RF-006, RF-016, CA-09 |
