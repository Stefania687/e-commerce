package it.objectmethod.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.entity.Utente;
import it.objectmethod.ecommerce.repository.UtenteRepository;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {

	@Autowired
	private UtenteRepository utenteRepo;

	@PostMapping("/login")
	ResponseEntity<Utente> utente(@RequestParam("nome-utente") String nomeUtente,
			@RequestParam("password") String password) {

		Utente utente = utenteRepo.findByNomeUtenteAndPassword(nomeUtente, password);
		ResponseEntity<Utente> response;

		if ((nomeUtente.isEmpty()) || (password.isEmpty())) {
			response = new ResponseEntity<Utente>(HttpStatus.BAD_REQUEST);

		} else if (utente == null) {
			response = new ResponseEntity<Utente>(HttpStatus.BAD_REQUEST);

		} else {
			response = new ResponseEntity<Utente>(utente, HttpStatus.OK);
		}
		return response;
	}

}
