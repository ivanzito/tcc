package edu.fatec.zl.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.Signature;

public aspect TransactionalAspect {

	Logger logger = Logger.getLogger(this.getClass());
	
	pointcut transaction() : 
			execution(* edu.fatec.zl.dao.GenericDao.persist(..)) || 
			execution(* edu.fatec.zl.dao.GenericDao.remove(..)) || 
			execution(* edu.fatec.zl.dao.GenericDao.merge(..)); 

	before() : transaction(){
		logger.info("==============================");
		logger.info("Begin Transaction");
		logger.info("==============================");
		Signature signature = thisJoinPointStaticPart.getSignature();
		logger.info(signature.toLongString());
		
		int i = 1;

		for(Object obj : thisJoinPoint.getArgs()){
			logger.info("Arg " + i + ":" + obj);
			i++;
		}
	}

	after():execution(@org.springframework.transaction.annotation.Transactional * *(..) ){
		logger.info("==============================");
		logger.info("End Transaction");
		logger.info("==============================");
	}
}