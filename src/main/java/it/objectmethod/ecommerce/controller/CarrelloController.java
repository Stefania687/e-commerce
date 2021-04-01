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
	public ResponseEntity<Carrello> aggiungiArticolo(@RequestParam("id-utente") Long idUtente,
			@RequestParam("quantita") Integer quantita, @RequestParam("id-articolo") Long idArticolo) {

		Optional<Articolo> optArticolo = articoloRepo.findById(idArticolo);
		ResponseEntity<Carrello> response = null;

		if (optArticolo.isPresent()) {
			Articolo articolo = optArticolo.get();
			Utente utente = utenteRepo.findById(idUtente).get();
			Carrello carrello = carrelloRepo.findByIdUtente(utente.getIdUtente());

			if (carrello == null) {
				carrello = new Carrello();
				carrello.setUtente(utente);
				carrello = carrelloRepo.save(carrello);
			}

			List<CarrelloDettaglio> dettagli = null;

			if (carrello.getCarrelloDettaglio() == null) {
				dettagli = new ArrayList<CarrelloDettaglio>();

			} else {
				dettagli = carrello.getCarrelloDettaglio();
			}

			boolean trovato = false;

			for (CarrelloDettaglio det : dettagli) {
				if (articolo.getIdArticolo().equals(det.getArticolo().getIdArticolo())) {
					int sommaQuantita = det.getQuantita() + quantita;
					if (sommaQuantita <= det.getArticolo().getDisponibilita()) {
						det.setQuantita(sommaQuantita);
						trovato = true;
						break;

					}

				}

			}

			if (!trovato) {
				if (quantita <= articolo.getDisponibilita()) {
					CarrelloDettaglio dettaglio = new CarrelloDettaglio();
					dettaglio.setArticolo(articolo);
					dettaglio.setQuantita(quantita);
					dettagli.add(dettaglio);
					carrello.setCarrelloDettaglio(dettagli);

				}
			}
			carrello = carrelloRepo.save(carrello);
			response = new ResponseEntity<Carrello>(carrello, HttpStatus.OK);

		} else {
			response = new ResponseEntity<Carrello>(HttpStatus.BAD_REQUEST);
		}

		return response;
	}

}