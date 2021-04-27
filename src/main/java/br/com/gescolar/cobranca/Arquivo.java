package br.com.gescolar.cobranca;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class Arquivo {
	
	@JacksonXmlElementWrapper(useWrapping = false)
	private List<Linha> linhas;

	public List<Linha> getLinhas() {
		return linhas;
	}

	public void setLinhas(List<Linha> linhas) {
		this.linhas = linhas;
	}
	
	
	
	
	
}
