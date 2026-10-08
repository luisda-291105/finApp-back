package com.luisda.personalapp.springboot.personal_app.Repositorio;


import com.luisda.personalapp.springboot.personal_app.Modelo.MUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @uso               Persiste y consulta usuarios
 * @capa              Repositorio
 * @responsabilidades CRUD y búsquedas por contacto y nombre
 * @datos             Recibe y devuelve entidades MUsuario
 * @dependencias      MUsuario, Spring Data JPA
 * @usadoPor          SUsuario
 */

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

}
