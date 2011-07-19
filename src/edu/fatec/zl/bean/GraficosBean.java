package edu.fatec.zl.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.persistence.Query;

import edu.fatec.zl.dto.GraficoPizzaDTO;
import edu.fatec.zl.entity.Funcionario;
import edu.fatec.zl.entity.Setor;

@ManagedBean
public class GraficosBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<GraficoPizzaDTO> dtoPizza = new LinkedList<GraficoPizzaDTO>();
	
	@PostConstruct
	public void load(){
		Funcionario funcionario = new Funcionario();
		List<Setor> listSetor = new Setor().getSetorList();
		for(Setor set : listSetor){
			Map<String,Object> parameters = new HashMap<String,Object>();
			parameters.put("aux", set.getId());
			Query query = funcionario.executeNamedQuery("funcionarioGraficoPizza", parameters);
			Long qtde = (Long) query.getSingleResult();
			dtoPizza.add(new GraficoPizzaDTO(qtde,set.getName()));
		}
	}

	public List<GraficoPizzaDTO> getDtoPizza() {
		return dtoPizza;
	}

	public void setDtoPizza(List<GraficoPizzaDTO> dtoPizza) {
		this.dtoPizza = dtoPizza;
	}

	


}
