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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import edu.fatec.zl.dao.DataAccess;

@Entity
@NamedQuery(name="ativoGraficoPizza",query="SELECT COUNT(a) FROM Ativo a WHERE a.tipoAtivo.id =:aux")
@Repository
public class Ativo extends DataAccess<Ativo> implements Serializable {

	
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

	public List<Ativo> getAtivoList(){
		
		TypedQuery<Ativo> tqAtivo = null;
		List<Ativo> listAtivo = null;
		tqAtivo = new Ativo().executeCriteria(Ativo.class);
		
		if(tqAtivo.getMaxResults() > 0)
			listAtivo = tqAtivo.getResultList();
		
		return listAtivo;
	}
	
	
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
}
