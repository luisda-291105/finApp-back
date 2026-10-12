package com.luisda.personalapp.springboot.personal_app.Repositorio;

import com.luisda.personalapp.springboot.personal_app.Modelo.MGrupo;
import com.luisda.personalapp.springboot.personal_app.Modelo.MBolsillo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class GrupoBolsilloRepositoryTest {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private BolsilloRepository bolsilloRepository;

    @Test
    void consultaGruposPorUsuarioYDevuelveVacioSinCoincidencias() {
        grupoRepository.save(new MGrupo(
                "grupo-1", "usuario-1", "Grupo uno", "Descripción uno", true,
                LocalDate.of(2026, 1, 10)));
        grupoRepository.save(new MGrupo(
                "grupo-2", "usuario-1", "Grupo dos", "Descripción dos", false,
                LocalDate.of(2026, 1, 11)));
        grupoRepository.save(new MGrupo(
                "grupo-3", "usuario-2", "Grupo tres", "Descripción tres", true,
                LocalDate.of(2026, 1, 12)));

        List<MGrupo> grupos = grupoRepository.findByIdUsuario("usuario-1");
        List<MGrupo> sinCoincidencias = grupoRepository.findByIdUsuario("usuario-3");

        assertEquals(2, grupos.size());
        assertTrue(grupos.stream().anyMatch(grupo -> "grupo-1".equals(grupo.getIdGrupo())));
        assertTrue(grupos.stream().anyMatch(grupo -> "grupo-2".equals(grupo.getIdGrupo())));
        assertTrue(grupos.stream().noneMatch(grupo -> "grupo-3".equals(grupo.getIdGrupo())));
        assertTrue(sinCoincidencias.isEmpty());
    }

    @Test
    void consultaBolsillosPorContabilidadYDevuelveVacioSinCoincidencias() {
        guardarBolsillosDePrueba();

        List<MBolsillo> bolsillos = bolsilloRepository.findByIdContabilidad("contabilidad-1");
        List<MBolsillo> sinCoincidencias = bolsilloRepository.findByIdContabilidad("contabilidad-3");

        assertEquals(2, bolsillos.size());
        assertTrue(bolsillos.stream().anyMatch(bolsillo -> "bolsillo-1".equals(bolsillo.getIdBolsillo())));
        assertTrue(bolsillos.stream().anyMatch(bolsillo -> "bolsillo-2".equals(bolsillo.getIdBolsillo())));
        assertTrue(bolsillos.stream().noneMatch(bolsillo -> "bolsillo-3".equals(bolsillo.getIdBolsillo())));
        assertTrue(sinCoincidencias.isEmpty());
    }

    @Test
    void consultaBolsillosPorTipoYDevuelveVacioSinCoincidencias() {
        guardarBolsillosDePrueba();

        List<MBolsillo> bolsillos = bolsilloRepository.findByTipo(MBolsillo.Tipo.GASTO);
        List<MBolsillo> sinCoincidencias = bolsilloRepository.findByTipo(MBolsillo.Tipo.OTRO);

        assertEquals(2, bolsillos.size());
        assertTrue(bolsillos.stream().anyMatch(bolsillo -> "bolsillo-1".equals(bolsillo.getIdBolsillo())));
        assertTrue(bolsillos.stream().anyMatch(bolsillo -> "bolsillo-3".equals(bolsillo.getIdBolsillo())));
        assertTrue(bolsillos.stream().noneMatch(bolsillo -> "bolsillo-2".equals(bolsillo.getIdBolsillo())));
        assertTrue(sinCoincidencias.isEmpty());
    }

    private void guardarBolsillosDePrueba() {
        bolsilloRepository.save(new MBolsillo(
                "bolsillo-1", "contabilidad-1", "Gastos", MBolsillo.Tipo.GASTO, true,
                LocalDate.of(2026, 1, 10)));
        bolsilloRepository.save(new MBolsillo(
                "bolsillo-2", "contabilidad-1", "Ingresos", MBolsillo.Tipo.INGRESO, false,
                LocalDate.of(2026, 1, 11)));
        bolsilloRepository.save(new MBolsillo(
                "bolsillo-3", "contabilidad-2", "Gastos secundarios", MBolsillo.Tipo.GASTO, false,
                LocalDate.of(2026, 1, 12)));
    }
}
