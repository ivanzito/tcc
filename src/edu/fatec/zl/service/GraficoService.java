package edu.fatec.zl.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import edu.fatec.zl.dao.GraficosDao;
import edu.fatec.zl.entity.Setor;
import edu.fatec.zl.entity.TipoAtivo;

@Service
public class GraficoService {

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
}
