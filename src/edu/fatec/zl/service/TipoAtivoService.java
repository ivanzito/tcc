package edu.fatec.zl.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import edu.fatec.zl.dao.TipoAtivoDao;
import edu.fatec.zl.entity.TipoAtivo;

@Service
public class TipoAtivoService implements Serviceable<TipoAtivo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private TipoAtivoDao<TipoAtivoService> dao;

	@Override
	public void persist(TipoAtivo entity) throws Exception {
		dao.persist(entity);
	}

	@Override
	public void remove(TipoAtivo entity) throws Exception {
		dao.remove(entity);
	}

	@Override
	public void merge(TipoAtivo entity) throws Exception {
		dao.merge(entity);
	}

	@Override
	public List<TipoAtivo> getAll() {
		return dao.getAll(TipoAtivo.class);
	}

	public TipoAtivo getTipoAtivoByName(String name){
		return dao.getTipoAtivoByName(name);
		
	}
	
	public TipoAtivoDao<TipoAtivoService> getDao() {
		return dao;
	}

	public void setDao(TipoAtivoDao<TipoAtivoService> dao) {
		this.dao = dao;
	}

}
