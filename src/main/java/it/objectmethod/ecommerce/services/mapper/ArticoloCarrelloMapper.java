package it.objectmethod.ecommerce.services.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.objectmethod.ecommerce.entity.Articolo;
import it.objectmethod.ecommerce.services.dto.ArticoloCarrelloDTO;

@Mapper(componentModel = "spring")
public interface ArticoloCarrelloMapper extends EntityMapper<ArticoloCarrelloDTO, Articolo> {

	@Override
	ArticoloCarrelloDTO toDto(Articolo entity);

	@Override
	@Mapping(target = "codiceArticolo", ignore = true)
	@Mapping(target = "nomeArticolo", ignore = true)
	@Mapping(target = "disponibilita", ignore = true)
	@Mapping(target = "prezzo", ignore = true)
	Articolo toEntity(ArticoloCarrelloDTO dto);

	@Override
	List<ArticoloCarrelloDTO> toDto(List<Articolo> entity);

	@Override
	List<Articolo> toEntity(List<ArticoloCarrelloDTO> dto);

}

//
//@Component
//public class ArticoloCarrelloMapper {
//
//	public ArticoloCarrelloDTO toDto(Articolo articolo) {
//		ArticoloCarrelloDTO dto = new ArticoloCarrelloDTO();
//		dto.setIdArticolo(articolo.getIdArticolo());
//		return dto;
//	}
//
//	public Articolo toEntity(ArticoloCarrelloDTO dto) {
//		Articolo art = new Articolo();
//		art.setIdArticolo(dto.getIdArticolo());
//		return art;
//	}
//
//}
