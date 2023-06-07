package tech.devinhouse.aviacao.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import tech.devinhouse.aviacao.repositories.models.Assento;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class AssentoRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private AssentoRepository repo;

    @Test
    @DisplayName("Quando for buscar por todos, deve retornar 60 assentos")
    void consultarPorTodos() {
        List<Assento> listaAssentos = repo.findAll();
        assertEquals(60, listaAssentos.size());
    }

}
