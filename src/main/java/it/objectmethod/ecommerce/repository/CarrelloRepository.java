package it.objectmethod.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.ecommerce.entity.Carrello;
import it.objectmethod.ecommerce.entity.Utente;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello, Long> {

	public Optional<Carrello> findById(Long idUtente);

	public Carrello save(Utente idUtente);

}