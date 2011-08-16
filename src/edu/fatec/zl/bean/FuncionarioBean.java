package edu.fatec.zl.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.persistence.Query;

import org.springframework.stereotype.Controller;

import edu.fatec.zl.entity.Funcionario;
import edu.fatec.zl.entity.Setor;
import edu.fatec.zl.util.FacesUtil;


@ManagedBean
@Controller
public class FuncionarioBean extends AbstractBean {


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FacesUtil faces = new FacesUtil();
	private List<Funcionario> listFuncionario = null;
	private Funcionario selected = null;
	
	
	@Inject
	private Funcionario funcionario;
	
	@Inject
	private Setor setor;
	


	@PostConstruct
	public void load() {
		listFuncionario = funcionario.getFuncionarioList();
		listFuncionario.add(0, new Funcionario());
	}

	public void add(ActionEvent evt) {
		
		FacesContext ctx = faces.getFacesContext();
		
		if(selected == null){
			ctx.addMessage(null, new FacesMessage(super.getBundle().getString("select_row")));
			return;
		}
			
		Map<String,Object> parameters = new HashMap<String,Object>();
		parameters.put("aux", selected.getSetor().getName());
		Query query = setor.executeNamedQuery("setorPorNome", parameters);
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

		FacesContext ctx = faces.getFacesContext();
		
		if (selected == null)
			ctx.addMessage(null, new FacesMessage("Selecione uma linha."));

		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("aux", selected.getSetor().getName());
		Query query = setor.executeNamedQuery("setorPorNome", parameters);

		try {
			Setor setor = (Setor) query.getSingleResult();
			selected.setSetor(setor);
			selected.update();
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(e.getMessage()));
		}
	}


	public void delete(ActionEvent evt) {
		
		FacesContext ctx = faces.getFacesContext();
		
		if(selected == null){
			ctx.addMessage(null, new FacesMessage(super.getBundle().getString("select_row")));
			return;
		}
		
		try {
			selected.delete();
			listFuncionario = funcionario.getFuncionarioList();
			listFuncionario.add(0, new Funcionario());
			
		} catch (Exception e) {
			faces.getFacesContext().addMessage(null, new FacesMessage(super.getBundle().getString("referencial_integrity")));
		}
	}

	
	public List<String> completeSetor(String query) {
		List<String> results = new ArrayList<String>();

		
		for (Setor set : setor.getSetorList()) {
			if (set.getName().startsWith(query))
				results.add(set.getName());
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

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
}