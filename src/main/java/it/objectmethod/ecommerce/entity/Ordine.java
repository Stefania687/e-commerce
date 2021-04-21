package it.objectmethod.ecommerce.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
	private LocalDate dataOrdine;

	@ManyToOne
	@JoinColumn(name = "id_utente")
	private Utente utente;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_ordine")
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

	public LocalDate getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(LocalDate dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public List<RigaOrdine> getRigaOrdine() {
		return rigaOrdine;
	}

	public void setRigaOrdine(List<RigaOrdine> rigaOrdine) {
		this.rigaOrdine = rigaOrdine;
	}

}
