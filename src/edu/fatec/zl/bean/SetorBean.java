package edu.fatec.zl.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import org.springframework.stereotype.Controller;

import edu.fatec.zl.entity.Setor;
import edu.fatec.zl.service.SetorService;

@ManagedBean
@Controller
public class SetorBean extends GenericBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Setor> listSetor = null;
	private Setor selected = null;

	
	@Inject
	private SetorService setorService;
	

	
	@PostConstruct
	public void load(){
		listSetor = setorService.getAll();
		
		listSetor.add(0,new Setor());
	}

	public void add(ActionEvent evt) throws RuntimeException,Exception{
		
		if(selected == null)
			throw new RuntimeException();
		
		
		setorService.persist(selected);
		listSetor = setorService.getAll();
		listSetor.add(0,new Setor());
		
	}

	public void update(ActionEvent evt) throws RuntimeException,Exception {
		
 		if(selected == null)
			throw new RuntimeException();
		
		setorService.merge(selected);
	}

	public void delete(ActionEvent evt) throws RuntimeException,Exception{
		
		if(selected == null)
			throw new RuntimeException();
		
		setorService.remove(selected);
		listSetor = setorService.getAll();
		listSetor.add(0,new Setor());
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

	public SetorService getSetorService() {
		return setorService;
	}

	public void setSetorService(SetorService setorService) {
		this.setorService = setorService;
	}
}