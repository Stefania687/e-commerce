package it.objectmethod.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.services.ArticoloService;
import it.objectmethod.ecommerce.services.dto.ArticoloDTO;

@RestController
@RequestMapping("/api/articoli")
public class ArticoloController {

	@Autowired
	private ArticoloService articoloServ;

	@GetMapping("/cerca")
	public List<ArticoloDTO> findByNomeOCodiceArticolo(@RequestParam("nome-articolo") String nomeArticolo,
			@RequestParam("codice-articolo") String codiceArticolo) {
		List<ArticoloDTO> articoloDto = articoloServ.findByNameOrCode(nomeArticolo, codiceArticolo);
		System.out.println(articoloDto);
		return articoloDto;
	}

}
