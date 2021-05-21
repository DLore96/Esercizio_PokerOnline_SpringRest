package it.prova.gestionepokerspringrest.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tavolo")
public class Tavolo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@NotBlank(message="{denominazione.notblank}")
	@Column(name = "denominazione")
	private String denominazione;
	@NotNull(message = "{dateCreated.notnull}")
	@Column(name = "dateCreated")
	private Date dateCreated;
	@NotNull(message="{esperienzaMinima.notnull}")
	@Column(name="esperienza_minima")
	private Integer esperienzaMinima;
	@Min(value = 0)
	@Column(name = "cifra_minima")
	private Double cifraMinima;
	
	@JsonIgnoreProperties(value= {"tavolo"})
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tavolo")
	private Set<Utente> utenti = new HashSet<Utente>(0);
	
	@JsonIgnoreProperties(value= { "tavolo"})
	@NotNull(message="{utenteCreazione.notnull}")
	@ManyToOne(fetch= FetchType.LAZY)
	@JoinColumn(name = "utente_creazione")
	private Utente utenteCreazione;
	
	public Tavolo() {}
	
	public Tavolo(String denominazione) {
		this.denominazione=denominazione;
	}
	
	public Tavolo(String denominazione,Date dateCreated, Double cifraMinima, Integer esperienzaMinima, Utente utenteCreazione) {
		this.denominazione=denominazione;
		this.dateCreated=dateCreated;
		this.cifraMinima=cifraMinima;
		this.esperienzaMinima=esperienzaMinima;
		this.utenteCreazione=utenteCreazione;
	}
	
	public Tavolo(Long id, String denominazione,Date dateCreated, Double cifraMinima, Integer esperienzaMinima, Utente utenteCreazione) {
		this.id=id;
		this.denominazione=denominazione;
		this.dateCreated=dateCreated;
		this.cifraMinima=cifraMinima;
		this.esperienzaMinima=esperienzaMinima;
		this.utenteCreazione=utenteCreazione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Integer getEsperienzaMinima() {
		return esperienzaMinima;
	}

	public void setEsperienzaMinima(Integer esperienzaMinima) {
		this.esperienzaMinima = esperienzaMinima;
	}

	public Double getCifraMinima() {
		return cifraMinima;
	}

	public void setCifraMinima(Double cifraMinima) {
		this.cifraMinima = cifraMinima;
	}

	public Set<Utente> getUtenti() {
		return utenti;
	}

	public void setUtenti(Set<Utente> utenti) {
		this.utenti = utenti;
	}

	public Utente getUtenteCreazione() {
		return utenteCreazione;
	}

	public void setUtenteCreazione(Utente utenteCreazione) {
		this.utenteCreazione = utenteCreazione;
	}
	
	
	

}
