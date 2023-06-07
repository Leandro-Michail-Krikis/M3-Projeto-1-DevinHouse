package tech.devinhouse.aviacao.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.devinhouse.aviacao.enums.CategoriaFidelidadeEnum;
import tech.devinhouse.aviacao.repositories.PassageiroRepository;
import tech.devinhouse.aviacao.repositories.models.Passageiro;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PassageiroServiceTest {
    @Mock
    private PassageiroRepository passageiroRepository;
    @InjectMocks
    private PassageiroService passageiroService;

    @Test
    @DisplayName("Quando existe passageiros, deve retornar todos os passageiros")
    void findAllTest() {
        Passageiro passageiro1 = Passageiro.builder()
                .cpf("101.010.101-01")
                .nome("Passageiro de testes1")
                .build();
        Passageiro passageiro2 = Passageiro.builder()
                .cpf("101.010.101-02")
                .nome("Passageiro de testes2")
                .build();
        Passageiro passageiro3 = Passageiro.builder()
                .cpf("101.010.101-03")
                .nome("Passageiro de testes3")
                .build();

        when(passageiroRepository.findAll()).thenReturn(Arrays.asList(passageiro1, passageiro2, passageiro3));
        List<Passageiro> listaPassageiros = passageiroService.findAll();
        assertEquals(3, listaPassageiros.size());
    }

    @Test
    @DisplayName("Quando existe passageiro com id especifico, deve retornar o assento")
    void findByIdTest() {
        Passageiro passageiro = Passageiro.builder()
                .cpf("101.010.101-01")
                .nome("Passageiro de testes1")
                .build();
        when(passageiroRepository.findById("101.010.101-01")).thenReturn(Optional.of(passageiro));
        Optional<Passageiro> retorno = passageiroService.findById("101.010.101-01");
        assertTrue(retorno.isPresent());
        assertEquals("101.010.101-01", retorno.get().getCpf());
    }

    @Test
    @DisplayName("Quando atualiza as milhas do passageiro VIP, deve retornar a entidade atualizada corretamente")
    void atualizaMilhasPassageiroTest_VIP() {
        Passageiro passageiro = Passageiro.builder()
                .cpf("101.010.101-01")
                .nome("Passageiro de testes")
                .classificacao(CategoriaFidelidadeEnum.VIP)
                .milhas(100)
                .build();
        when(passageiroRepository.save(any(Passageiro.class))).thenReturn(passageiro);

        Passageiro retorno = passageiroService.atualizaMilhasPassageiro(passageiro);

        assertEquals(200, retorno.getMilhas());
    }

    @Test
    @DisplayName("Quando atualiza as milhas do passageiro OURO, deve retornar a entidade atualizada corretamente")
    void atualizaMilhasPassageiroTest_OURO() {
        Passageiro passageiro = Passageiro.builder()
                .cpf("101.010.101-01")
                .nome("Passageiro de testes")
                .classificacao(CategoriaFidelidadeEnum.OURO)
                .milhas(100)
                .build();
        when(passageiroRepository.save(any(Passageiro.class))).thenReturn(passageiro);

        Passageiro retorno = passageiroService.atualizaMilhasPassageiro(passageiro);

        assertEquals(180, retorno.getMilhas());
    }

    @Test
    @DisplayName("Quando atualiza as milhas do passageiro PRATA, deve retornar a entidade atualizada corretamente")
    void atualizaMilhasPassageiroTest_PRATA() {
        Passageiro passageiro = Passageiro.builder()
                .cpf("101.010.101-01")
                .nome("Passageiro de testes")
                .classificacao(CategoriaFidelidadeEnum.PRATA)
                .milhas(100)
                .build();
        when(passageiroRepository.save(any(Passageiro.class))).thenReturn(passageiro);

        Passageiro retorno = passageiroService.atualizaMilhasPassageiro(passageiro);

        assertEquals(150, retorno.getMilhas());
    }

    @Test
    @DisplayName("Quando atualiza as milhas do passageiro BRONZE, deve retornar a entidade atualizada corretamente")
    void atualizaMilhasPassageiroTest_BRONZE() {
        Passageiro passageiro = Passageiro.builder()
                .cpf("101.010.101-01")
                .nome("Passageiro de testes")
                .classificacao(CategoriaFidelidadeEnum.BRONZE)
                .milhas(100)
                .build();
        when(passageiroRepository.save(any(Passageiro.class))).thenReturn(passageiro);

        Passageiro retorno = passageiroService.atualizaMilhasPassageiro(passageiro);

        assertEquals(130, retorno.getMilhas());
    }

    @Test
    @DisplayName("Quando atualiza as milhas do passageiro ASSOCIADO, deve retornar a entidade atualizada corretamente")
    void atualizaMilhasPassageiroTest_ASSOCIADO() {
        Passageiro passageiro = Passageiro.builder()
                .cpf("101.010.101-01")
                .nome("Passageiro de testes")
                .classificacao(CategoriaFidelidadeEnum.ASSOCIADO)
                .milhas(100)
                .build();
        when(passageiroRepository.save(any(Passageiro.class))).thenReturn(passageiro);

        Passageiro retorno = passageiroService.atualizaMilhasPassageiro(passageiro);

        assertEquals(110, retorno.getMilhas());
    }

}
