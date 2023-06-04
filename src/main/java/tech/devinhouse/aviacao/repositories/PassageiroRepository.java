package tech.devinhouse.aviacao.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import tech.devinhouse.aviacao.repositories.models.Passageiro;

public interface PassageiroRepository extends CrudRepository<Passageiro, String>{
	
	List<Passageiro> findAll();
	
	Optional<Passageiro> findByCpf(String cpf);
	
}
