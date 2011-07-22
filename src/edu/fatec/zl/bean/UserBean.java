package edu.fatec.zl.bean;

import java.util.HashMap;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import edu.fatec.zl.entity.Login;
import edu.fatec.zl.util.FacesUtil;

@ManagedBean
@Controller
public class UserBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String user;
	private String password;
	
	private Logger logger = Logger.getLogger(UserBean.class);

	public String doLogin() {
		FacesUtil faces = new FacesUtil();
		FacesContext ctx = faces.getFacesContext();
		String forward = "home";
		
		Login login = new Login();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("usr", user);
		map.put("pwd", password);
		
		try{
			login = (Login) login.executeNamedQuery("login", map).getSingleResult();
		
		} catch(NoResultException e){
			logger.error(e.getMessage());
			ctx.addMessage(null,new FacesMessage("Usuário ou senha incorretos."));
			forward = "erro";			
		}
		
		return forward;
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

}
