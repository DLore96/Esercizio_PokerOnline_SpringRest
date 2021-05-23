package it.prova.gestionepokerspringrest.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.prova.gestionepokerspringrest.model.Utente;
import it.prova.gestionepokerspringrest.repository.utente.UtenteRepository;

@Service
@Transactional
public class UtenteServiceImpl implements UtenteService {

	@Autowired
	private UtenteRepository repository;
	@Override
	public List<Utente> listAllUtenti() {
		return (List<Utente>) repository.findAll();
	}

	@Override
	public Utente caricaSingoloUtente(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Utente caricaUtenteConRuoli(Long id) {
		return repository.findOneEagerRuoli(id);
	}

	@Override
	public Utente aggiorna(Utente utenteInstance) {

		return repository.save(utenteInstance);
	}

	@Override
	public Utente inserisciNuovo(Utente utenteInstance) {

		return repository.save(utenteInstance);
	}

	@Override
	public void rimuovi(Utente utenteInstance) {

		repository.delete(utenteInstance);
	}

	@Override
	public Utente cercaPerUsername(String username) {

		return repository.findByUsername(username);
	}

	@Override
	public List<Utente> findByExample(Utente utenteInstance) {
		return repository.findByExample(utenteInstance);
	}

	@Override
	public void disabilitaUtente(Utente utenteInstance) {

		repository.disabilitaUtente(utenteInstance);
	}

	@Override
	public Utente caricaUtentiConRuoliETavolo(Long id) {
		return repository.findOneEagerRuoliTavoli(id);
	}

}
