package tech.devinhouse.aviacao.service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.devinhouse.aviacao.controllers.dtos.request.ConfirmacaoRequest;
import tech.devinhouse.aviacao.exceptions.custom.AssentoEmergencialException;
import tech.devinhouse.aviacao.exceptions.custom.AssentoOcupadoException;
import tech.devinhouse.aviacao.exceptions.custom.CheckInJaRealizadoException;
import tech.devinhouse.aviacao.exceptions.custom.EntidadeNaoEncontradaException;
import tech.devinhouse.aviacao.repositories.CheckInRepository;
import tech.devinhouse.aviacao.repositories.models.Assento;
import tech.devinhouse.aviacao.repositories.models.CheckIn;
import tech.devinhouse.aviacao.repositories.models.Passageiro;

@Service
@RequiredArgsConstructor
public class CheckInService {
	private final PassageiroService passageiroService;	
	private final AssentoService assentoService;
	private final CheckInRepository checkInRepository;
	private static final Logger logger = LoggerFactory.getLogger(CheckInService.class);
	
	public CheckIn findByPassageiro(Passageiro passageiro) {
		return checkInRepository.findByPassageiro(passageiro);
	}

	public CheckIn realiza(ConfirmacaoRequest confirmacaoRequest) {
		Passageiro passageiro = passageiroService.findById(confirmacaoRequest.getCpf())
				 .orElseThrow(() -> new EntidadeNaoEncontradaException(Map.of("cpf", confirmacaoRequest.getCpf())));
		Assento assento = assentoService.findById(confirmacaoRequest.getAssento())
				 .orElseThrow(() -> new EntidadeNaoEncontradaException(Map.of("Assento", confirmacaoRequest.getAssento())));
		if(passageiroJaRealizouCheckIn(passageiro))
			throw new CheckInJaRealizadoException();
		if(assento.isOcupado())
			throw new AssentoOcupadoException(Map.of("Assento", assento.getAssentoId()));
		if(assento.isEmergencial() && !passageiro.isAdulto())
			throw new AssentoEmergencialException(Map.of("Erro", "Esse assento so pode ser usado por maior de idade."));
		if(assento.isEmergencial() && Boolean.FALSE.equals(confirmacaoRequest.getMalasDespachadas()))
			throw new AssentoEmergencialException(Map.of("Erro", "Esse assento e obrigatorio ter despachado as malas."));
		var checkIn = CheckIn.builder()
							 .passageiro(passageiro)
							 .assento(assento)
							 .dataHoraConfirmacao(LocalDateTime.now())
							 .eticket(UUID.randomUUID().toString())
							 .build();
		checkIn = salva(checkIn);
		assentoService.marcaAssentoComoOcupado(assento);
		passageiroService.atualizaMilhasPassageiro(passageiro);
		logger.info("Confirmação feita pelo passageiro de CPF {} com e-ticket {}", passageiro.getCpf(), checkIn.getEticket());
		return checkIn;
	}
	
	private CheckIn salva(CheckIn checkIn) {
		return checkInRepository.save(checkIn);
	}
	
	private boolean passageiroJaRealizouCheckIn(Passageiro passageiro) {
		return checkInRepository.existsByPassageiro(passageiro);
	}
	
}
