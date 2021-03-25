package it.objectmethod.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carrello_dettaglio")
public class CarrelloDettaglio {

	@GeneratedValue
	@Id
	@Column(name = "id_carrello_dettaglio")
	private Long idCarrelloDettaglio;

	private Integer quantita;

	@OneToOne
	@JoinColumn(name = "id_articolo")
	private Articolo idArticolo;

	@ManyToOne
	@JoinColumn(name = "id_carrello")
	private Carrello idCarrello;

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

	public Articolo getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(Articolo idArticolo) {
		this.idArticolo = idArticolo;
	}

	public Carrello getIdCarrello() {
		return idCarrello;
	}

	public void setIdCarrello(Carrello idCarrello) {
		this.idCarrello = idCarrello;
	}

}
