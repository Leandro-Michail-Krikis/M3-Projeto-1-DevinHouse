package tech.devinhouse.aviacao.repositories.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckIn {
	@Id
	private String eticket;
	@OneToOne
	private Assento assento;
	private LocalDateTime dataHoraConfirmacao;
	@OneToOne
	private Passageiro passageiro;
}
