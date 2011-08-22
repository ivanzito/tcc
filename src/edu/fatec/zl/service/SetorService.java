package edu.fatec.zl.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import edu.fatec.zl.dao.SetorDao;
import edu.fatec.zl.entity.Setor;

@Service
public class SetorService implements Serviceable<Setor> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private SetorDao<SetorService> dao;

	@Override
	public void persist(Setor entity) throws Exception {
		dao.persist(entity);
	}

	@Override
	public void remove(Setor entity) throws Exception {
		dao.remove(entity);
	}

	@Override
	public void merge(Setor entity) throws Exception {
		dao.merge(entity);
	}

	@Override
	public List<Setor> getAll() {
		return dao.getAll(Setor.class);
	}
	
	public Setor getSetorByName(String name){
		return dao.getSetorByName(name);
	}
	
	public Setor getSetor(Long id){
		return dao.getEntityManager().find(Setor.class, id);
	}

	public SetorDao<SetorService> getDao() {
		return dao;
	}

	public void setDao(SetorDao<SetorService> dao) {
		this.dao = dao;
	}

}
