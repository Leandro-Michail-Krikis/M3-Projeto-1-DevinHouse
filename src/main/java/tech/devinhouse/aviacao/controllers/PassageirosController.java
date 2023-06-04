package tech.devinhouse.aviacao.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import tech.devinhouse.aviacao.controllers.dtos.response.PassageiroResponse;
import tech.devinhouse.aviacao.controllers.mappers.PassageiroMapper;
import tech.devinhouse.aviacao.exceptions.custom.EntidadeNaoEncontradaException;
import tech.devinhouse.aviacao.service.EticketService;
import tech.devinhouse.aviacao.service.PassageiroService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/passageiros")
public class PassageirosController {

	private final PassageiroService passageiroService;
	
	private final EticketService eticketService;
	
	private final PassageiroMapper passageiroMapper;
	
	@GetMapping
	public ResponseEntity<List<PassageiroResponse>> getAll() {
		List<PassageiroResponse> listaResponse = new ArrayList<>();
		for (var passageiro : passageiroService.findAll()) {
			var eticket = eticketService.findByPassageiro(passageiro);
			listaResponse.add(passageiroMapper.map(passageiro, eticket));
		}
		return ResponseEntity.ok(listaResponse);
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<PassageiroResponse> getPorCpf(
												@PathVariable(name = "cpf") String cpf
											  ) {
		var passageiro = passageiroService.findByCpf(cpf)
										 .orElseThrow(() -> new EntidadeNaoEncontradaException(Map.of("cpf", cpf)));
		return ResponseEntity.ok(passageiroMapper.map(passageiro));
	}
}
