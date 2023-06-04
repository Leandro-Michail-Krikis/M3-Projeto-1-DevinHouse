package tech.devinhouse.aviacao.service;

import java.util.List;

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
	
}
