Consultas nativas a través de Mapper

si el JPA no es capaz de convertir los resultados de una consulta nativa a una DTO directamente (nativeQuery = true) y devuelve directamente el resultado en SQL es necesario hacerlo a través de proyecciones y utilizando la anotación @Mapper

Record en Java

Es un tipo especial de clase diseñado para almacenar datos de forma simple y sin escribir código repetitivo.

Sintaxis
public record NombreRecord(
        Tipo campo1,
        Tipo campo2,
        Tipo campo3) {
}

Características de los Record en una sola línea

No se coloca atributos privados el record los asume de esta manera
Crea, además, los constructores
Inmutabilidad: Los datos no se pueden cambiar después de crear el objeto. Todos los campos son implícitamente final (Su valor solo se puede asignar una vez y, después de eso, no se puede cambiar) y no tienen métodos setters (los setter no son necesarios porque la función principal de una DTO es entregar y no recibir información)
Crea, también, los métodos equals(), hashCode() (Código entero de 32 bits que se utiliza como código de identificación único) 
Concisión: Reduce el código repetitivo al mínimo.
Uso ideal: Diseñados para transportar información entre capas, como objetos DTO (Data Transfer Object) o respuestas de una API.
Restricciones: No pueden extender (heredar de) otras clases, aunque sí pueden implementar interfaces.

Proyecciones en Java
Es una técnica que permite extraer solamente los campos específicos que se necesitan de una tabla en lugar de cargar la entidad completa (No trae todas las columnas y propiedades de un registro (lo que consume más memoria y recursos de red)).
Beneficios de usar proyecciones
Mejor el rendimiento: Al consultar menos datos a la base de datos, se optimiza el uso de memoria y se acelera la respuesta.
Seguridad: Evita exponer información sensible o crítica (como contraseñas o datos privados) al cliente o capas superiores.
Consultas limpias: Facilita la creación de vistas personalizadas de un modelo sin necesidad de escribir código complejo de mapeo manual.
Ejemplo

public interface IClienteProjection {
Integer getIdcliente();
String getNomcliente();
    	String getTelcliente();
} 

MapStruct 

es una biblioteca de Java que genera automáticamente código para convertir (mapear) datos entre diferentes objetos sin tener que escribir el código repetitivo a mano, por ejemplo, una entidad de base de datos a un objeto DTO (Data Transfer Object)

Características

Se genera en tiempo de compilación, crea las clases de mapeo mientras se compila el programa, lo que da como resultado que sea rápido y seguro.
Sin reflexión: Utiliza llamadas a métodos normales en lugar de reflexión (reflection), mejorando el rendimiento de la aplicación (la reflexión en Java es una característica de la API del lenguaje que permite a un programa examinar, inspeccionar y modificar su propia estructura y comportamiento (clases, métodos, atributos y constructores) en tiempo de ejecución)
Basado en interfaces: Solo necesitas definir una interfaz con anotaciones como @Mapper y MapStruct se encarga de crear la implementación.
Detección de errores: Si cambia una propiedad en los objetos y no se actualiza el mapeo, MapStruct avisa durante la compilación.

@Mapper
Es una clase o interfaz del JPA de Spring Boot, cuya responsabilidad es convertir un objeto de un tipo a otro 

Nota 1: La anotación @Mapper utiliza la librería de Java, MapStruct que se utiliza para convertir un objeto en otro automáticamente
Nota 2: La anotación @Mapping sirve para decirle a MapStruct cómo convertir un objeto en otro










@Mapping


Se utiliza en librerías de mapeo de objetos como MapStruct para indicar cómo se deben transferir o transformar los campos de un objeto a otro (por ejemplo, cuando el nombre del atributo en la entidad no coincide con el nombre de una DTO)

Lógica gráfica


[Base de Datos]
          Consulta SQL Nativa (SELECT C.nomcliente, C.telcliente, F.codfactura...)
        
 [Repositorio JPA] ──── (IFactura)
          Retorna una lista de Proyecciones
        
 [Proyección JPA] ───── (List)
                   	     	    getNomcliente()
                            	    getTelcliente()
                            	    getCodfactura()...
        
 [Mapper MapStruct] ─── (IFacturaCompletaMapper)
           Convierte 'nomcliente'  ➔ 'nombrecliente'
           Convierte 'telcliente' ➔ 'telefonocliente'
        
 [DTO Record] ───────── (List)
                           		        nombrecliente()
                           		        telefonocliente()
                           		        codfactura()...
        
 [Capa de Servicio] ─── (FacturaServiceImpl)
          Retorna los datos limpios al controlador
        
 [REST Controller] ───> Responde al Cliente (JSON)

Pasos a seguir
Adicionar la dependencia y el plugins para el Mapper en el archivo de configuración pom.xml

<dependency>
        <groupId>org.mapstruct</groupId>
        <artifactId>mapstruct</artifactId>
        <version>1.6.3</version>
</dependency>




En el mismo archivo, en el build, agregar el siguiente plugin

<plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.13.0</version>
            <configuration>
                <annotationProcessorPaths>
                    <path>
                        <groupId>org.mapstruct</groupId>
                        <artifactId>mapstruct-processor</artifactId>
                        <version>1.6.3</version>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>

Crear la consulta en el repositorio (en este caso el de factura)

// Consulta de las facturas completas de un cliente
@Query(value = """
SELECT
    C.nomcliente,
    C.telcliente,
    F.codfactura,
    F.fecha,
    F.idcliente,
    VF.placa,
    VF.valventa,
    V.modelo,
    V.marca
    from cliente C inner join factura F on C.idcliente=F.idcliente
    inner join vehiculofactura VF on F.codfactura=VF.codfactura
    inner join vehiculo V on VF.placa=V.placa
    where F.idcliente = :idcliente""",nativeQuery = true)

Crear la interface que va a contener la proyección en el mismo repositorio

public interface IFacturasCompletasClienteProjection {
    String getNomcliente();
    String getTelcliente();
    Integer getCodfactura();
    LocalDate getFecha();
    String getIdcliente();
    String getPlaca();
    Integer getValventa();
    String getModelo();
    String getMarca();
}

Agregar los alias a la consulta nativa para que coincidan con los de la proyección (de esta manera el resultado de la consulta no se descarga directamente a la DTO, sino a la proyección), tener cuidado en no cambiar los nombres de los atributos

@Query(value = """
SELECT
    C.nomcliente As nomcliente,
    C.telcliente AS telcliente,
    F.codfactura AS codfactura,
    F.fecha AS fecha,
    F.idcliente AS idcliente,
    VF.placa AS placa,
    VF.valventa AS valventa,
    V.modelo AS modelo,
    V.marca AS marca
    from cliente C inner join factura F on C.idcliente=F.idcliente
    inner join vehiculofactura VF on F.codfactura=VF.codfactura
    inner join vehiculo V on VF.placa=V.placa
    where F.idcliente = :idcliente""",nativeQuery = true)

Nota: Es importante que los nombres de los métodos correspondan con los alias de la consulta SQL.


¿Por qué esos alias?
La consulta devuelve:
F.codfactura AS codfactura


La proyección tiene:
Integer getCodfactura();


Entonces:
codfactura → getCodfactura()


Lo mismo con los otros campos


Agregar el método después de la consulta nativa (notar que el resultado se descarga sobre una lista con la estructura de la proyección)

List<IFacturasCompletasClienteProjection> buscarFacturasCompletasCliente (@Param("idcliente") String idcliente);

Crear una DTO como un record

public record FacturasCompletasClienteDTO (
    String nombrecliente,
    String telefonocliente,
    Integer codfactura,
    LocalDate fecha,
    String idcliente,
    String placa,
    Integer valventa,
    String modelo,
    String marca) {
}

Crear un Mapper (para convertir la proyección en la DTO)


Crear una nueva capa para los Mapper (no puede ser ni en los repositorios ni en los servicios) y luego crear la interface, quedando así:


@Mapper(componentModel = "spring")
public interface IFacturaCompletaMapper {
    @Mapping(source = "nomcliente", target = "nombrecliente")
    @Mapping(source = "telcliente", target = "telefonocliente")
    FacturasCompletasClienteDTO toDTO(IFacturasCompletasClienteProjection p);

    List<FacturasCompletasClienteDTO> toDTOList(
            List<IFacturasCompletasClienteProjection> projections
    );
}


Notas: Se debe verificar que se hallan importado las librerías correspondientes y se haya colocado la anotaciones para indicar que es un Mapper (@Mapper(componentModel=”spring”) y además se haya colocado la anotación para indicar los nombres diferentes de los atributos de la proyección y la DTO (@Mapping)


Crear el método dentro de la capa de Servicio (SCliente)


La capa de servicio se encarga de enlazar todo lo anterior y tiene la siguiente lógica:


Crear una interface que sirva de puente entre la lógica y los repositorios (no es aconsejable que un modulo de alto nivel interactúe con uno de bajo nivel, además la interface facilita las pruebas unitarias y la compartición de la información) 
public interface IFacturaCompletaService {
    List obtenerFacturasCompletasCliente(String idcliente);
}


Un método que consuma la consulta nativa a través de la DTO (records)


public class SFactura  implements IFacturaCompletaService{
    private final IFactura iFactura;
    private final IFacturaCompletaMapper iFacturaCompletaMapper;

    // Constructor
    public SFactura(IFactura iFactura, IFacturaCompletaMapper iFacturaCompletaMapper) {
        this.iFactura = iFactura;
        this.iFacturaCompletaMapper = iFacturaCompletaMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List obtenerFacturasCompletasCliente(String idcliente)  {
            List proyecciones = iFactura.buscarFacturasCompletasCliente(idcliente);
            return iFacturaCompletaMapper.toDTOList(proyecciones);
    }
}

