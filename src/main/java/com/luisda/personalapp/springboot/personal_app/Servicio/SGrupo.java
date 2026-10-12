package com.luisda.personalapp.springboot.personal_app.Servicio;

import com.luisda.personalapp.springboot.personal_app.Modelo.MGrupo;
import com.luisda.personalapp.springboot.personal_app.Repositorio.GrupoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @uso               Gestiona las operaciones disponibles de grupos
 * @capa              Servicio
 * @responsabilidades CRUD y consulta por identificador del usuario creador
 * @datos             Recibe y devuelve entidades MGrupo
 * @dependencias      GrupoRepository
 * @usadoPor          Capas consumidoras de servicios
 */
@Service
public class SGrupo {
    private final GrupoRepository grupoRepository;

    public SGrupo(GrupoRepository grupoRepository) {
        this.grupoRepository = grupoRepository;
    }

    /** Guarda un grupo y devuelve la entidad resultante. */
    public MGrupo adicionarGrupo(MGrupo grupo) {
        try {
            return grupoRepository.save(grupo);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo adicionar el grupo", exception);
        }
    }

    /** Elimina el grupo identificado. */
    public void eliminarGrupo(String idGrupo) {
        try {
            grupoRepository.deleteById(idGrupo);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo eliminar el grupo", exception);
        }
    }

    /** Guarda los cambios de un grupo y devuelve la entidad resultante. */
    public MGrupo actualizarGrupo(MGrupo grupo) {
        try {
            return grupoRepository.save(grupo);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo actualizar el grupo", exception);
        }
    }

    /** Devuelve todos los grupos registrados. */
    public List<MGrupo> consultarTodosGrupos() {
        try {
            return grupoRepository.findAll();
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar los grupos", exception);
        }
    }

    /** Busca un grupo por identificador. */
    public Optional<MGrupo> consultarGrupo(String idGrupo) {
        try {
            return grupoRepository.findById(idGrupo);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo consultar el grupo", exception);
        }
    }

    /** Busca grupos creados por el usuario indicado; no consulta membresías. */
    public List<MGrupo> consultarGruposPorUsuario(String idUsuario) {
        try {
            return grupoRepository.findByIdUsuario(idUsuario);
        } catch (RuntimeException exception) {
            throw new IllegalStateException(
                    "No se pudieron consultar los grupos por usuario", exception);
        }
    }
}
