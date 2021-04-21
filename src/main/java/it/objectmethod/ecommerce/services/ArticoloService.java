package it.objectmethod.ecommerce.services;

import java.util.List;

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

	public List<ArticoloDTO> findByNameOrCode(String nomeArticolo, String codiceArticolo) {

		List<Articolo> articoli = articoloRepo.findByNameOrCode(nomeArticolo, codiceArticolo);
		List<ArticoloDTO> articoliDto = articoloMap.toDto(articoli);		
		
		return articoliDto;
	}

}
