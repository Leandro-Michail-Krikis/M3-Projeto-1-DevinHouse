package tech.devinhouse.aviacao.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tech.devinhouse.aviacao.repositories.models.Assento;
import tech.devinhouse.aviacao.service.AssentoService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssentosController.class)
public class AssentosControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssentoService assentoService;

    @Test
    @DisplayName("Quando há assentos, deve retornar os assentos")
    public void getAllTest() throws Exception {
        Assento assento1 = Assento.builder().assentoId("0TESTE").fileira(0).poltrona("TESTE").build();
        Assento assento2 = Assento.builder().assentoId("1TESTE").fileira(0).poltrona("TESTE").build();
        Assento assento3 = Assento.builder().assentoId("2TESTE").fileira(0).poltrona("TESTE").build();
        List<Assento> assentos = Arrays.asList(assento1, assento2, assento3);
        when(assentoService.findAll()).thenReturn(assentos);

        mockMvc.perform(get("/assentos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    @DisplayName("Quando não há assentos, deve retornar um json vazio")
    public void getAllTest_noresult() throws Exception {

        List<Assento> assentos = Collections.emptyList();
        when(assentoService.findAll()).thenReturn(assentos);

        mockMvc.perform(get("/assentos").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
