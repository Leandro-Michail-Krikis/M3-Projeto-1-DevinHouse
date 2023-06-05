package tech.devinhouse.aviacao.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tech.devinhouse.aviacao.repositories.models.Assento;

public interface AssentoRepository extends CrudRepository<Assento, String>{
	
	List<Assento> findAll();
	
}
