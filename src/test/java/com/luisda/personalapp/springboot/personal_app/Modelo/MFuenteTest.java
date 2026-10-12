package com.luisda.personalapp.springboot.personal_app.Modelo;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class MFuenteTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void constructorYAccesoresConservanLosSeisCampos() {
        LocalDate fechaCreacion = LocalDate.of(2026, 10, 12);
        MFuente fuente = new MFuente(
                "d4bb50d1-a7d4-4bb8-8f8c-6847f2ae1438",
                "usuario-externo",
                MFuente.Tipo.CORREO,
                "persona@example.com",
                true,
                fechaCreacion);

        assertEquals("d4bb50d1-a7d4-4bb8-8f8c-6847f2ae1438", fuente.getIdFuente());
        assertEquals("usuario-externo", fuente.getIdUsuario());
        assertEquals(MFuente.Tipo.CORREO, fuente.getTipo());
        assertEquals("persona@example.com", fuente.getIdentificador());
        assertTrue(fuente.isEstado());
        assertEquals(fechaCreacion, fuente.getFechaCreacion());

        fuente.setIdFuente("e370bd1e-2576-44bd-a741-bfd225c8e10a");
        fuente.setIdUsuario("otro-usuario");
        fuente.setTipo(MFuente.Tipo.CHAT);
        fuente.setIdentificador("cuenta-chat");
        fuente.setEstado(false);
        fuente.setFechaCreacion(LocalDate.of(2026, 10, 13));

        assertEquals("e370bd1e-2576-44bd-a741-bfd225c8e10a", fuente.getIdFuente());
        assertEquals("otro-usuario", fuente.getIdUsuario());
        assertEquals(MFuente.Tipo.CHAT, fuente.getTipo());
        assertEquals("cuenta-chat", fuente.getIdentificador());
        assertFalse(fuente.isEstado());
        assertEquals(LocalDate.of(2026, 10, 13), fuente.getFechaCreacion());
    }

    @Test
    void tipoIncluyeLosValoresAprobados() {
        assertArrayEquals(
                new MFuente.Tipo[] {
                        MFuente.Tipo.CORREO,
                        MFuente.Tipo.CHAT,
                        MFuente.Tipo.TELEFONO,
                        MFuente.Tipo.OTRO
                },
                MFuente.Tipo.values());
    }

    @Test
    void persisteTipoComoTextoYReferenciaEscalarSinUsuarioRelacionado() {
        String idFuente = "d4bb50d1-a7d4-4bb8-8f8c-6847f2ae1438";
        MFuente fuente = new MFuente(
                idFuente,
                "usuario-que-no-existe",
                MFuente.Tipo.TELEFONO,
                "+525512345678",
                true,
                LocalDate.of(2026, 10, 12));

        entityManager.persist(fuente);
        entityManager.flush();

        assertEquals(
                "TELEFONO",
                jdbcTemplate.queryForObject(
                        "select tipo from MFuente where id_fuente = ?", String.class, idFuente));

        entityManager.clear();
        MFuente recuperada = entityManager.find(MFuente.class, idFuente);

        assertEquals(MFuente.Tipo.TELEFONO, recuperada.getTipo());
        assertEquals("usuario-que-no-existe", recuperada.getIdUsuario());
        assertEquals("+525512345678", recuperada.getIdentificador());
        assertTrue(recuperada.isEstado());
        assertEquals(LocalDate.of(2026, 10, 12), recuperada.getFechaCreacion());
    }
}
