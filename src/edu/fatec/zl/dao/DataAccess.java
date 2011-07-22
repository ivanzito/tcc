package edu.fatec.zl.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.eclipse.persistence.exceptions.DatabaseException;
import org.eclipse.persistence.exceptions.JPQLException;

/**
 * <b>Description</b> 
 * Classe utilitaria que e usada para fazer as operacoes na
 * base de dados
 * @author Ivan Rodrigues - E468735
 * @param <T>
 */

public class DataAccess<T> extends AbstractDataAccess {
	
	private final Logger logger = Logger.getLogger(DataAccess.class);
	
	private EntityManager entityManager = null;
	
	/**
	 * Construtor Default
	 */
	public DataAccess(){
		super();
		entityManager = super.getEntityManager(PERSISTENCE_UNIT);
	}
	
	
	/**
	 * Executa uma namedQuery passando os seus parametros.
	 * @param namedQuery {@link Map}
	 * @param parameters {@link String}
	 * @return {@link Query}
	 */
	public Query executeNamedQuery(String namedQuery, Map<String,Object> parameters){
		
		Query query = null;
		
		try{
			query = entityManager.createNamedQuery(namedQuery);
			
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
		}catch(JPQLException e){
			logger.error(e.getMessage());
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

		Query query = null;
		
		try{
			query = entityManager.createQuery(jpql);
			
			Iterator<String> it = parameters.keySet().iterator();
			while(it.hasNext()){
				String key = it.next();
				String value = parameters.get(key);
				query.setParameter(key, value);	
			}
		}catch(JPQLException e){
			logger.error(e.getMessage());
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

		Query query = null;
		
		try{
			 query = entityManager.createQuery(jpql);
		}catch(JPQLException e){
			logger.error(e.getMessage());
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
	public void insert() throws Exception {
		
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			if (!transaction.isActive()) 
				entityManager.getTransaction().begin();
			
			entityManager.persist(this);
			transaction.commit();
		} catch(RollbackException e){
			logger.error(e.getMessage());
			transaction.rollback();
			throw e;			
		} catch (DatabaseException e) {
			logger.error(e.getMessage());
			transaction.rollback();
			throw e;
		} catch(Exception e){
			logger.error(e.getMessage());
			transaction.rollback();
			throw e;			
		}
	}

	/**
	 * Faz delete de um objeto passando-se a instancia dele
	 */
	public void delete() throws Exception {
		
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			if (!transaction.isActive()) 
				entityManager.getTransaction().begin(); 
			
			DataAccess<T> obj = entityManager.merge(this);
			entityManager.remove(obj);
			transaction.commit();
		} catch (DatabaseException e) {
			logger.error(e.getMessage());
			transaction.rollback();
			throw e;
		}  catch(RollbackException e){
			logger.error(e.getMessage());
			transaction.rollback();
			throw e;			
		} catch(Exception e){
			logger.error(e.getMessage());
			transaction.rollback();
			throw e;			
		} finally{
			
		}
	}

	/**
	 * Faz update em um objeto passando-se a instancia dele
	 * @param obj
	 */
	public void update() throws Exception {
		
		EntityTransaction transaction = entityManager.getTransaction();
		try {
			if (!transaction.isActive()) 
				entityManager.getTransaction().begin();
			
			DataAccess<T> obj = entityManager.merge(this);
			entityManager.merge(obj);
			transaction.commit();
		} catch(RollbackException e){
			logger.error(e.getMessage());
			transaction.rollback();
			throw e;			
		} catch (DatabaseException e) {
			logger.error(e.getMessage());
			transaction.rollback();
			throw e;
		} catch(Exception e){
			logger.error(e.getMessage());
			transaction.rollback();
			throw e;			
		}
	}
}
