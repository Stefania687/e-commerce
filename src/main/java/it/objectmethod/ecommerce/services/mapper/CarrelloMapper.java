package it.objectmethod.ecommerce.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.objectmethod.ecommerce.entity.Carrello;
import it.objectmethod.ecommerce.services.dto.CarrelloDTO;

@Mapper(componentModel = "spring")
public interface CarrelloMapper extends EntityMapper<CarrelloDTO, Carrello> {

	@Override
	@Mapping(source = "utente.idUtente", target = "idUtente")
	@Mapping(source = "utente.nomeUtente", target = "nomeUtente")
	@Mapping(source = "idCarrello", target = "id")
	CarrelloDTO toDto(Carrello entity);

	@Override
	@Mapping(target = "carrelloDettaglio", ignore = true)
	Carrello toEntity(CarrelloDTO dto);

	@Override
	List<CarrelloDTO> toDto(List<Carrello> entity);

	@Override
	List<Carrello> toEntity(List<CarrelloDTO> dto);

}

//@Component
//public class CarrelloMapper {
//
//	public CarrelloDTO toDto(Carrello carrello) {
//		CarrelloDTO dto = new CarrelloDTO();
//		dto.setId(carrello.getIdCarrello());
//		dto.setIdUtente(carrello.getUtente().getIdUtente());
//		dto.setNomeUtente(carrello.getUtente().getNomeUtente());
//		return dto;
//	}
//
//	public Carrello toEntity(CarrelloDTO carrelloDto) {
//		Carrello carr = new Carrello();
//		carr.setIdCarrello(carrelloDto.getId());
//		return carr;
//	}
//
//}
