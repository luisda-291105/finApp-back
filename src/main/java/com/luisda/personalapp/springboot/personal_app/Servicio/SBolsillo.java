package com.luisda.personalapp.springboot.personal_app.Servicio;

import com.luisda.personalapp.springboot.personal_app.Modelo.MBolsillo;
import com.luisda.personalapp.springboot.personal_app.Repositorio.BolsilloRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @uso               Gestiona las operaciones disponibles de bolsillos
 * @capa              Servicio
 * @responsabilidades CRUD, consulta por contabilidad y consulta por tipo
 * @datos             Recibe y devuelve entidades MBolsillo
 * @dependencias      BolsilloRepository
 * @usadoPor          Capas consumidoras de servicios
 */
@Service
public class SBolsillo {
    private final BolsilloRepository bolsilloRepository;

    public SBolsillo(BolsilloRepository bolsilloRepository) {
        this.bolsilloRepository = bolsilloRepository;
    }

    /** Guarda un bolsillo y devuelve la entidad resultante. */
    public MBolsillo adicionarBolsillo(MBolsillo bolsillo) {
        try {
            return bolsilloRepository.save(bolsillo);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo adicionar el bolsillo", exception);
        }
    }

    /** Elimina el bolsillo identificado. */
    public void eliminarBolsillo(String idBolsillo) {
        try {
            bolsilloRepository.deleteById(idBolsillo);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo eliminar el bolsillo", exception);
        }
    }

    /** Guarda los cambios de un bolsillo y devuelve la entidad resultante. */
    public MBolsillo actualizarBolsillo(MBolsillo bolsillo) {
        try {
            return bolsilloRepository.save(bolsillo);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo actualizar el bolsillo", exception);
        }
    }

    /** Devuelve todos los bolsillos registrados. */
    public List<MBolsillo> consultarTodosBolsillos() {
        try {
            return bolsilloRepository.findAll();
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar los bolsillos", exception);
        }
    }

    /** Busca un bolsillo por identificador. */
    public Optional<MBolsillo> consultarBolsillo(String idBolsillo) {
        try {
            return bolsilloRepository.findById(idBolsillo);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo consultar el bolsillo", exception);
        }
    }

    /** Busca bolsillos asociados a una contabilidad. */
    public List<MBolsillo> consultarBolsillosPorContabilidad(String idContabilidad) {
        try {
            return bolsilloRepository.findByIdContabilidad(idContabilidad);
        } catch (RuntimeException exception) {
            throw new IllegalStateException(
                    "No se pudieron consultar los bolsillos por contabilidad", exception);
        }
    }

    /** Busca bolsillos del tipo indicado, independientemente de su contabilidad. */
    public List<MBolsillo> consultarBolsillosPorTipo(MBolsillo.Tipo tipo) {
        try {
            return bolsilloRepository.findByTipo(tipo);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar los bolsillos por tipo", exception);
        }
    }
}
