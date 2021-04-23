package it.objectmethod.ecommerce.services.dto;

import java.time.LocalDate;

public class OrdineDTO {

	private Long id;
	private Long idUtente;
	// private String nomeUtente;
	private LocalDate dataOrdine;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Long idUtente) {
		this.idUtente = idUtente;
	}

	public LocalDate getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(LocalDate dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

}
