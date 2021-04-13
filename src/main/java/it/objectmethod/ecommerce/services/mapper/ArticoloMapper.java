package it.objectmethod.ecommerce.services.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.objectmethod.ecommerce.entity.Articolo;
import it.objectmethod.ecommerce.services.dto.ArticoloDTO;

@Component
public class ArticoloMapper {

	public ArticoloDTO toDto(Articolo articolo) {
		ArticoloDTO dto = new ArticoloDTO();
		dto.setId(articolo.getIdArticolo());
		dto.setNomeArticolo(articolo.getNomeArticolo());
		dto.setCodiceArticolo(articolo.getCodiceArticolo());
		dto.setDisponibilita(articolo.getDisponibilita());
		dto.setPrezzo(articolo.getPrezzo());
		return dto;
	}

	public Articolo toEntity(ArticoloDTO articoloDto) {
		Articolo art = new Articolo();
		art.setIdArticolo(articoloDto.getId());
		art.setNomeArticolo(articoloDto.getNomeArticolo());
		art.setCodiceArticolo(articoloDto.getCodiceArticolo());
		art.setDisponibilita(articoloDto.getDisponibilita());
		art.setPrezzo(articoloDto.getPrezzo());
		return art;
	}

	public List<ArticoloDTO> toDto(List<Articolo> art) {
		List<ArticoloDTO> artDto = new ArrayList<ArticoloDTO>();
		for (Articolo articolo : art) {
			ArticoloDTO articoloDto = new ArticoloDTO();
			articoloDto.setId(articolo.getIdArticolo());
			articoloDto.setNomeArticolo(articolo.getNomeArticolo());
			articoloDto.setCodiceArticolo(articolo.getCodiceArticolo());
			articoloDto.setDisponibilita(articolo.getDisponibilita());
			articoloDto.setPrezzo(articolo.getPrezzo());
			artDto.add(articoloDto);
		}
		return artDto;
	}

	public List<Articolo> toEntity(List<ArticoloDTO> artDto) {
		List<Articolo> artList = new ArrayList<Articolo>();
		for (ArticoloDTO articoloDto : artDto) {
			Articolo articolo = new Articolo();
			articolo.setIdArticolo(articoloDto.getId());
			articolo.setNomeArticolo(articoloDto.getNomeArticolo());
			articolo.setCodiceArticolo(articoloDto.getCodiceArticolo());
			articolo.setDisponibilita(articoloDto.getDisponibilita());
			articolo.setPrezzo(articoloDto.getPrezzo());
			artList.add(articolo);
		}
		return artList;
	}

}