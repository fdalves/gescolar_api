package br.com.gescolar.dto;

import java.io.Serializable;
import java.util.List;

import br.com.gescolar.model.TurmaPeriodo;


public class TurmaPeriodoDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	
	private List<TurmaPeriodo> segunda;
	private List<TurmaPeriodo> terca;
	private List<TurmaPeriodo> quarta;
	private List<TurmaPeriodo> quinta;
	private List<TurmaPeriodo> sexta;
	private List<TurmaPeriodo> sabado;
	
	
	public List<TurmaPeriodo> getSegunda() {
		return segunda;
	}
	public void setSegunda(List<TurmaPeriodo> segunda) {
		this.segunda = segunda;
	}
	public List<TurmaPeriodo> getTerca() {
		return terca;
	}
	public void setTerca(List<TurmaPeriodo> terca) {
		this.terca = terca;
	}
	public List<TurmaPeriodo> getQuarta() {
		return quarta;
	}
	public void setQuarta(List<TurmaPeriodo> quarta) {
		this.quarta = quarta;
	}
	public List<TurmaPeriodo> getQuinta() {
		return quinta;
	}
	public void setQuinta(List<TurmaPeriodo> quinta) {
		this.quinta = quinta;
	}
	public List<TurmaPeriodo> getSexta() {
		return sexta;
	}
	public void setSexta(List<TurmaPeriodo> sexta) {
		this.sexta = sexta;
	}
	public List<TurmaPeriodo> getSabado() {
		return sabado;
	}
	public void setSabado(List<TurmaPeriodo> sabado) {
		this.sabado = sabado;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
}
