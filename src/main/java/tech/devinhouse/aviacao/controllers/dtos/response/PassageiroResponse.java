package tech.devinhouse.aviacao.controllers.dtos.request;

import java.time.LocalDate;

import lombok.Data;
import tech.devinhouse.aviacao.enums.CategoriaFidelidadeEnum;

@Data
public class PassageiroResponse {
	private String cpf;
	private String nome;
	private LocalDate dataNascimento;
	private CategoriaFidelidadeEnum classificacao;
	private Long milhas;
	private String eticket;
	private String assento;
	private LocalDate dataHoraConfirmacao;
}
