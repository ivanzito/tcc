package edu.fatec.zl.bean;

import java.io.Serializable;
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
public class TipoAtivoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<TipoAtivo> listTipoAtivo = new LinkedList<TipoAtivo>();
	private FacesUtil faces = new FacesUtil();
	private TipoAtivo selected = new TipoAtivo();
	private FacesContext ctx = faces.getFacesContext();
	
	@Inject
	private TipoAtivo tipoAtivo;
	
	@PostConstruct
	public void load(){
		listTipoAtivo = tipoAtivo.getTipoAtivoList();
		listTipoAtivo.add(0,new TipoAtivo());
	}
	

	public void add(ActionEvent evt) {
		
		if(selected == null){
			ctx.addMessage(null, new FacesMessage("Selecione uma linha."));
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
		
		if(selected == null)
			ctx.addMessage(null, new FacesMessage("Selecione uma linha."));
		
		try {
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
			listTipoAtivo = tipoAtivo.getTipoAtivoList();
			listTipoAtivo.add(0,new TipoAtivo());
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(e.getMessage()));
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
