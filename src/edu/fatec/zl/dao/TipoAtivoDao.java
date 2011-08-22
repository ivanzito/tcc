package edu.fatec.zl.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.fatec.zl.entity.TipoAtivo;

@Repository
public class TipoAtivoDao<TipoAtivoService> extends GenericDao<TipoAtivo> {

	public TipoAtivo getTipoAtivoByName(String name){
		Map<String,Object> parameters = new HashMap<String,Object>(); 
		parameters.put("aux", name);
		
		return (TipoAtivo) super.executeNamedQuery("tipoAtivoPorNome", parameters).getSingleResult();
		
	}
}
