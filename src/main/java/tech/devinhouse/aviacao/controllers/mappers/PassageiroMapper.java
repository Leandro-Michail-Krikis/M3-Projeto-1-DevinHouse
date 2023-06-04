package tech.devinhouse.aviacao.controllers.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import tech.devinhouse.aviacao.controllers.dtos.request.PassageiroResponse;
import tech.devinhouse.aviacao.repositories.models.Eticket;
import tech.devinhouse.aviacao.repositories.models.Passageiro;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {MapperComponent.class})
public interface PassageiroMapper {
	
	PassageiroResponse map(Passageiro passageiro, Eticket eticket);
	
}
