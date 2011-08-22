package edu.fatec.zl.service;

import java.io.Serializable;
import java.util.List;

import edu.fatec.zl.entity.Persistable;

public interface Serviceable<T extends Persistable> extends Serializable {

	void persist(T entity) throws Exception;
	void remove(T entity) throws Exception;
	void merge(T entity) throws Exception;
	List<T> getAll() throws Exception;
	
}
