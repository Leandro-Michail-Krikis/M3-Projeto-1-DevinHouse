package tech.devinhouse.aviacao.repositories.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.devinhouse.aviacao.enums.CategoriaFidelidadeEnum;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Passageiro {
	@Id
	private String cpf;
	private String nome;
	private LocalDate dataDeNascimento;
	@Builder.Default
	private CategoriaFidelidadeEnum classificacao = CategoriaFidelidadeEnum.ASSOCIADO;
	private Integer milhas;
}
