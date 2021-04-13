package it.objectmethod.ecommerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.ecommerce.entity.Articolo;
import it.objectmethod.ecommerce.entity.Carrello;
import it.objectmethod.ecommerce.entity.CarrelloDettaglio;
import it.objectmethod.ecommerce.entity.Utente;
import it.objectmethod.ecommerce.repository.ArticoloRepository;
import it.objectmethod.ecommerce.repository.CarrelloRepository;
import it.objectmethod.ecommerce.repository.UtenteRepository;
import it.objectmethod.ecommerce.services.dto.ArticoloCarrelloDTO;
import it.objectmethod.ecommerce.services.dto.CarrelloDTO;
import it.objectmethod.ecommerce.services.mapper.ArticoloCarrelloMapper;
import it.objectmethod.ecommerce.services.mapper.CarrelloMapper;

@Service
public class CarrelloService {
	@Autowired
	private CarrelloRepository carrelloRepo;

	@Autowired
	private ArticoloRepository articoloRepo;

	@Autowired
	private UtenteRepository utenteRepo;

	@Autowired
	private CarrelloMapper carrelloMap;

	@Autowired
	private ArticoloCarrelloMapper artCarrMap;

	public CarrelloDTO aggiungiArticolo(ArticoloCarrelloDTO articoloCarrDto) {

		CarrelloDTO carrelloDto = null;
		Articolo articolo = artCarrMap.toEntity(articoloCarrDto);
		Optional<Articolo> optArticolo = articoloRepo.findById(articoloCarrDto.getIdArticolo());
		int quantita = articoloCarrDto.getQuantita();

		if (optArticolo.isPresent()) {
			articolo = optArticolo.get();
			Optional<Utente> optUtente = utenteRepo.findById(articoloCarrDto.getIdUtente());
			Utente utente = optUtente.get();
			utente = utenteRepo.findById(utente.getIdUtente()).get();
			Carrello carrello = carrelloRepo.findByIdUtente(utente.getIdUtente());

			if (carrello == null) {
				carrello = new Carrello();
				carrello.setUtente(utente);
				carrello = carrelloRepo.save(carrello);
			}
			List<CarrelloDettaglio> dettagli = null;
			if (carrello.getCarrelloDettaglio() == null) {
				dettagli = new ArrayList<CarrelloDettaglio>();
			} else {
				dettagli = carrello.getCarrelloDettaglio();
			}
			boolean trovato = false;
			for (CarrelloDettaglio det : dettagli) {
				if (articolo.getIdArticolo().equals(det.getArticolo().getIdArticolo())) {
					int somma = det.getQuantita() + quantita;
					if (somma <= det.getArticolo().getDisponibilita()) {
						det.setQuantita(somma);
						trovato = true;
						break;
					}
				}
			}
			if (!trovato) {
				if (quantita <= articolo.getDisponibilita()) {
					CarrelloDettaglio dettaglio = new CarrelloDettaglio();
					dettaglio.setArticolo(articolo);
					dettaglio.setQuantita(quantita);
					dettagli.add(dettaglio);

				}
			}
			carrello.setCarrelloDettaglio(dettagli);
			carrello = carrelloRepo.save(carrello);
			carrelloDto = carrelloMap.toDto(carrello);

		}

		return carrelloDto;

	}

}
