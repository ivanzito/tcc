package edu.fatec.zl.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import edu.fatec.zl.dao.GraficosDao;
import edu.fatec.zl.entity.Persistable;
import edu.fatec.zl.entity.Setor;
import edu.fatec.zl.entity.TipoAtivo;

@SuppressWarnings("rawtypes")
@Service
public class GraficoService implements Serviceable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private GraficosDao graficoDao;
	
	
	
	public Long getFuncionario(Setor set){
		return graficoDao.getFuncionario(set);
	}
	
	public Long getAtivo(TipoAtivo tpAtivo){
		return graficoDao.getAtivo(tpAtivo);
	}

	public GraficosDao getGraficoDao() {
		return graficoDao;
	}

	public void setGraficoDao(GraficosDao graficoDao) {
		this.graficoDao = graficoDao;
	}

	@Override
	public void persist(Persistable entity) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void remove(Persistable entity) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public void merge(Persistable entity) throws Exception {
		throw new NotImplementedException();
	}

	@Override
	public List getAll() throws Exception {
		throw new NotImplementedException();
	}
}
