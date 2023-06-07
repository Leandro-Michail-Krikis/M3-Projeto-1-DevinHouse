package tech.devinhouse.aviacao.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.devinhouse.aviacao.controllers.dtos.request.ConfirmacaoRequest;
import tech.devinhouse.aviacao.controllers.dtos.response.PassageiroResponse;
import tech.devinhouse.aviacao.controllers.mappers.PassageiroMapper;
import tech.devinhouse.aviacao.enums.CategoriaFidelidadeEnum;
import tech.devinhouse.aviacao.repositories.models.Assento;
import tech.devinhouse.aviacao.repositories.models.CheckIn;
import tech.devinhouse.aviacao.repositories.models.Passageiro;
import tech.devinhouse.aviacao.service.CheckInService;
import tech.devinhouse.aviacao.service.PassageiroService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PassageirosController.class)
public class PassageirosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PassageiroService passageiroService;

    @MockBean
    private CheckInService checkInService;

    @MockBean
    private PassageiroMapper passageiroMapper;

    @Autowired
    private ObjectMapper objectMapper;
    @Nested
    @DisplayName("Testes do endpoint getAll ")
    class getAllTests {
        @Test
        @DisplayName("Quando não há passageiros registrados, deve retornar lista vazia")
        void getAll_quandoNaoAPassageiros_deveRetornarListaVazia() throws Exception {

            when(passageiroService.findAll()).thenReturn(new ArrayList<>());

            mockMvc.perform(get("/passageiros"))
                    .andExpect(status().isOk())
                    .andExpect(content().json("[]"));

        }

        @Test
        @DisplayName("Quando há passageiros registrados, deve retornar lista ")
        void getAll_quandoNaoAPassageiros_deveRetornarLista() throws Exception {
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

            when(passageiroService.findAll()).thenReturn(List.of(passageiro1, passageiro2, passageiro3));

            mockMvc.perform(get("/passageiros"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$", hasSize(3)));


        }
    }

    @Nested
    @DisplayName("Testes do endpoint getPorCpf ")
    class getPorCpfTests {
        @Test
        @DisplayName("Quando há passageiro registrado, deve retornar ele")
        void getPorCpf_quandoAPassageiro_deveRetornarEle() throws Exception {
            Passageiro passageiro = Passageiro.builder()
                    .cpf("101.010.101-01")
                    .nome("Passageiro de testes")
                    .dataNascimento(LocalDate.of(1996, 05, 20))
                    .classificacao(CategoriaFidelidadeEnum.VIP)
                    .milhas(0)
                    .build();
            when(passageiroService.findById("101.010.101-01")).thenReturn(Optional.of(passageiro));
            when(passageiroMapper.map(passageiro)).thenReturn(PassageiroResponse.builder().cpf("101.010.101-01").build());
            mockMvc.perform(get("/passageiros/{cpf}", passageiro.getCpf()))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.cpf", is("101.010.101-01")));
        }

        @Test
        @DisplayName("Quando não há passageiro registrado, deve retornar 404 ")
        void getPorCpf_quandoNãoAPassageiro_deveRetornar404() throws Exception {
            when(passageiroService.findById(anyString())).thenReturn(Optional.empty());
            mockMvc.perform(get("/passageiros/{cpf}", "101.010.101-01"))
                    .andExpect(status().isNotFound())
                    .andExpect(content().json("{}"));
        }
    }

    @Nested
    @DisplayName("Testes do endpoint confirmacao ")
    class confirmacaoTests {

        @Test
        @DisplayName("Quando o objeto nao e valido, deve retornar 400 ")
        void confirmacao_quandoObjetoNaoValido_deveRetornar400() throws Exception {
            mockMvc.perform(post("/passageiros/confirmacao")
                            .content("{}")
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(status().isBadRequest());
        }

        @Test
        void confirmacao_sucesso() throws Exception {
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
            var checkIn = CheckIn.builder()
                    .passageiro(passageiro)
                    .assento(assento)
                    .dataHoraConfirmacao(LocalDateTime.now())
                    .eticket(UUID.randomUUID().toString())
                    .build();
            var confirmacaoRequest = ConfirmacaoRequest.builder()
                    .cpf("101.010.101-01")
                    .assento("0TESTE")
                    .malasDespachadas(true)
                    .build();
            when(checkInService.realiza(any())).thenReturn(checkIn);
            mockMvc.perform(post("/passageiros/confirmacao")
                            .content(objectMapper.writeValueAsString(confirmacaoRequest))
                            .contentType(MediaType.APPLICATION_JSON)
                    )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.eticket", is(checkIn.getEticket())));
        }


    }

}
