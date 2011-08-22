package edu.fatec.zl.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import edu.fatec.zl.entity.Funcionario;
import edu.fatec.zl.entity.Setor;
import edu.fatec.zl.service.FuncionarioService;
import edu.fatec.zl.service.SetorService;
import edu.fatec.zl.util.FacesUtil;


@ManagedBean
@Controller
public class FuncionarioBean extends GenericBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FacesUtil faces = new FacesUtil();
	private List<Object[]> listFuncionario = null;
	private Object[] selected = null;
	

	@Inject
	private FuncionarioService funcionarioService;
	
	@Inject
	private SetorService setorService;
	


	@PostConstruct
	public void load() {
		listFuncionario = funcionarioService.getFuncionarios();
		listFuncionario.add(0, new Object[4]);
	}

	public void add(ActionEvent evt) {
		
		FacesContext ctx = faces.getFacesContext();
		
		if(selected == null){
			ctx.addMessage(null, new FacesMessage(super.getBundle().getString("select_row")));
			return;
		}

		Funcionario funcionario = new Funcionario();
		funcionario.setNome(selected[0].toString());
		funcionario.setSetor(setorService.getSetorByName(selected[1].toString()));
		funcionario.setDataModificacao((Date)selected[2]);

		try {
			funcionarioService.persist(funcionario);
			listFuncionario = funcionarioService.getFuncionarios();
			listFuncionario.add(0, new Object[4]);
			
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(e.getMessage()));
		}
	}

	
	public void update(ActionEvent evt) {
		

		FacesContext ctx = faces.getFacesContext();
		
		if (selected == null)
			ctx.addMessage(null, new FacesMessage(super.getBundle().getString("select_row")));
		
		Funcionario funcionario = funcionarioService.getFuncionario(Long.parseLong(selected[3].toString()));
		funcionario.setNome(selected[0].toString());
		funcionario.setSetor(setorService.getSetorByName(selected[1].toString()));
		funcionario.setDataModificacao((Date)selected[2]);

		try {
			funcionarioService.merge(funcionario);
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
		
		Funcionario funcionario = funcionarioService.getFuncionario(Long.parseLong(selected[3].toString()));
		
		try {
			funcionarioService.remove(funcionario);
			listFuncionario = funcionarioService.getFuncionarios();
			listFuncionario.add(0, new String[4]);
			
		} catch (Exception e) {
			faces.getFacesContext().addMessage(null, new FacesMessage(super.getBundle().getString("referencial_integrity")));
		}
	}
	
	
	public List<String> completeSetor(String query) {
		List<String> results = new ArrayList<String>();

		
		for (Setor set : setorService.getAll()) {
			if (set.getName().startsWith(query))
				results.add(set.getName());
		}

		return results;
	}

	// GETTERS AND SETTERS

	public List<Object[]> getListFuncionario() {
		return listFuncionario;
	}

	public void setListFuncionario(List<Object[]> listFuncionario) {
		this.listFuncionario = listFuncionario;
	}

	public void setSelected(Object[] selected) {
		this.selected = selected;
	}

	public Object[] getSelected() {
		return this.selected;
	}

	public FuncionarioService getFuncionarioService() {
		return funcionarioService;
	}

	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	public SetorService getSetorService() {
		return setorService;
	}

	public void setSetorService(SetorService setorService) {
		this.setorService = setorService;
	}
}