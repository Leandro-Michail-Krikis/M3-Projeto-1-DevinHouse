package tech.devinhouse.aviacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.devinhouse.aviacao.repositories.PassageiroRepository;
import tech.devinhouse.aviacao.repositories.models.Passageiro;

@Service
@RequiredArgsConstructor
public class PassageiroService {
	
	private final PassageiroRepository passageiroRepository;
	
	public List<Passageiro> findAll() {
		return passageiroRepository.findAll();
	}

	public Optional<Passageiro> findById(String cpf) {
		return passageiroRepository.findById(cpf);
	}

	public void atualizaMilhasPassageiro(Passageiro passageiro) {
		var pontos = 0;
	    pontos = switch (passageiro.getClassificacao()) {
		case VIP -> 100;
		case OURO -> 80;
		case PRATA -> 50;
		case BRONZE -> 30;
		case ASSOCIADO -> 10;
		};
	    passageiro.setMilhas(passageiro.getMilhas() + pontos);
	    passageiroRepository.save(passageiro);
	}
	
}
