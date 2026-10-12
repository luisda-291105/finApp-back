# Spec 001 — Corrección de paquetes y limpieza mínima del modelo

**Estado:** borrador

## Propósito

El código de finApp-back conserva la identidad de paquetes de otro proyecto
(`com.Cesde.concesionario`) en declaraciones `package`, sentencias `import` y cadenas
JPQL, y su modelo referencia una entidad de contabilidad que no existe. Como resultado, el
proyecto no puede construirse y arrastra conceptos ya descartados (Ahorro y Otro) y DTO
cuyo contenido no está definido.

Esta spec acota el cambio a **solo paquetes + limpieza mínima**: recuperar la identidad de
paquete real del proyecto, hacer que el proyecto compile y empaquete con lo que ya existe,
y aplicar las eliminaciones/adiciones mínimas ya decididas por el usuario. El **por qué**
es doble: (1) el proyecto debe poder construirse como la aplicación de finanzas personales
que es, y (2) el modelo y los DTO deben quedar sin conceptos descartados ni referencias
colgantes, para no dejar deuda estructural antes de modelar la arquitectura refinada.

El modelo refinado completo (entidades Fuente, Correo, Llamada, GrupoUsuario y el traslado
de Gasto/Ingreso a Bolsillo) **no** forma parte de este cambio: se abordará en specs
posteriores.

## Alcance

### Dentro del alcance

- Unificar la identidad de paquete de los archivos bajo `src/` a la identidad real del
  proyecto, incluidas declaraciones `package`, sentencias `import` y referencias a
  paquetes embebidas en cadenas JPQL.
- Que el proyecto compile y empaquete correctamente.
- Crear la entidad de contabilidad ausente (`MContabilidad`) y ajustar el repositorio y
  servicio que la consultan por usuario, para que no queden referencias colgantes.
- Completar `MGrupo` y `MBolsillo` con los campos ya descritos por la documentación de
  diseño, sin relaciones JPA ni entidad de asociación grupo-usuario.
- Eliminar los conceptos Ahorro y Otro del código, incluidos sus valores de enum en las
  entidades correspondientes.
- Eliminar los DTO de dashboard no definidos y los métodos de repositorio/servicio que los
  producen.
- Asegurar la coherencia de la documentación del proyecto con lo implementado.

### Fuera del alcance

- Crear las entidades del modelo refinado (Fuente, Correo, Llamada, GrupoUsuario) o
  introducir sus relaciones.
- Trasladar `MGasto`/`MIngreso` de Contabilidad a Bolsillo.
- Alinear el modelo con la arquitectura completa de grupos (relaciones JPA entre
  entidades, entidad de asociación grupo-usuario).
- Definir o implementar los DTO del dashboard del cliente web.
- Implementar la capa Controlador ni endpoints REST.
- Cambios de formato de datos persistidos, migraciones o el ciclo de vida de los datos.
- Reorganizar el código más allá de lo indicado.

## Casos límite

- **Java no disponible en el entorno:** si no es posible compilar ni empaquetar, la
  validación se reporta como **no ejecutada**, nunca como aprobada. El cambio no puede
  declararse cerrado sin esa validación en un entorno con Java.
- **Referencias colgantes:** ninguna declaración, import, consulta JPQL o método debe
  quedar apuntando a un paquete ajeno, a una entidad eliminada o a un DTO eliminado.
- **Ajuste obligado de contabilidad:** al crear `MContabilidad` sin `idUsuario` (usa
  `idGrupo`), el método que consultaba contabilidades por usuario queda inválido y debe
  pasar a consultar por grupo; de lo contrario quedaría una referencia colgante.
- **Archivo vacío:** `MBolsillo` existe pero está vacía; completarla no debe introducir
  campos ni relaciones distintos a los descritos por la documentación de diseño.
- **Valor `OTRO` con significados distintos:** `OTRO` debe eliminarse de las categorías de
  `MGasto` y `MIngreso` y del tipo de `MContacto`, pero **se conserva** en el tipo de
  `MBolsillo` porque representa el bolsillo de tipo "otro" y forma parte del diseño
  vigente. No deben confundirse ambos usos.
- **Entidades del modelo refinado ausentes:** no debe suponerse que `MGrupoUsuario`,
  `Fuente`, `Correo` o `Llamada` ya existan; su ausencia está prevista y no bloquea este
  cambio.
- **Discrepancia documentación vs. código:** cuando la documentación de diseño describa
  relaciones que este cambio no implementa, prevalece la decisión de alcance de esta spec;
  no debe extenderse el patrón ni migrarse el resto.
- **DTO simples:** `UsuarioDTO`, `ContactoDTO`, `GastoDTO`, `IngresoDTO` y
  `ContabilidadDTO` se conservan tal como están; su contenido depende de una decisión del
  usuario todavía no tomada.
- **Base de datos aún no creada:** no existen registros que conservar ni valores enum
  históricos que convertir. El esquema inicial puede incorporar o retirar los campos y
  valores indicados por esta spec sin migración de datos.

## Requisitos funcionales (EARS)

- **RF-001 — Identidad de paquete.** Todo archivo bajo `src/` que declare un paquete
  deberá declarar `com.luisda.personalapp.springboot.personal_app` como paquete base,
  respetando su subcapa.

- **RF-002 — Importaciones sin identidad ajena.** Cuando un archivo bajo `src/` importe un
  tipo del propio proyecto, deberá hacerlo desde la identidad de paquete base del proyecto
  y no desde `com.Cesde.concesionario` ni otra identidad ajena.

- **RF-003 — Consultas JPQL sin paquetes ajenos.** Cuando una consulta JPQL referencie un
  tipo del proyecto por su nombre cualificado, deberá usar la identidad de paquete base
  del proyecto.

- **RF-004 — Compilación y empaquetado.** El proyecto deberá compilar y empaquetar
  correctamente (`./mvnw package`) con las clases existentes y las adiciones/eliminaciones
  de esta spec; si el entorno no dispone de Java, la validación se reportará como no
  ejecutada.

- **RF-005 — Entidad de contabilidad.** El modelo deberá incluir `MContabilidad` con los
  campos de la documentación de diseño (`idContabilidad`, `idGrupo`, `idContacto`
  opcional, `nombre`, `valor`, `estado`, `fechaCreacion`), sin relaciones JPA.

- **RF-006 — Consulta de contabilidad por grupo.** Dado que `MContabilidad` carece de
  `idUsuario`, el repositorio de contabilidad deberá exponer la consulta por `idGrupo`
  (en lugar de por usuario) y el servicio deberá ofrecer el método correspondiente por
  grupo, de modo que no queden referencias a un campo inexistente.

- **RF-007 — Entidad de grupo.** `MGrupo` deberá incluir los campos de la documentación de
  diseño que hoy faltan (`idUsuario` como referencia al creador, tratado como columna
  simple y no como relación JPA, y `fechaCreacion`), manteniendo el resto de sus campos.

- **RF-008 — Entidad de bolsillo.** `MBolsillo` deberá dejar de estar vacía e incluir los
  campos de la documentación de diseño (`idBolsillo`, `idContabilidad`, `nombre`, `tipo`,
  `estado`, `fechaCreacion`), sin relaciones JPA.

- **RF-009 — Convención de nombre de tabla.** Cuando se cree o modifique una entidad, su
  anotación de tabla deberá seguir la convención ya usada por el código existente
  (`@Table(name = "<NombreClase>")`), para no introducir dos convenciones distintas.

- **RF-010 — Eliminación de Ahorro y Otro.** El código bajo `src/` no deberá contener
  entidades, repositorios, servicios, DTO ni métodos referidos a Ahorro u Otro como
  conceptos independientes, dado que se representan mediante bolsillos.

- **RF-011 — Limpieza de valores de enum.** Las categorías de `MGasto` y `MIngreso` y el
  tipo de `MContacto` no deberán contener el valor `OTRO`; el tipo de `MBolsillo` sí
  conserva `OTRO` según su diseño.

- **RF-012 — Eliminación de DTO de dashboard no definidos.** El código deberá eliminar los
  DTO de dashboard aún no definidos por el usuario (`UsuarioContactoDTO`,
  `UsuarioContabilidadDTO`, `UsuarioGastoDTO`, `UsuarioIngresoDTO`, `UsuarioAhorroDTO`,
  `UsuarioOtroDTO`) junto con los métodos `@Query` del repositorio de usuario y los
  métodos del servicio de usuario que los producen, corrigiendo así la violación de que
  los DTO solo pertenecen a la capa de Controlador.

- **RF-013 — DTO simples conservados.** Los DTO simples `UsuarioDTO`, `ContactoDTO`,
  `GastoDTO`, `IngresoDTO` y `ContabilidadDTO` deberán conservarse sin redefinirse en este
  cambio, a la espera de la definición del dashboard.

- **RF-014 — Sin referencias colgantes en el código.** Cuando se elimine o modifique una
  entidad, DTO o método, ningún archivo bajo `src/` deberá conservar declaraciones,
  importaciones, consultas ni métodos que lo referencien.

- **RF-015 — Coherencia de la documentación.** La documentación del proyecto
  (`doc/planificacion/entidades/` y `doc/modelo/modelo-datos.md`) deberá quedar coherente con lo
  implementado: sin referencias a Ahorro u Otro como entidades, sin una entidad de
  gráficas (`mGrafica`) y sin identidad de paquete ajena.

- **RF-016 — Consulta JPA verificable.** Cuando el repositorio consulte contabilidades por
  grupo, deberá devolver los registros cuyo `idGrupo` coincida; una prueba de persistencia
  deberá verificar la consulta usando datos sintéticos.

## Persistencia, migración y compatibilidad

La aplicación usa entidades JPA como forma de los datos y repositorios Spring Data JPA
como vía de persistencia; la constitución define H2 para desarrollo, `pom.xml` incluye H2
y el conector MySQL previsto para producción. El ciclo de vida previsto
para los registros es el que ofrecen los repositorios: alta, consulta, actualización y
eliminación. Esta spec no agrega operaciones de negocio ni cambia ese ciclo. Los campos
de las entidades se representan como columnas y los enums de gasto e ingreso se almacenan
como texto (`EnumType.STRING`).

El usuario confirma que la base de datos aún no ha sido creada. Por tanto, este cambio
establece el esquema inicial de contabilidad, grupo y bolsillo, retira los modelos
descartados y elimina `OTRO` de los enums indicados sin afectar datos persistidos: no hay
registros históricos que requieran migración ni compatibilidad con una estructura previa.
No se necesita migración de datos; el modelo resultante será la estructura inicial que se
use al configurar H2 en desarrollo y MySQL en producción.

## Criterios de aceptación verificables

- **CA-01:** ninguna coincidencia de `com.Cesde.concesionario` en `package`, `import` ni
  cadenas de `src/`.
- **CA-02:** `./mvnw test` y `./mvnw package` finalizan correctamente; si Java no está
  disponible, se documenta como validación no ejecutada.
- **CA-03:** `MContabilidad` existe en el modelo con los campos indicados y sin relaciones
  JPA; la consulta por grupo sustituye a la consulta por usuario sin referencias al campo
  inexistente.
- **CA-04:** `MGrupo` y `MBolsillo` contienen los campos indicados y siguen la convención
  de nombre de tabla `@Table(name = "<NombreClase>")`.
- **CA-05:** no existe código que referencie Ahorro u Otro como entidad, repositorio,
  servicio o DTO; `MGasto`, `MIngreso` y `MContacto` no contienen el valor `OTRO`;
  `MBolsillo.tipo` sí conserva `OTRO`.
- **CA-06:** los seis DTO de dashboard no definidos y sus métodos asociados ya no existen,
  y no quedan referencias a ellos.
- **CA-07:** `UsuarioDTO`, `ContactoDTO`, `GastoDTO`, `IngresoDTO` y `ContabilidadDTO`
  permanecen presentes.
- **CA-08:** la documentación no referencia Ahorro u Otro como entidades, ni `mGrafica`,
  ni el paquete ajeno.
- **CA-09:** una prueba JUnit `@DataJpaTest` persiste una contabilidad sintética y verifica
  que la consulta por `idGrupo` la recupera; el contexto JPA arranca con las entidades y
  enums resultantes.

Sobre pruebas: aunque no se agregan operaciones de negocio, RF-006 cambia una consulta
JPA y se modifican mapeos persistentes. Se deberá añadir o ajustar la prueba
`@DataJpaTest` indicada en CA-09. También se ejecutarán `./mvnw test` y
`./mvnw package`; si Java no está disponible, se reportan como no ejecutados. No se
requiere `@WebMvcTest` porque no se modifica la capa Controlador.

## Puntos abiertos

- **PO-001 — Destino de los DTO simples.** No se define aquí si `UsuarioDTO`,
  `ContactoDTO`, `GastoDTO`, `IngresoDTO` y `ContabilidadDTO` corresponden a la forma
  final que necesita el dashboard o si deberán redefinirse cuando el usuario concrete los
  datos a mostrar. RF-013 los preserva provisionalmente para no suponer.
- **PO-002 — Validación de compilación.** La ejecución de `./mvnw test` y `./mvnw package`
  queda pendiente de un entorno con Java; el cambio no puede darse por aprobado sin esa
  validación.
