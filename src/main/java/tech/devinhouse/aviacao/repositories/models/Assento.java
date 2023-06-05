package tech.devinhouse.aviacao.repositories.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assento {
	
	@Id
	private String assentoId;
	private Integer fileira;
	private String poltrona;
	@Column(columnDefinition = "boolean default false")
	private boolean ocupado;
	private boolean emergencial;
	@Override
	public String toString() {
		return getFileira().toString() + getPoltrona();
	}
	
}
