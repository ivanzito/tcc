package edu.fatec.zl.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Repository;

@Entity
@NamedQuery(name="tipoAtivoPorNome",query="SELECT ta FROM TipoAtivo ta WHERE ta.name =:aux")
@Repository
public class TipoAtivo implements Serializable,Persistable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SQ",sequenceName="SEQUENCE",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SQ")
	private Long id;
	
	@Column(nullable=false,unique=true)
	private String name;
	
	@Column(nullable=false)
	private Double depreciacao;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataModificacao;

	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Double getDepreciacao() {
		return depreciacao;
	}


	public void setDepreciacao(Double depreciacao) {
		this.depreciacao = depreciacao;
	}


	public Date getDataModificacao() {
		return dataModificacao;
	}


	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}


	@Override
	public String toString() {
		return "TipoAtivo [id=" + id + ", name=" + name + ", depreciacao="
				+ depreciacao + ", dataModificacao=" + dataModificacao + "]";
	}

	
}
