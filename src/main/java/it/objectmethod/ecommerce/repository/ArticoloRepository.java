package it.objectmethod.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.objectmethod.ecommerce.entity.Articolo;

@Repository
public interface ArticoloRepository extends JpaRepository<Articolo, Long> {

	@Query(value = "select a from Articolo a")
	public List<Articolo> findArticolo();

	public List<Articolo> findByNomeArticolo(String nomeArticolo);

	public List<Articolo> findByCodiceArticolo(String codiceArticolo);

}
