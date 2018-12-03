package br.com.gescolar.dto;

import java.io.Serializable;
import java.util.List;


public class PeriodosDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	private Long codigoTurma;
	private List<TurmaPeriodoDTO> segunda;
	private List<TurmaPeriodoDTO> terca;
	private List<TurmaPeriodoDTO> quarta;
	private List<TurmaPeriodoDTO> quinta;
	private List<TurmaPeriodoDTO> sexta;
	private List<TurmaPeriodoDTO> sabado;
	private List<TurmaPeriodoDTO> all;
	private List<TurmaPeriodoDTO> vagos;
	
	public Long getCodigoTurma() {
		return codigoTurma;
	}
	public void setCodigoTurma(Long codigoTurma) {
		this.codigoTurma = codigoTurma;
	}
	public List<TurmaPeriodoDTO> getSegunda() {
		return segunda;
	}
	public void setSegunda(List<TurmaPeriodoDTO> segunda) {
		this.segunda = segunda;
	}
	public List<TurmaPeriodoDTO> getTerca() {
		return terca;
	}
	public void setTerca(List<TurmaPeriodoDTO> terca) {
		this.terca = terca;
	}
	public List<TurmaPeriodoDTO> getQuarta() {
		return quarta;
	}
	public void setQuarta(List<TurmaPeriodoDTO> quarta) {
		this.quarta = quarta;
	}
	public List<TurmaPeriodoDTO> getQuinta() {
		return quinta;
	}
	public void setQuinta(List<TurmaPeriodoDTO> quinta) {
		this.quinta = quinta;
	}
	public List<TurmaPeriodoDTO> getSexta() {
		return sexta;
	}
	public void setSexta(List<TurmaPeriodoDTO> sexta) {
		this.sexta = sexta;
	}
	public List<TurmaPeriodoDTO> getSabado() {
		return sabado;
	}
	public void setSabado(List<TurmaPeriodoDTO> sabado) {
		this.sabado = sabado;
	}
	public List<TurmaPeriodoDTO> getAll() {
		return all;
	}
	public void setAll(List<TurmaPeriodoDTO> all) {
		this.all = all;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<TurmaPeriodoDTO> getVagos() {
		return vagos;
	}
	public void setVagos(List<TurmaPeriodoDTO> vagos) {
		this.vagos = vagos;
	}
	
	
	
	
	
	
}
