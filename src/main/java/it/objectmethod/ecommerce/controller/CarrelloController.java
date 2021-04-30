package it.objectmethod.ecommerce.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.services.CarrelloService;
import it.objectmethod.ecommerce.services.JWTService;
import it.objectmethod.ecommerce.services.dto.ArticoloCarrelloDTO;
import it.objectmethod.ecommerce.services.dto.CarrelloDTO;
import it.objectmethod.ecommerce.services.dto.UtenteDTO;

@RestController
@RequestMapping("/api/carrello")
public class CarrelloController {

	@Autowired
	CarrelloService carrelloServ;
	
	@Autowired
	private JWTService jwtServ;
	
	private static final Logger logger = LogManager.getLogger(CarrelloController.class);

	@GetMapping("/aggiungi-articolo")
	public ResponseEntity<CarrelloDTO> aggiungiArticolo(@RequestBody ArticoloCarrelloDTO artCarrDto,
			@RequestHeader("auth-token") String token) {
		UtenteDTO utenteDto = jwtServ.getUtenteByToken(token);
		CarrelloDTO carrelloDto = carrelloServ.aggiungiArticolo(artCarrDto, utenteDto);
		ResponseEntity<CarrelloDTO> response = new ResponseEntity<CarrelloDTO>(HttpStatus.BAD_REQUEST);

		if (carrelloDto != null) {
			response = new ResponseEntity<CarrelloDTO>(carrelloDto, HttpStatus.ACCEPTED);
			logger.info("Carrello creato per l'utente [" + utenteDto.getNome() + "]");
		}

		return response;

	}

}