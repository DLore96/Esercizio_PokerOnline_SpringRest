package it.prova.gestionepokerspringrest.web.api;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionepokerspringrest.model.Ruolo;
import it.prova.gestionepokerspringrest.model.Utente;
import it.prova.gestionepokerspringrest.service.RuoloService;
import it.prova.gestionepokerspringrest.service.UtenteService;
import it.prova.gestionepokerspringrest.web.api.exception.AuthorizationException;
import it.prova.gestionepokerspringrest.web.api.exception.UtenteNotFoundException;

@RestController
@RequestMapping("api/utente")
public class UtenteController {
	
	@Autowired
	private UtenteService utenteService;
	@Autowired 
	private RuoloService ruoloService;
	
	@GetMapping
	public List<Utente> listall(@RequestHeader("authorization") String username){
		Utente utenteInstance= utenteService.cercaPerUsername(username);
		
		//UTILIZZO UN METODO PER VERIFICARE L'UTENTE CHE FA LA RICHIESTA 
		//EVITANDO DUPLICAZIONE DI CODICE
		int result = validaUtente(utenteInstance);
		
		if(result==-1) {
			throw new UtenteNotFoundException("username non valido");
		}
		if(result==0) {
			throw new AuthorizationException("non sei autorizzato");
		}
		
		return utenteService.listAllUtenti();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Utente createNew(@Valid @RequestBody Utente utenteInput, @RequestBody Set<Ruolo> ruoli, @RequestHeader("authorization")String username) {
		Utente utenteInstance= utenteService.cercaPerUsername(username);
		
		//UTILIZZO UN METODO PER VERIFICARE L'UTENTE CHE FA LA RICHIESTA 
		//EVITANDO DUPLICAZIONE DI CODICE
		int result = validaUtente(utenteInstance);
		
		if(result==-1) {
			throw new UtenteNotFoundException("username non valido");
		}
		if(result==0) {
			throw new AuthorizationException("non sei autorizzato");
		}
		return utenteService.inserisciNuovo(utenteInput);
		
	}
	
	@GetMapping("/{id}")
	public Utente findById(@PathVariable(value="id")Long id,@RequestHeader("authorization") String username) {
		Utente utenteInstance= utenteService.cercaPerUsername(username);
		
		//UTILIZZO UN METODO PER VERIFICARE L'UTENTE CHE FA LA RICHIESTA 
		//EVITANDO DUPLICAZIONE DI CODICE
		int result = validaUtente(utenteInstance);
		
		if(result==-1) {
			throw new UtenteNotFoundException("username non valido");
		}
		if(result==0) {
			throw new AuthorizationException("non sei autorizzato");
		}
		
		return utenteService.caricaSingoloUtente(id);
	}
	
	@PutMapping("/{id}")
	public Utente aggiorna(@Valid @RequestBody Utente utenteInput,@RequestHeader("authorization")String username) {
		
		Utente utenteInstance= utenteService.cercaPerUsername(username);
		
		//UTILIZZO UN METODO PER VERIFICARE L'UTENTE CHE FA LA RICHIESTA 
		//EVITANDO DUPLICAZIONE DI CODICE
		int result = validaUtente(utenteInstance);
		
		if(result==-1) {
			throw new UtenteNotFoundException("username non valido");
		}
		if(result==0) {
			throw new AuthorizationException("non sei autorizzato");
		}
		return utenteService.aggiorna(utenteInput);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(required = true) Long id, @RequestHeader("authorization") String username) {
		Utente utenteInstance= utenteService.cercaPerUsername(username);
		
		//UTILIZZO UN METODO PER VERIFICARE L'UTENTE CHE FA LA RICHIESTA 
		//EVITANDO DUPLICAZIONE DI CODICE
		int result = validaUtente(utenteInstance);
		
		if(result==-1) {
			throw new UtenteNotFoundException("username non valido");
		}
		if(result==0) {
			throw new AuthorizationException("non sei autorizzato");
		}
		
		//QUINDI PROCEDO A QUELLO CHE DEVO FARE
		Utente utente = utenteService.caricaSingoloUtente(id);
		utenteService.disabilitaUtente(utente);
		utenteService.aggiorna(utente);
	}
	
	
	
	//METODI DI UTILITY:
	
	private int  validaUtente(Utente utenteInstance) {
		if(utenteInstance==null) {
			return -1;
		}
		if(!utenteInstance.getRuoli().contains(ruoloService.caricaSingoloElemento(1L))) {
			return 0;
		}
		return 1;
	}

}
