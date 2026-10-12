package com.luisda.personalapp.springboot.personal_app.Repositorio;

import com.luisda.personalapp.springboot.personal_app.Modelo.MGasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @uso               Persiste y consulta gastos
 * @capa              Repositorio
 * @responsabilidades CRUD y búsqueda por contabilidad
 * @datos             Recibe y devuelve entidades MGasto
 * @dependencias      MGasto, Spring Data JPA
 * @usadoPor          SGasto
 */

@Repository
public interface GastoRepository extends JpaRepository<MGasto, String> {

    // Agrega un gasto.
    default MGasto adicionarGasto(MGasto gasto) {
        return save(gasto);
    }

    // Elimina un gasto por su identificador.
    default void eliminarGasto(String idGasto) {
        deleteById(idGasto);
    }

    // Actualiza un gasto existente.
    default MGasto actualizarGasto(MGasto gasto) {
        return save(gasto);
    }

    // Consulta todos los gastos.
    default List<MGasto> consultarTodosGastos() {
        return findAll();
    }

    // Consulta un gasto por su identificador.
    default Optional<MGasto> consultarGasto(String idGasto) {
        return findById(idGasto);
    }

    // Consulta los gastos de una contabilidad.
    List<MGasto> findByIdContabilidad(String idContabilidad);

}
