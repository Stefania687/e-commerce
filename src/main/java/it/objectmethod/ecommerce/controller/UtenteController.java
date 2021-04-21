package it.objectmethod.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.services.UtenteService;
import it.objectmethod.ecommerce.services.dto.UtenteDTO;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {

	@Autowired
	private UtenteService utenteServ;

	@PostMapping("/login")
	public ResponseEntity<UtenteDTO> findByNomeUtenteAndPassword(@RequestParam("nome-utente") String nomeUtente,
			@RequestParam("password") String password) {

		UtenteDTO utente = utenteServ.findByNomeUtenteAndPassword(nomeUtente, password);

		if (utente.getId() != null && utente.getNome() != null) {

			return new ResponseEntity<UtenteDTO>(utente, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<UtenteDTO>(HttpStatus.BAD_REQUEST);

	}

}
