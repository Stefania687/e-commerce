package it.objectmethod.ecommerce.controller;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static final Logger logger = LogManager.getLogger(ArticoloController.class);

	@GetMapping("/cerca")
	public ArticoloDTO findByNomeOCodiceArticolo(@RequestParam("nome-articolo") String nomeArticolo) {
		logger.info("Richiesta servizio ricerca articolo per nome [" + nomeArticolo + "]");
		ArticoloDTO articoloDto = articoloServ.findByName(nomeArticolo);
		return articoloDto;
	}

	@GetMapping("/cerca-meglio")
	public List<ArticoloDTO> findByNomeOCodiceArticolo(@RequestParam("nome-articolo") String nomeArticolo,
			@RequestParam("codice-articolo") String codiceArticolo) {
		logger.info("Richiesta servizio ricerca articolo per nome [" + nomeArticolo + "] o codice: [" + codiceArticolo
				+ "]");
		List<ArticoloDTO> articoloDto = articoloServ.findByNameOrCode(nomeArticolo, codiceArticolo);
		return articoloDto;
	}

	@GetMapping("/cerca-tutto")
	public List<ArticoloDTO> findAll() {
		List<ArticoloDTO> articoloDto = articoloServ.findAll();
		return articoloDto;
	}

	@GetMapping("/conta")
	public Integer contaArticoli() {
		int number = articoloServ.countArticles();
		return number;

	}

}
