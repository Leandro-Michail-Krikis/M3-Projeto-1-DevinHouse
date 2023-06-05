package tech.devinhouse.aviacao.exceptions.custom;

import java.util.Map;

import tech.devinhouse.aviacao.exceptions.ValorInvalidoException;

public class AssentoEmergencialException extends ValorInvalidoException {
	
	public AssentoEmergencialException() {
		super();
	}
	
	public AssentoEmergencialException(Map<String, Object> detalhes) {
		super(detalhes);
	}
	
}
