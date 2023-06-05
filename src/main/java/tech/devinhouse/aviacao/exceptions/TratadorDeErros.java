package tech.devinhouse.aviacao.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import tech.devinhouse.aviacao.exceptions.custom.AssentoEmergencialException;
import tech.devinhouse.aviacao.exceptions.custom.AssentoOcupadoException;
import tech.devinhouse.aviacao.exceptions.custom.CheckInJaRealizadoException;
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
	
	@ExceptionHandler(AssentoOcupadoException.class)
	public ResponseEntity<Map<String, Object>> trataErro409(AssentoOcupadoException e) {
		e.put("Erro", "Assento ja esta ocupado.");
		return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getDetalhes());
	}
	
	@ExceptionHandler(AssentoEmergencialException.class)
	public ResponseEntity<Map<String, Object>> trataErro400(AssentoEmergencialException e) {
		if(e.getDetalhes().isEmpty()) {
			e.put("Erro", "Ao escolher assento emergencial");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getDetalhes());
	}
	
	@ExceptionHandler(CheckInJaRealizadoException.class)
	public ResponseEntity<Map<String, Object>> trataErro400(CheckInJaRealizadoException e) {
		if(e.getDetalhes().isEmpty()) {
			e.put("Erro", "Esse passageiro ja tem realizado check in");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getDetalhes());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> trataErro400(MethodArgumentNotValidException e) {
		var retorno = new HashMap<String, Object>();
		retorno.put("erro", "Erro de validação");
		for(var error :  e.getBindingResult().getFieldErrors()) {			
			retorno.put(error.getField(), error.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(retorno);
	}
	
	@ExceptionHandler(ValorInvalidoException.class)
	public ResponseEntity<Map<String, Object>> trataErro400(ValorInvalidoException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getDetalhes());
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> trataErro400(HttpMessageNotReadableException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> trataErro500(Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro Interno");
	}
}
