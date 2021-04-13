package it.objectmethod.ecommerce.services.mapper;

import org.springframework.stereotype.Component;

import it.objectmethod.ecommerce.entity.Utente;
import it.objectmethod.ecommerce.services.dto.UtenteRequestDTO;

@Component
public class UtenteRequestMapper {

	public UtenteRequestDTO toDto(Utente ute) {
		UtenteRequestDTO dto = new UtenteRequestDTO();
		dto.setNome(ute.getNomeUtente());
		dto.setPassword(ute.getPassword());
		return dto;
	}
	
	public Utente toEntity(UtenteRequestDTO dto) {
		Utente ute = new Utente();
		ute.setNomeUtente(dto.getNome());
		ute.setPassword(dto.getPassword());
		return ute;
	}
}
