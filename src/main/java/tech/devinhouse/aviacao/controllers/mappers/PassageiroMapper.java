package tech.devinhouse.aviacao.controllers.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import tech.devinhouse.aviacao.controllers.dtos.response.PassageiroResponse;
import tech.devinhouse.aviacao.repositories.models.CheckIn;
import tech.devinhouse.aviacao.repositories.models.Passageiro;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {MapperComponent.class})
public interface PassageiroMapper {
	
	PassageiroResponse map(Passageiro passageiro, CheckIn checkIn);
	
	PassageiroResponse map(Passageiro passageiro);
	
}
