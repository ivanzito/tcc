package edu.fatec.zl.test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.fatec.zl.entity.Ativo;
import edu.fatec.zl.entity.Funcionario;
import edu.fatec.zl.entity.Login;
import edu.fatec.zl.entity.Setor;
import edu.fatec.zl.entity.TipoAtivo;

public class DataAccessTest {


	@Before
	public void createTables(){
		//DataAccess.getEntityManager();		
	}
	
/*	@Test 
	public void createMassOfTest()throws Exception{

		
		Setor setor = new Setor();
		setor.setDataModificacao(new Date());
		setor.setName("Desenvolvimento");
		setor.insert();

		TipoAtivo tpAtivo = new TipoAtivo();
		tpAtivo.setDataModificacao(new Date());
		tpAtivo.setDepreciacao(0.02);
		tpAtivo.setName("computador");
		tpAtivo.insert();
		
		List<Ativo> set = new LinkedList<Ativo>();
		Ativo ativo = new Ativo();
		ativo.setAtivo("pc");
		ativo.setDataModificacao(new Date());
		ativo.setTipoAtivo(tpAtivo);
		ativo.insert();
		set.add(ativo);
		
		ativo = new Ativo();
		ativo.setAtivo("laptop");
		ativo.setDataModificacao(new Date());
		ativo.setTipoAtivo(tpAtivo);
		ativo.insert();
		set.add(ativo);
		
		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Ivan");
		funcionario.setDataModificacao(new Date());
		funcionario.setSetor(setor);
		funcionario.setListAtivo(set);
		funcionario.insert();
		
		
		Login login = new Login();
		login.setUsuario("ivan");
		login.setSenha("ivan");
		login.setFuncionario(funcionario);
		login.setDataModificacao(new Date());
		login.insert();
		
		login = DataAccess.getEntityManager().find(Login.class, 1l);

		Assert.assertNotNull(login.getFuncionario().getSetor());
		Assert.assertNotNull(login.getFuncionario());
		Assert.assertNotNull(login.getUsuario()); 
	    
		funcionario = DataAccess.getEntityManager().find(Funcionario.class, 1l);
		Assert.assertFalse(funcionario.getListAtivo().isEmpty());
	}*/
	
	@Test 
	public void createMassOfTest()throws Exception{

		
		Setor setor = new Setor();
		setor.setDataModificacao(new Date());
		setor.setName("Desenvolvimento");
		setor.insert();

		TipoAtivo tpAtivo = new TipoAtivo();
		tpAtivo.setDataModificacao(new Date());
		tpAtivo.setDepreciacao(0.02);
		tpAtivo.setName("computador");
		tpAtivo.insert();

		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Ivan");
		funcionario.setDataModificacao(new Date());
		funcionario.setSetor(setor);
		funcionario.insert();
		
		List<Ativo> list = new LinkedList<Ativo>();
		Ativo ativo = new Ativo();
		ativo.setAtivo("pc");
		ativo.setDataModificacao(new Date());
		ativo.setTipoAtivo(tpAtivo);
		ativo.setFuncionario(funcionario);
		ativo.insert();
		list.add(ativo);
		
		ativo = new Ativo();
		ativo.setAtivo("laptop");
		ativo.setDataModificacao(new Date());
		ativo.setTipoAtivo(tpAtivo);
		ativo.setFuncionario(funcionario);
		ativo.insert();
		list.add(ativo);
			
		Login login = new Login();
		login.setUsuario("ivan");
		login.setSenha("ivan");
		login.setFuncionario(funcionario);
		login.setDataModificacao(new Date());
		login.insert();	
	}
}
