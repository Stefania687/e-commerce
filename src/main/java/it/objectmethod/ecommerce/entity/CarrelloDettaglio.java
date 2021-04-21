package it.objectmethod.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carrello_dettaglio")
public class CarrelloDettaglio {

	@GeneratedValue
	@Id
	@Column(name = "id_carrello_dettaglio")
	private Long idCarrelloDettaglio;

	private Integer quantita;

	@ManyToOne
	@JoinColumn(name = "id_articolo")
	private Articolo articolo;

	@ManyToOne
	@JoinColumn(name = "id_carrello")
	private Carrello carrello;

	public Long getIdCarrelloDettaglio() {
		return idCarrelloDettaglio;
	}

	public void setIdCarrelloDettaglio(Long idCarrelloDettaglio) {
		this.idCarrelloDettaglio = idCarrelloDettaglio;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public Articolo getArticolo() {
		return articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public Carrello getCarrello() {
		return carrello;
	}

	public void setCarrello(Carrello carrello) {
		this.carrello = carrello;
	}



}
