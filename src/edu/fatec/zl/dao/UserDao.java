package edu.fatec.zl.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import edu.fatec.zl.entity.Login;

@Repository
public class UserDao<UserService> extends GenericDao<Login> {

	public Login doLogin(String user, String password){
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("usr", user);
		map.put("pwd", password);
		
		return (Login)super.executeNamedQuery("login", map).getSingleResult();
	}
}
