package edu.fatec.zl.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.persistence.Query;

import org.springframework.stereotype.Controller;

import edu.fatec.zl.dto.GraficoPizzaDTO;
import edu.fatec.zl.entity.Ativo;
import edu.fatec.zl.entity.Funcionario;
import edu.fatec.zl.entity.Setor;
import edu.fatec.zl.entity.TipoAtivo;

@ManagedBean
@Controller
public class GraficosBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<GraficoPizzaDTO> funcionarioSetor = new LinkedList<GraficoPizzaDTO>();
	private List<GraficoPizzaDTO> ativoTipoAtivo = new LinkedList<GraficoPizzaDTO>();
	
	@PostConstruct
	public void load(){
		
		Funcionario funcionario = new Funcionario();
		List<Setor> listSetor = new Setor().getSetorList();
		for(Setor set : listSetor){
			Map<String,Object> parameters = new HashMap<String,Object>();
			parameters.put("aux", set.getId());
			Query query = funcionario.executeNamedQuery("funcionarioGraficoPizza", parameters);
			Long qtde = (Long) query.getSingleResult();
			funcionarioSetor.add(new GraficoPizzaDTO(qtde,set.getName()));
		}
		
		
		Ativo ativo = new Ativo();
		List<TipoAtivo> listTipoAtivo = new TipoAtivo().getTipoAtivoList();
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
}
