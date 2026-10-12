---
name: spring-native-query
description: Crea consultas nativas (SQL) en proyectos Java Spring Boot con Spring Data JPA - la anotación @Query(nativeQuery = true), la interfaz de proyección con los campos que devuelve, el método del repositorio con @Param y su uso desde la capa de servicio. Úsala siempre que el usuario pida una consulta nativa, un @Query con SQL, un JOIN entre varias tablas, un listado o reporte que mezcle tablas, una "consulta personalizada" en un repositorio, o pegue un SELECT con tipos Java delante de las columnas (por ejemplo "String C.nomcliente AS nomcliente"), aunque no diga la palabra "nativa".
---

# Consultas nativas en Spring Boot

Esta skill convierte lo que el usuario necesita consultar en tres piezas que funcionan juntas: el SQL, la proyección que recibe el resultado y el método del repositorio. Si falta una de las tres, la consulta compila pero falla en tiempo de ejecución o devuelve datos sin tipo.

## Cuándo conviene una consulta nativa

Usa una consulta nativa cuando hay JOIN entre varias tablas sin relaciones mapeadas en las entidades, reportes o listados con columnas de varias tablas, o funciones propias del motor de base de datos. Si la consulta es sobre una sola entidad y es simple, un método derivado (`findByIdcliente`) o JPQL es más seguro y portable; dilo y ofrece esa alternativa.

## Proceso

1. **Entiende qué se necesita.** Identifica columnas, tablas, uniones y filtros. Si el usuario escribió algo como `String C.nomcliente AS nomcliente`, el tipo Java delante de la columna indica el tipo del campo de la proyección. No es SQL válido: quítalo del SELECT y llévalo a la interfaz de proyección.
2. **Verifica los nombres reales.** El SQL nativo usa nombres de tablas y columnas de la base de datos, no los de los campos Java. Si el proyecto está disponible, busca en las entidades (`@Table`, `@Column`) y confirma los nombres. Si no está disponible, usa los que dio el usuario y avisa de que no los pudiste comprobar.
3. **Escribe el SQL.** Usa bloque de texto (`"""`, Java 15 o superior), parámetros con nombre (`:idcliente`) y un alias `AS` en cada columna. Nunca concatenes valores en el texto: los parámetros evitan inyección SQL y dejan que la base reutilice el plan.
4. **Crea la proyección.** Una interfaz con un getter por alias y el tipo Java correcto. Cada alias debe coincidir con el nombre del getter (`AS nomcliente` es `getNomcliente()`), porque así Spring Data une la columna con el método. Los alias en minúscula evitan problemas de mayúsculas en motores como PostgreSQL.
5. **Escribe el método del repositorio** con `@Query(value = ..., nativeQuery = true)` y un `@Param` por cada parámetro.
6. **Muestra cómo se usa desde el servicio**, siguiendo la arquitectura del proyecto.

## Dónde poner cada pieza

Antes de crear archivos, mira cómo está organizado el proyecto (carpetas como `Dto/`, `Modelo/`, `Servicio/`, y dónde viven los repositorios existentes) y sigue esas convenciones de nombres y paquetes. Si no hay un lugar claro para los repositorios o proyecciones, pregunta en vez de inventarlo. Los nombres en español del proyecto (`nomcliente`, `valventa`) se respetan tal cual.

## Tipos de datos

| Tipo SQL | Tipo Java habitual |
|---|---|
| varchar, text, char | `String` |
| int, integer | `Integer` |
| bigint | `Long` |
| numeric, decimal (dinero) | `BigDecimal` (usa `Double` solo si el usuario lo pide) |
| date | `LocalDate` |
| timestamp | `LocalDateTime` |
| boolean | `Boolean` |

Si el usuario especificó un tipo, respétalo aunque no sea el de la tabla, y menciona si puede perder precisión (por ejemplo `Double` para dinero).

## Formato de la respuesta

Entrega siempre, en este orden: la interfaz de proyección, el método del repositorio con su `@Query`, un ejemplo de uso en el servicio y una nota breve con las suposiciones (nombres de tablas sin verificar, tipo de base de datos). Para paginación, filtros opcionales, `LIKE`, listas `IN` o consultas que modifican datos, lee `references/variantes.md`.

## Ejemplo completo

Petición:

```java
@Query(value= """
Select
String C.nomcliente AS nomcliente,
Integer F.codfactura AS codfactura,
Double VF.valventa AS valventa
from cliente C inner join factura F on C.idcliente=F.idcliente
inner join vehiculofactura VF on F.codfactura=VF.codfactura
where F.idcliente = :idcliente""",nativeQuery = true)
```

Resultado:

```java
public interface FacturaClienteProyeccion {
    String getNomcliente();
    Integer getCodfactura();
    Double getValventa();
}
```

```java
@Query(value = """
    SELECT
        C.nomcliente AS nomcliente,
        F.codfactura AS codfactura,
        VF.valventa  AS valventa
    FROM cliente C
    INNER JOIN factura F ON C.idcliente = F.idcliente
    INNER JOIN vehiculofactura VF ON F.codfactura = VF.codfactura
    WHERE F.idcliente = :idcliente
    """, nativeQuery = true)
List<FacturaClienteProyeccion> buscarFacturasPorCliente(@Param("idcliente") String idcliente);
```

```java
public List<FacturaClienteProyeccion> facturasDeCliente(String idcliente) {
    return facturaRepositorio.buscarFacturasPorCliente(idcliente);
}
```

Si el servicio no debe exponer la proyección hacia el controlador, conviértela a un DTO propio del proyecto en la capa de servicio.

## Errores frecuentes que debes evitar

- Dejar el tipo Java dentro del SELECT: la base de datos lo rechaza.
- Un alias que no coincide con el getter: el campo llega `null` sin ningún error.
- Usar nombres de campos Java en lugar de nombres de columnas.
- `SELECT *` en consultas con JOIN: columnas repetidas entre tablas rompen el mapeo.
- Olvidar `@Param` o escribirlo distinto al `:nombre` del SQL.