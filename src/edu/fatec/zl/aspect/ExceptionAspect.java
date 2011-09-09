 package edu.fatec.zl.aspect;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import edu.fatec.zl.bean.GenericBean;

@Aspect
public class ExceptionAspect {

	Logger logger = Logger.getLogger(ExceptionAspect.class);

	private GenericBean bean = new GenericBean();
	
	@AfterThrowing(
			pointcut="execution(@org.springframework.transaction.annotation.Transactional * *(..) )"
		,throwing="ex")
	public void logError(JoinPoint jp,Exception ex) {
		logger.error("=========== ERROR DURING TRANSACTION ===================");
		logger.error(ex.getMessage());
		logger.error("=========== ERROR DURING TRANSACTION ===================");	
		if(jp.toString().contains("remove(Object)"))
			FacesContext.getCurrentInstance()
			.addMessage(null, new FacesMessage(bean.getBundle().getString("referencial_integrity")));
			
		else
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(ex.getMessage()));
	}

	
	
	@AfterThrowing(
			pointcut="execution(* edu.fatec.zl.bean.*.add(..)) ||" +
			"execution(* edu.fatec.zl.bean.*.delete(..)) ||" +
			"execution(* edu.fatec.zl.bean.*.update(..))",throwing="ex")
	public void showMessage(RuntimeException ex) throws RuntimeException {
		logger.error("=========== ERROR ROW NOT SELECTED ===================");
		FacesContext.getCurrentInstance()
		.addMessage(null, new FacesMessage(bean.getBundle().getString("select_row")));		
		logger.error("=========== ERROR ROW NOT SELECTED ===================");		
	}
}
