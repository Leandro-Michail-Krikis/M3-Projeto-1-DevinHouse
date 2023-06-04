package tech.devinhouse.aviacao.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import tech.devinhouse.aviacao.repositories.AssentoRepository;
import tech.devinhouse.aviacao.repositories.models.Assento;

@Service
@RequiredArgsConstructor
public class AssentoService {

	private final AssentoRepository assentoRepository;
	
	public List<Assento> findAll(){
		return assentoRepository.findAll();
	}
	
}
