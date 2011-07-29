package edu.fatec.zl.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import edu.fatec.zl.entity.Funcionario;
import edu.fatec.zl.entity.Login;
import edu.fatec.zl.entity.Setor;
import edu.fatec.zl.util.CypherUtil;
import edu.fatec.zl.util.FacesUtil;

@ManagedBean
@Controller
public class UserBean extends AbstractBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String user;
	private String password;
	private String name;
	
	private List<SelectItem> selectSetor;
	private Long setorSelected;
	private FacesUtil faces = new FacesUtil();
	
	@Inject
	private CypherUtil cypher;
	
	@Inject
	private Login login;
	
	@Inject 
	private Setor setor;
	
	
	private Logger logger = Logger.getLogger(UserBean.class);
	
	
	@PostConstruct
	public void load(){
		selectSetor = new LinkedList<SelectItem>();
		List<Setor>list = setor.getSetorList();
		for(Setor set : list)
			selectSetor.add(new SelectItem(set.getId(),set.getName()));
	}
	
	
	public String doLogin() {
		
		FacesUtil faces = new FacesUtil();
		FacesContext ctx = faces.getFacesContext();
		String forward = "home";
		
		password = cypher.cypherLogin(user,password);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("usr", user);
		map.put("pwd", password);
		
		try{
			login = (Login) login.executeNamedQuery("login", map).getSingleResult();
		
		} catch(NoResultException e){
			logger.error(e.getMessage());
			ctx.addMessage(null,new FacesMessage(super.getBundle().getString("user_or_password_wrong")));
			forward = "erro";			
		} finally{
			user = "";
			password = "";
		}
		
		return forward;
	}
	
	public void register(ActionEvent evt){
		
		FacesContext ctx = faces.getFacesContext();
		password = cypher.cypherLogin(user,password);
		
		try {
			
			Setor set = setor.getEntityManager().find(Setor.class, setorSelected);  
		
			Funcionario funcionario = new Funcionario();
			funcionario.setSetor(set);
			funcionario.setNome(name);
			funcionario.setDataModificacao(new Date());
			funcionario.insert();
			
			Login login = new Login();
			login.setUsuario(user);
			login.setSenha(password);
			login.setDataModificacao(new Date());
			login.setFuncionario(funcionario);
			login.insert();
			
			ctx.addMessage(null, new FacesMessage(super.getBundle().getString("login_created")));
			
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(e.getMessage()));
		
		} finally{
			name = "";
			user = "";
			password = "";
		}
		
	}

	/* GETTERS AND SETTERS */
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SelectItem> getSelectSetor() {
		return selectSetor;
	}

	public void setSelectSetor(List<SelectItem> selectSetor) {
		this.selectSetor = selectSetor;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}


	public Long getSetorSelected() {
		return setorSelected;
	}


	public void setSetorSelected(Long setorSelected) {
		this.setorSelected = setorSelected;
	}


	public CypherUtil getCypher() {
		return cypher;
	}


	public void setCypher(CypherUtil cypher) {
		this.cypher = cypher;
	}
}
