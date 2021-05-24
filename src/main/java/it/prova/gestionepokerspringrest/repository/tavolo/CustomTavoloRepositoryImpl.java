package it.prova.gestionepokerspringrest.repository.tavolo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;

import it.prova.gestionepokerspringrest.model.Tavolo;

public class CustomTavoloRepositoryImpl implements CustomTavoloRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Tavolo> findByExample(Tavolo example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder(
				"select t from Tavolo t left join fetch t.utenti u left join fetch t.utenteCreazione uc  where t.id = t.id ");

		if (StringUtils.isNotEmpty(example.getDenominazione())) {
			whereClauses.add(" t.denominazione  like :denominazione ");
			paramaterMap.put("denominazione", "%" + example.getDenominazione() + "%");
		}
		
		if (example.getEsperienzaMinima()!=null && example.getEsperienzaMinima()>=0) {
			whereClauses.add(" t.esperienzaMinima  <= :esperienzaMinima ");
			paramaterMap.put("esperienzaMinima", example.getEsperienzaMinima());
		}
		if (example.getCifraMinima()!=null && example.getCifraMinima()>=0) {
			whereClauses.add(" t.cifraMinima  = :cifra ");
			paramaterMap.put("cifra", example.getCifraMinima());
		}
		if (example.getDateCreated() != null) {
			whereClauses.add("u.dateCreated >= :dateCreated ");
			paramaterMap.put("dateCreated", example.getDateCreated());
		}
		if (example.getUtenti() != null && !example.getUtenti().isEmpty()) {
			whereClauses.add("u in :utenti ");
			paramaterMap.put("utenti", example.getUtenti());
		}
		if(example.getUtenteCreazione() != null ) {
			whereClauses.add("uc = :utente");
			paramaterMap.put("utente", example.getUtenteCreazione());
		}

		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Tavolo> typedQuery = entityManager.createQuery(queryBuilder.toString(), Tavolo.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}

}
