package tech.devinhouse.aviacao.controllers.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmacaoRequest {
	@NotBlank
	private String cpf;
	@NotBlank
	private String assento;
	@NotNull
	private Boolean malasDespachadas;
}
