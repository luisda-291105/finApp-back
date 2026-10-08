
package com.luisda.personalapp.springboot.personal_app.Repositorio;

import com.Cesde.concesionario.Modelo.MContacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactoRepository extends JpaRepository<MContacto, String> {

    List<MContacto> findByIdUsuario(String idUsuario);

}