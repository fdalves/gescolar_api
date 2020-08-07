package br.com.gescolar.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class CalendarioFiltro implements Serializable {

	private static final long serialVersionUID = 1L;
	private List<Long> turmas;
	private List<Long> professores;
	public List<Long> getTurmas() {
		return turmas;
	}
	public void setTurmas(List<Long> turmas) {
		this.turmas = turmas;
	}
	public List<Long> getProfessores() {
		return professores;
	}
	public void setProfessores(List<Long> professores) {
		this.professores = professores;
	}
	
	
}
