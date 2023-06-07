package tech.devinhouse.aviacao.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import tech.devinhouse.aviacao.repositories.models.Passageiro;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PassageiroRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private PassageiroRepository repo;

    @Test
    @DisplayName("Quando for buscar por todos, deve retornar 10 passageiros")
    void consultarPorTodos() {
        List<Passageiro> listaPassageiros = repo.findAll();
        assertEquals(10, listaPassageiros.size());
    }


}
