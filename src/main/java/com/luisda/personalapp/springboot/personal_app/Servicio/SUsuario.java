package com.luisda.personalapp.springboot.personal_app.Servicio;

import com.Cesde.concesionario.Dto.UsuarioContactoDTO;
import com.Cesde.concesionario.Dto.UsuarioContabilidadDTO;
import com.Cesde.concesionario.Dto.UsuarioGastoDTO;
import com.Cesde.concesionario.Dto.UsuarioIngresoDTO;
import com.Cesde.concesionario.Dto.UsuarioAhorroDTO;
import com.Cesde.concesionario.Dto.UsuarioOtroDTO;
import com.Cesde.concesionario.Modelo.MUsuario;
import com.luisda.personalapp.springboot.personal_app.Repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SUsuario {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public SUsuario(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Agrega un usuario.
    public MUsuario adicionarUsuario(MUsuario usuario) {
        try {
            return usuarioRepository.adicionarUsuario(usuario);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo adicionar el usuario", exception);
        }
    }

    // Elimina un usuario.
    public void eliminarUsuario(String idUsuario) {
        try {
            usuarioRepository.eliminarUsuario(idUsuario);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo eliminar el usuario", exception);
        }
    }

    // Actualiza un usuario.
    public MUsuario actualizarUsuario(MUsuario usuario) {
        try {
            return usuarioRepository.actualizarUsuario(usuario);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo actualizar el usuario", exception);
        }
    }

    // Consulta todos los usuarios.
    public List<MUsuario> consultarTodosUsuarios() {
        try {
            return usuarioRepository.consultarTodosUsuarios();
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar los usuarios", exception);
        }
    }

    // Consulta un usuario por identificador.
    public Optional<MUsuario> consultarUsuario(String idUsuario) {
        try {
            return usuarioRepository.consultarUsuario(idUsuario);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo consultar el usuario", exception);
        }
    }

    // Consulta un usuario por contacto.
    public Optional<MUsuario> consultarUsuarioPorContacto(String contacto) {
        try {
            return usuarioRepository.findByContacto(contacto);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo consultar el usuario por contacto", exception);
        }
    }

    // Consulta un usuario por nombre.
    public Optional<MUsuario> consultarUsuarioPorNombre(String nombre) {
        try {
            return usuarioRepository.findByNombre(nombre);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudo consultar el usuario por nombre", exception);
        }
    }

    // Consulta los contactos relacionados con un usuario.
    public List<UsuarioContactoDTO> consultarContactosDeUsuario(String idUsuario) {
        try {
            return usuarioRepository.consultarContactosDeUsuario(idUsuario);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar los contactos del usuario", exception);
        }
    }

    // Consulta las contabilidades relacionadas con un usuario.
    public List<UsuarioContabilidadDTO> consultarContabilidadesDeUsuario(String idUsuario) {
        try {
            return usuarioRepository.consultarContabilidadesDeUsuario(idUsuario);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar las contabilidades del usuario", exception);
        }
    }

    // Consulta los gastos relacionados con un usuario.
    public List<UsuarioGastoDTO> consultarGastosDeUsuario(String idUsuario) {
        try {
            return usuarioRepository.consultarGastosDeUsuario(idUsuario);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar los gastos del usuario", exception);
        }
    }

    // Consulta los ingresos relacionados con un usuario.
    public List<UsuarioIngresoDTO> consultarIngresosDeUsuario(String idUsuario) {
        try {
            return usuarioRepository.consultarIngresosDeUsuario(idUsuario);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar los ingresos del usuario", exception);
        }
    }

    // Consulta los ahorros relacionados con un usuario.
    public List<UsuarioAhorroDTO> consultarAhorrosDeUsuario(String idUsuario) {
        try {
            return usuarioRepository.consultarAhorrosDeUsuario(idUsuario);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar los ahorros del usuario", exception);
        }
    }

    // Consulta otros movimientos relacionados con un usuario.
    public List<UsuarioOtroDTO> consultarOtrosDeUsuario(String idUsuario) {
        try {
            return usuarioRepository.consultarOtrosDeUsuario(idUsuario);
        } catch (RuntimeException exception) {
            throw new IllegalStateException("No se pudieron consultar otros movimientos del usuario", exception);
        }
    }
}
