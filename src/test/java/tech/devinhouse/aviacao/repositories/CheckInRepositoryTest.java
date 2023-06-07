package tech.devinhouse.aviacao.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import tech.devinhouse.aviacao.repositories.models.Assento;
import tech.devinhouse.aviacao.repositories.models.CheckIn;
import tech.devinhouse.aviacao.repositories.models.Passageiro;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CheckInRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private CheckInRepository repo;

    @Nested
    @DisplayName("Testes do metodo findByPassageiro")
    class FindByPassageiroTest {

        @Test
        @DisplayName("Quando existe um CheckIn para o Passageiro, deve retornar CheckIn")
        void findByPassageiro_existe() {
            Passageiro passageiro = em.persist(Passageiro.builder()
                    .cpf("101.010.101-01")
                    .nome("Passageiro de testes")
                    .build());
            Assento assento = em.persist(Assento.builder()
                    .assentoId("0TESTE")
                    .ocupado(false)
                    .emergencial(false)
                    .build());
            CheckIn checkInEsperado = CheckIn.builder()
                    .assento(assento)
                    .passageiro(passageiro)
                    .eticket(UUID.randomUUID().toString())
                    .build();
            checkInEsperado = em.persist(checkInEsperado);

            CheckIn checkInDoBanco = repo.findByPassageiro(passageiro);

            assertEquals(checkInEsperado, checkInDoBanco);
        }

        @Test
        @DisplayName("Quando nao existem CheckIns para o Passageiro, deve retornar null")
        void findByPassageiro_naoExiste() {
            Passageiro passageiro = em.persist(Passageiro.builder()
                    .cpf("101.010.101-01")
                    .nome("Passageiro de testes")
                    .build());

            CheckIn checkInDoBanco = repo.findByPassageiro(passageiro);

            assertNull(checkInDoBanco);
        }
    }

    @Nested
    @DisplayName("Testes do metodo existsByPassageiro")
    class ExistsByPassageiroTest {

        @Test
        @DisplayName("Quando existe um CheckIn para o Passageiro, deve retornar true")
        void existsByPassageiro_existe() {
            Passageiro passageiro = em.persist(Passageiro.builder()
                    .cpf("101.010.101-01")
                    .nome("Passageiro de testes")
                    .build());
            Assento assento = em.persist(Assento.builder()
                    .assentoId("0TESTE")
                    .ocupado(false)
                    .emergencial(false)
                    .build());
            CheckIn checkInEsperado = CheckIn.builder()
                    .assento(assento)
                    .passageiro(passageiro)
                    .eticket(UUID.randomUUID().toString())
                    .build();
            em.persist(checkInEsperado);

            boolean exists = repo.existsByPassageiro(passageiro);

            assertTrue(exists);
        }

        @Test
        @DisplayName("Quando nao existem CheckIns para o Passageiro, deve retornar false")
        void existsByPassageiro_naoExiste() {
            Passageiro passageiro = em.persist(Passageiro.builder()
                    .cpf("101.010.101-01")
                    .nome("Passageiro de testes")
                    .build());

            boolean exists = repo.existsByPassageiro(passageiro);

            assertFalse(exists);
        }
    }
}
