package edu.fatec.zl.bean;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.persistence.Query;

import org.springframework.stereotype.Controller;

import edu.fatec.zl.dto.GraficoPizzaDTO;
import edu.fatec.zl.entity.Ativo;
import edu.fatec.zl.entity.Funcionario;
import edu.fatec.zl.entity.Setor;
import edu.fatec.zl.entity.TipoAtivo;

@ManagedBean
@Controller
public class GraficosBean extends AbstractBean {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<GraficoPizzaDTO> funcionarioSetor = new LinkedList<GraficoPizzaDTO>();
	private List<GraficoPizzaDTO> ativoTipoAtivo = new LinkedList<GraficoPizzaDTO>();
	
	@Inject
	private Funcionario funcionario;
	
	@Inject
	private Ativo ativo;
	
	@Inject
	private TipoAtivo tipoAtivo;
	
	@Inject
	private Setor setor;
	
	@PostConstruct
	public void load(){
		
		List<Setor> listSetor = setor.getSetorList();
		for(Setor set : listSetor){
			Map<String,Object> parameters = new HashMap<String,Object>();
			parameters.put("aux", set.getId());
			Query query = funcionario.executeNamedQuery("funcionarioGraficoPizza", parameters);
			Long qtde = (Long) query.getSingleResult();
			funcionarioSetor.add(new GraficoPizzaDTO(qtde,set.getName()));
		}
		
		
		
		List<TipoAtivo> listTipoAtivo = tipoAtivo.getTipoAtivoList();
		for(TipoAtivo tpAtivo : listTipoAtivo){
			Map<String,Object> parameters = new HashMap<String,Object>();
			parameters.put("aux", tpAtivo.getId());
			Query query = ativo.executeNamedQuery("ativoGraficoPizza", parameters);
			Long qtde = (Long) query.getSingleResult();
			ativoTipoAtivo.add(new GraficoPizzaDTO(qtde,tpAtivo.getName()));
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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	public TipoAtivo getTipoAtivo() {
		return tipoAtivo;
	}

	public void setTipoAtivo(TipoAtivo tipoAtivo) {
		this.tipoAtivo = tipoAtivo;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
}