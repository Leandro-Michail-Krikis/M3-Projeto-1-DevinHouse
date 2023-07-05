package tech.devinhouse.aviacao.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import tech.devinhouse.aviacao.repositories.models.Passageiro;

@DataJpaTest
class PassageiroRepositoryTest {

    @Autowired
    private PassageiroRepository repo;

    @Test
    @DisplayName("Quando for buscar por todos, deve retornar 10 passageiros")
    void consultarPorTodos() {
        List<Passageiro> listaPassageiros = repo.findAll();
        assertEquals(10, listaPassageiros.size());
    }


}
