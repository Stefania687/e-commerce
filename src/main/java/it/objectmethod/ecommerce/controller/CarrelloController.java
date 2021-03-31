package it.objectmethod.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.entity.Articolo;
import it.objectmethod.ecommerce.entity.Carrello;
import it.objectmethod.ecommerce.entity.CarrelloDettaglio;
import it.objectmethod.ecommerce.entity.Utente;
import it.objectmethod.ecommerce.repository.ArticoloRepository;
import it.objectmethod.ecommerce.repository.CarrelloRepository;
import it.objectmethod.ecommerce.repository.UtenteRepository;

@RestController
@RequestMapping("/api/carrello")
public class CarrelloController {
	@Autowired
	private CarrelloRepository carrelloRepo;

	@Autowired
	private ArticoloRepository articoloRepo;

	@Autowired
	private UtenteRepository utenteRepo;

	@GetMapping("/aggiungi-articolo")
	public ResponseEntity<String> aggiungiArticolo(@RequestParam("id-utente") Long idUtente,
			@RequestParam("quantita") Integer quantita, @RequestParam("id-articolo") Long idArticolo) {
		Optional<Articolo> optArticolo = articoloRepo.findById(idArticolo);
		Articolo articolo = optArticolo.get();
		ResponseEntity<String> response = null;

		if (optArticolo.isPresent()) {
			Utente utente = utenteRepo.findById(idUtente).get();
			Carrello carrello = carrelloRepo.findByIdUtente(utente.getIdUtente());
			boolean trovato = false;

			// se il carrello non esiste
			if (carrello == null) {
				carrello = new Carrello();
				utente = utenteRepo.findById(utente.getIdUtente()).get();
				carrello.setUtente(utente);
				carrello = carrelloRepo.save(carrello);
				response = new ResponseEntity<String>("carrello creato", HttpStatus.OK);
			}

			List<CarrelloDettaglio> dettagli;

			// se non ci sono dettagli
			if (carrello.getCarrelloDettaglio() == null) {
				dettagli = new ArrayList<CarrelloDettaglio>();

				// se ci sono dettagli
			} else {
				dettagli = carrello.getCarrelloDettaglio();
			}
			for (CarrelloDettaglio det : dettagli) {
				if (articolo.getIdArticolo().equals(det.getArticolo().getIdArticolo())) {
					int sommaQuantita = det.getQuantita() + quantita;
					if (sommaQuantita <= det.getArticolo().getDisponibilita()) {
						det.setQuantita(sommaQuantita);
						response = new ResponseEntity<String>("quantita aggiornata", HttpStatus.OK);
						trovato = true;
					} else {

						response = new ResponseEntity<String>("quantita non disponibile", HttpStatus.BAD_REQUEST);
					}

				}
				

			}
			carrello = carrelloRepo.save(carrello);

			if (!trovato) {
				if (quantita <= articolo.getDisponibilita()) {
					CarrelloDettaglio dettaglio = new CarrelloDettaglio();
					dettaglio.setArticolo(articolo);
					dettaglio.setQuantita(quantita);
					dettagli.add(dettaglio);

					carrello.setCarrelloDettaglio(dettagli);
					carrello = carrelloRepo.save(carrello);
					response = new ResponseEntity<String>("dettaglio aggiunto", HttpStatus.OK);
				} else {
					response = new ResponseEntity<String>("quantita non disponibile", HttpStatus.BAD_REQUEST);
				}
			}

		} else {
			response = new ResponseEntity<String>("articolo non presente", HttpStatus.BAD_REQUEST);
		}

		return response;
	}

}