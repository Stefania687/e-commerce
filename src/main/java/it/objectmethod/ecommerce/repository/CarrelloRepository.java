package it.objectmethod.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.ecommerce.entity.Carrello;
import it.objectmethod.ecommerce.entity.CarrelloDettaglio;
import it.objectmethod.ecommerce.entity.Utente;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello, Long> {

	public Carrello findByUtente(Utente utente);

	public Carrello save(Utente utente);

	public Carrello save(CarrelloDettaglio carDett);

}