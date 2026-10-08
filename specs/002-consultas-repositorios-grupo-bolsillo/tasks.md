# Tareas — Spec 002: Consultas de grupos y bolsillos en repositorios

## 1. Añadir búsqueda de grupos por creador — Hecha

**RF:** RF-001, RF-004, RF-005

- Añadir el repositorio de grupo siguiendo el patrón vigente y una consulta por
  `idUsuario`.

**Hecho cuando:** la consulta puede recuperar todos los grupos cuyo `idUsuario` coincide
con el solicitado y devuelve colección vacía si no hay coincidencias; no consulta
membresías ni añade relaciones al modelo.

## 2. Añadir búsquedas de bolsillos — Hecha

**RF:** RF-002, RF-003, RF-004, RF-005

- Añadir el repositorio de bolsillo con consultas independientes por `idContabilidad` y
  por el tipo existente de `MBolsillo`.

**Hecho cuando:** cada búsqueda devuelve todos los bolsillos que coinciden con su criterio,
devuelve colección vacía si no encuentra resultados y no modifica el modelo o el esquema.

## 3. Verificar los repositorios con pruebas JPA — Hecha

**RF:** RF-001, RF-002, RF-003, RF-004, RF-005

- Añadir pruebas `@DataJpaTest` con datos sintéticos para grupos y bolsillos: coincidencias
  múltiples, valores distintos, ningún resultado y ambos estados para las coincidencias.

**Hecho cuando:** `GrupoBolsilloRepositoryTest` confirma que los tres filtros devuelven
todos y solo los resultados coincidentes; los casos sin resultados devuelven listas vacías
y los registros inactivos no se excluyen.

## 4. Ejecutar validación integral — Hecha

**RF:** RF-001, RF-002, RF-003, RF-004, RF-005

- Ejecutar `./mvnw test -Dtest=GrupoBolsilloRepositoryTest`, `./mvnw test` y
  `./mvnw package`.

**Hecho cuando:** la prueba específica, la suite y el empaquetado finalizan correctamente.
Si Java no está disponible, cada validación se registra como no ejecutada y el cambio
queda pendiente de validación.
