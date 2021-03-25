package it.objectmethod.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.objectmethod.ecommerce.entity.Utente;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long> {

	@Query(value = "select u.nomeUtente from Utente u where u.nomeUtente = ?1 and password = ?2")
	public List<String> findByNomeUtenteAndPassword(String nomeUtente, String password);

}
