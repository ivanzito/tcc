package edu.fatec.zl.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import edu.fatec.zl.dao.DataAccess;

@Entity
@NamedQuery(name="login",query="SELECT login FROM Login login WHERE login.usuario= :usr AND login.senha= :pwd")
public class Login extends DataAccess<Login> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@SequenceGenerator(name="SQ",sequenceName="SEQUENCE",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SQ")
	private Long id; 
	
	/*@Column
	private String ativo;*/
	
	@Column(nullable=false)
	private String usuario;
	
	@Column(nullable=false)
	private String senha;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataModificacao;
	
	/*private Long tipoAtivo;*/
	
	@OneToOne
	@JoinColumn
	private Funcionario funcionario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
}
