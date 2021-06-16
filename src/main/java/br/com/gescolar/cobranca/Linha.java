package br.com.gescolar.cobranca;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Linha implements Cloneable {
	
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Campo> campos;
	@JacksonXmlProperty(localName = "tamanho")
	private int tamanho;
	@JacksonXmlProperty(localName = "tipo")
	private TipoLinhaEnum linhaEnum;
	@JacksonXmlProperty(localName = "posicao")
	private Integer posicao;
	
	
	public List<Campo> getCampos() {
		return campos;
	}
	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}
	public int getTamanho() {
		return tamanho;
	}
	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	public TipoLinhaEnum getLinhaEnum() {
		return linhaEnum;
	}
	public void setLinhaEnum(TipoLinhaEnum linhaEnum) {
		this.linhaEnum = linhaEnum;
	}
	public Integer getPosicao() {
		return posicao;
	}
	public void setPosicao(Integer posicao) {
		this.posicao = posicao;
	}
	
	
}
