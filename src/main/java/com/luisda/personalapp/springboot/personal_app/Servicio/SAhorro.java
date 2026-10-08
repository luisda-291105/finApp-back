package com.luisda.personalapp.springboot.personal_app.Servicio;

import com.Cesde.concesionario.Modelo.MAhorro;
import com.luisda.personalapp.springboot.personal_app.Repositorio.AhorroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SAhorro {
    private final AhorroRepository ahorroRepository;

    @Autowired
    public SAhorro(AhorroRepository ahorroRepository) {
        this.ahorroRepository = ahorroRepository;
    }

    // Agrega un ahorro.
    public MAhorro adicionarAhorro(MAhorro ahorro) {
        try {
            return ahorroRepository.adicionarAhorro(ahorro);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo adicionar el ahorro", exception);
        }
    }

    // Elimina un ahorro.
    public void eliminarAhorro(String idAhorro) {
        try {
            ahorroRepository.eliminarAhorro(idAhorro);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo eliminar el ahorro", exception);
        }
    }

    // Actualiza un ahorro.
    public MAhorro actualizarAhorro(MAhorro ahorro) {
        try {
            return ahorroRepository.actualizarAhorro(ahorro);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo actualizar el ahorro", exception);
        }
    }

    // Consulta todos los ahorros.
    public List<MAhorro> consultarTodosAhorros() {
        try {
            return ahorroRepository.consultarTodosAhorros();
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar los ahorros", exception);
        }
    }

    // Consulta un ahorro por identificador.
    public Optional<MAhorro> consultarAhorro(String idAhorro) {
        try {
            return ahorroRepository.consultarAhorro(idAhorro);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo consultar el ahorro", exception);
        }
    }

    // Consulta los ahorros de una contabilidad.
    public List<MAhorro> consultarAhorrosPorContabilidad(String idContabilidad) {
        try {
            return ahorroRepository.findByIdContabilidad(idContabilidad);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar los ahorros por contabilidad", exception);
        }
    }
}
