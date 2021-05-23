package it.prova.gestionepokerspringrest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionepokerspringrest.model.Tavolo;
import it.prova.gestionepokerspringrest.model.Utente;
import it.prova.gestionepokerspringrest.repository.tavolo.TavoloRepository;

@Service
public class TavoloServiceImpl implements TavoloService{

	@Autowired
	private TavoloRepository repository;
	
	@Override
	public List<Tavolo> listAllTavoli() {
		return (List<Tavolo>) repository.findAll();
	}

	@Override
	public Tavolo caricaSingoloTavolo(Long id) {

		return repository.findById(id).orElse(null);
	}

	@Override
	public Tavolo caricaTavoloconUtenti(Long id) {
		return null;
	}

	@Override
	public Tavolo aggiorna(Tavolo tavoloInstance) {
		if(!tavoloInstance.getUtenti().isEmpty()) {
			throw new RuntimeException("Non puoi aggiornare mentre ci sono giocatori");
		}
		return repository.save(tavoloInstance);
	}

	@Override
	public Tavolo inserisciNuovo(Tavolo tavoloInstance) {
		return repository.save(tavoloInstance);
	}

	@Override
	public void rimuovi(Tavolo tavoloInstance) {

		if(!tavoloInstance.getUtenti().isEmpty()) {
			throw new RuntimeException("Non puoi cancellare, ci sono giocatori");
		}
		repository.delete(tavoloInstance);
	}

	@Override
	public List<Tavolo> cercaPerUtente(Utente utenteInstance) {
		return repository.findTavoloByUtente(utenteInstance);
	}

}
