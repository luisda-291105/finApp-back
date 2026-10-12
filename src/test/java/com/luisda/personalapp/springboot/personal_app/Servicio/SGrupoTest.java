package com.luisda.personalapp.springboot.personal_app.Servicio;

import com.luisda.personalapp.springboot.personal_app.Modelo.MGrupo;
import com.luisda.personalapp.springboot.personal_app.Repositorio.GrupoRepository;
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

class SGrupoTest {

    private GrupoRepository grupoRepository;
    private SGrupo servicio;
    private MGrupo grupo;

    @BeforeEach
    void preparar() {
        grupoRepository = mock(GrupoRepository.class);
        servicio = new SGrupo(grupoRepository);
        grupo = new MGrupo(
                "grupo-1", "usuario-1", "Grupo", "Descripción", true,
                LocalDate.of(2026, 1, 10));
    }

    @Test
    void adicionarGrupoDelegaYDevuelveElResultado() {
        when(grupoRepository.save(grupo)).thenReturn(grupo);

        assertSame(grupo, servicio.adicionarGrupo(grupo));
        verify(grupoRepository).save(grupo);
    }

    @Test
    void eliminarGrupoDelegaElIdentificador() {
        servicio.eliminarGrupo("grupo-1");

        verify(grupoRepository).deleteById("grupo-1");
    }

    @Test
    void actualizarGrupoDelegaYDevuelveElResultado() {
        when(grupoRepository.save(grupo)).thenReturn(grupo);

        assertSame(grupo, servicio.actualizarGrupo(grupo));
        verify(grupoRepository).save(grupo);
    }

    @Test
    void consultarTodosGruposDelegaYDevuelveElResultado() {
        List<MGrupo> grupos = List.of(grupo);
        when(grupoRepository.findAll()).thenReturn(grupos);

        assertSame(grupos, servicio.consultarTodosGrupos());
        verify(grupoRepository).findAll();
    }

    @Test
    void consultarGrupoDelegaYDevuelveElResultado() {
        Optional<MGrupo> resultado = Optional.of(grupo);
        when(grupoRepository.findById("grupo-1")).thenReturn(resultado);

        assertSame(resultado, servicio.consultarGrupo("grupo-1"));
        verify(grupoRepository).findById("grupo-1");
    }

    @Test
    void consultarGruposPorUsuarioCreadorDelegaElIdentificador() {
        List<MGrupo> resultado = List.of(grupo);
        when(grupoRepository.findByIdUsuario("usuario-1")).thenReturn(resultado);

        assertSame(resultado, servicio.consultarGruposPorUsuario("usuario-1"));
        verify(grupoRepository).findByIdUsuario("usuario-1");
    }

    @Test
    void conservaResultadosVaciosDelRepositorio() {
        when(grupoRepository.findAll()).thenReturn(List.of());
        when(grupoRepository.findById("inexistente")).thenReturn(Optional.empty());
        when(grupoRepository.findByIdUsuario("inexistente")).thenReturn(List.of());

        assertTrue(servicio.consultarTodosGrupos().isEmpty());
        assertEquals(Optional.empty(), servicio.consultarGrupo("inexistente"));
        assertTrue(servicio.consultarGruposPorUsuario("inexistente").isEmpty());
    }

    @Test
    void envolverFalloAlAdicionarGrupoConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(grupoRepository.save(grupo)).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.adicionarGrupo(grupo));

        assertEquals("No se pudo adicionar el grupo", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlEliminarGrupoConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        doThrow(causa).when(grupoRepository).deleteById("grupo-1");

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.eliminarGrupo("grupo-1"));

        assertEquals("No se pudo eliminar el grupo", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlActualizarGrupoConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(grupoRepository.save(grupo)).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.actualizarGrupo(grupo));

        assertEquals("No se pudo actualizar el grupo", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlConsultarTodosLosGruposConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(grupoRepository.findAll()).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.consultarTodosGrupos());

        assertEquals("No se pudieron consultar los grupos", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlConsultarGrupoConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(grupoRepository.findById("grupo-1")).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.consultarGrupo("grupo-1"));

        assertEquals("No se pudo consultar el grupo", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlConsultarGruposPorUsuarioConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(grupoRepository.findByIdUsuario("usuario-1")).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class,
                () -> servicio.consultarGruposPorUsuario("usuario-1"));

        assertEquals("No se pudieron consultar los grupos por usuario", error.getMessage());
        assertSame(causa, error.getCause());
    }
}
