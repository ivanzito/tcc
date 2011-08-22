package edu.fatec.zl.service;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import edu.fatec.zl.dao.AtivoDao;
import edu.fatec.zl.entity.Ativo;

@Service
public class AtivoService implements Serviceable<Ativo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private AtivoDao<AtivoService> dao;
	

	@Override
	public void persist(Ativo entity) throws Exception {
		dao.persist(entity);
	}

	@Override
	public void remove(Ativo entity) throws Exception {
		dao.remove(entity);
	}

	@Override
	public void merge(Ativo entity) throws Exception {
		dao.merge(entity);
	}

	@Override
	public List<Ativo> getAll() {
		return dao.getAll(Ativo.class);
	}
	
	public List<Object[]> getAtivos(){

		List<Object[]> list = new LinkedList<Object[]>();
		for(Ativo ativo : this.getAll()){
			Object[] obj = new Object[5];
			obj[0] = ativo.getId();
			obj[1] = ativo.getAtivo();
			obj[2] = ativo.getTipoAtivo().getName();
			obj[3] = ativo.getFuncionario().getNome();
			obj[4] = ativo.getDataModificacao();
			
			list.add(obj);
		}
		
	return list;
	
	}
	
	public Ativo getAtivo(Long id){
		return dao.getAtivo(id);
	}

	public AtivoDao<AtivoService> getDao() {
		return dao;
	}

	public void setDao(AtivoDao<AtivoService> dao) {
		this.dao = dao;
	}
}
