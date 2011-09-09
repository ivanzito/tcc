package edu.fatec.zl.bean;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

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
import edu.fatec.zl.service.FuncionarioService;
import edu.fatec.zl.service.SetorService;
import edu.fatec.zl.service.UserService;
import edu.fatec.zl.util.CypherUtil;
import edu.fatec.zl.util.FacesUtil;

@ManagedBean
@Controller
public class UserBean extends GenericBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String user;
	private String password;
	private String name;
	
	private List<SelectItem> selectSetor =  new LinkedList<SelectItem>();
	private Long setorSelected;
	private FacesUtil faces = new FacesUtil();
	
	@Inject
	private UserService userService;
	
	@Inject 
	private SetorService setorService;
	
	@Inject
	private FuncionarioService funcionarioService;
	
	@Inject
	private CypherUtil cypher;
	
	private Logger logger = Logger.getLogger(UserBean.class);
	
	
	@PostConstruct
	public void load(){
		List<Setor>list = setorService.getAll();
		for(Setor set : list)
			selectSetor.add(new SelectItem(set.getId(),set.getName()));
	}
	
	
	public String doLogin() {

		FacesUtil faces = new FacesUtil();
		FacesContext ctx = faces.getFacesContext();
		String forward = "home";
		
		password = cypher.cypherLogin(user,password);
		
		try{
		
			userService.doLogin(user,password); 
		
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
			
			Setor set = setorService.getSetor(setorSelected);  
		
			Funcionario funcionario = new Funcionario();
			funcionario.setSetor(set);
			funcionario.setNome(name);
			funcionario.setDataModificacao(new Date());
			funcionarioService.persist(funcionario);
			
			Login login = new Login();
			login.setUsuario(user);
			login.setSenha(password);
			login.setDataModificacao(new Date());
			login.setFuncionario(funcionario);
			userService.persist(login);
			
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SelectItem> getSelectSetor() {
		selectSetor = new LinkedList<SelectItem>();
		List<Setor>list = setorService.getAll();
		for(Setor set : list)
			selectSetor.add(new SelectItem(set.getId(),set.getName()));
	
		return selectSetor;
	}


	public Long getSetorSelected() {
		return setorSelected;
	}


	public void setSetorSelected(Long setorSelected) {
		this.setorSelected = setorSelected;
	}


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}


	public SetorService getSetorService() {
		return setorService;
	}


	public void setSetorService(SetorService setorService) {
		this.setorService = setorService;
	}


	public FuncionarioService getFuncionarioService() {
		return funcionarioService;
	}


	public void setFuncionarioService(FuncionarioService funcionarioService) {
		this.funcionarioService = funcionarioService;
	}


	public void setSelectSetor(List<SelectItem> selectSetor) {
		this.selectSetor = selectSetor;
	}


	public CypherUtil getCypher() {
		return cypher;
	}


	public void setCypher(CypherUtil cypher) {
		this.cypher = cypher;
	}
}
