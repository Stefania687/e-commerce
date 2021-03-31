package it.objectmethod.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.repository.UtenteRepository;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {

	@Autowired
	private UtenteRepository utenteRepo;

	@PostMapping("/login")
	ResponseEntity<String> utente(@RequestParam("nome-utente") String nomeUtente,
			@RequestParam("password") String password) {

		List<String> utenti = utenteRepo.findByNomeUtenteAndPassword(nomeUtente, password);
		ResponseEntity<String> response;

		if ((nomeUtente.isEmpty()) || (password.isEmpty())) {
			response = new ResponseEntity<String>("credenziali non inserite!", HttpStatus.BAD_REQUEST);

		} else if (utenti == null) {
			response = new ResponseEntity<String>("credenziali errate!", HttpStatus.BAD_REQUEST);

		} else {
			response = new ResponseEntity<String>(nomeUtente, HttpStatus.OK);
		}
		return response;
	}

}
