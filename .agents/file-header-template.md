# .agents/file-header-template.md

Toda clase nueva (modelo, repositorio, servicio, controlador, DTO, config) inicia con
este bloque, resumido al máximo (1 línea por campo cuando sea posible):

```java
/**
 * @uso               Qué problema resuelve esta clase (1 línea)
 * @capa              Modelo | Repositorio | Servicio | Controlador | DTO | Config
 * @responsabilidades Lista corta de métodos/responsabilidades principales
 * @datos             Cómo entra/sale la data (DTOs, entidades, parámetros)
 * @dependencias      Clases de las que depende (inyectadas o usadas)
 * @usadoPor          Clases/capas que la consumen
 */
```

## Ejemplo — servicio
```java
/**
 * @uso               Gestiona la lógica de negocio de usuarios
 * @capa              Servicio
 * @responsabilidades crearUsuario, buscarPorId, actualizarUsuario
 * @datos             Recibe y devuelve entidades MUsuario; no maneja DTOs
 * @dependencias      RUsuario
 * @usadoPor          CUsuario
 */
```

## Ejemplo — controlador
```java
/**
 * @uso               Expone endpoints REST de usuarios
 * @capa              Controlador
 * @responsabilidades POST /usuarios, GET /usuarios/{id}
 * @datos             Entrada/salida con DTOs de `Dto/`; nunca entidades
 * @dependencias      SUsuario
 * @usadoPor          Clientes HTTP (finApp-web)
 */
```

Regla: si el archivo crece y el header deja de ser un resumen (>6-7 líneas
de contenido real por campo), la clase probablemente debe dividirse.
