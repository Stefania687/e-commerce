package it.objectmethod.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.objectmethod.ecommerce.services.OrdineService;
import it.objectmethod.ecommerce.services.dto.OrdineDTO;


@RestController
@RequestMapping("/api/ordine")
public class OrdineController {

	@Autowired
	private OrdineService ordineServ;

	@GetMapping("/aggiungi-ordine")
	public ResponseEntity<OrdineDTO> salvaOrdine(@RequestParam("id-utente") Long idUtente) {
		OrdineDTO ordineDto = ordineServ.aggiungiOrdine(idUtente);
		ResponseEntity<OrdineDTO> response = null;

		if (ordineDto != null) {
			response = new ResponseEntity<OrdineDTO>(ordineDto, HttpStatus.ACCEPTED);
		} else {
			response = new ResponseEntity<OrdineDTO>(HttpStatus.BAD_REQUEST);
		}

		return response;

	}

}
