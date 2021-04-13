package it.objectmethod.ecommerce.services.mapper;

import org.springframework.stereotype.Component;

import it.objectmethod.ecommerce.entity.Carrello;
import it.objectmethod.ecommerce.services.dto.CarrelloDTO;

@Component
public class CarrelloMapper {

	public CarrelloDTO toDto(Carrello carrello) {
		CarrelloDTO dto = new CarrelloDTO();
		dto.setId(carrello.getIdCarrello());
		dto.setIdUtente(carrello.getUtente().getIdUtente());
		dto.setNomeUtente(carrello.getUtente().getNomeUtente());
		return dto;
	}

	public Carrello toEntity(CarrelloDTO carrelloDto) {
		Carrello carr = new Carrello();
		carr.setIdCarrello(carrelloDto.getId());
		return carr;
	}

}
