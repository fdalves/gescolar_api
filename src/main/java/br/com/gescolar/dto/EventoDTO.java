package br.com.gescolar.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private LocalDate dataIniEvento;
	private LocalDate dataFimEvento;
	private List<LocalDate> datasNotificar;
	private String descEvento;
	private List<Long> professoresSelecionados;
	private List<Long> turmasSelecionados;
	private String selectedOpcao;
	
	
	public LocalDate getDataIniEvento() {
		return dataIniEvento;
	}
	public void setDataIniEvento(LocalDate dataIniEvento) {
		this.dataIniEvento = dataIniEvento;
	}
	public LocalDate getDataFimEvento() {
		return dataFimEvento;
	}
	public void setDataFimEvento(LocalDate dataFimEvento) {
		this.dataFimEvento = dataFimEvento;
	}
	public List<LocalDate> getDatasNotificar() {
		return datasNotificar;
	}
	public void setDatasNotificar(List<LocalDate> datasNotificar) {
		this.datasNotificar = datasNotificar;
	}
	public String getDescEvento() {
		return descEvento;
	}
	public void setDescEvento(String descEvento) {
		this.descEvento = descEvento;
	}
	public List<Long> getProfessoresSelecionados() {
		return professoresSelecionados;
	}
	public void setProfessoresSelecionados(List<Long> professoresSelecionados) {
		this.professoresSelecionados = professoresSelecionados;
	}
	public List<Long> getTurmasSelecionados() {
		return turmasSelecionados;
	}
	public void setTurmasSelecionados(List<Long> turmasSelecionados) {
		this.turmasSelecionados = turmasSelecionados;
	}
	public String getSelectedOpcao() {
		return selectedOpcao;
	}
	public void setSelectedOpcao(String selectedOpcao) {
		this.selectedOpcao = selectedOpcao;
	}
	@Override
	public String toString() {
		return "EventoDTO [dataIniEvento=" + dataIniEvento + ", dataFimEvento=" + dataFimEvento + ", datasNotificar="
				+ datasNotificar + ", descEvento=" + descEvento + ", professoresSelecionados=" + professoresSelecionados
				+ ", turmasSelecionados=" + turmasSelecionados + ", selectedOpcao=" + selectedOpcao + "]";
	}
	
	
	
	
}
