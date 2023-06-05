package tech.devinhouse.aviacao.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import tech.devinhouse.aviacao.controllers.dtos.request.ConfirmacaoRequest;
import tech.devinhouse.aviacao.controllers.dtos.response.ConfirmacaoResponse;
import tech.devinhouse.aviacao.controllers.dtos.response.PassageiroResponse;
import tech.devinhouse.aviacao.controllers.mappers.PassageiroMapper;
import tech.devinhouse.aviacao.exceptions.custom.EntidadeNaoEncontradaException;
import tech.devinhouse.aviacao.service.CheckInService;
import tech.devinhouse.aviacao.service.PassageiroService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/passageiros")
public class PassageirosController {

	private final PassageiroService passageiroService;	
	private final CheckInService checkInService;
	private final PassageiroMapper passageiroMapper;
	
	@GetMapping
	public ResponseEntity<List<PassageiroResponse>> getAll() {
		List<PassageiroResponse> listaResponse = new ArrayList<>();
		for (var passageiro : passageiroService.findAll()) {
			var checkIn = checkInService.findByPassageiro(passageiro);
			listaResponse.add(passageiroMapper.map(passageiro, checkIn));
		}
		return ResponseEntity.ok(listaResponse);
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<PassageiroResponse> getPorCpf(
												@PathVariable(name = "cpf") String cpf
											  ) {
		var passageiro = passageiroService.findById(cpf)
										 .orElseThrow(() -> new EntidadeNaoEncontradaException(Map.of("cpf", cpf)));
		return ResponseEntity.ok(passageiroMapper.map(passageiro));
	}
	
	@PostMapping("/confirmacao") 
	public ResponseEntity<ConfirmacaoResponse> confirmacao(
								 	@RequestBody @Valid ConfirmacaoRequest confirmacaoRequest
								 ) {
		var checkIn = checkInService.realiza(confirmacaoRequest);
		return ResponseEntity.ok( ConfirmacaoResponse.builder()
											 .eticket(checkIn.getEticket())
											 .dataHoraConfirmacao(checkIn.getDataHoraConfirmacao())
											 .build()
						 		);
	}
}
