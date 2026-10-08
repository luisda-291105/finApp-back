package com.luisda.personalapp.springboot.personal_app.Servicio;

import com.Cesde.concesionario.Modelo.MOtro;
import com.luisda.personalapp.springboot.personal_app.Repositorio.OtroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SOtro {
    private final OtroRepository otroRepository;

    @Autowired
    public SOtro(OtroRepository otroRepository) {
        this.otroRepository = otroRepository;
    }

    // Agrega otro movimiento.
    public MOtro adicionarOtro(MOtro otro) {
        try {
            return otroRepository.adicionarOtro(otro);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo adicionar el movimiento", exception);
        }
    }

    // Elimina otro movimiento.
    public void eliminarOtro(String idOtro) {
        try {
            otroRepository.eliminarOtro(idOtro);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo eliminar el movimiento", exception);
        }
    }

    // Actualiza otro movimiento.
    public MOtro actualizarOtro(MOtro otro) {
        try {
            return otroRepository.actualizarOtro(otro);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo actualizar el movimiento", exception);
        }
    }

    // Consulta todos los movimientos adicionales.
    public List<MOtro> consultarTodosOtros() {
        try {
            return otroRepository.consultarTodosOtros();
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar los movimientos", exception);
        }
    }

    // Consulta otro movimiento por identificador.
    public Optional<MOtro> consultarOtro(String idOtro) {
        try {
            return otroRepository.consultarOtro(idOtro);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo consultar el movimiento", exception);
        }
    }

    // Consulta otros movimientos de una contabilidad.
    public List<MOtro> consultarOtrosPorContabilidad(String idContabilidad) {
        try {
            return otroRepository.findByIdContabilidad(idContabilidad);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar los movimientos por contabilidad", exception);
        }
    }
}
