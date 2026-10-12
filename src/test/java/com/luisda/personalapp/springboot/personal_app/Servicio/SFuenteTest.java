package com.luisda.personalapp.springboot.personal_app.Servicio;

import com.luisda.personalapp.springboot.personal_app.Modelo.MFuente;
import com.luisda.personalapp.springboot.personal_app.Repositorio.FuenteRepository;
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

class SFuenteTest {

    private FuenteRepository fuenteRepository;
    private SFuente servicio;
    private MFuente fuente;

    @BeforeEach
    void preparar() {
        fuenteRepository = mock(FuenteRepository.class);
        servicio = new SFuente(fuenteRepository);
        fuente = new MFuente(
                "fuente-1", "usuario-1", MFuente.Tipo.CORREO, "correo@ejemplo.test",
                true, LocalDate.of(2026, 1, 10));
    }

    @Test
    void adicionarFuenteDelegaYDevuelveElResultado() {
        when(fuenteRepository.adicionarFuente(fuente)).thenReturn(fuente);

        assertSame(fuente, servicio.adicionarFuente(fuente));
        verify(fuenteRepository).adicionarFuente(fuente);
    }

    @Test
    void eliminarFuenteDelegaElIdentificador() {
        servicio.eliminarFuente("fuente-1");

        verify(fuenteRepository).eliminarFuente("fuente-1");
    }

    @Test
    void actualizarFuenteDelegaYDevuelveElResultado() {
        when(fuenteRepository.actualizarFuente(fuente)).thenReturn(fuente);

        assertSame(fuente, servicio.actualizarFuente(fuente));
        verify(fuenteRepository).actualizarFuente(fuente);
    }

    @Test
    void consultarTodasFuentesDelegaYDevuelveElResultado() {
        List<MFuente> fuentes = List.of(fuente);
        when(fuenteRepository.consultarTodasFuentes()).thenReturn(fuentes);

        assertSame(fuentes, servicio.consultarTodasFuentes());
        verify(fuenteRepository).consultarTodasFuentes();
    }

    @Test
    void consultarFuenteDelegaYDevuelveElResultado() {
        Optional<MFuente> resultado = Optional.of(fuente);
        when(fuenteRepository.consultarFuente("fuente-1")).thenReturn(resultado);

        assertSame(resultado, servicio.consultarFuente("fuente-1"));
        verify(fuenteRepository).consultarFuente("fuente-1");
    }

    @Test
    void conservaResultadosVaciosDelRepositorio() {
        when(fuenteRepository.consultarTodasFuentes()).thenReturn(List.of());
        when(fuenteRepository.consultarFuente("inexistente")).thenReturn(Optional.empty());

        assertTrue(servicio.consultarTodasFuentes().isEmpty());
        assertEquals(Optional.empty(), servicio.consultarFuente("inexistente"));
    }

    @Test
    void envolverFalloAlAdicionarFuenteConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(fuenteRepository.adicionarFuente(fuente)).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.adicionarFuente(fuente));

        assertEquals("No se pudo adicionar la fuente", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlEliminarFuenteConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        doThrow(causa).when(fuenteRepository).eliminarFuente("fuente-1");

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.eliminarFuente("fuente-1"));

        assertEquals("No se pudo eliminar la fuente", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlActualizarFuenteConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(fuenteRepository.actualizarFuente(fuente)).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.actualizarFuente(fuente));

        assertEquals("No se pudo actualizar la fuente", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlConsultarTodasLasFuentesConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(fuenteRepository.consultarTodasFuentes()).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.consultarTodasFuentes());

        assertEquals("No se pudieron consultar las fuentes", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlConsultarFuenteConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(fuenteRepository.consultarFuente("fuente-1")).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.consultarFuente("fuente-1"));

        assertEquals("No se pudo consultar la fuente", error.getMessage());
        assertSame(causa, error.getCause());
    }
}
