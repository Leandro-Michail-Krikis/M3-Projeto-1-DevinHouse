package tech.devinhouse.aviacao.repositories.models;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	private LocalDate dataNascimento;
	@Enumerated(EnumType.STRING)
	@Builder.Default
	private CategoriaFidelidadeEnum classificacao = CategoriaFidelidadeEnum.ASSOCIADO;
	private Integer milhas;
	
	public boolean isAdulto() {
	    return Period.between(this.dataNascimento, LocalDate.now()).getYears() >= 18;
	}

	
}

