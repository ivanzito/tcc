package edu.fatec.zl.service;

import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import edu.fatec.zl.dao.FuncionarioDao;
import edu.fatec.zl.entity.Funcionario;

@Service
public class FuncionarioService implements Serviceable<Funcionario> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private FuncionarioDao<FuncionarioService> dao;

	@Override
	public void persist(Funcionario entity) throws Exception {
		dao.persist(entity);
	}

	@Override
	public void remove(Funcionario entity) throws Exception {
		dao.remove(entity);
	}

	@Override
	public void merge(Funcionario entity) throws Exception {
		dao.merge(entity);
	}

	@Override
	public List<Funcionario> getAll() {
		return dao.getAll(Funcionario.class);
	}
	
	public List<Object[]> getFuncionarios(){
		List<Object[]> ret = new LinkedList<Object[]>();
		List<Funcionario> list = dao.getAll(Funcionario.class);
		
		for(Funcionario func : list){
			Object[] str = new Object[4];
			str[0] = func.getNome();
			str[1] = func.getSetor().getName();
			str[2] = func.getDataModificacao();
			str[3] = func.getId();
			ret.add(str);
		}
		
		
		return ret;
	}
	
	
	public Funcionario getFuncionarioByName(String name){
		return dao.getFuncionarioByName(name);
	}
	
	public Funcionario getFuncionario(Long id){
		return dao.getFuncionario(id);
	}

	public FuncionarioDao<FuncionarioService> getDao() {
		return dao;
	}

	public void setDao(FuncionarioDao<FuncionarioService> dao) {
		this.dao = dao;
	}
}
