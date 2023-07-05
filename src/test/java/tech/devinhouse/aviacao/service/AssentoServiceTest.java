package tech.devinhouse.aviacao.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.devinhouse.aviacao.repositories.AssentoRepository;
import tech.devinhouse.aviacao.repositories.models.Assento;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssentoServiceTest {

    @Mock
    private AssentoRepository assentoRepository;

    @InjectMocks
    private AssentoService assentoService;

    @Test
    @DisplayName("Quando existe assentos, deve retornar todos os assentos")
    void findAllTest() {
        Assento assento1 = Assento.builder()
                .assentoId("0TESTE")
                .build();
        Assento assento2 = Assento.builder()
                .assentoId("0TESTE")
                .build();
        Assento assento3 = Assento.builder()
                .assentoId("0TESTE")
                .build();
        when(assentoRepository.findAll()).thenReturn(Arrays.asList(assento1, assento2, assento3));

        List<Assento> listaAssentos = assentoService.findAll();

        assertNotNull(listaAssentos);
        assertEquals(3, listaAssentos.size());
    }

    @Test
    @DisplayName("Quando existe assento com id especifico, deve retornar o assento")
    void findByIdTest() {
        Assento assento = Assento.builder()
                .assentoId("0TESTE")
                .build();
        when(assentoRepository.findById("0TESTE")).thenReturn(Optional.of(assento));

        Optional<Assento> retorno = assentoService.findById("0TESTE");

        assertTrue(retorno.isPresent());
        assertEquals("0TESTE", retorno.get().getAssentoId());
    }

    @Test
    @DisplayName("Quando marcar o assento como ocupado, deve retornar o campo ocupado como true")
    void marcaAssentoComoOcupado() {
        Assento assento = Assento.builder()
                .assentoId("0TESTE")
                .ocupado(false)
                .emergencial(false)
                .build();
        when(assentoRepository.save(any(Assento.class))).thenReturn(assento);
        Assento result = assentoService.marcaAssentoComoOcupado(assento);
        assertTrue(result.isOcupado());
    }
}
