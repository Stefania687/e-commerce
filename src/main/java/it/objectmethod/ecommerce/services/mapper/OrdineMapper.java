package it.objectmethod.ecommerce.services.mapper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.objectmethod.ecommerce.entity.Ordine;
import it.objectmethod.ecommerce.entity.Utente;
import it.objectmethod.ecommerce.repository.UtenteRepository;
import it.objectmethod.ecommerce.services.dto.OrdineDTO;

@Component
public class OrdineMapper {

	@Autowired
	private UtenteRepository utenteRepo;

	public OrdineDTO toDto(Ordine ordine) {
		OrdineDTO dto = new OrdineDTO();
		dto.setId(ordine.getIdOrdine());
		dto.setDataOrdine(ordine.getDataOrdine());
		dto.setIdUtente(ordine.getUtente().getIdUtente());
		dto.setNomeUtente(ordine.getUtente().getNomeUtente());
		return dto;
	}

	public Ordine toEntity(OrdineDTO ordineDto) {
		Ordine ord = new Ordine();
		ord.setDataOrdine(ordineDto.getDataOrdine());
		ord.setIdOrdine(ordineDto.getId());
		Optional<Utente> optUtente = utenteRepo.findById(ordineDto.getIdUtente());
		Utente utente = optUtente.get();
		ord.setUtente(utente);
		return ord;
	}
}
