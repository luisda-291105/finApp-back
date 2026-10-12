# Roadmap

## Estado actual

- Frontend demo: dashboard con cifras de ejemplo, sin persistencia real ni login
  autenticado.
- Backend: capas Modelo, Repositorio y Servicio implementadas; Controlador pendiente.
- Tests: `ContabilidadRepositoryTest` implementada y pasando.
- Specs: 001 (corrección de paquetes y limpieza) y 002 (consultas de grupos y bolsillos)
  completadas.
- n8n e IA: visión futura. Integración con bancos: no implementada.

## Próximos pasos sugeridos

1. Implementar capa Controlador (endpoints REST).
2. Crear entidades pendientes: Fuente, Correo, Llamada, GrupoUsuario.
3. Crear repositorios pendientes: FuenteRepository, CorreoRepository, LlamadaRepository,
   GrupoUsuarioRepository.
4. Crear servicios pendientes: GrupoService, BolsilloService, CorreoService,
   LlamadaService, ReporteService.
5. Autenticación real en login/registro.
6. Persistencia financiera real en el frontend.
7. Configurar MySQL para producción.
8. Flujos n8n iniciales (filtrado y clasificación de correos).
9. Integración de IA para interpretar mensajes y extraer datos financieros.
10. Modelo de permisos y fuentes conectadas.
11. Integración progresiva con servicios externos y bancos.
