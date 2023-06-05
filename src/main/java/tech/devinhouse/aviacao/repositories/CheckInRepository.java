package tech.devinhouse.aviacao.repositories;

import org.springframework.data.repository.CrudRepository;

import tech.devinhouse.aviacao.repositories.models.Eticket;
import tech.devinhouse.aviacao.repositories.models.Passageiro;

public interface EticketRepository extends CrudRepository<Eticket, String>{

	Eticket findByPassageiro(Passageiro passageiro);
	
}
