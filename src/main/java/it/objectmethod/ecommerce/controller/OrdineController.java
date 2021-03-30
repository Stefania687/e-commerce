package it.objectmethod.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.entity.Carrello;
import it.objectmethod.ecommerce.repository.ArticoloRepository;
import it.objectmethod.ecommerce.repository.CarrelloRepository;
import it.objectmethod.ecommerce.repository.OrdineRepository;
import it.objectmethod.ecommerce.repository.UtenteRepository;

@RestController
@RequestMapping("/api/ordine")
public class OrdineController {
// Realizzare un servizio di inserimento ordine per salvare i dati 
// contenuti nel carrello nelle tabelle ORDINE e RIGA ORDINE.
	@Autowired
	private CarrelloRepository carrelloRepo;

	@Autowired
	private ArticoloRepository articoloRepo;

	@Autowired
	private UtenteRepository utenteRepo;
	
	@Autowired
	private OrdineRepository ordineRepo;

	@GetMapping("/aggiungi-ordine")
	public ResponseEntity<String> aggiungiOrdine(@RequestParam("id-utente") Long idUtente) {
		ResponseEntity<String> response = null;
		Carrello carrello = carrelloRepo.findByIdUtente(idUtente);
		
		
			
		
		
		
		
		return response;
	}

}
