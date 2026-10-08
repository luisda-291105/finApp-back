package com.luisda.personalapp.springboot.personal_app.Repositorio;

import com.Cesde.concesionario.Modelo.MAhorro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AhorroRepository extends JpaRepository<MAhorro, String> {

    // Agrega un ahorro.
    default MAhorro adicionarAhorro(MAhorro ahorro) {
        return save(ahorro);
    }

    // Elimina un ahorro por su identificador.
    default void eliminarAhorro(String idAhorro) {
        deleteById(idAhorro);
    }

    // Actualiza un ahorro existente.
    default MAhorro actualizarAhorro(MAhorro ahorro) {
        return save(ahorro);
    }

    // Consulta todos los ahorros.
    default List<MAhorro> consultarTodosAhorros() {
        return findAll();
    }

    // Consulta un ahorro por su identificador.
    default Optional<MAhorro> consultarAhorro(String idAhorro) {
        return findById(idAhorro);
    }

    // Consulta los ahorros de una contabilidad.
    List<MAhorro> findByIdContabilidad(String idContabilidad);

}