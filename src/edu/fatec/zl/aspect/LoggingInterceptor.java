package edu.fatec.zl.aspect;



import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingInterceptor{

	Logger logger = Logger.getLogger(LoggingInterceptor.class);
	
	
	@Before("execution(public * edu.fatec.zl.*.DataAccess.*(..))") 
	public void anyAction() {
		logger.info("****************************************************");
	}
}
