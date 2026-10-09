package com.luisda.personalapp.springboot.personal_app.Repositorio;

import com.luisda.personalapp.springboot.personal_app.Modelo.MContabilidad;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ContabilidadRepositoryTest {

    @Autowired
    private ContabilidadRepository contabilidadRepository;

    @Test
    void consultaContabilidadesPorGrupo() {
        contabilidadRepository.save(new MContabilidad(
                "contabilidad-1", "grupo-1", null, "Cuenta principal", 120.5, true,
                LocalDate.of(2026, 1, 10)));
        contabilidadRepository.save(new MContabilidad(
                "contabilidad-2", "grupo-2", null, "Cuenta secundaria", 80.0, true,
                LocalDate.of(2026, 1, 11)));

        List<MContabilidad> contabilidades = contabilidadRepository.findByIdGrupo("grupo-1");

        assertEquals(1, contabilidades.size());
        assertEquals("contabilidad-1", contabilidades.getFirst().getIdContabilidad());
    }
}
