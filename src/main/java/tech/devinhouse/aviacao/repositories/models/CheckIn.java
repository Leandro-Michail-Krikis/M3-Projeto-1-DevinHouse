package tech.devinhouse.aviacao.repositories.models;

import java.time.LocalDate;

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
public class Eticket {
	@Id
	private String eticketId;
	@OneToOne
	private Assento assento;
	private LocalDate dataHoraConfirmacao;
	@OneToOne
	private Passageiro passageiro;
}
