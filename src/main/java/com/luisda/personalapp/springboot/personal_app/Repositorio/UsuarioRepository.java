
package com.luisda.personalapp.springboot.personal_app.Repositorio;


import com.Cesde.concesionario.Modelo.MUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<MUsuario, String> {

    Optional<MUsuario> findByContacto(String correo);

}