package edu.fatec.zl.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

/**
 * <b>Description</b> 
 * Classe utilitaria usada para fazer as operacoes na base de dados
 * @author Ivan Rodrigues
 * @param <T>
 */
@Transactional 
public class GenericDao<T> {
	
	@PersistenceContext(unitName="tcc")
	private EntityManager entityManager;
	
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
	 * Faz insert em um objeto passando-se a instancia dele
	 */
	@Transactional
	public void persist(T entity) throws Exception {
		entityManager.persist(entity);
	}

	/**
	 * Faz delete de um objeto passando-se a instancia dele
	 */
	@Transactional
	public void remove(T entity) throws Exception {
		T obj = entityManager.merge(entity);
		entityManager.remove(obj);
	}

	/**
	 * Faz update em um objeto passando-se a instancia dele
	 * @param obj
	 */
	@Transactional
	public void merge(T entity) throws Exception {
		entityManager.merge(entity);
	}
	
	/**
	 * Obtem uma lista de elementos passando determinada classe.
	 * @param clazz
	 * @return 
	 */
	public List<T> getAll(Class<T> clazz){
		TypedQuery<T> criteria = this.executeCriteria(clazz);
		return criteria.getResultList();
	}


	public EntityManager getEntityManager() {
		return entityManager;
	}


	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}