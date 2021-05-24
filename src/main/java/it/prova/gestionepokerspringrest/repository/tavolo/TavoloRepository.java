package it.prova.gestionepokerspringrest.repository.tavolo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepokerspringrest.model.Tavolo;
import it.prova.gestionepokerspringrest.model.Utente;

public interface TavoloRepository extends CrudRepository<Tavolo, Long>, CustomTavoloRepository{
	
	@Query("select t from Tavolo t left join fetch t.utenteCreazione u where id=?1")
	public Tavolo findTavoloEagerUtenteCreazione(Long id);
	
	@Query("select t from Tavolo t left join fetch t.utenteCreazione u left join fetch t.utenti where id=?1")
	public Tavolo findTavoloEagerUtenteCreazioneEUtenti(Long id);

	@Query("select t from Tavolo t left join fetch t.utenti u where ?1 in u")
	public List<Tavolo> findTavoloByUtente(Utente utenteInput);
}
