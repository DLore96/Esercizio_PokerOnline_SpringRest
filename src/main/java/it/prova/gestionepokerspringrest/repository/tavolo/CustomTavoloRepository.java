package it.prova.gestionepokerspringrest.repository.tavolo;

import java.util.List;

import it.prova.gestionepokerspringrest.model.Tavolo;

public interface CustomTavoloRepository {
	
	public List<Tavolo> findByExample(Tavolo example);

}
