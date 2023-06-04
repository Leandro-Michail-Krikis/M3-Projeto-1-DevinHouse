package tech.devinhouse.aviacao.repositories.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long assentoId;
	private Integer fileira;
	private String poltrona;
	
	@Override
	public String toString() {
		return getFileira().toString() + getPoltrona();
	}
	
}
