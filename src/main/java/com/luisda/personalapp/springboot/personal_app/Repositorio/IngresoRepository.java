package com.luisda.personalapp.springboot.personal_app.Repositorio;

import com.Cesde.concesionario.Modelo.MIngreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngresoRepository extends JpaRepository<MIngreso, String> {

    List<MIngreso> findByIdContabilidad(String idContabilidad);

}