
package com.luisda.personalapp.springboot.personal_app.Repositorio;

import com.Cesde.concesionario.Modelo.MContabilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContabilidadRepository extends JpaRepository<MContabilidad, String> {

    List<MContabilidad> findByIdUsuario(String idUsuario);

}