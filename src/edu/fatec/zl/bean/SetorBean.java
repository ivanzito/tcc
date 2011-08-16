package edu.fatec.zl.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import edu.fatec.zl.entity.Setor;
import edu.fatec.zl.util.FacesUtil;

@ManagedBean
@Controller
public class SetorBean extends AbstractBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Setor> listSetor = null;
	private Setor selected = null;
	private FacesUtil faces = new FacesUtil();

	
	@Inject
	private Setor setor;
	

	
	@PostConstruct
	public void load(){
		listSetor = setor.getSetorList();
		
		listSetor.add(0,new Setor());
	}

	public void add(ActionEvent evt) {
		
		FacesContext ctx = faces.getFacesContext();
		
		if(selected == null){
			ctx.addMessage(null, new FacesMessage(super.getBundle().getString("select_row")));
			return;
		}
		
		try {
			selected.insert();
			listSetor = selected.getSetorList();
			listSetor.add(0,new Setor());
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
			listSetor = setor.getSetorList();
			listSetor.add(0,new Setor());
		} catch (Exception e) {
			faces.getFacesContext().addMessage(null, new FacesMessage(super.getBundle().getString("referencial_integrity")));
		}
	}

	/* GETTERS AND SETTERS*/

	public List<Setor> getListSetor() {
		return listSetor;
	}

	public void setListSetor(List<Setor> list) {
		this.listSetor = list;
	}

	public Setor getSelected() {
		return selected;
	}

	public void setSelected(Setor selected) {
		this.selected = selected;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
}