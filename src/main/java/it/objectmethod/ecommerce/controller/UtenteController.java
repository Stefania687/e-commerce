package it.objectmethod.ecommerce.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.services.JWTService;
import it.objectmethod.ecommerce.services.UtenteService;
import it.objectmethod.ecommerce.services.dto.UtenteDTO;

@RestController
@RequestMapping("/api/utente")
public class UtenteController {

	@Autowired
	private UtenteService utenteServ;

	@Autowired
	private JWTService jwtServ;

	private static final Logger logger = LogManager.getLogger(UtenteController.class);

	@PostMapping("/login")
	public ResponseEntity<String> findByNomeUtenteAndPassword(@RequestParam("nome-utente") String nomeUtente,
			@RequestParam("password") String password) {
		logger.info("Richiesta di login con nome utente: [" + nomeUtente + "]");
		UtenteDTO utenteDto = utenteServ.findByNomeUtenteAndPassword(nomeUtente, password);

		if (utenteDto != null) {
			String token = jwtServ.createJWTToken(utenteDto);
			logger.info("Creato token per l'utente con id [" + utenteDto.getId() + "] e nome [" + utenteDto.getNome()
					+ "] e il token è [" + token + "]");
			return new ResponseEntity<>(token, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
