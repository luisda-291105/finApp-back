package com.luisda.personalapp.springboot.personal_app.Repositorio;


import com.Cesde.concesionario.Modelo.MGasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GastoRepository extends JpaRepository<MGasto, String> {

    List<MGasto> findByIdContabilidad(String idContabilidad);

}