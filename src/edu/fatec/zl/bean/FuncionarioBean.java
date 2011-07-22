package edu.fatec.zl.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.Query;

import org.springframework.stereotype.Controller;

import edu.fatec.zl.dao.DataAccess;
import edu.fatec.zl.entity.Funcionario;
import edu.fatec.zl.entity.Setor;
import edu.fatec.zl.util.FacesUtil;


@ManagedBean
@Controller
public class FuncionarioBean extends DataAccess<AtivoBean> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FacesUtil faces = new FacesUtil();
	private List<Funcionario> listFuncionario = null;
	private List<Setor> setores = null;
	private Funcionario selected = null;
	private FacesContext ctx = faces.getFacesContext();


	@PostConstruct
	public void load() {
		listFuncionario = new Funcionario().getFuncionarioList();
		setores = new Setor().getSetorList();
		listFuncionario.add(0, new Funcionario());
	}

	public void add(ActionEvent evt) {
		
		if(selected == null){
			ctx.addMessage(null, new FacesMessage("Selecione uma linha."));
			return;
		}
			
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("aux", selected.getSetor().getName());
		Query query = new Setor().executeNamedQuery("setorPorNome", parameters);
		Setor setor = (Setor) query.getSingleResult();

		selected.setSetor(setor);
		try {
			selected.insert();
			listFuncionario = selected.getFuncionarioList();
			listFuncionario.add(0, new Funcionario());			
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(e.getMessage()));
		}
	}

	public void update(ActionEvent evt) {
	
		if(selected == null)
			ctx.addMessage(null, new FacesMessage("Selecione uma linha."));
		
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("aux", selected.getSetor().getName());
		Query query = new Setor().executeNamedQuery("setorPorNome", parameters);
		

		try {
			Setor setor = (Setor) query.getSingleResult();
			selected.getSetor().setId(setor.getId());
			selected.update();
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(e.getMessage()));
		}
	}

	public void delete(ActionEvent evt) {
		
		if(selected == null)
			ctx.addMessage(null, new FacesMessage("Selecione uma linha."));
		
		try {
			selected.delete();
			listFuncionario = new Funcionario().getFuncionarioList();
			listFuncionario.add(0, new Funcionario());			
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(e.getMessage()));
		}
	}
	public List<String> completeSetor(String query) {
		List<String> results = new ArrayList<String>();

		for (Setor setor : setores) {
			if (setor.getName().startsWith(query))
				results.add(setor.getName());
		}

		return results;
	}

	// GETTERS AND SETTERS

	public List<Funcionario> getListFuncionario() {
		return listFuncionario;
	}

	public void setListFuncionario(List<Funcionario> listFuncionario) {
		this.listFuncionario = listFuncionario;
	}

	public void setSelected(Funcionario selected) {
		this.selected = selected;
	}

	public Funcionario getSelected() {
		return this.selected;
	}
}