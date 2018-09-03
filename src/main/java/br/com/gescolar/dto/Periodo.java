package br.com.gescolar.dto;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Periodo implements Serializable{

	private Integer quant;
	private String nome;
	private String value;
	
	
	public Integer getQuant() {
		return quant;
	}
	public void setQuant(Integer quant) {
		this.quant = quant;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
