package it.objectmethod.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.ecommerce.entity.Carrello;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello, Long> {

	public Carrello findByIdUtente(Long idUtente);

}