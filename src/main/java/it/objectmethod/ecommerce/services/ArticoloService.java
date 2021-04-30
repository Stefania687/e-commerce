package it.objectmethod.ecommerce.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.ecommerce.entity.Articolo;
import it.objectmethod.ecommerce.repository.ArticoloRepository;
import it.objectmethod.ecommerce.services.dto.ArticoloDTO;
import it.objectmethod.ecommerce.services.mapper.ArticoloMapper;

@Service
public class ArticoloService {

	@Autowired
	private ArticoloMapper articoloMap;

	@Autowired
	private ArticoloRepository articoloRepo;

	private static final Logger logger = LogManager.getLogger(ArticoloService.class);

	public List<ArticoloDTO> findByNameOrCode(String nomeArticolo, String codiceArticolo) {

		logger.info("Richiesta ricerca articolo per nome [" + nomeArticolo + "] e per codice [" + codiceArticolo + "]");

		List<Articolo> articoli = articoloRepo.findByNameOrCode(nomeArticolo, codiceArticolo);
		List<ArticoloDTO> articoliDto = articoloMap.toDto(articoli);
		logger.info("Articoli trovati : [" + articoli.size() + "]");
		return articoliDto;
	}

}
