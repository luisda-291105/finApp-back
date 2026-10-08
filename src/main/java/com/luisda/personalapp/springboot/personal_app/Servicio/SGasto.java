package com.luisda.personalapp.springboot.personal_app.Servicio;

import com.Cesde.concesionario.Modelo.MGasto;
import com.luisda.personalapp.springboot.personal_app.Repositorio.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SGasto {
    private final GastoRepository gastoRepository;

    @Autowired
    public SGasto(GastoRepository gastoRepository) {
        this.gastoRepository = gastoRepository;
    }

    // Agrega un gasto.
    public MGasto adicionarGasto(MGasto gasto) {
        try {
            return gastoRepository.adicionarGasto(gasto);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo adicionar el gasto", exception);
        }
    }

    // Elimina un gasto.
    public void eliminarGasto(String idGasto) {
        try {
            gastoRepository.eliminarGasto(idGasto);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo eliminar el gasto", exception);
        }
    }

    // Actualiza un gasto.
    public MGasto actualizarGasto(MGasto gasto) {
        try {
            return gastoRepository.actualizarGasto(gasto);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo actualizar el gasto", exception);
        }
    }

    // Consulta todos los gastos.
    public List<MGasto> consultarTodosGastos() {
        try {
            return gastoRepository.consultarTodosGastos();
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar los gastos", exception);
        }
    }

    // Consulta un gasto por identificador.
    public Optional<MGasto> consultarGasto(String idGasto) {
        try {
            return gastoRepository.consultarGasto(idGasto);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo consultar el gasto", exception);
        }
    }

    // Consulta los gastos de una contabilidad.
    public List<MGasto> consultarGastosPorContabilidad(String idContabilidad) {
        try {
            return gastoRepository.findByIdContabilidad(idContabilidad);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar los gastos por contabilidad", exception);
        }
    }
}
