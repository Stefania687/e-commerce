package it.objectmethod.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.ecommerce.entity.Ordine;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long>{
	public Ordine findByIdUtente(Long idUtente);

}
