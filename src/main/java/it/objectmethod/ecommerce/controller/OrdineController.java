package it.objectmethod.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.services.JWTService;
import it.objectmethod.ecommerce.services.OrdineService;
import it.objectmethod.ecommerce.services.dto.OrdineDTO;
import it.objectmethod.ecommerce.services.dto.UtenteDTO;

@RestController
@RequestMapping("/api/ordine")
public class OrdineController {

	@Autowired
	private OrdineService ordineServ;

	@Autowired
	private JWTService jwtServ;

	@GetMapping("/aggiungi-ordine")
	public ResponseEntity<OrdineDTO> salvaOrdine(@RequestHeader("auth-token") String token) {
		UtenteDTO utenteDto = jwtServ.getUtenteByToken(token);
		OrdineDTO ordineDto = ordineServ.aggiungiOrdine(utenteDto);
		ResponseEntity<OrdineDTO> response = null;

		if (ordineDto != null) {
			response = new ResponseEntity<OrdineDTO>(ordineDto, HttpStatus.ACCEPTED);
		} else {
			response = new ResponseEntity<OrdineDTO>(HttpStatus.BAD_REQUEST);
			System.out.println("bad request");
		}

		return response;

	}

}
