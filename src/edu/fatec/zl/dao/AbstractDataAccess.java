package edu.fatec.zl.dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public abstract class AbstractDataAccess {


	public static final String PERSISTENCE_UNIT = "tcc";

	public AbstractDataAccess() {
		super();
	}

	/**
	 * Retorna um objeto do tipo EntityManager. Este e usado para fazer acesso
	 * aos dados.
	 * 
	 * @return {@link EntityManager}
	 */
	public EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory(PERSISTENCE_UNIT).createEntityManager();
	}

	/**
	 * Retorna um objeto do tipo EntityManager. Este e usado para fazer acesso
	 * aos dados.
	 * 
	 * @return {@link EntityManager}
	 * @param {@link String}
	 */
	public EntityManager getEntityManager(String persistenceUnit) {
		return Persistence.createEntityManagerFactory(persistenceUnit).createEntityManager();
	}
}
