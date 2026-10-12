package com.luisda.personalapp.springboot.personal_app.Repositorio;

import jakarta.persistence.EntityManager;
import com.luisda.personalapp.springboot.personal_app.Modelo.MFuente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class FuenteRepositoryTest {

    private static final String ID_FUENTE_1 = "d4bb50d1-a7d4-4bb8-8f8c-6847f2ae1438";
    private static final String ID_FUENTE_2 = "e370bd1e-2576-44bd-a741-bfd225c8e10a";

    @Autowired
    private FuenteRepository fuenteRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void adicionaFuenteYPersisteTodosSusDatos() {
        MFuente fuente = crearFuente(
                ID_FUENTE_1, "usuario-1", MFuente.Tipo.CORREO, "correo@ejemplo.com",
                true, LocalDate.of(2026, 1, 10));

        MFuente persistida = fuenteRepository.adicionarFuente(fuente);

        assertFuente(persistida, ID_FUENTE_1, "usuario-1", MFuente.Tipo.CORREO,
                "correo@ejemplo.com", true, LocalDate.of(2026, 1, 10));
        entityManager.flush();
        entityManager.clear();
        assertFuente(fuenteRepository.consultarFuente(ID_FUENTE_1).orElseThrow(),
                ID_FUENTE_1, "usuario-1", MFuente.Tipo.CORREO,
                "correo@ejemplo.com", true, LocalDate.of(2026, 1, 10));
    }

    @Test
    void actualizaFuenteExistenteYPersisteLosCambios() {
        MFuente fuente = fuenteRepository.adicionarFuente(crearFuente(
                ID_FUENTE_1, "usuario-1", MFuente.Tipo.CORREO, "antes@ejemplo.com",
                true, LocalDate.of(2026, 1, 10)));
        entityManager.flush();
        entityManager.clear();
        fuente = fuenteRepository.consultarFuente(ID_FUENTE_1).orElseThrow();
        entityManager.detach(fuente);
        fuente.setIdUsuario("usuario-2");
        fuente.setTipo(MFuente.Tipo.CHAT);
        fuente.setIdentificador("canal-actualizado");
        fuente.setEstado(false);
        fuente.setFechaCreacion(LocalDate.of(2026, 2, 11));

        MFuente actualizada = fuenteRepository.actualizarFuente(fuente);

        assertFuente(actualizada, ID_FUENTE_1, "usuario-2", MFuente.Tipo.CHAT,
                "canal-actualizado", false, LocalDate.of(2026, 2, 11));
        entityManager.flush();
        entityManager.clear();
        assertFuente(fuenteRepository.consultarFuente(ID_FUENTE_1).orElseThrow(),
                ID_FUENTE_1, "usuario-2", MFuente.Tipo.CHAT, "canal-actualizado",
                false, LocalDate.of(2026, 2, 11));
    }

    @Test
    void eliminaFuentePorIdentificador() {
        fuenteRepository.adicionarFuente(crearFuente(
                ID_FUENTE_1, "usuario-1", MFuente.Tipo.TELEFONO, "+1-555-0100",
                true, LocalDate.of(2026, 1, 10)));

        fuenteRepository.eliminarFuente(ID_FUENTE_1);

        assertTrue(fuenteRepository.consultarFuente(ID_FUENTE_1).isEmpty());
    }

    @Test
    void consultaTodasLasFuentesSinFiltrarPorUsuarioNiEstado() {
        fuenteRepository.adicionarFuente(crearFuente(
                ID_FUENTE_1, "usuario-1", MFuente.Tipo.CORREO, "uno@ejemplo.com",
                true, LocalDate.of(2026, 1, 10)));
        fuenteRepository.adicionarFuente(crearFuente(
                ID_FUENTE_2, "usuario-2", MFuente.Tipo.OTRO, "cuenta-dos",
                false, LocalDate.of(2026, 1, 11)));

        List<MFuente> fuentes = fuenteRepository.consultarTodasFuentes();

        assertEquals(2, fuentes.size());
        assertTrue(fuentes.stream().anyMatch(fuente ->
                ID_FUENTE_1.equals(fuente.getIdFuente())
                        && "usuario-1".equals(fuente.getIdUsuario())
                        && fuente.isEstado()));
        assertTrue(fuentes.stream().anyMatch(fuente ->
                ID_FUENTE_2.equals(fuente.getIdFuente())
                        && "usuario-2".equals(fuente.getIdUsuario())
                        && !fuente.isEstado()));
    }

    @Test
    void consultasVaciasDevuelvenColeccionVaciaYOptionalSinValor() {
        assertTrue(fuenteRepository.consultarTodasFuentes().isEmpty());
        Optional<MFuente> fuenteInexistente = fuenteRepository.consultarFuente("no-existe");
        assertFalse(fuenteInexistente.isPresent());
    }

    @Test
    void consultaFuenteExistentePorIdentificador() {
        fuenteRepository.adicionarFuente(crearFuente(
                ID_FUENTE_1, "usuario-1", MFuente.Tipo.OTRO, "cuenta-uno",
                false, LocalDate.of(2026, 1, 10)));

        MFuente encontrada = fuenteRepository.consultarFuente(ID_FUENTE_1).orElseThrow();

        assertFuente(encontrada, ID_FUENTE_1, "usuario-1", MFuente.Tipo.OTRO,
                "cuenta-uno", false, LocalDate.of(2026, 1, 10));
    }

    private MFuente crearFuente(String idFuente, String idUsuario, MFuente.Tipo tipo,
                                String identificador, boolean estado, LocalDate fechaCreacion) {
        return new MFuente(idFuente, idUsuario, tipo, identificador, estado, fechaCreacion);
    }

    private void assertFuente(MFuente fuente, String idFuente, String idUsuario,
                              MFuente.Tipo tipo, String identificador, boolean estado,
                              LocalDate fechaCreacion) {
        assertEquals(idFuente, fuente.getIdFuente());
        assertEquals(idUsuario, fuente.getIdUsuario());
        assertEquals(tipo, fuente.getTipo());
        assertEquals(identificador, fuente.getIdentificador());
        assertEquals(estado, fuente.isEstado());
        assertEquals(fechaCreacion, fuente.getFechaCreacion());
    }
}
