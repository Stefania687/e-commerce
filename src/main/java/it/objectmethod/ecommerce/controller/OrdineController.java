package it.objectmethod.ecommerce.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import it.objectmethod.ecommerce.entity.Ordine;
import it.objectmethod.ecommerce.entity.RigaOrdine;
import it.objectmethod.ecommerce.repository.ArticoloRepository;
import it.objectmethod.ecommerce.repository.CarrelloRepository;
import it.objectmethod.ecommerce.repository.OrdineRepository;

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
	private OrdineRepository ordineRepo;

	@GetMapping("/aggiungi-ordine")
	public ResponseEntity<String> aggiungiOrdine(@RequestParam("id-utente") Long idUtente) {
		ResponseEntity<String> response = null;
		Boolean errore = false;
		
		Carrello carrello = carrelloRepo.findByIdUtente(idUtente);

		if (carrello != null) {
			Ordine ordine = new Ordine();
			List<RigaOrdine> righe = new ArrayList<RigaOrdine>();
			for (CarrelloDettaglio dett : carrello.getCarrelloDettaglio()) {
				Articolo art = dett.getArticolo();
				int newDisponibilita = art.getDisponibilita() - dett.getQuantita();

				if (newDisponibilita >= 0) {
					art.setDisponibilita(newDisponibilita);
					RigaOrdine riga = new RigaOrdine();
					riga.setArticolo(art);
					riga.setQuantita(dett.getQuantita());
					righe.add(riga);
					articoloRepo.save(art);

				} else {
					errore = true;
					response = new ResponseEntity<String>("La quantita richiesta non è disponibile",
							HttpStatus.BAD_REQUEST);
				}

			}
			if (!errore) {
				LocalDate data = LocalDate.now();
				ordine.setDataOrdine(data);
				ordine.setUtente(carrello.getUtente());
				ordine.setRigaOrdine(righe);
				ordine.setNumeroOrdine("A000" + carrello.getIdCarrello());

				ordine = ordineRepo.save(ordine);
				carrelloRepo.deleteById(carrello.getIdCarrello());
				response = new ResponseEntity<String>("ordine aggiunto", HttpStatus.OK);
			}

		} else {
			response = new ResponseEntity<String>("carrello non esistente", HttpStatus.BAD_REQUEST);
		}

		return response;
	}

}
