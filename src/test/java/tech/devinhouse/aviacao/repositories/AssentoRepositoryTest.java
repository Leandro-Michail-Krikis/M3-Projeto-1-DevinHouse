package tech.devinhouse.aviacao.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import tech.devinhouse.aviacao.repositories.models.Assento;

@DataJpaTest
class AssentoRepositoryTest {

    @Autowired
    private AssentoRepository repo;

    @Test
    @DisplayName("Quando for buscar por todos, deve retornar 60 assentos")
    void consultarPorTodos() {
        List<Assento> listaAssentos = repo.findAll();
        assertEquals(60, listaAssentos.size());
    }

}
