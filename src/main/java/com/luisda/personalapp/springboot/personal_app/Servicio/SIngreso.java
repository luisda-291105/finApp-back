package com.luisda.personalapp.springboot.personal_app.Servicio;

import com.Cesde.concesionario.Modelo.MIngreso;
import com.luisda.personalapp.springboot.personal_app.Repositorio.IngresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SIngreso {
    private final IngresoRepository ingresoRepository;

    @Autowired
    public SIngreso(IngresoRepository ingresoRepository) {
        this.ingresoRepository = ingresoRepository;
    }

    // Agrega un ingreso.
    public MIngreso adicionarIngreso(MIngreso ingreso) {
        try {
            return ingresoRepository.adicionarIngreso(ingreso);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo adicionar el ingreso", exception);
        }
    }

    // Elimina un ingreso.
    public void eliminarIngreso(String idIngreso) {
        try {
            ingresoRepository.eliminarIngreso(idIngreso);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo eliminar el ingreso", exception);
        }
    }

    // Actualiza un ingreso.
    public MIngreso actualizarIngreso(MIngreso ingreso) {
        try {
            return ingresoRepository.actualizarIngreso(ingreso);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo actualizar el ingreso", exception);
        }
    }

    // Consulta todos los ingresos.
    public List<MIngreso> consultarTodosIngresos() {
        try {
            return ingresoRepository.consultarTodosIngresos();
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar los ingresos", exception);
        }
    }

    // Consulta un ingreso por identificador.
    public Optional<MIngreso> consultarIngreso(String idIngreso) {
        try {
            return ingresoRepository.consultarIngreso(idIngreso);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo consultar el ingreso", exception);
        }
    }

    // Consulta los ingresos de una contabilidad.
    public List<MIngreso> consultarIngresosPorContabilidad(String idContabilidad) {
        try {
            return ingresoRepository.findByIdContabilidad(idContabilidad);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar los ingresos por contabilidad", exception);
        }
    }
}
