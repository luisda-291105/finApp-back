package com.luisda.personalapp.springboot.personal_app.Repositorio;


import com.Cesde.concesionario.Modelo.MAhorro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AhorroRepository extends JpaRepository<MAhorro, String> {

    List<MAhorro> findByIdContabilidad(String idContabilidad);

}