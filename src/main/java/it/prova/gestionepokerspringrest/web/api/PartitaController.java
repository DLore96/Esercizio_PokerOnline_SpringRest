package it.prova.gestionepokerspringrest.web.api;

import java.util.List;

import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import it.prova.gestionepokerspringrest.web.api.exception.AuthorizationException;

@RestController
@RequestMapping("api/partita")
public class PartitaController {

	@Autowired
	private UtenteService utenteService;
	@Autowired
	private TavoloService tavoloService;
	
	@PostMapping("/compra")
	public Utente compraCredito(@RequestBody Double credito, @RequestHeader("authorization")String username) {
		
		Utente utenteInstance= utenteService.cercaPerUsername(username);
		Double creditoAttuale=utenteInstance.getCreditoAccumulato();
		
		utenteInstance.setCreditoAccumulato(creditoAttuale+=credito);
		utenteService.aggiorna(utenteInstance);
		return utenteInstance;	
		
	}
	
	@GetMapping("/lastGame")
	public Tavolo getLastGame( @RequestHeader("authorization")String username) {
		Utente utenteInstance = utenteService.cercaPerUsername(username);
		//Fare i controlli
		return utenteInstance.getTavolo();
	}
	
	@GetMapping("/abbandona")
	public Response abbandonaPartita(@PathVariable("id") Long id,@RequestHeader("authorization")String username) {
		Utente utenteInstance= utenteService.cercaPerUsername(username);
		Integer expAttuale= utenteInstance.getEsperienza();
		utenteInstance.setEsperienza(expAttuale++);
		utenteService.aggiorna(utenteInstance);
		
		return  Response.status(200).entity("esperienza aggiornata:"+utenteInstance.getEsperienza()).build();
	}
	
	@GetMapping("/ricerca")
	public List<Tavolo> ricercaTavolo(@RequestHeader("authorization") String username) {
		
		Utente utenteInstance=utenteService.cercaPerUsername(username);
		Tavolo example= new Tavolo();
		example.setEsperienzaMinima(utenteInstance.getEsperienza());
		return tavoloService.findByExample(example);
	}
	
	@PostMapping("/gioca")
	public ResponseEntity<String>  giocaPartita(@RequestBody Long idTavolo, @RequestHeader("authorization")String username) {
		
		Utente utenteInstance = utenteService.cercaPerUsername(username);
		Tavolo tavoloInstance= tavoloService.caricaSingoloTavolo(idTavolo);
		if(utenteInstance.getCreditoAccumulato()< tavoloInstance.getCifraMinima() || 
				utenteInstance.getEsperienza()<tavoloInstance.getEsperienzaMinima()) {
			throw new AuthorizationException("non hai i requisiti adatti");
		}
		utenteInstance.setTavolo(tavoloInstance);
		String messaggio="benvenuto";
		Double segno=Math.random();
		if(segno>0.5) {
			segno=1d;
		}else {
			segno=-1d;
		}
		Integer somma=(int)(Math.random()*1000);
		Double totale=segno*somma;
		System.out.println(totale);
		utenteInstance.setCreditoAccumulato(utenteInstance.getCreditoAccumulato()+totale);
		if(totale<0) {
			if(utenteInstance.getCreditoAccumulato()<=0) {
				messaggio="Ci dispiace credito esaurito, compra altra moneta!!";
				utenteInstance.setCreditoAccumulato(0d);

			}
			messaggio="ops, hai perso"+totale+", Nuovo saldo:"+utenteInstance.getCreditoAccumulato();
			utenteService.aggiorna(utenteInstance);
		}
		if(totale>0) {
			messaggio="Congratulazioni, hai vinto: "+totale+", Nuovo saldo:"+utenteInstance.getCreditoAccumulato();
			utenteService.aggiorna(utenteInstance);
		}
		
		
		
		return ResponseEntity.status(200).body(messaggio);

	}
}
