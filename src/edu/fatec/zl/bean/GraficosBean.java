package edu.fatec.zl.bean;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import edu.fatec.zl.dto.GraficoPizzaDTO;
import edu.fatec.zl.entity.Setor;
import edu.fatec.zl.entity.TipoAtivo;
import edu.fatec.zl.service.GraficoService;
import edu.fatec.zl.service.SetorService;
import edu.fatec.zl.service.TipoAtivoService;

@ManagedBean
@Controller 
public class GraficosBean extends GenericBean {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<GraficoPizzaDTO> funcionarioSetor = new LinkedList<GraficoPizzaDTO>();
	private List<GraficoPizzaDTO> ativoTipoAtivo = new LinkedList<GraficoPizzaDTO>();
	
	@Inject
	private TipoAtivoService tipoAtivoService;
	
	@Inject
	private SetorService setorService;
	
	@Inject
	private GraficoService graficoService;
	
	@PostConstruct
	public void load(){
		
		List<Setor> listSetor = setorService.getAll();
		for(Setor set : listSetor){
			funcionarioSetor.add(new GraficoPizzaDTO(graficoService.getFuncionario(set),set.getName()));
		}
		
		List<TipoAtivo> listTipoAtivo = tipoAtivoService.getAll();
		for(TipoAtivo tpAtivo : listTipoAtivo){
			ativoTipoAtivo.add(new GraficoPizzaDTO(graficoService.getAtivo(tpAtivo),tpAtivo.getName()));
		}
		
	}

	public List<GraficoPizzaDTO> getFuncionarioSetor() {
		return funcionarioSetor;
	}

	public void setFuncionarioSetor(List<GraficoPizzaDTO> funcionarioSetor) {
		this.funcionarioSetor = funcionarioSetor;
	}

	public List<GraficoPizzaDTO> getAtivoTipoAtivo() {
		return ativoTipoAtivo;
	}

	public void setAtivoTipoAtivo(List<GraficoPizzaDTO> ativoTipoAtivo) {
		this.ativoTipoAtivo = ativoTipoAtivo;
	}


	public TipoAtivoService getTipoAtivoService() {
		return tipoAtivoService;
	}

	public void setTipoAtivoService(TipoAtivoService tipoAtivoService) {
		this.tipoAtivoService = tipoAtivoService;
	}

	public SetorService getSetorService() {
		return setorService;
	}

	public void setSetorService(SetorService setorService) {
		this.setorService = setorService;
	}

	public GraficoService getGraficoService() {
		return graficoService;
	}

	public void setGraficoService(GraficoService graficoService) {
		this.graficoService = graficoService;
	}
}