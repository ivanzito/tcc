package edu.fatec.zl.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;

@Entity
@NamedQuery(name="ativoGraficoPizza",query="SELECT COUNT(a) FROM Ativo a WHERE a.tipoAtivo.id =:aux")
@Repository
public class Ativo implements Serializable,Persistable {

	
	public Ativo(){
		super();
		this.tipoAtivo = new TipoAtivo();
		this.funcionario = new Funcionario();
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
	private String ativo;

	@OneToOne
	@JoinColumn(nullable=false)
	private TipoAtivo tipoAtivo;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataModificacao;
	
	@ManyToOne
    private Funcionario funcionario;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public TipoAtivo getTipoAtivo() {
		return tipoAtivo;
	}

	public void setTipoAtivo(TipoAtivo tipoAtivo) {
		this.tipoAtivo = tipoAtivo;
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

	@Override
	public String toString() {
		return "Ativo [id=" + id + ", ativo=" + ativo + ", tipoAtivo="
				+ tipoAtivo + ", dataModificacao=" + dataModificacao
				+ ", funcionario=" + funcionario + "]";
	}
	
	
}
