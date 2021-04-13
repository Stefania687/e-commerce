package it.objectmethod.ecommerce.services.mapper;

import org.springframework.stereotype.Component;

import it.objectmethod.ecommerce.entity.Articolo;
import it.objectmethod.ecommerce.services.dto.ArticoloCarrelloDTO;

@Component
public class ArticoloCarrelloMapper {

	public ArticoloCarrelloDTO toDto(Articolo articolo) {
		ArticoloCarrelloDTO dto = new ArticoloCarrelloDTO();
		dto.setIdArticolo(articolo.getIdArticolo());
		return dto;
	}

	public Articolo toEntity(ArticoloCarrelloDTO dto) {
		Articolo art = new Articolo();
		art.setIdArticolo(dto.getIdArticolo());
		return null;
	}

}
