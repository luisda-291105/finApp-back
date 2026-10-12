# Tareas — Spec 001: Corrección de paquetes y limpieza mínima del modelo

## 1. ✅ Unificar la identidad de paquetes

**RF:** RF-001, RF-002, RF-003, RF-014

- Actualizar declaraciones `package`, imports internos y nombres cualificados JPQL bajo
  `src/` a `com.luisda.personalapp.springboot.personal_app`, respetando cada subcapa.

**Hecho cuando:** no quedan declaraciones, imports ni referencias JPQL al paquete ajeno y
los imports internos corresponden al paquete base del proyecto.

## 2. ✅ Crear y conectar el modelo de contabilidad

**RF:** RF-005, RF-006, RF-009, RF-014

- Incorporar los campos definidos para `MContabilidad` sin relaciones JPA.
- Cambiar repositorio y servicio de consulta por usuario a consulta por grupo.

**Hecho cuando:** la entidad tiene los campos especificados, la ruta de consulta usa
`idGrupo` y no quedan referencias a un campo inexistente.

Implementación y validación completadas. `ContabilidadRepositoryTest`, la suite y
`package` pasaron con `JAVA_HOME=/home/daniel/.jdks/openjdk-27`.

## 3. ✅ Completar grupo y bolsillo con sus campos mínimos

**RF:** RF-007, RF-008, RF-009, RF-011, RF-014

- Añadir a `MGrupo` y `MBolsillo` únicamente los campos indicados en la spec y la
  documentación de diseño; mantener las referencias como columnas simples.
- Conservar `OTRO` como tipo de bolsillo.

**Hecho cuando:** ambas entidades contienen los campos requeridos, no incorporan
relaciones JPA y respetan la convención de tabla establecida.

Implementación completada y validada por la prueba JPA, la suite completa y `package`.

## 4. ✅ Retirar Ahorro/Otro como entidades independientes y limpiar enums

**RF:** RF-010, RF-011, RF-014

- Eliminar modelos, repositorios, servicios, DTO o métodos que representen Ahorro/Otro
  como conceptos independientes.
- Quitar `OTRO` de las categorías de `MGasto`/`MIngreso` y del tipo de `MContacto`.

**Hecho cuando:** no quedan esas referencias en `src/`, los enums indicados ya no tienen
ese valor y `MBolsillo` conserva su tipo `OTRO`.

Implementación completada; las validaciones posteriores confirmaron el esquema inicial
resultante y pasaron.

## 5. ✅ Eliminar DTO de dashboard no definidos y sus productores

**RF:** RF-012, RF-013, RF-014

- Retirar los seis DTO de dashboard nombrados por la spec y los métodos `@Query` y de
  servicio que los producen.
- Mantener los cinco DTO simples especificados sin redefinir su contenido.

**Hecho cuando:** los seis DTO y sus métodos asociados no existen ni tienen referencias;
los cinco DTO simples siguen presentes sin cambios de definición.

Implementación completada. La búsqueda en `src/` no encuentra referencias a los seis DTO
ni a sus métodos; la suite Maven posterior pasó.

## 6. ✅ Alinear documentación y verificar el código

**RF:** RF-014, RF-015

- Actualizar `doc/planificacion/entidades/` y `doc/modelo/modelo-datos.md` para reflejar únicamente la
  limpieza y el alcance implementados, eliminando referencias obsoletas a Ahorro/Otro
  como entidades, `mGrafica` y el paquete ajeno.
- Revisar referencias colgantes en `src/`.

**Hecho cuando:** la documentación cumple RF-015 y no hay referencias a clases, métodos,
DTO o paquetes retirados.

Documentación alineada en `doc/planificacion/entidades/` y `doc/modelo/modelo-datos.md`: `AHORRO` y
`OTRO` se describen como valores del tipo de bolsillo. Las búsquedas no encontraron
referencias a `MAhorro`, `MOtro`, sus DTO, `mGrafica` ni `com.Cesde.concesionario` en esos
documentos; tampoco quedaron referencias a los DTO eliminados bajo `src/`. La validación
Maven no forma parte de esta tarea.

## 7. ✅ Validar prueba JPA

**RF:** RF-004, RF-016; CA-02, CA-09

- Añadir y ejecutar con JUnit `@DataJpaTest` una prueba que persista una contabilidad
  sintética y compruebe su consulta por `idGrupo`, además de verificar que el contexto JPA
  arranca con las entidades y enums resultantes.

**Hecho cuando:** `@DataJpaTest` pasa y valida el resultado esperado para `idGrupo`.

`ContabilidadRepositoryTest` usa `@DataJpaTest`, persiste dos contabilidades sintéticas
con grupos distintos y verifica que `findByIdGrupo("grupo-1")` devuelve solo el registro
esperado. La prueba pasó: 1 test, 0 fallos. El import usa el paquete de Spring Boot 4.1.1.

## 8. ✅ Ejecutar validación integral

**RF:** RF-004, RF-014; CA-01, CA-02

- Ejecutar `./mvnw test -Dtest=NombreClase` para la prueba específica, `./mvnw test` y
  `./mvnw package`. No se requiere `@WebMvcTest` porque no se modifica la capa
  Controlador.

**Hecho cuando:** las pruebas específicas, la batería completa y el empaquetado terminan
correctamente. Si Java no está disponible, cada validación se registra como no ejecutada
y el cambio queda pendiente de validación.

Validación integral pasada con `JAVA_HOME=/home/daniel/.jdks/openjdk-27`:
`./mvnw test` y `./mvnw package` finalizaron correctamente.
