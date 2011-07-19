package edu.fatec.zl.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import edu.fatec.zl.dao.DataAccess;

@Entity
@NamedQueries({
	@NamedQuery(name="funcionarioPorNome",query="SELECT f FROM Funcionario f WHERE f.nome =:aux"),
	@NamedQuery(name="funcionarioGraficoPizza",query="SELECT COUNT(f) FROM Funcionario f WHERE f.setor.id =:aux")})
public class Funcionario extends DataAccess<Funcionario> implements Serializable {

	public Funcionario(){
		this.setSetor(new Setor());
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@SequenceGenerator(name="SQ",sequenceName="SEQUENCE",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SQ")
	private Long id;
	
	
	@Column(nullable=false)
	private String nome;
	
	@OneToOne
	@JoinColumn(nullable=false)
	private Setor setor;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataModificacao;
	
	@OneToMany(mappedBy="funcionario")
	private List<Ativo> listAtivo;

	public List<Funcionario> getFuncionarioList(){
		
		TypedQuery<Funcionario> tqFuncionario = null;
		List<Funcionario> listFuncionario = null;
		tqFuncionario = new Funcionario().executeCriteria(Funcionario.class);
		
		if(tqFuncionario.getMaxResults() > 0)
			listFuncionario = tqFuncionario.getResultList();
		
		return listFuncionario;
	}
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	public List<Ativo> getListAtivo() {
		return listAtivo;
	}

	public void setListAtivo(List<Ativo> listAtivo) {
		this.listAtivo = listAtivo;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}
}
