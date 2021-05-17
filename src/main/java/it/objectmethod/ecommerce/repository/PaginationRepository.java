package it.objectmethod.ecommerce.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import it.objectmethod.ecommerce.entity.Articolo;

@Repository
public interface PaginationRepository extends PagingAndSortingRepository<Articolo, Long> {

}
