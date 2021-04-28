package it.objectmethod.ecommerce.services.dto;

public class ArticoloCarrelloDTO {
	private Long idArticolo;
	private Integer quantita;

	public Long getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(Long idArticolo) {
		this.idArticolo = idArticolo;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

}
