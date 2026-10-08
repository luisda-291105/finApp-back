
package com.luisda.personalapp.springboot.personal_app.Repositorio;

import com.Cesde.concesionario.Modelo.MContabilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContabilidadRepository extends JpaRepository<MContabilidad, String> {

    // Agrega una contabilidad.
    default MContabilidad adicionarContabilidad(MContabilidad contabilidad) {
        return save(contabilidad);
    }

    // Elimina una contabilidad por su identificador.
    default void eliminarContabilidad(String idContabilidad) {
        deleteById(idContabilidad);
    }

    // Actualiza una contabilidad existente.
    default MContabilidad actualizarContabilidad(MContabilidad contabilidad) {
        return save(contabilidad);
    }

    // Consulta todas las contabilidades.
    default List<MContabilidad> consultarTodasContabilidades() {
        return findAll();
    }

    // Consulta una contabilidad por su identificador.
    default Optional<MContabilidad> consultarContabilidad(String idContabilidad) {
        return findById(idContabilidad);
    }

    // Consulta las contabilidades de un usuario.
    List<MContabilidad> findByIdUsuario(String idUsuario);

    // Consulta contabilidades por estado.
    List<MContabilidad> findByEstado(boolean estado);

}