package edu.fatec.zl.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.springframework.stereotype.Controller;

import edu.fatec.zl.dao.DataAccess;
import edu.fatec.zl.entity.Setor;
import edu.fatec.zl.util.FacesUtil;

@ManagedBean
@Controller
public class SetorBean extends DataAccess<AtivoBean> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Setor> listSetor = null;
	private Setor selected = new Setor();
	private FacesUtil faces = new FacesUtil();
	private FacesContext ctx = faces.getFacesContext();
	private Date date = new Date();
	
	
	@PostConstruct
	public void load(){
		listSetor = new Setor().getSetorList();
		listSetor.add(0,new Setor());
	}

	public void add(ActionEvent evt) {
		
		if(selected == null){
			ctx.addMessage(null, new FacesMessage("Selecione uma linha."));
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
			listSetor = new Setor().getSetorList();
			listSetor.add(0,new Setor());
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(e.getMessage()));
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
