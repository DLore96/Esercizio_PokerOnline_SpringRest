package it.prova.gestionepokerspringrest.service;

import java.util.List;

import it.prova.gestionepokerspringrest.model.Utente;

public interface UtenteService {
	
	public List<Utente> listAllUtenti() ;

	public Utente caricaSingoloUtente(Long id);
	
	public Utente caricaUtenteConRuoli(Long id);
	
	public Utente caricaUtentiConRuoliETavolo(Long id);

	public Utente aggiorna(Utente utenteInstance);

	public Utente inserisciNuovo(Utente utenteInstance);

	public void rimuovi(Utente utenteInstance);
	
	public Utente cercaPerUsername(String username);
	
	public List<Utente> findByExample(Utente utenteInstance);
	
	public void disabilitaUtente(Utente utenteInstance);

}
