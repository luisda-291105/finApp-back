package com.luisda.personalapp.springboot.personal_app.Servicio;

import com.Cesde.concesionario.Modelo.MContabilidad;
import com.luisda.personalapp.springboot.personal_app.Repositorio.ContabilidadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SContabilidad {
    private final ContabilidadRepository contabilidadRepository;

    @Autowired
    public SContabilidad(ContabilidadRepository contabilidadRepository) {
        this.contabilidadRepository = contabilidadRepository;
    }

    // Agrega una contabilidad.
    public MContabilidad adicionarContabilidad(MContabilidad contabilidad) {
        try {
            return contabilidadRepository.adicionarContabilidad(contabilidad);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo adicionar la contabilidad", exception);
        }
    }

    // Elimina una contabilidad.
    public void eliminarContabilidad(String idContabilidad) {
        try {
            contabilidadRepository.eliminarContabilidad(idContabilidad);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo eliminar la contabilidad", exception);
        }
    }

    // Actualiza una contabilidad.
    public MContabilidad actualizarContabilidad(MContabilidad contabilidad) {
        try {
            return contabilidadRepository.actualizarContabilidad(contabilidad);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo actualizar la contabilidad", exception);
        }
    }

    // Consulta todas las contabilidades.
    public List<MContabilidad> consultarTodasContabilidades() {
        try {
            return contabilidadRepository.consultarTodasContabilidades();
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar las contabilidades", exception);
        }
    }

    // Consulta una contabilidad por identificador.
    public Optional<MContabilidad> consultarContabilidad(String idContabilidad) {
        try {
            return contabilidadRepository.consultarContabilidad(idContabilidad);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo consultar la contabilidad", exception);
        }
    }

    // Consulta las contabilidades de un usuario.
    public List<MContabilidad> consultarContabilidadesPorUsuario(String idUsuario) {
        try {
            return contabilidadRepository.findByIdUsuario(idUsuario);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar las contabilidades por usuario", exception);
        }
    }

    // Consulta contabilidades por estado.
    public List<MContabilidad> consultarContabilidadesPorEstado(boolean estado) {
        try {
            return contabilidadRepository.findByEstado(estado);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar las contabilidades por estado", exception);
        }
    }
}
