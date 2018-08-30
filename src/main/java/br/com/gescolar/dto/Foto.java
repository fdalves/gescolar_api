package br.com.gescolar.dto;

public class Foto {
	
	private String nome;
	
	private String url;

	public Foto(String nome, String url) {
		this.nome = nome;
		this.url = url;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getUrl() {
		return url;
	}
}
