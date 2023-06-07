package tech.devinhouse.aviacao.controllers.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfirmacaoRequest {
	@NotBlank
	private String cpf;
	@NotBlank
	private String assento;
	@NotNull
	private Boolean malasDespachadas;
}
