package it.objectmethod.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.objectmethod.ecommerce.entity.Articolo;

@Repository
public interface ArticoloRepository extends JpaRepository<Articolo, Long> {

	@Query(value = "select a from Articolo a where ('' = ?1 or a.nomeArticolo = ?1) and ('' = ?2 or a.codiceArticolo = ?2)")
	public List<Articolo> findByNameOrCode(String nomeArticolo, String codiceArticolo);

	@Query(value = "select a from Articolo a where a.nomeArticolo = ?1")
	public Articolo findByName(String nomeArticolo);

	@Query(value = "SELECT COUNT(idArticolo) FROM Articolo a")
	public Integer findArticlesNumber();

}
