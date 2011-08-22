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

import edu.fatec.zl.entity.Ativo;
import edu.fatec.zl.entity.Funcionario;
import edu.fatec.zl.entity.TipoAtivo;
import edu.fatec.zl.service.AtivoService;
import edu.fatec.zl.service.FuncionarioService;
import edu.fatec.zl.service.TipoAtivoService;
import edu.fatec.zl.util.FacesUtil;

@ManagedBean
@Controller
public class AtivoBean extends GenericBean {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private FacesUtil faces = new FacesUtil();
	private List<Object[]> listAtivo = null;

	
	private Object[] selected = null;
	
	@Inject
	private AtivoService ativoService;
	
	@Inject
	private FuncionarioService funcionarioService;
	
	@Inject
	private TipoAtivoService tipoAtivoService;
	

	@PostConstruct
	public void load(){
		listAtivo = ativoService.getAtivos();
		listAtivo.add(0,new Object[5]);
	}
	
	public void add(ActionEvent evt) {
		
		FacesContext ctx = faces.getFacesContext();
		
		if(selected == null){
			ctx.addMessage(null, new FacesMessage(super.getBundle().getString("select_row")));
			return;
		}
		
		try{
			Ativo ativo = new Ativo();
			ativo.setAtivo(selected[1].toString());
			TipoAtivo tipoAtivo = tipoAtivoService.getTipoAtivoByName(selected[2].toString());
			Funcionario funcionario = funcionarioService.getFuncionarioByName(selected[3].toString());
			ativo.setDataModificacao((Date) selected[4]);
			
			ativo.setFuncionario(funcionario);
			ativo.setTipoAtivo(tipoAtivo);
			ativoService.persist(ativo);
			
			listAtivo = ativoService.getAtivos();
			listAtivo.add(0,new Object[5]);
			
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
		
		Ativo ativo = ativoService.getAtivo(Long.parseLong(selected[0].toString()));
		ativo.setAtivo(selected[1].toString());
		TipoAtivo tipoAtivo = tipoAtivoService.getTipoAtivoByName(selected[2].toString());
		Funcionario funcionario = funcionarioService.getFuncionarioByName(selected[3].toString());
		ativo.setDataModificacao((Date) selected[4]);
		ativo.setTipoAtivo(tipoAtivo);
		ativo.setFuncionario(funcionario);
		
		try {
			ativoService.merge(ativo);
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
		
		Ativo ativo = ativoService.getAtivo(Long.parseLong(selected[0].toString()));
		
		try{
			ativoService.remove(ativo);
		}catch(Exception e){
			faces.getFacesContext().addMessage(null, new FacesMessage(super.getBundle().getString("referencial_integrity")));
		}
		listAtivo = ativoService.getAtivos();
		listAtivo.add(0,new Object[5]);
	}
	

	public List<String> completeTipoAtivo(String query) {
		List<String> results = new ArrayList<String>();

		for (TipoAtivo tpAtivo : tipoAtivoService.getAll()) {
			if (tpAtivo.getName().toLowerCase().startsWith(query.toLowerCase()))
				results.add(tpAtivo.getName());
		}

		return results;
	}
	
	
	public List<String> completeFuncionario(String query) {
		List<String> results = new ArrayList<String>();

		for (Funcionario func : funcionarioService.getAll()) {
			if (func.getNome().toLowerCase().startsWith(query.toLowerCase()))
				results.add(func.getNome());
		}

		return results;
	}
	
	// GETTERS AND SETTERS 

	public List<Object[]> getListAtivo() {
		return listAtivo;
	}

	public void setListAtivo(List<Object[]> listAtivo) {
		this.listAtivo = listAtivo;
	}

	public Object[] getSelected() {
		return selected;
	}

	public void setSelected(Object[] selected) {
		this.selected = selected;
	}

	public AtivoService getAtivoService() {
		return ativoService;
	}

	public void setAtivoService(AtivoService ativoService) {
		this.ativoService = ativoService;
	}

	public FuncionarioService getFuncionarioService() {
		return funcionarioService;
	}

	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}

	public TipoAtivoService getTipoAtivoService() {
		return tipoAtivoService;
	}

	public void setTipoAtivoService(TipoAtivoService tipoAtivoService) {
		this.tipoAtivoService = tipoAtivoService;
	}
}