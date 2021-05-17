package it.objectmethod.ecommerce.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import it.objectmethod.ecommerce.entity.Articolo;
import it.objectmethod.ecommerce.repository.PaginationRepository;
import it.objectmethod.ecommerce.services.dto.ArticoloDTO;
import it.objectmethod.ecommerce.services.mapper.ArticoloMapper;



@Service
public class PaginationService {
	@Autowired
	PaginationRepository paginationRepo;
	
	@Autowired
	ArticoloMapper articoloMap;
	
	public List<ArticoloDTO> getPages(Integer pageNum, Integer elemNum) {
		PageRequest page = PageRequest.of(pageNum, elemNum);

		Page<Articolo> articoloPage = paginationRepo.findAll(page);
		List<Articolo> articoli = articoloPage.getContent();
		List<ArticoloDTO> articoloDto = articoloMap.toDto(articoli);

		return articoloDto;

	}
}
