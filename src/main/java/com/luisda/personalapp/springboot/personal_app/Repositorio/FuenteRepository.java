package com.luisda.personalapp.springboot.personal_app.Repositorio;

import com.luisda.personalapp.springboot.personal_app.Modelo.MFuente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @uso               Persiste y consulta fuentes de usuarios
 * @capa              Repositorio
 * @responsabilidades CRUD básico de fuentes
 * @datos             Recibe y devuelve entidades MFuente
 * @dependencias      MFuente, Spring Data JPA
 * @usadoPor          Capas de servicio consumidoras
 */
@Repository
public interface FuenteRepository extends JpaRepository<MFuente, String> {

    default MFuente adicionarFuente(MFuente fuente) {
        return save(fuente);
    }

    default void eliminarFuente(String idFuente) {
        deleteById(idFuente);
    }

    default MFuente actualizarFuente(MFuente fuente) {
        return save(fuente);
    }

    default List<MFuente> consultarTodasFuentes() {
        return findAll();
    }

    default Optional<MFuente> consultarFuente(String idFuente) {
        return findById(idFuente);
    }
}
