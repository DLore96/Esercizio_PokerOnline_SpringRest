package it.prova.gestionepokerspringrest.repository.ruolo;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepokerspringrest.model.Ruolo;

public interface RuoloRepository extends CrudRepository<Ruolo, Long>{

	Ruolo findByDescrizioneAndCodice(String descrizione, String codice);
	
}
