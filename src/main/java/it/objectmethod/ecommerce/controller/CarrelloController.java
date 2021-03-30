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
		ResponseEntity<String> response = null;
		Utente utente = utenteRepo.findById(idUtente).get();

		if (optArticolo.isPresent()) {
			Articolo articolo = optArticolo.get();
			Carrello carrello = carrelloRepo.findByUtente(utente);
			List<CarrelloDettaglio> carDett;
			CarrelloDettaglio dettaglio;

			/////////// CARRELLO SI ////////////

			if (carrello != null) {
				carDett = carrello.getCarrelloDettaglio();

				for (CarrelloDettaglio dett : carDett) {
					//// MODIFICA QUANTITA ////
					if (idArticolo == (dett.getArticolo().getIdArticolo())) {

						int sommaQuantita = dett.getQuantita() + quantita;
						if (dett.getArticolo().getDisponibilita() > sommaQuantita) {
							dett.setQuantita(sommaQuantita);
						} else {
							response = new ResponseEntity<String>("Quantita non disponibile", HttpStatus.BAD_REQUEST);
						}
						carrello = carrelloRepo.save(carrello);

					//// AGGIUNTA ARTICOLO ////
					} else {

						if (articolo.getDisponibilita() > quantita) {
							dettaglio = new CarrelloDettaglio();
							dettaglio.setArticolo(articolo);
							dettaglio.setCarrello(carrello);
							dettaglio.setQuantita(quantita);
							carDett.add(dettaglio);
							carrello.setCarrelloDettaglio(carDett);
							carrello = carrelloRepo.save(carrello);

						} else {
							response = new ResponseEntity<String>("Quantita non disponibile", HttpStatus.BAD_REQUEST);
						}

					}
				}

				//////////// CARRELLO NO ////////////

			} else {
				carrello = new Carrello();
				carrello.setUtente(utente);
				carDett = new ArrayList<CarrelloDettaglio>();
				CarrelloDettaglio carDettaglio = new CarrelloDettaglio();

				if (articolo.getDisponibilita() > quantita) {
					carDettaglio.setArticolo(articolo);
					carDettaglio.setQuantita(quantita);
					carDettaglio.setCarrello(carrello);
					carDett.add(carDettaglio);
					carrello.setCarrelloDettaglio(carDett);

				} else {
					response = new ResponseEntity<String>("Quantita non disponibile", HttpStatus.BAD_REQUEST);
				}
				carrello = carrelloRepo.save(carrello);
			}

		} else {
			response = new ResponseEntity<String>("Articolo non presente", HttpStatus.BAD_REQUEST);

		}
		return response;

	}

}