Objetos de transferencia usados entre el Controlador y el Cliente Web/Servicio, para no
exponer las entidades JPA directamente.

**Estado:** los DTO del dashboard aún no están definidos por el usuario. Esta capa se
modelará cuando se concreten los datos a mostrar; no se propaga a Servicio ni
Repositorio.

**Reglas:**
- Los DTO pertenecen a la capa Controlador; los servicios y repositorios trabajan con
  entidades.
- No se expone ninguna entidad JPA directamente en la API.
