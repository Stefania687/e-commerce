//package it.objectmethod.ecommerce.entity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class aaa {
//
//	public CarrelloDTO addItems(String codiceArticolo, Integer quantita, UtenteDTO utenteDTO) {
//		CarrelloDTO carrelloDTO = null;
//		boolean errore = false;
//		Articolo articolo = articoloRep.findByCodiceArticolo(codiceArticolo);
//		if (articolo != null && articolo.getDisponibilita() > quantita) {
//			Utente utente = utenteMapper.toEntity(utenteDTO);
//			boolean trovato = false;
//			Carrello carrello = carrelloRep.findByIdUtente(utente.getId());
//			if (carrello == null) {
//				carrello = new Carrello();
//				utente = utenteRep.findById(utente.getId()).get();
//				carrello.setUtente(utente);
//
//			}
//
//			List<CarrelloDettaglio> dettagli;
//
//			if (carrello.getDettagli() == null) {
//				dettagli = new ArrayList<CarrelloDettaglio>();
//			} else {
//				dettagli = carrello.getDettagli();
//			}
//
//			for (CarrelloDettaglio det : dettagli) {
//				if (articolo.getCodiceArticolo().equals(det.getArticolo().getCodiceArticolo())) {
//					int somma = det.getQuantita() + quantita;
//					if (somma <= det.getArticolo().getDisponibilita()) {
//						det.setQuantita(somma);
//						trovato = true;
//						break;
//					} else {
//						errore = true;
//						break;
//					}
//
//				}
//			}
//			if (!errore) {
//				if (!trovato) {
//					CarrelloDettaglio dettaglio = new CarrelloDettaglio();
//					dettaglio.setArticolo(articolo);
//					dettaglio.setQuantita(quantita);
//					dettagli.add(dettaglio);
//				}
//
//				carrello.setDettagli(dettagli);
//
//				carrello = carrelloRep.save(carrello);
//				carrelloDTO = carrelloMapper.toDto(carrello);
//
//			}
//
//		}
//		return carrelloDTO;
//	}
//}
//}
