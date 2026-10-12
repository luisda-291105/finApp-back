# Tareas — Repositorio de fuente

## 1. Implementar y validar el repositorio mediante TDD — Hecha

- **RF:** RF-001, RF-002, RF-003, RF-004, RF-005, RF-006.
- **TDD, fase roja:** escribir primero pruebas `@DataJpaTest` para alta, actualización,
  eliminación, consulta completa (vacía y no filtrada) y consulta por ID (presente y
  ausente); ejecutar `./mvnw test -Dtest=FuenteRepositoryTest` y confirmar fallo esperado
  porque `FuenteRepository` aún no existe.
- **Implementación y fase verde:** crear `FuenteRepository`, anotado con `@Repository`,
  que extiende `JpaRepository<MFuente, String>` e incluye `adicionarFuente(MFuente)`,
  `eliminarFuente(String)`, `actualizarFuente(MFuente)`, `consultarTodasFuentes()` y
  `consultarFuente(String)`, con tipos de retorno equivalentes a `UsuarioRepository`,
  delegando a los métodos CRUD heredados. Sin consultas adicionales. Repetir la prueba
  focalizada.
- **Validación de cierre:** ejecutar `./mvnw test` y `./mvnw package`.
- **Hecho cuando:**
  - la fase roja falla por la ausencia de la interfaz o sus métodos, no por otro motivo;
  - `FuenteRepository` conserva los tipos `MFuente` y `String`, y expone los cinco alias
    con firmas y delegaciones descritas, sin consultas personalizadas;
  - `./mvnw test -Dtest=FuenteRepositoryTest` pasa después de la implementación;
  - `./mvnw test` pasa;
  - `./mvnw package` pasa;
  - se cubren los casos previstos de RF-001 a RF-006 y no se han cambiado servicios,
    controladores, DTOs, endpoints, `MFuente`, su esquema ni la spec aprobada.
