package com.luisda.personalapp.springboot.personal_app.Repositorio;

import com.luisda.personalapp.springboot.personal_app.Modelo.MGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @uso               Consulta grupos creados por usuario
 * @capa              Repositorio
 * @responsabilidades findByIdUsuario
 * @datos             Recibe identificador de usuario y devuelve entidades MGrupo
 * @dependencias      MGrupo, Spring Data JPA
 * @usadoPor          Capas consumidoras de repositorios
 */

@Repository
public interface GrupoRepository extends JpaRepository<MGrupo, String> {

    /** Devuelve los grupos cuyo identificador de creador coincide con el recibido. */
    List<MGrupo> findByIdUsuario(String idUsuario);
}
