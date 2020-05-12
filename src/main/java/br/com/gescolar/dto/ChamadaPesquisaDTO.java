package br.com.gescolar.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
@JsonAutoDetect
public class ChamadaPesquisaDTO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private Long codigoProfessor;
	private Long codigoAluno;
	private String status;
	private Long codigoDisciplinaTurma;
	private LocalDate dataIni;
	private LocalDate dataFim;
	
	public Long getCodigoProfessor() {
		return codigoProfessor;
	}
	public void setCodigoProfessor(Long codigoProfessor) {
		this.codigoProfessor = codigoProfessor;
	}
	public Long getCodigoDisciplinaTurma() {
		return codigoDisciplinaTurma;
	}
	public void setCodigoDisciplinaTurma(Long codigoDisciplinaTurma) {
		this.codigoDisciplinaTurma = codigoDisciplinaTurma;
	}
	
	public LocalDate getDataIni() {
		return dataIni;
	}
	public void setDataIni(LocalDate dataIni) {
		this.dataIni = dataIni;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	
	
	public Long getCodigoAluno() {
		return codigoAluno;
	}
	public void setCodigoAluno(Long codigoAluno) {
		this.codigoAluno = codigoAluno;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ChamadaPesquisaDTO [codigoProfessor=" + codigoProfessor + ", codigoAluno=" + codigoAluno + ", status="
				+ status + ", codigoDisciplinaTurma=" + codigoDisciplinaTurma + ", dataIni=" + dataIni + ", dataFim="
				+ dataFim + "]";
	}
	
	
	
		
	

}
