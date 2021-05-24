package it.prova.gestionepokerspringrest.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="utente")
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@NotBlank(message = "{username.notblank}")
	@Column(name = "username", unique = true)
	private String username;
	@NotBlank(message = "{password.notblank}")
	@Column(name = "password")
	private String password;
	@NotBlank(message = "{nome.notblank}")
	@Column(name = "nome")
	private String nome;
	@NotBlank(message = "{cognome.notblank}")
	@Column(name = "cognome")
	private String cognome;
	@NotNull(message= "{dateCreated.notnull}")
	@Column(name="dateCreated")
	private Date dateCreated;
	@NotNull(message = "{stato.notnull}")
	@Enumerated(EnumType.STRING)
	private StatoUtente stato;
	@NotNull(message="{esperienza.notnull}")
	@Column(name="esperienza")
	private Integer esperienza;
	@Min(value = 0)
	@Column(name = "credito_accumulato")
	private Double creditoAccumulato;

	@NotEmpty(message = "{ruoli.notempty}")
	@ManyToMany
	@JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
	private Set<Ruolo> ruoli = new HashSet<>(0);
	
	@JsonIgnoreProperties(value= {"utenti", "utenteCreazione"})
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tavolo_id")
	private Tavolo tavolo;
	
	public Utente() {
	}

	public Utente(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Utente(String username, String password, String nome, String cognome, Date dateCreated) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dateCreated = dateCreated;
	}
	
	public Utente(String username, String password, String nome, String cognome, Date dateCreated,StatoUtente stato, Double credito,Integer esperienza) {
		this.username = username;
		this.password = password;
		this.nome = nome;
		this.cognome = cognome;
		this.dateCreated = dateCreated;
		this.stato=stato;
		this.creditoAccumulato=credito;
		this.esperienza=esperienza;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public StatoUtente getStato() {
		return stato;
	}

	public void setStato(StatoUtente stato) {
		this.stato = stato;
	}

	public Integer getEsperienza() {
		return esperienza;
	}

	public void setEsperienza(Integer esperienza) {
		this.esperienza = esperienza;
	}

	public Double getCreditoAccumulato() {
		return creditoAccumulato;
	}

	public void setCreditoAccumulato(Double creditoAccumulato) {
		this.creditoAccumulato = creditoAccumulato;
	}

	public Set<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}
	
	
	
	
	
}
