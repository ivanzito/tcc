package edu.fatec.zl.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.fatec.zl.entity.Setor;
import edu.fatec.zl.entity.TipoAtivo;


@Repository
@SuppressWarnings("rawtypes")
public class GraficosDao extends GenericDao {

	@SuppressWarnings("unchecked")
	public Long getFuncionario(Setor set){
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("aux", set.getId());
		Query query = super.executeNamedQuery("funcionarioGraficoPizza", parameters);
		return (Long) query.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public Long getAtivo(TipoAtivo tpAtivo){
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("aux", tpAtivo.getId());
		Query query = super.executeNamedQuery("ativoGraficoPizza", parameters);
		return (Long) query.getSingleResult();
	}
}
