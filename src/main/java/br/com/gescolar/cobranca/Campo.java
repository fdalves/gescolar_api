package br.com.gescolar.cobranca;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Campo {
	
	@JacksonXmlProperty(localName = "tpoCampo")
	private TpoCampoEnum tpoCampo;
	@JacksonXmlProperty(localName = "posicao")
	private Integer posicao;
	@JacksonXmlProperty(localName = "posIni")	
	private Integer posIni;
	@JacksonXmlProperty(localName = "posFim")
	private Integer posFim;
	@JacksonXmlProperty(localName = "decimais")
	private Integer decimais;
	@JacksonXmlProperty(localName = "alinhadoDireita")
	private Boolean alinhadoDireita;
	@JacksonXmlProperty(localName = "zeroEsquerda")
	private Boolean zeroEsquerda;
	@JacksonXmlProperty(localName = "valor")
	private String valor;
	
	
	public TpoCampoEnum getTpoCampo() {
		return tpoCampo;
	}
	public void setTpoCampo(TpoCampoEnum tpoCampo) {
		this.tpoCampo = tpoCampo;
	}
	public Integer getPosicao() {
		return posicao;
	}
	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}
	public Integer getPosIni() {
		return posIni;
	}
	public void setPosIni(Integer posIni) {
		this.posIni = posIni;
	}
	public Integer getPosFim() {
		return posFim;
	}
	public void setPosFim(Integer posFim) {
		this.posFim = posFim;
	}
	public Integer getDecimais() {
		return decimais;
	}
	public void setDecimais(Integer decimais) {
		this.decimais = decimais;
	}
	public Boolean getAlinhadoDireita() {
		return alinhadoDireita;
	}
	public void setAlinhadoDireita(Boolean alinhadoDireita) {
		this.alinhadoDireita = alinhadoDireita;
	}
	public Boolean getZeroEsquerda() {
		return zeroEsquerda;
	}
	public void setZeroEsquerda(Boolean zeroEsquerda) {
		this.zeroEsquerda = zeroEsquerda;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
	
	
}
