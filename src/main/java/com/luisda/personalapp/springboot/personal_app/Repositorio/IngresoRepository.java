package com.luisda.personalapp.springboot.personal_app.Repositorio;

import com.Cesde.concesionario.Modelo.MIngreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngresoRepository extends JpaRepository<MIngreso, String> {

    // Agrega un ingreso.
    default MIngreso adicionarIngreso(MIngreso ingreso) {
        return save(ingreso);
    }

    // Elimina un ingreso por su identificador.
    default void eliminarIngreso(String idIngreso) {
        deleteById(idIngreso);
    }

    // Actualiza un ingreso existente.
    default MIngreso actualizarIngreso(MIngreso ingreso) {
        return save(ingreso);
    }

    // Consulta todos los ingresos.
    default List<MIngreso> consultarTodosIngresos() {
        return findAll();
    }

    // Consulta un ingreso por su identificador.
    default Optional<MIngreso> consultarIngreso(String idIngreso) {
        return findById(idIngreso);
    }

    // Consulta los ingresos de una contabilidad.
    List<MIngreso> findByIdContabilidad(String idContabilidad);

}