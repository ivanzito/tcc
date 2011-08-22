package edu.fatec.zl.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import edu.fatec.zl.dao.UserDao;
import edu.fatec.zl.entity.Login;

@Service
public class UserService implements Serviceable<Login>{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UserDao<UserService> dao;
	
	@Override
	public void persist(Login entity) throws Exception {
		dao.persist(entity);
	}

	@Override
	public void remove(Login entity) throws Exception {
		dao.remove(entity);
	}

	@Override
	public void merge(Login entity) throws Exception {
		dao.merge(entity);
	}

	@Override
	public List<Login> getAll() throws Exception {
		return dao.getAll(Login.class);
	}

	public UserDao<UserService> getDao() {
		return dao;
	}

	public void setDao(UserDao<UserService> dao) {
		this.dao = dao;
	}
	
	public Login doLogin(String user, String password){
		return dao.doLogin(user, password);
	}
}