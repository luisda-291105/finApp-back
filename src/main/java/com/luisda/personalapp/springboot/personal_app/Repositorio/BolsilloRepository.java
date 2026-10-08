package com.luisda.personalapp.springboot.personal_app.Repositorio;

import com.luisda.personalapp.springboot.personal_app.Modelo.MBolsillo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @uso               Consulta bolsillos por contabilidad o tipo
 * @capa              Repositorio
 * @responsabilidades findByIdContabilidad, findByTipo
 * @datos             Recibe criterios y devuelve entidades MBolsillo
 * @dependencias      MBolsillo, Spring Data JPA
 * @usadoPor          Capas consumidoras de repositorios
 */
@Repository
public interface BolsilloRepository extends JpaRepository<MBolsillo, String> {

    /** Devuelve los bolsillos asociados a la contabilidad indicada. */
    List<MBolsillo> findByIdContabilidad(String idContabilidad);

    /** Devuelve los bolsillos cuyo tipo coincide con el valor recibido. */
    List<MBolsillo> findByTipo(MBolsillo.Tipo tipo);
}
