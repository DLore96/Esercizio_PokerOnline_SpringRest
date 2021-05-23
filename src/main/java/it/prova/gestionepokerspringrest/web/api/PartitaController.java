package it.prova.gestionepokerspringrest.web.api;

import javax.validation.Valid;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.gestionepokerspringrest.model.Tavolo;
import it.prova.gestionepokerspringrest.model.Utente;
import it.prova.gestionepokerspringrest.service.TavoloService;
import it.prova.gestionepokerspringrest.service.UtenteService;

@RestController
@RequestMapping("api/partita")
public class PartitaController {

	@Autowired
	private UtenteService utenteService;
	@Autowired
	private TavoloService tavoloService;
	
	@PostMapping("/compra")
	public Utente compraCredito(@Valid @RequestBody Double credito, @RequestHeader("authorization")String username) {
		
		Utente utenteInstance= utenteService.cercaPerUsername(username);
		Double creditoAttuale=utenteInstance.getCreditoAccumulato();
		
		utenteInstance.setCreditoAccumulato(creditoAttuale+=credito);
		utenteService.aggiorna(utenteInstance);
		return utenteInstance;	
		
	}
	
	@GetMapping("/lastGame")
	public Tavolo getLastGame( @RequestHeader("authorization")String username) {
		Utente utenteInstance = utenteService.cercaPerUsername(username);
		return tavoloService.cercaPerUtente(utenteInstance).get(0);
	}
	
	@GetMapping("/abbandona")
	public Response abbandonaPartita(@PathVariable("id") Long id,@RequestHeader("authorization")String username) {
		Utente utenteInstance= utenteService.cercaPerUsername(username);
		Integer expAttuale= utenteInstance.getEsperienza();
		utenteInstance.setEsperienza(expAttuale++);
		utenteService.aggiorna(utenteInstance);
		
		return  Response.status(200).entity("esperienza aggiornata:"+utenteInstance.getEsperienza()).build();
	}
}
