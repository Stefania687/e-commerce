package it.objectmethod.ecommerce.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.ecommerce.entity.Utente;
import it.objectmethod.ecommerce.repository.UtenteRepository;
import it.objectmethod.ecommerce.services.dto.UtenteDTO;
import it.objectmethod.ecommerce.services.mapper.UtenteMapper;

@Service
public class UtenteService {

	@Autowired
	private UtenteRepository utenteRepo;

	@Autowired
	private UtenteMapper utenteMap;

	private static final Logger logger = LogManager.getLogger(UtenteService.class);

	public UtenteDTO findByNomeUtenteAndPassword(String nomeUtente, String password) {

		logger.info("Richieta di login per l'utente [" + nomeUtente + "]");

		Utente utente = utenteRepo.findByNomeUtenteAndPassword(nomeUtente, password);
		UtenteDTO utenteDto = new UtenteDTO();

		if (utente != null) {
			utenteDto = utenteMap.toDto(utente);
		}

		return utenteDto;

	}

}
