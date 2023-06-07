package tech.devinhouse.aviacao.controllers.dtos.response;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;
import tech.devinhouse.aviacao.enums.CategoriaFidelidadeEnum;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class PassageiroResponse {
	private String cpf;
	private String nome;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataNascimento;
	private CategoriaFidelidadeEnum classificacao;
	private Long milhas;
	private String eticket;
	private String assento;
	private LocalDate dataHoraConfirmacao;
}
