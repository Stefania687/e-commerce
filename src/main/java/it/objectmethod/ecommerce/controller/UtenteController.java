package it.objectmethod.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.services.UtenteService;
import it.objectmethod.ecommerce.services.dto.UtenteDTO;
import it.objectmethod.ecommerce.services.dto.UtenteRequestDTO;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {

	@Autowired
	private UtenteService utenteServ;

	@PostMapping("/login")
	public ResponseEntity<UtenteDTO> findByNomeUtenteAndPassword(@RequestBody() UtenteRequestDTO ute) {

		ResponseEntity<UtenteDTO> response = null;

		UtenteDTO utente = utenteServ.findByNomeUtenteAndPassword(ute);

		if (utente != null) {
			response = new ResponseEntity<UtenteDTO>(utente, HttpStatus.ACCEPTED);
		} else if (utente == null) {
			response = new ResponseEntity<UtenteDTO>(HttpStatus.BAD_REQUEST);
		}

		return response;

	}

}
