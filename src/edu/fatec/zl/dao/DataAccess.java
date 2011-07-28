package edu.fatec.zl.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * <b>Description</b> 
 * Classe utilitaria que e usada para fazer as operacoes na
 * base de dados
 * @author Ivan Rodrigues - E468735
 * @param <T>
 */


@Repository
public class DataAccess<T> implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager = null;
	
	public static final String PERSISTENCE_UNIT = "tcc";
	
	
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
	
	/**
	 * Construtor Default
	 */
	public DataAccess(){
		super();
		entityManager = this.getEntityManager();
	}
	
	
	/**
	 * Executa uma namedQuery passando os seus parametros.
	 * @param namedQuery {@link Map}
	 * @param parameters {@link String}
	 * @return {@link Query}
	 */
	public Query executeNamedQuery(String namedQuery, Map<String,Object> parameters){
		
		Query query = entityManager.createNamedQuery(namedQuery);
		
		Iterator<String> it = parameters.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			Object o = parameters.get(key);
			
			if(o instanceof Long)
				query.setParameter(key, (Long) o);
			
			if(o instanceof Integer)
				query.setParameter(key, (Integer) o);
			
			if(o instanceof BigInteger)
				query.setParameter(key, (BigInteger) o);
			
			if(o instanceof BigDecimal)
				query.setParameter(key, (BigDecimal) o);
			
			if(o instanceof Date)
				query.setParameter(key, (Date) o);
			
			if(o instanceof String)
				query.setParameter(key,o);
			
		}

		
		return query;
	}
	
	/**
	 * Executa uma Query passando os seus parametros.
	 * @param namedQuery {@link Map}
	 * @param parameters {@link String}
	 * @return {@link Query}
	 */
	public Query executeJPQL(String jpql,Map<String,String> parameters){

		Query query = entityManager.createQuery(jpql);
			
		Iterator<String> it = parameters.keySet().iterator();
		while(it.hasNext()){
			String key = it.next();
			String value = parameters.get(key);
			query.setParameter(key, value);	
		}

		return query;
	}
	
	/**
	 * Executa uma Query.
	 * 
	 * @param parameters {@link String}
	 * @return {@link Query}
	 */
	public Query executeJPQL(String jpql){
		return entityManager.createQuery(jpql);
	}
	
	/**
	 * Executa uma Criteria simples, retornando um objeto TypedQuery<T>
	 * para tratar o select.
	 * 
	 * @param parameters {@link Class}
	 * @return {@link TypedQuery}
	 */
	public TypedQuery<T> executeCriteria(Class<T> clazz){
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> select = cq.from(clazz);
		cq.select(select);

		return entityManager.createQuery(cq);
	}
	
	
	/**
	 * Executa uma Criteria simples, retornando um objeto TypedQuery<T>
	 * para tratar o select.
	 * 
	 * @param parameters {@link Class}
	 * @return {@link CriteriaQuery}
	 */
	public CriteriaQuery<T> getCriteriaQuery(Class<T> clazz){
		
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> cq = cb.createQuery(clazz);
		Root<T> select = cq.from(clazz);
		return cq.select(select);
	}

	/**
	 * Faz insert em um objeto passando-se a instancia dele
	 */
	@Transactional
	public void insert() throws Exception {
		
		EntityTransaction transaction = entityManager.getTransaction();
		
		if (!transaction.isActive()) 
			entityManager.getTransaction().begin();
			
		entityManager.persist(this);
		transaction.commit();
	}

	/**
	 * Faz delete de um objeto passando-se a instancia dele
	 */
	@Transactional
	public void delete() throws Exception {
		
		EntityTransaction transaction = entityManager.getTransaction();
		if (!transaction.isActive()) 
			entityManager.getTransaction().begin(); 
			
		DataAccess<T> obj = entityManager.merge(this);
		entityManager.remove(obj);
		transaction.commit();
	}

	/**
	 * Faz update em um objeto passando-se a instancia dele
	 * @param obj
	 */
	@Transactional
	public void update() throws Exception {
		
		EntityTransaction transaction = entityManager.getTransaction();
		if (!transaction.isActive()) 
			entityManager.getTransaction().begin();
		
		DataAccess<T> obj = entityManager.merge(this);
		entityManager.merge(obj);
		transaction.commit(); 
	}
}
