package com.luisda.personalapp.springboot.personal_app.Servicio;

import com.luisda.personalapp.springboot.personal_app.Modelo.MUsuario;
import com.luisda.personalapp.springboot.personal_app.Repositorio.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SUsuarioTest {

    private UsuarioRepository usuarioRepository;
    private SUsuario servicio;
    private MUsuario usuario;

    @BeforeEach
    void preparar() {
        usuarioRepository = mock(UsuarioRepository.class);
        servicio = new SUsuario(usuarioRepository);
        usuario = new MUsuario(
                "usuario-1", "Ana", "ana@example.test", "clave", null,
                LocalDate.of(2026, 1, 10));
    }

    @Test
    void adicionarUsuarioDelegaYDevuelveElResultado() {
        when(usuarioRepository.adicionarUsuario(usuario)).thenReturn(usuario);

        assertSame(usuario, servicio.adicionarUsuario(usuario));
        verify(usuarioRepository).adicionarUsuario(usuario);
    }

    @Test
    void eliminarUsuarioDelegaElIdentificador() {
        servicio.eliminarUsuario("usuario-1");

        verify(usuarioRepository).eliminarUsuario("usuario-1");
    }

    @Test
    void actualizarUsuarioDelegaYDevuelveElResultado() {
        when(usuarioRepository.actualizarUsuario(usuario)).thenReturn(usuario);

        assertSame(usuario, servicio.actualizarUsuario(usuario));
        verify(usuarioRepository).actualizarUsuario(usuario);
    }

    @Test
    void consultarTodosUsuariosDelegaYDevuelveElResultado() {
        List<MUsuario> usuarios = List.of(usuario);
        when(usuarioRepository.consultarTodosUsuarios()).thenReturn(usuarios);

        assertSame(usuarios, servicio.consultarTodosUsuarios());
        verify(usuarioRepository).consultarTodosUsuarios();
    }

    @Test
    void consultarUsuarioDelegaYDevuelveElResultado() {
        Optional<MUsuario> resultado = Optional.of(usuario);
        when(usuarioRepository.consultarUsuario("usuario-1")).thenReturn(resultado);

        assertSame(resultado, servicio.consultarUsuario("usuario-1"));
        verify(usuarioRepository).consultarUsuario("usuario-1");
    }

    @Test
    void consultarUsuarioPorContactoDelegaYDevuelveElResultado() {
        Optional<MUsuario> resultado = Optional.of(usuario);
        when(usuarioRepository.findByContacto("ana@example.test")).thenReturn(resultado);

        assertSame(resultado, servicio.consultarUsuarioPorContacto("ana@example.test"));
        verify(usuarioRepository).findByContacto("ana@example.test");
    }

    @Test
    void consultarUsuarioPorNombreDelegaYDevuelveElResultado() {
        Optional<MUsuario> resultado = Optional.of(usuario);
        when(usuarioRepository.findByNombre("Ana")).thenReturn(resultado);

        assertSame(resultado, servicio.consultarUsuarioPorNombre("Ana"));
        verify(usuarioRepository).findByNombre("Ana");
    }

    @Test
    void conservaResultadosVaciosDelRepositorio() {
        when(usuarioRepository.consultarTodosUsuarios()).thenReturn(List.of());
        when(usuarioRepository.consultarUsuario("inexistente")).thenReturn(Optional.empty());
        when(usuarioRepository.findByContacto("inexistente")).thenReturn(Optional.empty());
        when(usuarioRepository.findByNombre("inexistente")).thenReturn(Optional.empty());

        assertTrue(servicio.consultarTodosUsuarios().isEmpty());
        assertEquals(Optional.empty(), servicio.consultarUsuario("inexistente"));
        assertEquals(Optional.empty(), servicio.consultarUsuarioPorContacto("inexistente"));
        assertEquals(Optional.empty(), servicio.consultarUsuarioPorNombre("inexistente"));
    }

    @Test
    void envolverFalloAlAdicionarUsuarioConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(usuarioRepository.adicionarUsuario(usuario)).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.adicionarUsuario(usuario));

        assertEquals("No se pudo adicionar el usuario", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlEliminarUsuarioConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        doThrow(causa).when(usuarioRepository).eliminarUsuario("usuario-1");

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.eliminarUsuario("usuario-1"));

        assertEquals("No se pudo eliminar el usuario", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlActualizarUsuarioConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(usuarioRepository.actualizarUsuario(usuario)).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.actualizarUsuario(usuario));

        assertEquals("No se pudo actualizar el usuario", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlConsultarTodosLosUsuariosConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(usuarioRepository.consultarTodosUsuarios()).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.consultarTodosUsuarios());

        assertEquals("No se pudieron consultar los usuarios", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlConsultarUsuarioConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(usuarioRepository.consultarUsuario("usuario-1")).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.consultarUsuario("usuario-1"));

        assertEquals("No se pudo consultar el usuario", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlConsultarUsuarioPorContactoConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(usuarioRepository.findByContacto("ana@example.test")).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class,
                () -> servicio.consultarUsuarioPorContacto("ana@example.test"));

        assertEquals("No se pudo consultar el usuario por contacto", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlConsultarUsuarioPorNombreConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(usuarioRepository.findByNombre("Ana")).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.consultarUsuarioPorNombre("Ana"));

        assertEquals("No se pudo consultar el usuario por nombre", error.getMessage());
        assertSame(causa, error.getCause());
    }
}
