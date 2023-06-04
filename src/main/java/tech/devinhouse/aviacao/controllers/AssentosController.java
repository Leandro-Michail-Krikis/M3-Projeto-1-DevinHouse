package tech.devinhouse.aviacao.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tech.devinhouse.aviacao.service.AssentoService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/assentos")
public class AssentosController {
	
	private final AssentoService assentoService;
	
	@GetMapping
	public ResponseEntity<List<String>> getAll() {
		return ResponseEntity.ok( assentoService.findAll().stream().map(Object::toString).toList() );
	}
}
