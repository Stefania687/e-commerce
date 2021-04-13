package it.objectmethod.ecommerce.services.mapper;

import org.springframework.stereotype.Component;

import it.objectmethod.ecommerce.entity.Utente;
import it.objectmethod.ecommerce.services.dto.UtenteDTO;

@Component
public class UtenteMapper {

	public UtenteDTO toDto(Utente utente) {
		UtenteDTO dto = new UtenteDTO();
		dto.setId(utente.getIdUtente());
		dto.setNome(utente.getNomeUtente());
		return dto;
	}

	public Utente toEntity(UtenteDTO utenteDto) {
		Utente ut = new Utente();
		ut.setIdUtente(utenteDto.getId());
		ut.setNomeUtente(utenteDto.getNome());
		return ut;
	}

}
