package edu.fatec.zl.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.fatec.zl.entity.Setor;

@Repository
public class SetorDao<SetorService> extends GenericDao<Setor> {

	public Setor getSetorByName(String name) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("aux", name);
		Query query = super.executeNamedQuery("setorPorNome", parameters);
		return (Setor) query.getSingleResult();
	}
}
