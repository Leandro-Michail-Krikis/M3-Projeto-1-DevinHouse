package tech.devinhouse.aviacao.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.devinhouse.aviacao.controllers.dtos.request.ConfirmacaoRequest;
import tech.devinhouse.aviacao.enums.CategoriaFidelidadeEnum;
import tech.devinhouse.aviacao.exceptions.custom.AssentoEmergencialException;
import tech.devinhouse.aviacao.exceptions.custom.AssentoOcupadoException;
import tech.devinhouse.aviacao.exceptions.custom.CheckInJaRealizadoException;
import tech.devinhouse.aviacao.exceptions.custom.EntidadeNaoEncontradaException;
import tech.devinhouse.aviacao.repositories.CheckInRepository;
import tech.devinhouse.aviacao.repositories.models.Assento;
import tech.devinhouse.aviacao.repositories.models.CheckIn;
import tech.devinhouse.aviacao.repositories.models.Passageiro;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckInServiceTest {

    @InjectMocks
    private CheckInService checkInService;

    @Mock
    private PassageiroService passageiroService;

    @Mock
    private AssentoService assentoService;

    @Mock
    private CheckInRepository checkInRepository;

    @Test
    @DisplayName("Quando exise check-in com esse passageiro, deve retornar o check in")
    void findByPassageiroTest() {
        Passageiro passageiro = Passageiro.builder()
                .cpf("101.010.101-01")
                .nome("Passageiro de testes")
                .build();
        Assento assento = Assento.builder()
                .assentoId("0TESTE")
                .ocupado(false)
                .emergencial(false)
                .build();
        CheckIn checkInEsperado = CheckIn.builder()
                .assento(assento)
                .passageiro(passageiro)
                .eticket(UUID.randomUUID().toString())
                .build();

        when(checkInRepository.findByPassageiro(passageiro)).thenReturn(checkInEsperado);

        CheckIn retorno = checkInService.findByPassageiro(passageiro);
        assertEquals(checkInEsperado, retorno);
    }

    @Nested
    @DisplayName("Testes do metodo realiza")
    class RealizaTests {

        @Test
        @DisplayName("Quando o passageiro nao existe, deve retornar exception EntidadeNaoEncontradaException")
        void realizaTest_passageiroInexistente() {
            var confirmacaoRequest = ConfirmacaoRequest.builder()
                .cpf("101.010.101-01")
                .assento("0TESTE")
                .malasDespachadas(true)
                .build();
            when(passageiroService.findById(anyString())).thenReturn(Optional.empty());
            assertThrows(EntidadeNaoEncontradaException.class, ()-> {
                checkInService.realiza(confirmacaoRequest);
            });
        }

        @Test
        @DisplayName("Quando o assento nao existe, deve retornar exception EntidadeNaoEncontradaException")
        void realizaTest_assentoInexistente() {
            var passageiro = Passageiro.builder()
                    .cpf("101.010.101-01")
                    .nome("Passageiro de testes")
                    .build();
            var confirmacaoRequest = ConfirmacaoRequest.builder()
                    .cpf("101.010.101-01")
                    .assento("0TESTE")
                    .malasDespachadas(true)
                    .build();
            when(passageiroService.findById("101.010.101-01")).thenReturn(Optional.of(passageiro));
            when(assentoService.findById(anyString())).thenReturn(Optional.empty());
            assertThrows(EntidadeNaoEncontradaException.class, ()-> {
                checkInService.realiza(confirmacaoRequest);
            });
        }

        @Test
        @DisplayName("Quando o passageiro ja realizou checkin, deve retornar exception CheckInJaRealizadoException")
        void realizaTest_checkJaRealizado() {
            var passageiro = Passageiro.builder()
                    .cpf("101.010.101-01")
                    .nome("Passageiro de testes")
                    .build();
            var assento = Assento.builder()
                    .assentoId("0TESTE")
                    .ocupado(false)
                    .emergencial(false)
                    .build();
            var confirmacaoRequest = ConfirmacaoRequest.builder()
                    .cpf("101.010.101-01")
                    .assento("0TESTE")
                    .malasDespachadas(true)
                    .build();
            when(passageiroService.findById("101.010.101-01")).thenReturn(Optional.of(passageiro));
            when(assentoService.findById("0TESTE")).thenReturn(Optional.of(assento));
            when(checkInRepository.existsByPassageiro(passageiro)).thenReturn(true);
            assertThrows(CheckInJaRealizadoException.class, ()-> {
                checkInService.realiza(confirmacaoRequest);
            });
        }

        @Test
        @DisplayName("Quando o assento esta ocupado, deve retornar exception AssentoOcupadoException")
        void realizaTest_assentoOcupado() {
            var passageiro = Passageiro.builder()
                    .cpf("101.010.101-01")
                    .nome("Passageiro de testes")
                    .build();
            var assento = Assento.builder()
                    .assentoId("0TESTE")
                    .ocupado(true)
                    .emergencial(false)
                    .build();
            var confirmacaoRequest = ConfirmacaoRequest.builder()
                    .cpf("101.010.101-01")
                    .assento("0TESTE")
                    .malasDespachadas(true)
                    .build();
            when(passageiroService.findById("101.010.101-01")).thenReturn(Optional.of(passageiro));
            when(assentoService.findById("0TESTE")).thenReturn(Optional.of(assento));
            when(checkInRepository.existsByPassageiro(passageiro)).thenReturn(false);
            assertThrows(AssentoOcupadoException.class, ()-> {
                checkInService.realiza(confirmacaoRequest);
            });
        }

        @Test
        @DisplayName("Quando o assento for emergencial e o passageiro for menor que 18 anos, deve retornar exception AssentoOcupadoException")
        void realizaTest_assentoEmergencial_passageiroMenorde18() {
            var passageiro = Passageiro.builder()
                    .cpf("101.010.101-01")
                    .nome("Passageiro de testes")
                    .dataNascimento(LocalDate.of(2010, 05, 20))
                    .build();
            var assento = Assento.builder()
                    .assentoId("0TESTE")
                    .ocupado(false)
                    .emergencial(true)
                    .build();
            var confirmacaoRequest = ConfirmacaoRequest.builder()
                    .cpf("101.010.101-01")
                    .assento("0TESTE")
                    .malasDespachadas(true)
                    .build();
            when(passageiroService.findById("101.010.101-01")).thenReturn(Optional.of(passageiro));
            when(assentoService.findById("0TESTE")).thenReturn(Optional.of(assento));
            when(checkInRepository.existsByPassageiro(passageiro)).thenReturn(false);
            assertThrows(AssentoEmergencialException.class, ()-> {
                checkInService.realiza(confirmacaoRequest);
            });
        }

        @Test
        @DisplayName("Quando o assento for emergencial e o passageiro nao tenha despachado as malas, deve retornar exception AssentoEmergencialException")
        void realizaTest_assentoEmergencial_malasNaoDespachadas() {
            var passageiro = Passageiro.builder()
                    .cpf("101.010.101-01")
                    .nome("Passageiro de testes")
                    .dataNascimento(LocalDate.of(1996, 05, 20))
                    .build();
            var assento = Assento.builder()
                    .assentoId("0TESTE")
                    .ocupado(false)
                    .emergencial(true)
                    .build();
            var confirmacaoRequest = ConfirmacaoRequest.builder()
                    .cpf("101.010.101-01")
                    .assento("0TESTE")
                    .malasDespachadas(false)
                    .build();
            when(passageiroService.findById("101.010.101-01")).thenReturn(Optional.of(passageiro));
            when(assentoService.findById("0TESTE")).thenReturn(Optional.of(assento));
            when(checkInRepository.existsByPassageiro(passageiro)).thenReturn(false);
            assertThrows(AssentoEmergencialException.class, ()-> {
                checkInService.realiza(confirmacaoRequest);
            });
        }

        @Test
        @DisplayName("Quando realizar check-in com sucesso, deve retornar o check-in realizado")
        void realizaTest_sucesso() {
            var passageiro = Passageiro.builder()
                    .cpf("101.010.101-01")
                    .nome("Passageiro de testes")
                    .classificacao(CategoriaFidelidadeEnum.ASSOCIADO)
                    .milhas(100)
                    .dataNascimento(LocalDate.of(1996, 05, 20))
                    .build();
            var assento = Assento.builder()
                    .assentoId("0TESTE")
                    .fileira(10)
                    .poltrona("TESTE")
                    .ocupado(false)
                    .emergencial(false)
                    .build();
            var confirmacaoRequest = ConfirmacaoRequest.builder()
                    .cpf("101.010.101-01")
                    .assento("0TESTE")
                    .malasDespachadas(true)
                    .build();
            var checkIn = CheckIn.builder()
                .passageiro(passageiro)
                .assento(assento)
                .dataHoraConfirmacao(LocalDateTime.now())
                .eticket(UUID.randomUUID().toString())
                .build();
            when(passageiroService.findById("101.010.101-01")).thenReturn(Optional.of(passageiro));
            when(assentoService.findById(anyString())).thenReturn(Optional.of(assento));
            when(checkInRepository.existsByPassageiro(passageiro)).thenReturn(false);
            when(checkInRepository.save(any())).thenReturn(checkIn);
            CheckIn retorno = checkInService.realiza(confirmacaoRequest);
            assertEquals(checkIn, retorno);
        }

    }

}
