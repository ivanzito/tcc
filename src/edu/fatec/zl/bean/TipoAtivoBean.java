package edu.fatec.zl.bean;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import edu.fatec.zl.entity.TipoAtivo;
import edu.fatec.zl.service.TipoAtivoService;
import edu.fatec.zl.util.FacesUtil;

@ManagedBean
@Controller
public class TipoAtivoBean extends GenericBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<TipoAtivo> listTipoAtivo = new LinkedList<TipoAtivo>();
	private FacesUtil faces = new FacesUtil();
	private TipoAtivo selected = null;
	
	@Inject
	private TipoAtivoService tipoAtivoService;
	
	@PostConstruct
	public void load(){
		listTipoAtivo = tipoAtivoService.getAll();
		listTipoAtivo.add(0,new TipoAtivo());
	}
	

	public void add(ActionEvent evt) {
		
		FacesContext ctx = faces.getFacesContext();
		
		if(selected == null){
			ctx.addMessage(null, new FacesMessage(super.getBundle().getString("select_row")));
			return;
		}
		
		try {
			tipoAtivoService.persist(selected);
			this.listTipoAtivo = tipoAtivoService.getAll();
			this.listTipoAtivo.add(0,new TipoAtivo());
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(e.getMessage()));
		}
	}


	public void update(ActionEvent evt) {
		
		FacesContext ctx = faces.getFacesContext();
		
		if(selected == null){
			ctx.addMessage(null, new FacesMessage(super.getBundle().getString("select_row")));
			return;
		}
		
		try {
			tipoAtivoService.merge(selected);
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
			tipoAtivoService.remove(selected);
			listTipoAtivo = tipoAtivoService.getAll();
			listTipoAtivo.add(0,new TipoAtivo());
		} catch (Exception e) {
			faces.getFacesContext().addMessage(null, new FacesMessage(super.getBundle().getString("referencial_integrity")));
		}
	}

	/* GETTERS AND SETTERS*/
	
	public List<TipoAtivo> getListTipoAtivo() {
		return listTipoAtivo;
	}

	public void setListTipoAtivo(List<TipoAtivo> listTipoAtivo) {
		this.listTipoAtivo = listTipoAtivo;
	}

	public void setSelected(TipoAtivo model){
		this.selected = model;
	}
	
	public TipoAtivo getSelected(){
		return selected;
	}


	public TipoAtivoService getTipoAtivoService() {
		return tipoAtivoService;
	}


	public void setTipoAtivoService(TipoAtivoService tipoAtivoService) {
		this.tipoAtivoService = tipoAtivoService;
	}
}