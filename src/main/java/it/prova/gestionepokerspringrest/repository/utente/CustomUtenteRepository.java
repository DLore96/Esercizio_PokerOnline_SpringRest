package it.prova.gestionepokerspringrest.repository.utente;

import java.util.List;

import it.prova.gestionepokerspringrest.model.Utente;

public interface CustomUtenteRepository {

	public List<Utente> findByExample(Utente utenteInput);
	
	public void disabilitaUtente(Utente utenteInput);
}
