# Tareas — Modelo de fuente (`MFuente`)

## 1. Implementar `MFuente` mediante TDD y validar el cambio completo

**RF:** RF-001, RF-002, RF-003, RF-004, RF-005, RF-006, RF-007

Completar en una única tarea cohesiva el ciclo TDD de la entidad: crear primero
`src/test/java/com/luisda/personalapp/springboot/personal_app/Modelo/MFuenteTest.java`,
confirmar el fallo esperado por ausencia de `MFuente`, implementar
`src/main/java/com/luisda/personalapp/springboot/personal_app/Modelo/MFuente.java`,
repetir la prueba focalizada hasta obtener verde y ejecutar la suite completa y el
empaquetado.

**Hecho cuando:**

- Las pruebas cubren la construcción con `idFuente` suministrado; los seis campos y los
  cuatro valores del enum; persistencia/recuperación del enum como texto; y persistencia
  con `idUsuario` sin usuario correspondiente.
- Antes de implementar el modelo, la prueba focalizada falla por ausencia de `MFuente` o
  de sus miembros requeridos. Se confirma que no es un fallo ajeno de configuración o
  dependencias.
- Se implementa únicamente `MFuente` como entidad, con los seis campos de la spec,
  `idFuente` no generado, `idUsuario` escalar sin relación ni FK, y `Tipo` textual con
  `CORREO`, `CHAT`, `TELEFONO` y `OTRO`.
- Tras implementar, `JAVA_HOME=/home/daniel/.jdks/openjdk-27 ./mvnw test -Dtest=MFuenteTest`
  pasa.
- `JAVA_HOME=/home/daniel/.jdks/openjdk-27 ./mvnw test` y
  `JAVA_HOME=/home/daniel/.jdks/openjdk-27 ./mvnw package` terminan correctamente.
- No se modifica la spec ni se añaden otras entidades, capas, reglas o comportamiento.
- Si Java no está disponible o alguna validación falla, la tarea permanece incompleta;
  se informa la validación no ejecutada o fallida y no se declara cerrada.
