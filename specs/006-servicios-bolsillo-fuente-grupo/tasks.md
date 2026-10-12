# Tareas — Spec 006: Servicios de bolsillo, fuente y grupo

1. **Implementar `SBolsillo`, `SFuente` y `SGrupo` con TDD y validar el cambio completo**
   — cubre RF-001 a RF-008.
   Hecho cuando, para cada servicio, primero se añade su prueba y se confirma la fase roja
   por el comportamiento esperado; luego se implementa el servicio y pasa su prueba
   focalizada:
   - `SBolsilloTest` verifica el CRUD disponible, las consultas independientes por
     contabilidad y tipo, la conservación de resultados vacíos y el manejo de errores
     definido en RF-006; la clase de servicio incluye el header requerido.
   - `SFuenteTest` verifica las cinco operaciones CRUD expuestas por `FuenteRepository`,
     sin consultas personalizadas, la conservación de resultados vacíos y el manejo de
     errores definido en RF-006; la clase de servicio incluye el header requerido.
   - `SGrupoTest` verifica el CRUD disponible, la consulta por el `idUsuario` creador
     (no membresía), la conservación de resultados vacíos y el manejo de errores definido
     en RF-006; la clase de servicio incluye el header requerido.

   Después de los tres ciclos TDD, pasan conjuntamente los tests focalizados y las
   pruebas relevantes `GrupoBolsilloRepositoryTest` y `FuenteRepositoryTest`, la suite
   completa `./mvnw test` y `./mvnw package`. La revisión del diff confirma que solo se
   crearon los tres servicios y sus tres tests, sin cambios a repositorios, modelos,
   otros servicios, DTO, controladores o datos persistidos. La tarea no se considera
   completa si cualquiera de las validaciones falla.
