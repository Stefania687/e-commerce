package it.objectmethod.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.services.ArticoloService;
import it.objectmethod.ecommerce.services.dto.ArticoloDTO;

@RestController
@RequestMapping("/api/articoli")
public class ArticoloController {

	@Autowired
	private ArticoloService articoloServ;

	@GetMapping("/lista")
	public List<ArticoloDTO> findAll() {
		List<ArticoloDTO> articoliDto = articoloServ.findAll();
		return articoliDto;
	}

	@GetMapping("/lista/nome")
	public List<ArticoloDTO> findArticoloByName(@RequestBody ArticoloDTO art) {
		List<ArticoloDTO> articoloDto = articoloServ.findByNomeArticolo(art);
		return articoloDto;
	}

	@GetMapping("/lista/codice")
	public List<ArticoloDTO> findArticoloByCodiceArticolo(@RequestBody ArticoloDTO art) {
		List<ArticoloDTO> articoloDto = articoloServ.findByCodiceArticolo(art);
		return articoloDto;
	}
	
	@GetMapping("/cerca")
	public List<ArticoloDTO> findByNomeOCodiceArticolo(@RequestBody ArticoloDTO art){
		List<ArticoloDTO> articoloDto = articoloServ.findByNameOrCode(art);		
		return articoloDto;
	}

}
