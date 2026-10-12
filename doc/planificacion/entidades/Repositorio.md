Interfaces que extienden Spring Data JPA para el acceso a datos.

## Repositorios implementados

| Interfaz                 | Extiende                       | Entidad      | Métodos personalizados                                   |
| ------------------------ | ------------------------------ | ------------ | -------------------------------------------------------- |
| UsuarioRepository        | JpaRepository<MUsuario, String> | MUsuario     | findByContacto(String correo), findByNombre(String nombre) |
| ContactoRepository       | JpaRepository<MContacto, String>| MContacto   | findByIdUsuario(String idUsuario)                        |
| GrupoRepository          | JpaRepository<MGrupo, String>  | MGrupo       | findByIdUsuario(String idUsuario)                        |
| ContabilidadRepository   | JpaRepository<MContabilidad, String> | MContabilidad | findByIdGrupo(String idGrupo)                        |
| BolsilloRepository       | JpaRepository<MBolsillo, String>| MBolsillo   | findByIdContabilidad(String idContabilidad), findByTipo(MBolsillo.Tipo tipo) |
| GastoRepository          | JpaRepository<MGasto, String>  | MGasto       | findByIdContabilidad(String idContabilidad)              |
| IngresoRepository        | JpaRepository<MIngreso, String>| MIngreso     | findByIdContabilidad(String idContabilidad)              |

## Repositorios pendientes de implementar

| Interfaz                 | Extiende                       | Entidad      | Métodos personalizados                                   |
| ------------------------ | ------------------------------ | ------------ | -------------------------------------------------------- |
| FuenteRepository         | JpaRepository<MFuente, String> | MFuente      | findByIdUsuario(String idUsuario)                        |
| CorreoRepository         | JpaRepository<MCorreo, String> | MCorreo      | findByIdGrupo(String idGrupo), findByIdFuente(String idFuente) |
| LlamadaRepository        | JpaRepository<MLlamada, String>| MLlamada     | findByIdBolsillo(String idBolsillo)                      |
| GrupoUsuarioRepository   | JpaRepository<MGrupoUsuario, String> | MGrupoUsuario | findByIdGrupo(String idGrupo), findByIdUsuario(String idUsuario) |

**Notas:** los repositorios trabajan solo con entidades; **no** devuelven DTOs. Las
consultas de reporte/gráficas se calculan en la capa de servicio. Los bolsillos clasificados
como `AHORRO` u `OTRO` se consultan mediante `BolsilloRepository`.
