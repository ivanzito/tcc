package edu.fatec.zl.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import edu.fatec.zl.dao.DataAccess;

@Aspect
@Component
public class LoggingInterceptor {

	Logger logger = Logger.getLogger(DataAccess.class);

	@Pointcut("execution(public * edu.fatec.zl.dao.DataAccess.*(..))")
	public void transactionalMethod() {}

	@AfterThrowing(pointcut="transactionalMethod()",throwing="ex")
	public void logError(Throwable ex) throws Throwable {
		logger.error(ex.getMessage());
		throw ex;
	}
}
