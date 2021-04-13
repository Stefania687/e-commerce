package it.objectmethod.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.ecommerce.entity.Utente;
import it.objectmethod.ecommerce.repository.UtenteRepository;
import it.objectmethod.ecommerce.services.dto.UtenteDTO;
import it.objectmethod.ecommerce.services.dto.UtenteRequestDTO;
import it.objectmethod.ecommerce.services.mapper.UtenteMapper;
import it.objectmethod.ecommerce.services.mapper.UtenteRequestMapper;

@Service
public class UtenteService {

	@Autowired
	private UtenteRepository utenteRepo;

	@Autowired
	private UtenteMapper utenteMap;

	@Autowired
	private UtenteRequestMapper utenteReqMap;

	public UtenteDTO findByNomeUtenteAndPassword(UtenteRequestDTO ute) {

		Utente utente = utenteReqMap.toEntity(ute);
		UtenteDTO utenteDto = null;
		String nome = utente.getNomeUtente();
		String password = utente.getPassword();

		utente = utenteRepo.findByNomeUtenteAndPassword(nome, password);

		if (utente != null) {
			utenteDto = utenteMap.toDto(utente);
		}

		return utenteDto;

	}

}
