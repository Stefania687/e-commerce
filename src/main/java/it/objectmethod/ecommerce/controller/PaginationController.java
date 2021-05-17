package it.objectmethod.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import it.objectmethod.ecommerce.services.PaginationService;
import it.objectmethod.ecommerce.services.dto.ArticoloDTO;

@RestController
@RequestMapping("/api/pag-articoli")
public class PaginationController {
	
	@Autowired
	PaginationService pagServ;
	
	
	@GetMapping("/{page-num}/{elements-num}/articoli-list")
	public ResponseEntity<List<ArticoloDTO>> getQuestionsList(@PathVariable("page-num") Integer pageNumber,
			@PathVariable("elements-num") Integer elemNumber) {
		ResponseEntity<List<ArticoloDTO>> resp = null;
		List<ArticoloDTO> articoliList = null;
		try {
			articoliList = pagServ.getPages(pageNumber, elemNumber);
			if (articoliList != null) {
				resp = new ResponseEntity<List<ArticoloDTO>>(articoliList, HttpStatus.OK);
			} else {
				resp = new ResponseEntity<List<ArticoloDTO>>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception err) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Errore caricando lista di question",
					err);
		}
		return resp;
	}

}
