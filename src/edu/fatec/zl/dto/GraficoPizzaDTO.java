package edu.fatec.zl.dto;

import java.io.Serializable;

public class GraficoPizzaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long quantidade;
	private String setor;
	
	public GraficoPizzaDTO(Long quantidade,String setor){
		this.quantidade = quantidade;
		this.setor = setor;
	}
	
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
	public String getSetor() {
		return setor;
	}
	public void setSetor(String setor) {
		this.setor = setor;
	}
}
