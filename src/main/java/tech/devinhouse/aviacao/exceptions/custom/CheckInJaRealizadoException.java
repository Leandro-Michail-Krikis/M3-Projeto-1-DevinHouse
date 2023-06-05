package tech.devinhouse.aviacao.exceptions.custom;

import java.util.Map;

import tech.devinhouse.aviacao.exceptions.ValorInvalidoException;

public class CheckInJaRealizadoException extends ValorInvalidoException {
	
	public CheckInJaRealizadoException() {
		super();
	}
	
	public CheckInJaRealizadoException(Map<String, Object> detalhes) {
		super(detalhes);
	}
	
}
