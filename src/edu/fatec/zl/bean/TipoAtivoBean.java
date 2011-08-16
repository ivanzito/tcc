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
import edu.fatec.zl.util.FacesUtil;

@ManagedBean
@Controller
public class TipoAtivoBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<TipoAtivo> listTipoAtivo = new LinkedList<TipoAtivo>();
	private FacesUtil faces = new FacesUtil();
	private TipoAtivo selected = null;
	
	@Inject
	private TipoAtivo tipoAtivo;
	
	@PostConstruct
	public void load(){
		listTipoAtivo = tipoAtivo.getTipoAtivoList();
		listTipoAtivo.add(0,new TipoAtivo());
	}
	

	public void add(ActionEvent evt) {
		
		FacesContext ctx = faces.getFacesContext();
		
		if(selected == null){
			ctx.addMessage(null, new FacesMessage(super.getBundle().getString("select_row")));
			return;
		}
		
		try {
			selected.insert();
			this.listTipoAtivo = selected.getTipoAtivoList();
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
			listTipoAtivo = tipoAtivo.getTipoAtivoList();
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


	public TipoAtivo getTipoAtivo() {
		return tipoAtivo;
	}


	public void setTipoAtivo(TipoAtivo tipoAtivo) {
		this.tipoAtivo = tipoAtivo;
	}
}