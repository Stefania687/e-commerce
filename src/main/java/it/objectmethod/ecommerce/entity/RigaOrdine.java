
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
@Table(name = "riga_ordine")
public class RigaOrdine {

	@GeneratedValue
	@Id
	@Column(name = "id_riga_ordine")
	private Long idRigaOrdine;

	@ManyToOne
	@JoinColumn(name = "ordine")
	private Ordine idOrdine;

	@OneToOne
	@JoinColumn(name = "articolo")
	private Articolo idArticolo;

	@Column(name = "quantita")
	private Integer quantita;

	public Long getIdRigaOrdine() {
		return idRigaOrdine;
	}

	public void setIdRigaOrdine(Long idRigaOrdine) {
		this.idRigaOrdine = idRigaOrdine;
	}

	public Ordine getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(Ordine idOrdine) {
		this.idOrdine = idOrdine;
	}

	public Articolo getIdArticolo() {
		return idArticolo;
	}

	public void setIdArticolo(Articolo idArticolo) {
		this.idArticolo = idArticolo;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

}
