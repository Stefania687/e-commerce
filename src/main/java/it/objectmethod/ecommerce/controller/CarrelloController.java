package it.objectmethod.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.services.CarrelloService;
import it.objectmethod.ecommerce.services.dto.ArticoloCarrelloDTO;
import it.objectmethod.ecommerce.services.dto.CarrelloDTO;


@RestController
@RequestMapping("/api/carrello")
public class CarrelloController {

	@Autowired
	CarrelloService carrelloServ;

	@GetMapping("/aggiungi-articolo")
	public ResponseEntity<CarrelloDTO> aggiungiArticolo(@RequestBody ArticoloCarrelloDTO articoloCarrDto) {

		ResponseEntity<CarrelloDTO> response = new ResponseEntity<CarrelloDTO>(HttpStatus.BAD_REQUEST);
		CarrelloDTO carrelloDto = carrelloServ.aggiungiArticolo(articoloCarrDto);
		
		if (carrelloDto != null) {
			response = new ResponseEntity<CarrelloDTO>(carrelloDto, HttpStatus.ACCEPTED);
		}

		return response;

	}

}