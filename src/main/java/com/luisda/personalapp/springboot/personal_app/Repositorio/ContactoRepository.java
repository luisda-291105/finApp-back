
package com.luisda.personalapp.springboot.personal_app.Repositorio;

import com.Cesde.concesionario.Modelo.MContacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactoRepository extends JpaRepository<MContacto, String> {

    // Agrega un contacto.
    default MContacto adicionarContacto(MContacto contacto) {
        return save(contacto);
    }

    // Elimina un contacto por su identificador.
    default void eliminarContacto(String idContacto) {
        deleteById(idContacto);
    }

    // Actualiza un contacto existente.
    default MContacto actualizarContacto(MContacto contacto) {
        return save(contacto);
    }

    // Consulta todos los contactos.
    default List<MContacto> consultarTodosContactos() {
        return findAll();
    }

    // Consulta un contacto por su identificador.
    default Optional<MContacto> consultarContacto(String idContacto) {
        return findById(idContacto);
    }

    // Consulta los contactos de un usuario.
    List<MContacto> findByIdUsuario(String idUsuario);

    // Consulta un contacto por nombre.
    Optional<MContacto> findByNombre(String nombre);

    // Consulta un contacto por teléfono.
    Optional<MContacto> findByTelefono(String telefono);

    // Consulta un contacto por correo.
    Optional<MContacto> findByCorreo(String correo);

}