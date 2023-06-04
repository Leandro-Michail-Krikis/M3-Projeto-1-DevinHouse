package tech.devinhouse.aviacao.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tech.devinhouse.aviacao.repositories.PassageiroRepository;
import tech.devinhouse.aviacao.repositories.models.Passageiro;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hello")
public class HelloController {
	
	private final PassageiroRepository passageiroRepository;
	
	@GetMapping
	public ResponseEntity<List<Passageiro>> buscaTodasAsEstatisticas(){
		return ResponseEntity.status(HttpStatus.OK).body(passageiroRepository.findAll());
	}
}
