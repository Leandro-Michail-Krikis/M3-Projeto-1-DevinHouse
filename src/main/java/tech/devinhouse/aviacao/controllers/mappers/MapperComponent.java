package tech.devinhouse.aviacao.controllers.mappers;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tech.devinhouse.aviacao.repositories.models.Assento;

@Component
@RequiredArgsConstructor
public class MapperComponent {
	
	public String assentoToString(Assento assento) {
		if(assento == null) {
			return null;
		}
		return assento.toString();
	}
	
}
