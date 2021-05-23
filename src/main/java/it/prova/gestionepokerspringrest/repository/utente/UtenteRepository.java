package it.prova.gestionepokerspringrest.repository.utente;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.gestionepokerspringrest.model.Utente;

public interface UtenteRepository extends CrudRepository<Utente, Long>, CustomUtenteRepository{
	
	Utente findByUsername(String username);
	
	@Query("select u from Utente u left join fetch u.ruoli r where u.id=?1")
	Utente findOneEagerRuoli(Long id);
	
	@Query("select u from Utente u left join fetch u.ruoli r left join fetch u.tavolo t where u.id=?1")
	Utente findOneEagerRuoliTavoli(Long id);
	
	

}
