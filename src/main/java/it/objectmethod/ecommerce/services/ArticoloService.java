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

	public List<ArticoloDTO> findAll() {
		List<ArticoloDTO> articoloDtoList = null;
		List<Articolo> articoloList = articoloRepo.findAll();
		articoloDtoList = articoloMap.toDto(articoloList);

		return articoloDtoList;
	}

	public List<ArticoloDTO> findByNomeArticolo(ArticoloDTO articoloDto) {
		Articolo articolo = articoloMap.toEntity(articoloDto);
		List<Articolo> articoli = articoloRepo.findByNomeArticolo(articolo.getNomeArticolo());
		List<ArticoloDTO> articoliDto = articoloMap.toDto(articoli);

		return articoliDto;
	}

	public List<ArticoloDTO> findByCodiceArticolo(ArticoloDTO articoloDto) {
		Articolo articolo = articoloMap.toEntity(articoloDto);
		List<Articolo> articoli = articoloRepo.findByCodiceArticolo(articolo.getCodiceArticolo());
		List<ArticoloDTO> articoliDto = articoloMap.toDto(articoli);

		return articoliDto;
	}

	public List<ArticoloDTO> findByNameOrCode(ArticoloDTO articoloDto) {

		Articolo articolo = articoloMap.toEntity(articoloDto);
		List<Articolo> articoli = articoloRepo.findByNameOrCode(articolo.getNomeArticolo(),
				articolo.getCodiceArticolo());
		List<ArticoloDTO> articoliDto = articoloMap.toDto(articoli);

		return articoliDto;
	}

}
