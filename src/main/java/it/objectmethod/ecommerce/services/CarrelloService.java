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
import it.objectmethod.ecommerce.services.dto.UtenteDTO;
import it.objectmethod.ecommerce.services.mapper.ArticoloCarrelloMapper;
import it.objectmethod.ecommerce.services.mapper.CarrelloMapper;

@Service
public class CarrelloService {
	@Autowired
	private CarrelloRepository carrelloRepo;

	@Autowired
	private ArticoloRepository articoloRepo;

	@Autowired
	private CarrelloMapper carrelloMap;

	@Autowired
	private ArticoloCarrelloMapper artCarrMap;

	@Autowired
	private UtenteRepository utenteRepo;

	public CarrelloDTO aggiungiArticolo(ArticoloCarrelloDTO artCarrDto, UtenteDTO utenteDto) {

		CarrelloDTO carrelloDto = null;
		Integer quantita = artCarrDto.getQuantita();
		Articolo articolo = artCarrMap.toEntity(artCarrDto);
		Optional<Articolo> optArticolo = articoloRepo.findById(articolo.getIdArticolo());

		if (optArticolo.isPresent()) {
			articolo = optArticolo.get();
			Utente utente = utenteRepo.findById(utenteDto.getId()).get();
			Carrello carrello = carrelloRepo.findByIdUtente(utente.getIdUtente());
			if (carrello == null) {
				carrello = new Carrello();
				utente = utenteRepo.findById(utente.getIdUtente()).get();
				System.out.println(utente.getNomeUtente() + "sono nel carrello = null");
				carrello.setUtente(utente);
				carrello = carrelloRepo.save(carrello);
			}
			List<CarrelloDettaglio> dettagli = carrello.getCarrelloDettaglio();

			if (carrello.getCarrelloDettaglio() == null) {
				dettagli = new ArrayList<CarrelloDettaglio>();
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
