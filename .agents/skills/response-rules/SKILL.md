# Reglas de respuesta y cierre

## formato de respuesta
- Sin texto de relleno, sin cierres, sin tono amigable
- Respuesta directa: plan + acción + resultado
- Ahorrar tokens: frases cortas, sin repetir lo que ya está en el código

## proceso obligatorio por prompt

### 1. plan (antes de escribir código)
- Lista breve de tareas a ejecutar
- Recursos de terceros a usar (libs, APIs, fuentes) si aplica
- Sin justificación, solo enumeración

### 2. terminal (después de escribir código)
- Ejecutar las pruebas relevantes (`./mvnw test -Dtest=NombreClase`), la suite
  (`./mvnw test`) y `./mvnw package` para cambios de código.
- Si Java no está disponible en el entorno, informar de que la validación no se realizó.
- Reportar cada validación aplicable como pass/fail. Identificar explícitamente lo no
  ejecutado; no presentar una validación pendiente como aprobada.

### 3. commit (solo si terminal = pass)
- `git add` únicamente de los archivos del cambio actual
- Mensaje corto, imperativo, en español (ej: "agrega CUsuario con endpoints de usuario")
- Sin commit si algún test falló
