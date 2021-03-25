package it.objectmethod.ecommerce.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.entity.Carrello;
import it.objectmethod.ecommerce.entity.Utente;
import it.objectmethod.ecommerce.repository.CarrelloRepository;


@RestController
@RequestMapping("/api/carrello")
public class CarrelloController {

	@Autowired
	private CarrelloRepository carrelloRepo;

//	@Autowired
//	private ArticoloRepository articoloRepo;


	@GetMapping("/find")
	public Optional<Carrello> findCarrello(@RequestParam("id-utente") Long idUtente) {
		Optional<Carrello> carr = carrelloRepo.findById(idUtente);

		return carr;
	}
	
	@GetMapping("/save")
	public Carrello saveCarrello(@RequestParam("id-utente") Utente idUtente) {
		Carrello carr = new Carrello();
		
		carr.setUtente(idUtente);
		
		carr = carrelloRepo.save(idUtente);
		
		return carr;
	}

	
	
	
	
	
	
//	@GetMapping("/aggiungi-articolo")
//	public ResponseEntity<Carrello> aggiungiArticolo(@RequestParam("id-utente") Long idUtente,
//			@RequestParam("quantita") Integer quantita, @RequestParam("id-articolo") Long idArticolo) {
//
//		Optional<Articolo> optArticolo = articoloRepo.findById(idArticolo);
//		ResponseEntity<Carrello> response = null;
//
//		if (optArticolo.isPresent()) {
//			Optional<Carrello> carr = carrelloRepo.findById(idUtente);
//			if (carr.isPresent()) {
//				List<CarrelloDettaglio> carrDett;
//				if (disponibilita > quantita) {
//					
//					 aggiungi articolo al carrello
//					 aggiorna quantita
//					 aggiorna disponibilita 
//					  
//				} else {
//					response = new ResponseEntity<Carrello>(HttpStatus.BAD_REQUEST);
//				}
//			} else {
//				//creo il carrello
//				Carrello carrello = new Carrello();
//				for (Utente u : carrello.getIdUtente()) {
//					u.setCarrello(carrello);
//				}
//				
//			}
//		} else {
//			response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//		}
//
//		return response;
//
//	}

}

/*
 * controllo se esiste l'articolo: findArticoloByIdArticolo
 * 	_ se esiste:
 * 		controllo se esiste il carrello: findCarrelloByIdUtente
 * 			_ se il carrello non esiste lo creo: save
 * 			_ se il carrello esiste controllo la disponibilita:
 * 				_ se disponibilita > quantita -> aggiorno il carrello
 * 				_ se disponibilita < quantita -> bad request
 * _ se non esiste: mando una bad request
 */
