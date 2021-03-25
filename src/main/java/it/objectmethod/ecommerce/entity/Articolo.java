package it.objectmethod.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "articolo")
public class Articolo {

	@GeneratedValue
	@Id
	@Column(name = "id_articolo")
	private Long idArticolo;

	@Column(name = "codice_articolo")
	private String codiceArticolo;

	@Column(name = "nome_articolo")
	private String nomeArticolo;

	@Column(name = "disponibilita")
	private Integer disponibilita;

	@Column(name = "prezzo_unitario")
	private Integer prezzo;

	public Long getId() {
		return idArticolo;
	}

	public void setId(Long id) {
		this.idArticolo = id;
	}

	public String getCodiceArticolo() {
		return codiceArticolo;
	}

	public void setCodiceArticolo(String codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}

	public String getNomeArticolo() {
		return nomeArticolo;
	}

	public void setNomeArticolo(String nomeArticolo) {
		this.nomeArticolo = nomeArticolo;
	}

	public Integer getDisponibilita() {
		return disponibilita;
	}

	public void setDisponibilita(Integer disponibilita) {
		this.disponibilita = disponibilita;
	}

	public Integer getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Integer prezzo) {
		this.prezzo = prezzo;
	}

}
