# Tareas — Spec 003: Actualización de servicios

1. **Completar `SUsuario`** — cubre RF-001, RF-002, RF-003, RF-004, RF-005 y RF-006.
   Hecho cuando: las operaciones CRUD y búsquedas por contacto/nombre delegan en
   `UsuarioRepository`, manejan errores conforme a RF-005, conservan resultados vacíos,
   y la clase tiene el encabezado requerido si le falta; `SUsuarioTest` verifica esos
   comportamientos.

2. **Completar `SContacto`** — cubre RF-001, RF-002, RF-003, RF-004, RF-005 y RF-006.
   Hecho cuando: CRUD y búsquedas por usuario/nombre/teléfono/correo delegan en
   `ContactoRepository`, manejan errores conforme a RF-005, conservan resultados vacíos,
   y la clase tiene el encabezado requerido si le falta; `SContactoTest` verifica esos
   comportamientos.

3. **Completar `SContabilidad`** — cubre RF-001, RF-002, RF-003, RF-004, RF-005 y RF-006.
   Hecho cuando: CRUD y búsquedas por grupo/estado delegan en `ContabilidadRepository`,
   manejan errores conforme a RF-005, conservan resultados vacíos, y la clase tiene el
   encabezado requerido si le falta; `SContabilidadTest` verifica esos comportamientos.

4. **Completar `SGasto`** — cubre RF-001, RF-002, RF-003, RF-004, RF-005 y RF-006.
   Hecho cuando: CRUD y búsqueda por contabilidad delegan en `GastoRepository`, manejan
   errores conforme a RF-005, conservan resultados vacíos, y la clase tiene el encabezado
   requerido si le falta; `SGastoTest` verifica esos comportamientos.

5. **Completar `SIngreso`** — cubre RF-001, RF-002, RF-003, RF-004, RF-005 y RF-006.
   Hecho cuando: CRUD y búsqueda por contabilidad delegan en `IngresoRepository`, manejan
   errores conforme a RF-005, conservan resultados vacíos, y la clase tiene el encabezado
   requerido si le falta; `SIngresoTest` verifica esos comportamientos.

6. **Crear `SGrupo`** — cubre RF-001, RF-002, RF-003, RF-004, RF-005 y RF-006.
   Hecho cuando: el servicio delega CRUD y búsqueda por usuario en `GrupoRepository`,
   maneja errores conforme a RF-005, conserva colecciones vacías, incluye el encabezado de
   seis campos y `SGrupoTest` verifica esos comportamientos.

7. **Crear `SBolsillo`** — cubre RF-001, RF-002, RF-003, RF-004, RF-005 y RF-006.
   Hecho cuando: el servicio delega CRUD y búsquedas por contabilidad/tipo en
   `BolsilloRepository`, maneja errores conforme a RF-005, conserva colecciones vacías,
   incluye el encabezado de seis campos y `SBolsilloTest` verifica esos comportamientos.

8. **Validar alcance y build** — cubre RF-007 y verifica el conjunto completo de RF.
   Hecho cuando: las pruebas focalizadas de servicios, `./mvnw test` y
   `./mvnw package` pasan; el diff no cambia modelos, repositorios, DTO, controladores ni
   datos persistidos, y no incorpora servicios para modelos fuera de alcance.
