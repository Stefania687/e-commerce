package it.objectmethod.ecommerce.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ordine")
public class Ordine {

	@GeneratedValue
	@Id
	@Column(name = "id_ordine")
	private Long idOrdine;

	@Column(name = "numero_ordine")
	private String numeroOrdine;

	@Column(name = "data_ordine")
	private String dataOrdine;

	@ManyToOne
	@JoinColumn(name = "utente")
	private Utente idUtente;

	@OneToMany
	@JoinColumn(name = "riga_ordine")
	private List<RigaOrdine> rigaOrdine;

	public Long getIdOrdine() {
		return idOrdine;
	}

	public void setIdOrdine(Long idOrdine) {
		this.idOrdine = idOrdine;
	}

	public String getNumeroOrdine() {
		return numeroOrdine;
	}

	public void setNumeroOrdine(String numeroOrdine) {
		this.numeroOrdine = numeroOrdine;
	}

	public String getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(String dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public Utente getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Utente idUtente) {
		this.idUtente = idUtente;
	}

	public List<RigaOrdine> getRigaOrdine() {
		return rigaOrdine;
	}

	public void setRigaOrdine(List<RigaOrdine> rigaOrdine) {
		this.rigaOrdine = rigaOrdine;
	}

}
