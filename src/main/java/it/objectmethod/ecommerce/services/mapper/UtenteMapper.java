package it.objectmethod.ecommerce.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import it.objectmethod.ecommerce.entity.Utente;
import it.objectmethod.ecommerce.services.dto.UtenteDTO;

@Mapper(componentModel = "spring")
public interface UtenteMapper extends EntityMapper<UtenteDTO, Utente> {

	@Override
	@Mapping(source = "idUtente", target = "id")
	@Mapping(source = "nomeUtente", target = "nome")
	UtenteDTO toDto(Utente entity);

	@Override
	@Mapping(target = "password", ignore = true) // provo se funziona senza ignore true
	Utente toEntity(UtenteDTO dto);

}

//@Component
//public class UtenteMapper {
//
//	public UtenteDTO toDto(Utente utente) {
//		UtenteDTO dto = new UtenteDTO();
//		dto.setId(utente.getIdUtente());
//		dto.setNome(utente.getNomeUtente());
//		return dto;
//	}
//
//	public Utente toEntity(UtenteDTO utenteDto) {
//		Utente ut = new Utente();
//		ut.setIdUtente(utenteDto.getId());
//		ut.setNomeUtente(utenteDto.getNome());
//		return ut;
//	}
//
//}
