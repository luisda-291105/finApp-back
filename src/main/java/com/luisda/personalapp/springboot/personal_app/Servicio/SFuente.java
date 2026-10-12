package com.luisda.personalapp.springboot.personal_app.Servicio;

import com.luisda.personalapp.springboot.personal_app.Modelo.MFuente;
import com.luisda.personalapp.springboot.personal_app.Repositorio.FuenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @uso               Gestiona las operaciones CRUD disponibles de fuentes
 * @capa              Servicio
 * @responsabilidades Adicionar, eliminar, actualizar y consultar fuentes
 * @datos             Recibe y devuelve entidades MFuente
 * @dependencias      FuenteRepository
 * @usadoPor          Capas consumidoras de servicios
 */
@Service
public class SFuente {
    private final FuenteRepository fuenteRepository;

    public SFuente(FuenteRepository fuenteRepository) {
        this.fuenteRepository = fuenteRepository;
    }

    /** Adiciona una fuente mediante el alias disponible en el repositorio. */
    public MFuente adicionarFuente(MFuente fuente) {
        try {
            return fuenteRepository.adicionarFuente(fuente);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo adicionar la fuente", exception);
        }
    }

    /** Elimina la fuente identificada mediante el alias del repositorio. */
    public void eliminarFuente(String idFuente) {
        try {
            fuenteRepository.eliminarFuente(idFuente);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo eliminar la fuente", exception);
        }
    }

    /** Actualiza una fuente mediante el alias disponible en el repositorio. */
    public MFuente actualizarFuente(MFuente fuente) {
        try {
            return fuenteRepository.actualizarFuente(fuente);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo actualizar la fuente", exception);
        }
    }

    /** Consulta todas las fuentes mediante el alias del repositorio. */
    public List<MFuente> consultarTodasFuentes() {
        try {
            return fuenteRepository.consultarTodasFuentes();
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar las fuentes", exception);
        }
    }

    /** Consulta una fuente por identificador mediante el alias del repositorio. */
    public Optional<MFuente> consultarFuente(String idFuente) {
        try {
            return fuenteRepository.consultarFuente(idFuente);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo consultar la fuente", exception);
        }
    }
}
