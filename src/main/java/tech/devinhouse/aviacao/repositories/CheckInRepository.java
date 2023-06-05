package tech.devinhouse.aviacao.repositories;

import org.springframework.data.repository.CrudRepository;

import tech.devinhouse.aviacao.repositories.models.CheckIn;
import tech.devinhouse.aviacao.repositories.models.Passageiro;

public interface CheckInRepository extends CrudRepository<CheckIn, String>{

	CheckIn findByPassageiro(Passageiro passageiro);

	boolean existsByPassageiro(Passageiro passageiro);
	
}
