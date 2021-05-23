package it.prova.gestionepokerspringrest.service;

import java.util.List;

import it.prova.gestionepokerspringrest.model.Tavolo;

public interface TavoloService {

	public List<Tavolo> listAllTavoli() ;

	public Tavolo caricaSingoloTavolo(Long id);
	
	public Tavolo caricaTavoloconUtenti(Long id);

	public Tavolo aggiorna(Tavolo tavoloInstance);

	public Tavolo inserisciNuovo(Tavolo tavoloInstance);

	public void rimuovi(Tavolo tavoloInstance);
}
