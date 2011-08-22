package edu.fatec.zl.dao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.fatec.zl.entity.Funcionario;

@Repository
public class FuncionarioDao<FuncionarioService> extends GenericDao<Funcionario> {

	public Funcionario getFuncionarioByName(String name){
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("aux", name);
		Query query = super.executeNamedQuery("funcionarioPorNome", parameters);
		return (Funcionario) query.getSingleResult();
	}
	
	public Funcionario getFuncionario(Long id){
		return super.getEntityManager().find(Funcionario.class, id);
	}
}
