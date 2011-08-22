package edu.fatec.zl.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.fatec.zl.entity.Setor;
import edu.fatec.zl.service.SetorService;

public class TransactionSpringTest {

	@Test
	public void test() {
		String file = "file:C:/Users/Ivan/git/tcc/WebContent/WEB-INF/spring-beans.xml";
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(file);

		SetorService service = (SetorService) context.getBean("setorService");
		List<Setor> ativos = service.getAll();

		for (Setor setor : ativos)
			System.out.println(setor.getName());

	}
}
