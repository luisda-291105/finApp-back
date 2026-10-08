
package com.luisda.personalapp.springboot.personal_app.Repositorio;


import com.Cesde.concesionario.Dto.UsuarioContactoDTO;
import com.Cesde.concesionario.Dto.UsuarioContabilidadDTO;
import com.Cesde.concesionario.Dto.UsuarioGastoDTO;
import com.Cesde.concesionario.Dto.UsuarioIngresoDTO;
import com.Cesde.concesionario.Dto.UsuarioAhorroDTO;
import com.Cesde.concesionario.Dto.UsuarioOtroDTO;
import com.Cesde.concesionario.Modelo.MUsuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<MUsuario, String> {

    // Agrega un usuario.
    default MUsuario adicionarUsuario(MUsuario usuario) {
        return save(usuario);
    }

    // Elimina un usuario por su identificador.
    default void eliminarUsuario(String idUsuario) {
        deleteById(idUsuario);
    }

    // Actualiza un usuario existente.
    default MUsuario actualizarUsuario(MUsuario usuario) {
        return save(usuario);
    }

    // Consulta todos los usuarios.
    default List<MUsuario> consultarTodosUsuarios() {
        return findAll();
    }

    // Consulta un usuario por su identificador.
    default Optional<MUsuario> consultarUsuario(String idUsuario) {
        return findById(idUsuario);
    }

    // Consulta un usuario por su contacto.
    Optional<MUsuario> findByContacto(String correo);

    // Consulta un usuario por su nombre.
    Optional<MUsuario> findByNombre(String nombre);

        // Consulta un usuario con sus contactos.
        @Query("SELECT new com.Cesde.concesionario.Dto.UsuarioContactoDTO(" +
            "u.idUsuario, u.nombre, c.idContacto, c.nombre, c.telefono, " +
            "c.correo, c.tipo, c.notas) " +
            "FROM MUsuario u, MContacto c " +
            "WHERE c.idUsuario = u.idUsuario " +
            "AND u.idUsuario = :idUsuario")
        List<UsuarioContactoDTO> consultarContactosDeUsuario(@Param("idUsuario") String idUsuario);

        // Consulta un usuario con sus contabilidades.
        @Query("SELECT new com.Cesde.concesionario.Dto.UsuarioContabilidadDTO(" +
            "u.idUsuario, u.nombre, c.idContabilidad, c.nombre, c.valor, " +
            "c.estado, c.fechaCreacion) " +
            "FROM MUsuario u, MContabilidad c " +
            "WHERE c.idUsuario = u.idUsuario " +
            "AND u.idUsuario = :idUsuario")
        List<UsuarioContabilidadDTO> consultarContabilidadesDeUsuario(@Param("idUsuario") String idUsuario);

        // Consulta los gastos relacionados con un usuario.
        @Query("SELECT new com.Cesde.concesionario.Dto.UsuarioGastoDTO(" +
            "u.idUsuario, u.nombre, c.idContabilidad, c.nombre, g.idGasto, " +
            "g.categoria, g.descripcion, g.valor, g.fecha) " +
            "FROM MUsuario u, MContabilidad c, MGasto g " +
            "WHERE c.idUsuario = u.idUsuario " +
            "AND g.idContabilidad = c.idContabilidad " +
            "AND u.idUsuario = :idUsuario")
        List<UsuarioGastoDTO> consultarGastosDeUsuario(@Param("idUsuario") String idUsuario);

        // Consulta los ingresos relacionados con un usuario.
        @Query("SELECT new com.Cesde.concesionario.Dto.UsuarioIngresoDTO(" +
            "u.idUsuario, u.nombre, c.idContabilidad, c.nombre, i.idIngreso, " +
            "i.categoria, i.descripcion, i.valor, i.fecha) " +
            "FROM MUsuario u, MContabilidad c, MIngreso i " +
            "WHERE c.idUsuario = u.idUsuario " +
            "AND i.idContabilidad = c.idContabilidad " +
            "AND u.idUsuario = :idUsuario")
        List<UsuarioIngresoDTO> consultarIngresosDeUsuario(@Param("idUsuario") String idUsuario);

        // Consulta los ahorros relacionados con un usuario.
        @Query("SELECT new com.Cesde.concesionario.Dto.UsuarioAhorroDTO(" +
            "u.idUsuario, u.nombre, c.idContabilidad, c.nombre, a.idAhorro, " +
            "a.descripcion, a.valor, a.fecha) " +
            "FROM MUsuario u, MContabilidad c, MAhorro a " +
            "WHERE c.idUsuario = u.idUsuario " +
            "AND a.idContabilidad = c.idContabilidad " +
            "AND u.idUsuario = :idUsuario")
        List<UsuarioAhorroDTO> consultarAhorrosDeUsuario(@Param("idUsuario") String idUsuario);

        // Consulta otros movimientos relacionados con un usuario.
        @Query("SELECT new com.Cesde.concesionario.Dto.UsuarioOtroDTO(" +
            "u.idUsuario, u.nombre, c.idContabilidad, c.nombre, o.idOtro, " +
            "o.descripcion, o.valor, o.fecha) " +
            "FROM MUsuario u, MContabilidad c, MOtro o " +
            "WHERE c.idUsuario = u.idUsuario " +
            "AND o.idContabilidad = c.idContabilidad " +
            "AND u.idUsuario = :idUsuario")
        List<UsuarioOtroDTO> consultarOtrosDeUsuario(@Param("idUsuario") String idUsuario);

}