package com.luisda.personalapp.springboot.personal_app.Servicio;

import com.luisda.personalapp.springboot.personal_app.Modelo.MBolsillo;
import com.luisda.personalapp.springboot.personal_app.Repositorio.BolsilloRepository;
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

class SBolsilloTest {

    private BolsilloRepository bolsilloRepository;
    private SBolsillo servicio;
    private MBolsillo bolsillo;

    @BeforeEach
    void preparar() {
        bolsilloRepository = mock(BolsilloRepository.class);
        servicio = new SBolsillo(bolsilloRepository);
        bolsillo = new MBolsillo(
                "bolsillo-1", "contabilidad-1", "Gastos", MBolsillo.Tipo.GASTO,
                true, LocalDate.of(2026, 1, 10));
    }

    @Test
    void adicionarBolsilloDelegaYDevuelveElResultado() {
        when(bolsilloRepository.save(bolsillo)).thenReturn(bolsillo);

        assertSame(bolsillo, servicio.adicionarBolsillo(bolsillo));
        verify(bolsilloRepository).save(bolsillo);
    }

    @Test
    void eliminarBolsilloDelegaElIdentificador() {
        servicio.eliminarBolsillo("bolsillo-1");

        verify(bolsilloRepository).deleteById("bolsillo-1");
    }

    @Test
    void actualizarBolsilloDelegaYDevuelveElResultado() {
        when(bolsilloRepository.save(bolsillo)).thenReturn(bolsillo);

        assertSame(bolsillo, servicio.actualizarBolsillo(bolsillo));
        verify(bolsilloRepository).save(bolsillo);
    }

    @Test
    void consultarTodosBolsillosDelegaYDevuelveElResultado() {
        List<MBolsillo> bolsillos = List.of(bolsillo);
        when(bolsilloRepository.findAll()).thenReturn(bolsillos);

        assertSame(bolsillos, servicio.consultarTodosBolsillos());
        verify(bolsilloRepository).findAll();
    }

    @Test
    void consultarBolsilloDelegaYDevuelveElResultado() {
        Optional<MBolsillo> resultado = Optional.of(bolsillo);
        when(bolsilloRepository.findById("bolsillo-1")).thenReturn(resultado);

        assertSame(resultado, servicio.consultarBolsillo("bolsillo-1"));
        verify(bolsilloRepository).findById("bolsillo-1");
    }

    @Test
    void consultarBolsillosPorContabilidadDelegaElCriterioIndependientemente() {
        List<MBolsillo> resultado = List.of(bolsillo);
        when(bolsilloRepository.findByIdContabilidad("contabilidad-1")).thenReturn(resultado);

        assertSame(resultado, servicio.consultarBolsillosPorContabilidad("contabilidad-1"));
        verify(bolsilloRepository).findByIdContabilidad("contabilidad-1");
    }

    @Test
    void consultarBolsillosPorTipoDelegaElCriterioIndependientemente() {
        List<MBolsillo> resultado = List.of(bolsillo);
        when(bolsilloRepository.findByTipo(MBolsillo.Tipo.GASTO)).thenReturn(resultado);

        assertSame(resultado, servicio.consultarBolsillosPorTipo(MBolsillo.Tipo.GASTO));
        verify(bolsilloRepository).findByTipo(MBolsillo.Tipo.GASTO);
    }

    @Test
    void conservaResultadosVaciosDelRepositorio() {
        when(bolsilloRepository.findAll()).thenReturn(List.of());
        when(bolsilloRepository.findById("inexistente")).thenReturn(Optional.empty());
        when(bolsilloRepository.findByIdContabilidad("inexistente")).thenReturn(List.of());
        when(bolsilloRepository.findByTipo(MBolsillo.Tipo.OTRO)).thenReturn(List.of());

        assertTrue(servicio.consultarTodosBolsillos().isEmpty());
        assertEquals(Optional.empty(), servicio.consultarBolsillo("inexistente"));
        assertTrue(servicio.consultarBolsillosPorContabilidad("inexistente").isEmpty());
        assertTrue(servicio.consultarBolsillosPorTipo(MBolsillo.Tipo.OTRO).isEmpty());
    }

    @Test
    void envolverFalloAlAdicionarBolsilloConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(bolsilloRepository.save(bolsillo)).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.adicionarBolsillo(bolsillo));

        assertEquals("No se pudo adicionar el bolsillo", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlEliminarBolsilloConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        doThrow(causa).when(bolsilloRepository).deleteById("bolsillo-1");

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.eliminarBolsillo("bolsillo-1"));

        assertEquals("No se pudo eliminar el bolsillo", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlActualizarBolsilloConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(bolsilloRepository.save(bolsillo)).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.actualizarBolsillo(bolsillo));

        assertEquals("No se pudo actualizar el bolsillo", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlConsultarTodosLosBolsillosConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(bolsilloRepository.findAll()).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.consultarTodosBolsillos());

        assertEquals("No se pudieron consultar los bolsillos", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlConsultarBolsilloConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(bolsilloRepository.findById("bolsillo-1")).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class, () -> servicio.consultarBolsillo("bolsillo-1"));

        assertEquals("No se pudo consultar el bolsillo", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlConsultarBolsillosPorContabilidadConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(bolsilloRepository.findByIdContabilidad("contabilidad-1")).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class,
                () -> servicio.consultarBolsillosPorContabilidad("contabilidad-1"));

        assertEquals("No se pudieron consultar los bolsillos por contabilidad", error.getMessage());
        assertSame(causa, error.getCause());
    }

    @Test
    void envolverFalloAlConsultarBolsillosPorTipoConservandoLaCausa() {
        IllegalArgumentException causa = new IllegalArgumentException("fallo");
        when(bolsilloRepository.findByTipo(MBolsillo.Tipo.GASTO)).thenThrow(causa);

        IllegalStateException error = assertThrows(
                IllegalStateException.class,
                () -> servicio.consultarBolsillosPorTipo(MBolsillo.Tipo.GASTO));

        assertEquals("No se pudieron consultar los bolsillos por tipo", error.getMessage());
        assertSame(causa, error.getCause());
    }
}
