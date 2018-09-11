package br.com.gescolar.dto;

import java.io.Serializable;

import br.com.gescolar.types.DiaEnum;

@SuppressWarnings("serial")
public class Periodo implements Serializable {

	private Integer quant;
	private String nome;
	private String value;
	private DiaEnum dia;

	public Periodo(DiaEnum dia, Long quant) {
		super();
		this.quant = quant.intValue();
		this.dia = dia;
		this.nome = DiaEnum.getDiaStr(dia);
		this.value = DiaEnum.getDiaInt(dia);
	}

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

	public DiaEnum getDia() {
		return dia;
	}

	public void setDia(DiaEnum dia) {
		this.dia = dia;
	}

	@Override
	public String toString() {
		return "Periodo [quant=" + quant + ", nome=" + nome + ", value=" + value + ", dia=" + dia + "]";
	}

}
