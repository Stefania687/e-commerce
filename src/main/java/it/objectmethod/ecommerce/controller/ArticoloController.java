package it.objectmethod.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.entity.Articolo;
import it.objectmethod.ecommerce.repository.ArticoloRepository;

@RestController
@RequestMapping("/api/articoli")
public class ArticoloController {
	@Autowired
	private ArticoloRepository articoloRepo;

	@GetMapping("/lista")
	public List<Articolo> findArticoli() {
		List<Articolo> articoli = articoloRepo.findArticolo();
		return articoli;
	}
	
	@GetMapping("/lista/nome")
	public List<Articolo> findArticoloByName(@RequestParam("nomeArticolo") String nomeArticolo) {
		List<Articolo> articolo = articoloRepo.findByNomeArticolo(nomeArticolo);
		return articolo;
	}
	
	@GetMapping("/lista/codice")
	public List<Articolo> findArticoloByCode(@RequestParam("codiceArticolo") String codiceArticolo) {
		List<Articolo> articolo = articoloRepo.findByCodiceArticolo(codiceArticolo);
		return articolo;
	}

}
