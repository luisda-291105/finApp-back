## Servicios implementados

| Clase/Interfaz       | Depende de                                                                 | Responsabilidad                                                            |
| -------------------- | -------------------------------------------------------------------------- | -------------------------------------------------------------------------- |
| UsuarioService       | UsuarioRepository                                                          | Registro, autenticación, actualización; grupo por defecto                  |
| ContactoService      | ContactoRepository                                                         | Alta, edición, consulta de contactos                                       |
| ContabilidadService  | ContabilidadRepository, GrupoRepository                                    | Crear cuentas dentro de un grupo, cambiar estado                           |
| GastoService         | GastoRepository                                                            | Alta, edición, consulta de gastos                                          |
| IngresoService       | IngresoRepository                                                          | Alta, edición, consulta de ingresos                                        |

## Servicios pendientes de implementar

| Clase/Interfaz       | Depende de                                                                 | Responsabilidad                                                            |
| -------------------- | -------------------------------------------------------------------------- | -------------------------------------------------------------------------- |
| GrupoService         | GrupoRepository, GrupoUsuarioRepository, UsuarioRepository                 | Crear grupos, añadir/quitar miembros, asignar rol                          |
| BolsilloService      | BolsilloRepository, ContabilidadRepository                                 | Crear y controlar bolsillos por contabilidad                               |
| CorreoService        | CorreoRepository, FuenteRepository, BolsilloRepository                     | Ingesta e interpretación de correos; clasificación hacia un bolsillo       |
| LlamadaService       | LlamadaRepository, BolsilloRepository, ContactoRepository                  | Registro y consulta de llamadas por bolsillo                               |
| ReporteService       | GastoRepository, IngresoRepository, BolsilloRepository                     | Cálculo de saldos y gráficas (totales, por mes, por categoría) — **sin entidad** |

**Notas:** los servicios trabajan con entidades; la conversión a DTO ocurre en la capa
Controlador. Las gráficas y saldos son **datos dinámicos calculados**, no persistidos.
`BolsilloService` administra también los tipos `AHORRO` y `OTRO`.
