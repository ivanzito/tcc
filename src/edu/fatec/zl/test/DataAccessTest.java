package edu.fatec.zl.test;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import edu.fatec.zl.entity.Ativo;
import edu.fatec.zl.entity.Funcionario;
import edu.fatec.zl.entity.Login;
import edu.fatec.zl.entity.Setor;
import edu.fatec.zl.entity.TipoAtivo;
import edu.fatec.zl.service.AtivoService;
import edu.fatec.zl.service.FuncionarioService;
import edu.fatec.zl.service.SetorService;
import edu.fatec.zl.service.TipoAtivoService;
import edu.fatec.zl.service.UserService;

public class DataAccessTest {
	
	
	@Test 
	public void createMassOfTest()throws Exception{

		SetorService setorService = new SetorService();
		TipoAtivoService tpAtivoService = new TipoAtivoService();
		FuncionarioService funcionarioService = new FuncionarioService();
		AtivoService ativoService = new AtivoService();
		UserService loginService = new UserService();
		
		Setor setor = new Setor();
		setor.setDataModificacao(new Date());
		setor.setName("Desenvolvimento");
		setorService.persist(setor);

		TipoAtivo tpAtivo = new TipoAtivo();
		tpAtivo.setDataModificacao(new Date());
		tpAtivo.setDepreciacao(0.02);
		tpAtivo.setName("computador");
		tpAtivoService.persist(tpAtivo);

		Funcionario funcionario = new Funcionario();
		funcionario.setNome("Ivan");
		funcionario.setDataModificacao(new Date());
		funcionario.setSetor(setor);
		funcionarioService.persist(funcionario);
		
		List<Ativo> list = new LinkedList<Ativo>();
		Ativo ativo = new Ativo();
		ativo.setAtivo("pc");
		ativo.setDataModificacao(new Date());
		ativo.setTipoAtivo(tpAtivo);
		ativo.setFuncionario(funcionario);
		ativoService.persist(ativo);
		list.add(ativo);
		
		ativo = new Ativo();
		ativo.setAtivo("laptop");
		ativo.setDataModificacao(new Date());
		ativo.setTipoAtivo(tpAtivo);
		ativo.setFuncionario(funcionario);
		ativoService.persist(ativo);
		list.add(ativo);
			
		Login login = new Login();
		login.setUsuario("ivan");
		login.setSenha("ivan");
		login.setFuncionario(funcionario);
		login.setDataModificacao(new Date());
		loginService.persist(login);	
	}
}
