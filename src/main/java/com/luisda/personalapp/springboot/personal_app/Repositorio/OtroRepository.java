package com.luisda.personalapp.springboot.personal_app.Repositorio;

import com.Cesde.concesionario.Modelo.MOtro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OtroRepository extends JpaRepository<MOtro, String> {

    // Agrega otro movimiento.
    default MOtro adicionarOtro(MOtro otro) {
        return save(otro);
    }

    // Elimina otro movimiento por su identificador.
    default void eliminarOtro(String idOtro) {
        deleteById(idOtro);
    }

    // Actualiza otro movimiento existente.
    default MOtro actualizarOtro(MOtro otro) {
        return save(otro);
    }

    // Consulta todos los movimientos adicionales.
    default List<MOtro> consultarTodosOtros() {
        return findAll();
    }

    // Consulta otro movimiento por su identificador.
    default Optional<MOtro> consultarOtro(String idOtro) {
        return findById(idOtro);
    }

    // Consulta otros movimientos de una contabilidad.
    List<MOtro> findByIdContabilidad(String idContabilidad);

}