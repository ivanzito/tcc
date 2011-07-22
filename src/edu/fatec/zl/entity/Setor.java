package edu.fatec.zl.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import edu.fatec.zl.dao.DataAccess;

@Entity
@NamedQuery(name="setorPorNome",query="SELECT s FROM Setor s WHERE s.name =:aux")
@Repository
public class Setor extends DataAccess<Setor> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@SequenceGenerator(name="SQ",sequenceName="SEQUENCE",allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SQ")
	private Long id;
	
	@Column(nullable=false)
	private String name;
		
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataModificacao;
	
	public List<Setor> getSetorList(){
		
		TypedQuery<Setor> tqTpAtivo = null;
		List<Setor> listTpAtivo = null;
		tqTpAtivo = new Setor().executeCriteria(Setor.class);
		
		if(tqTpAtivo.getMaxResults() > 0)
			listTpAtivo = tqTpAtivo.getResultList();
		
		return listTpAtivo;
	}
	
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

	public Date getDataModificacao() {
		return dataModificacao;
	}

	public void setDataModificacao(Date dataModificacao) {
		this.dataModificacao = dataModificacao;
	}
	
	public String toString(){
		return this.getName();
	}
}
