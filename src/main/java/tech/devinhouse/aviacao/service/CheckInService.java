package tech.devinhouse.aviacao.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.devinhouse.aviacao.repositories.EticketRepository;
import tech.devinhouse.aviacao.repositories.models.Eticket;
import tech.devinhouse.aviacao.repositories.models.Passageiro;

@Service
@RequiredArgsConstructor
public class EticketService {

	private final EticketRepository eticketRepository;
	
	public Eticket findByPassageiro(Passageiro passageiro) {
		return eticketRepository.findByPassageiro(passageiro);
	}
	
}
