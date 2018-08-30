package br.com.gescolar.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.gescolar.types.SerieEnum;


@Component
public class Serie implements Serializable{
	
	
	private static final long serialVersionUID = 7964826391264403594L;
	private String label;
	private String value;
	
	public Serie(String value, String label) {
		super();
		this.value = value;
		this.label = label;
	}

	public Serie() {
		super();
	}
	
	public List<Serie> listarTodasSeries (){
		List<Serie> list = new ArrayList<>();
		list.add(new Serie(SerieEnum.JARDIM.name(), "Jardim de Infância"));
		list.add(new Serie(SerieEnum.PRE_ESCOLA.name(), "Pré Escola"));
		list.add(new Serie(SerieEnum.PRIMEIRA_SERIE.name(), "Primeira Série Fundamental"));
		list.add(new Serie(SerieEnum.SEGUNDA_SERIE.name(), "Segunda Série Fundamental"));
		list.add(new Serie(SerieEnum.TERCEIRA_SERIE.name(), "Terceira Série Fundamental"));
		list.add(new Serie(SerieEnum.QUARTA_SERIE.name(), "Quarta Série Fundamental"));
		list.add(new Serie(SerieEnum.QUINTA_SERIE.name(), "Quinta Série Fundamental"));
		list.add(new Serie(SerieEnum.SEXTA_SERIE.name(), "Sexta Série Fundamental"));
		list.add(new Serie(SerieEnum.SETIMA_SERIE.name(), "Sétima Série Fundamental"));
		list.add(new Serie(SerieEnum.OITAVA_SERIE.name(), "Oitava Série Fundamental"));
		list.add(new Serie(SerieEnum.PRIMIRO_ANO_SEGUNDO_GRAU.name(), "Primeiro Ano Segundo Grau"));
		list.add(new Serie(SerieEnum.SEGUNDO_ANO_SEGUNDO_GRAU.name(), "Segundo Ano Segundo Grau"));
		list.add(new Serie(SerieEnum.TERCEIRO_ANO_SEGUNDO_GRAU.name(), "Primeiro Ano Segundo Grau"));
		return list;
	}
	
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}