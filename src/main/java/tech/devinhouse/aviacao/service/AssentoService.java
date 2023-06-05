package tech.devinhouse.aviacao.service;

import java.util.List;
import java.util.Optional;

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

	public Optional<Assento> findById(String id) {
		return assentoRepository.findById(id);
	}

	public void marcaAssentoComoOcupado(Assento assento) {
		assento.setOcupado(true);
		assentoRepository.save(assento);
	}
	
}
