package it.objectmethod.ecommerce.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.objectmethod.ecommerce.entity.Articolo;
import it.objectmethod.ecommerce.entity.Carrello;
import it.objectmethod.ecommerce.entity.CarrelloDettaglio;
import it.objectmethod.ecommerce.entity.Ordine;
import it.objectmethod.ecommerce.entity.RigaOrdine;
import it.objectmethod.ecommerce.repository.ArticoloRepository;
import it.objectmethod.ecommerce.repository.CarrelloRepository;
import it.objectmethod.ecommerce.repository.OrdineRepository;
import it.objectmethod.ecommerce.services.dto.OrdineDTO;
import it.objectmethod.ecommerce.services.mapper.OrdineMapper;

@Service
public class OrdineService {

	@Autowired
	private CarrelloRepository carrelloRepo;

	@Autowired
	private OrdineRepository ordineRepo;

	@Autowired
	private ArticoloRepository articoloRepo;

	@Autowired
	private OrdineMapper ordineMap;

	public OrdineDTO aggiungiOrdine(Long idUtente) {

		OrdineDTO ordineDto = null;

		Carrello carrello = carrelloRepo.findByIdUtente(idUtente);

		if (carrello != null) {
			Ordine ordine = new Ordine();
			List<RigaOrdine> righe = new ArrayList<RigaOrdine>();

			boolean errore = false;

			for (CarrelloDettaglio dett : carrello.getCarrelloDettaglio()) {
				Articolo art = dett.getArticolo();
				int nuovaDisponibilita = art.getDisponibilita() - dett.getQuantita();

				if (nuovaDisponibilita >= 0) {

					RigaOrdine riga = new RigaOrdine();
					art.setDisponibilita(nuovaDisponibilita);
					riga.setArticolo(art);
					riga.setQuantita(dett.getQuantita());
					righe.add(riga);
					articoloRepo.save(art);

				} else {
					errore = true;
					break;
				}

			}

			if (!errore) {
				LocalDate data = LocalDate.now();
				ordine.setDataOrdine(data);
				ordine.setIdUtente(idUtente);
				ordine.setRigaOrdine(righe);
				ordine.setNumeroOrdine("A000" + carrello.getIdCarrello());

				ordine = ordineRepo.save(ordine);
				ordineDto = ordineMap.toDto(ordine);
				carrelloRepo.deleteById(carrello.getIdCarrello());
			}

		}
		System.out.println(ordineDto);

		return ordineDto;
	}
}
