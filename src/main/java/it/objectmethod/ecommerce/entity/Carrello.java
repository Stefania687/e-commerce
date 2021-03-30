package it.objectmethod.ecommerce.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "carrello")
public class Carrello {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id_carrello")
	private Long idCarrello;

	@OneToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_carrello")
	private List<CarrelloDettaglio> carrelloDettaglio;

	public Long getIdCarrello() {
		return idCarrello;
	}

	public void setIdCarrello(Long idCarrello) {
		this.idCarrello = idCarrello;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public List<CarrelloDettaglio> getCarrelloDettaglio() {
		return carrelloDettaglio;
	}

	public void setCarrelloDettaglio(List<CarrelloDettaglio> carrelloDettaglio) {
		this.carrelloDettaglio = carrelloDettaglio;
	}


}
