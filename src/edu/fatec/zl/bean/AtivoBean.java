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
import javax.inject.Inject;
import javax.persistence.Query;

import org.springframework.stereotype.Controller;

import edu.fatec.zl.entity.Ativo;
import edu.fatec.zl.entity.Funcionario;
import edu.fatec.zl.entity.Setor;
import edu.fatec.zl.entity.TipoAtivo;
import edu.fatec.zl.util.FacesUtil;

@ManagedBean
@Controller
public class AtivoBean extends AbstractBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FacesUtil faces = new FacesUtil();
	private List<Ativo> listAtivo = null;
	private List<Funcionario> listFuncionario = null;
	private List<TipoAtivo> listTipoAtivo = null;

	
	private Ativo selected = null;
	
	@Inject
	private Ativo ativo;
	
	@Inject
	private Funcionario funcionario;
	
	@Inject
	private TipoAtivo tipoAtivo;
	
	@Inject
	private Setor setor;
	


	@PostConstruct
	public void load(){
		listAtivo = ativo.getAtivoList();
		listFuncionario = funcionario.getFuncionarioList();
		listTipoAtivo = tipoAtivo.getTipoAtivoList();
		
		listAtivo.add(0,new Ativo());
	}
	
	public void add(ActionEvent evt) {
		
		FacesContext ctx = faces.getFacesContext();
		
		if(selected == null){
			ctx.addMessage(null, new FacesMessage(super.getBundle().getString("select_row")));
			return;
		}
		
		try{
			
			Map<String,Object> parameters = new HashMap<String,Object>();
			parameters.put("aux", selected.getFuncionario().getNome());
			Query query = funcionario.executeNamedQuery("funcionarioPorNome", parameters);
			funcionario = (Funcionario) query.getSingleResult();
			
			parameters.clear();
			parameters.put("aux", selected.getTipoAtivo().getName());
			query = setor.executeNamedQuery("tipoAtivoPorNome", parameters);
			tipoAtivo = (TipoAtivo) query.getSingleResult();			
			
			selected.setFuncionario(funcionario);
			selected.setTipoAtivo(tipoAtivo);
			selected.insert();
			
			listAtivo = selected.getAtivoList();
			listAtivo.add(0,new Ativo());
		} catch(Exception e){
			faces.getFacesContext().
			addMessage(null, new FacesMessage(e.getMessage()));
		}
	}

	public void update(ActionEvent evt) {
		
		FacesContext ctx = faces.getFacesContext();
		
		if(selected == null){
			ctx.addMessage(null, new FacesMessage(super.getBundle().getString("select_row")));
			return;
		}
		
		try {
			selected.update();
		}catch(Exception e){
			faces.getFacesContext().addMessage(null, new FacesMessage(e.getMessage()));			
		}
	}

	public void delete(ActionEvent evt) {
		
		FacesContext ctx = faces.getFacesContext();
		
		if(selected == null){
			ctx.addMessage(null, new FacesMessage(super.getBundle().getString("select_row")));
			return;
		}
		
		try{
			selected.delete();
		}catch(Exception e){
			faces.getFacesContext().addMessage(null, new FacesMessage(super.getBundle().getString("referencial_integrity")));
		}
		listAtivo = ativo.getAtivoList();
		listAtivo.add(0,new Ativo());
	}
	

	public List<String> completeTipoAtivo(String query) {
		List<String> results = new ArrayList<String>();

		for (TipoAtivo tipoAtivo : listTipoAtivo) {
			if (tipoAtivo.getName().startsWith(query))
				results.add(tipoAtivo.getName());
		}

		return results;
	}
	
	
	public List<String> completeFuncionario(String query) {
		List<String> results = new ArrayList<String>();

		for (Funcionario funcionario : listFuncionario) {
			if (funcionario.getNome().startsWith(query))
				results.add(funcionario.getNome());
		}

		return results;
	}
	
	// GETTERS AND SETTERS 

	public List<Ativo> getListAtivo() {
		return listAtivo;
	}

	public void setListAtivo(List<Ativo> listAtivo) {
		this.listAtivo = listAtivo;
	}

	public Ativo getSelected() {
		return selected;
	}

	public void setSelected(Ativo selected) {
		this.selected = selected;
	}

	public Ativo getAtivo() {
		return ativo;
	}

	public void setAtivo(Ativo ativo) {
		this.ativo = ativo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
