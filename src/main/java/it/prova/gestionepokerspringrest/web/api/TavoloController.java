package it.prova.gestionepokerspringrest.web.api;

import java.util.List;

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

import it.prova.gestionepokerspringrest.model.Tavolo;
import it.prova.gestionepokerspringrest.model.Utente;
import it.prova.gestionepokerspringrest.service.RuoloService;
import it.prova.gestionepokerspringrest.service.TavoloService;
import it.prova.gestionepokerspringrest.service.UtenteService;
import it.prova.gestionepokerspringrest.web.api.exception.AuthorizationException;

@RestController
@RequestMapping("api/tavolo")
public class TavoloController {
	
	@Autowired
	private TavoloService tavoloService;
	@Autowired
	private UtenteService utenteService;
	@Autowired
	private RuoloService ruoloService;
	
	@GetMapping
	public List<Tavolo> listAll(@RequestHeader("authorization")String username) {
		Utente utenteInstance= utenteService.cercaPerUsername(username);
		if(!validaAdmin(utenteInstance)) {
			if(!utenteInstance.getRuoli().contains(ruoloService.caricaSingoloElemento(2L))) {
				throw new AuthorizationException("non sei autorizzato");
			}
			//metodo che mostra lista tavoli special
		}
		
		return tavoloService.listAllTavoli();
		
		
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Tavolo createNew(@Valid @RequestBody Tavolo tavoloInput, @RequestHeader("authorization") String username) {
		Utente utenteInstance= utenteService.cercaPerUsername(username);
		if(!validaAdmin(utenteInstance)) {
			if(!utenteInstance.getRuoli().contains(ruoloService.caricaSingoloElemento(2L))) {
				throw new AuthorizationException("non sei autorizzato");
			}
		}
		return tavoloService.inserisciNuovo(tavoloInput);
	}
	
	@GetMapping("/{id}")
	public Tavolo findById(@PathVariable(value="id")Long id,@RequestHeader("authorization")String username) {
		Utente utenteInstance= utenteService.cercaPerUsername(username);
		if(!validaAdmin(utenteInstance)) {
			if(!utenteInstance.getRuoli().contains(ruoloService.caricaSingoloElemento(2L))) {
				throw new AuthorizationException("non sei autorizzato");
			}
			//metodo che mostra tavolo special
		}
		return tavoloService.caricaSingoloTavolo(id);
	}
	
	@PutMapping("/{id}")
	public Tavolo aggiorna(@Valid @RequestBody Tavolo utenteInput,@RequestHeader("authorization")String username) {
		
		Utente utenteInstance= utenteService.cercaPerUsername(username);
		if(!validaAdmin(utenteInstance)) {
			if(!utenteInstance.getRuoli().contains(ruoloService.caricaSingoloElemento(2L))) {
				throw new AuthorizationException("non sei autorizzato");
			}
			//metodo che mostra lista tavoli special
		}

		return tavoloService.aggiorna(utenteInput);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(required = true) Long id, @RequestHeader("authorization") String username) {
		
		Utente utenteInstance= utenteService.cercaPerUsername(username);
		if(!validaAdmin(utenteInstance)) {
			if(!utenteInstance.getRuoli().contains(ruoloService.caricaSingoloElemento(2L))) {
				throw new AuthorizationException("non sei autorizzato");
			}
			//metodo che mostra lista tavoli special
		}
		tavoloService.rimuovi(tavoloService.caricaSingoloTavolo(id));
	}
	
	
	 private boolean validaAdmin(Utente utenteInstance) {
		 if(utenteInstance.getRuoli().contains(ruoloService.caricaSingoloElemento(1L))) {
			 return true;
		 }
		 return false;
	 }


}
