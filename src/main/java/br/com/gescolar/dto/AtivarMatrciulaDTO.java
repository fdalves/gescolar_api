package br.com.gescolar.dto;

import java.io.Serializable;

public class AtivarMatrciulaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String dtInicial;
	private String dtFinal;
	private String diaBoleto;
	private String diaVencimento;
	private String juros;
	private String valor;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDtInicial() {
		return dtInicial;
	}
	public void setDtInicial(String dtInicial) {
		this.dtInicial = dtInicial;
	}
	public String getDtFinal() {
		return dtFinal;
	}
	public void setDtFinal(String dtFinal) {
		this.dtFinal = dtFinal;
	}
	public String getDiaBoleto() {
		return diaBoleto;
	}
	public void setDiaBoleto(String diaBoleto) {
		this.diaBoleto = diaBoleto;
	}
	public String getDiaVencimento() {
		return diaVencimento;
	}
	public void setDiaVencimento(String diaVencimento) {
		this.diaVencimento = diaVencimento;
	}
	public String getJuros() {
		return juros;
	}
	public void setJuros(String juros) {
		this.juros = juros;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	@Override
	public String toString() {
		return "AtivarMatrciulaDTO [codigo=" + codigo + ", dtInicial=" + dtInicial + ", dtFinal=" + dtFinal
				+ ", diaBoleto=" + diaBoleto + ", diaVencimento=" + diaVencimento + ", juros=" + juros + ", valor="
				+ valor + "]";
	}
	
	

}
