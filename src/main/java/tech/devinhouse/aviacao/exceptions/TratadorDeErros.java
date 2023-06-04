package tech.devinhouse.aviacao.exceptions;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import tech.devinhouse.aviacao.exceptions.custom.EntidadeNaoEncontradaException;

@RestControllerAdvice
public class TratadorDeErros {
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Void> trataErro404() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Map<String, Object>> trataErro404(EntidadeNaoEncontradaException e) {
		e.put("Erro", "Registro informado não encontrado.");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getDetalhes());
	}
	
}
