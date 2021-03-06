
package it.objectmethod.ecommerce.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "riga_ordine")
public class RigaOrdine {

	@GeneratedValue
	@Id
	@Column(name = "id_riga_ordine")
	private Long idRigaOrdine;

	@ManyToOne
	@JoinColumn(name = "id_ordine")
	private Ordine ordine;

	@ManyToOne
	@JoinColumn(name = "id_articolo")
	private Articolo articolo;

	@Column(name = "quantita")
	private Integer quantita;

	public Long getIdRigaOrdine() {
		return idRigaOrdine;
	}

	public void setIdRigaOrdine(Long idRigaOrdine) {
		this.idRigaOrdine = idRigaOrdine;
	}

	public Articolo getArticolo() {
		return articolo;
	}

	public void setArticolo(Articolo articolo) {
		this.articolo = articolo;
	}

	public Integer getQuantita() {
		return quantita;
	}

	public void setQuantita(Integer quantita) {
		this.quantita = quantita;
	}

	public Ordine getOrdine() {
		return ordine;
	}

	public void setOrdine(Ordine ordine) {
		this.ordine = ordine;
	}

}
