package edu.fatec.zl.dao;

import org.springframework.stereotype.Repository;

import edu.fatec.zl.entity.Ativo;

@Repository
public class AtivoDao<AtivoService> extends GenericDao<Ativo> {

	
	public Ativo getAtivo(Long id){
		return super.getEntityManager().find(Ativo.class, id);
	}
}
