package com.luisda.personalapp.springboot.personal_app.Servicio;

import com.Cesde.concesionario.Modelo.MContacto;
import com.luisda.personalapp.springboot.personal_app.Repositorio.ContactoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SContacto {
    private final ContactoRepository contactoRepository;

    @Autowired
    public SContacto(ContactoRepository contactoRepository) {
        this.contactoRepository = contactoRepository;
    }

    // Agrega un contacto.
    public MContacto adicionarContacto(MContacto contacto) {
        try {
            return contactoRepository.adicionarContacto(contacto);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo adicionar el contacto", exception);
        }
    }

    // Elimina un contacto.
    public void eliminarContacto(String idContacto) {
        try {
            contactoRepository.eliminarContacto(idContacto);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo eliminar el contacto", exception);
        }
    }

    // Actualiza un contacto.
    public MContacto actualizarContacto(MContacto contacto) {
        try {
            return contactoRepository.actualizarContacto(contacto);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo actualizar el contacto", exception);
        }
    }

    // Consulta todos los contactos.
    public List<MContacto> consultarTodosContactos() {
        try {
            return contactoRepository.consultarTodosContactos();
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar los contactos", exception);
        }
    }

    // Consulta un contacto por identificador.
    public Optional<MContacto> consultarContacto(String idContacto) {
        try {
            return contactoRepository.consultarContacto(idContacto);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo consultar el contacto", exception);
        }
    }

    // Consulta los contactos de un usuario.
    public List<MContacto> consultarContactosPorUsuario(String idUsuario) {
        try {
            return contactoRepository.findByIdUsuario(idUsuario);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar los contactos por usuario", exception);
        }
    }

    // Consulta un contacto por nombre.
    public Optional<MContacto> consultarContactoPorNombre(String nombre) {
        try {
            return contactoRepository.findByNombre(nombre);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo consultar el contacto por nombre", exception);
        }
    }

    // Consulta un contacto por teléfono.
    public Optional<MContacto> consultarContactoPorTelefono(String telefono) {
        try {
            return contactoRepository.findByTelefono(telefono);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo consultar el contacto por teléfono", exception);
        }
    }

    // Consulta un contacto por correo.
    public Optional<MContacto> consultarContactoPorCorreo(String correo) {
        try {
            return contactoRepository.findByCorreo(correo);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo consultar el contacto por correo", exception);
        }
    }
}
