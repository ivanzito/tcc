package edu.fatec.zl.dto;

import java.io.Serializable;

public class GraficoPizzaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long value;
	private String key;
	
	public GraficoPizzaDTO(Long quantidade,String setor){
		this.value = quantidade;
		this.key = setor;
	}

	public Long getValue() {
		return value;
	}

	public void setValue(Long value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
