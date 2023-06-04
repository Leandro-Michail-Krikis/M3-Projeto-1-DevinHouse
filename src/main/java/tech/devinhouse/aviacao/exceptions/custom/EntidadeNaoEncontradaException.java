package tech.devinhouse.aviacao.exceptions.custom;

import java.util.Map;

import tech.devinhouse.aviacao.exceptions.ValorInvalidoException;

public class EntidadeNaoEncontradaException extends ValorInvalidoException{
	
	public EntidadeNaoEncontradaException() {
		super();
	}
	
	public EntidadeNaoEncontradaException(Map<String, Object> detalhes) {
		super(detalhes);
	}
}
