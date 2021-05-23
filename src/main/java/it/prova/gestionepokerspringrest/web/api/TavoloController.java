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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionepokerspringrest.model.Tavolo;
import it.prova.gestionepokerspringrest.service.TavoloService;

@RestController
@RequestMapping("api/tavolo")
public class TavoloController {
	
	@Autowired
	private TavoloService tavoloService;
	
	@GetMapping
	public List<Tavolo> listAll() {
		return tavoloService.listAllTavoli();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Tavolo createNew(@Valid @RequestBody Tavolo tavoloInput) {
		return tavoloService.inserisciNuovo(tavoloInput);
	}
	
	@GetMapping("/{id}")
	public Tavolo findById(@PathVariable(value="id")Long id) {
		
		return tavoloService.caricaSingoloTavolo(id);
	}
	
	@PutMapping("/{id}")
	public Tavolo aggiorna(@Valid @RequestBody Tavolo utenteInput) {

		return tavoloService.aggiorna(utenteInput);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@PathVariable(required = true) Long id) {
		
		tavoloService.rimuovi(tavoloService.caricaSingoloTavolo(id));
	}

}
