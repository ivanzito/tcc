package edu.fatec.zl.test;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.fatec.zl.entity.Ativo;

public class TransactionSpringTest {

	@Test
	public void test() {
		String file = "file:C:/Users/irodrigues/git/tcc/WebContent/WEB-INF/spring-beans.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(file);
		Ativo ativo = (Ativo) context.getBean("ativo");
		ativo = ativo.getEntityManager().find(Ativo.class, 1l);
		System.out.println(ativo.getAtivo());
	}
}
