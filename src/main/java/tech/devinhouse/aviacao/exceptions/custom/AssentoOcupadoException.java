package tech.devinhouse.aviacao.exceptions.custom;

import java.util.Map;

import tech.devinhouse.aviacao.exceptions.ValorInvalidoException;

public class AssentoOcupadoException extends ValorInvalidoException {
	
	public AssentoOcupadoException() {
		super();
	}
	
	public AssentoOcupadoException(Map<String, Object> detalhes) {
		super(detalhes);
	}
	
}
