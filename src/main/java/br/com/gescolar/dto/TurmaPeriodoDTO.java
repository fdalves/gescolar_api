package br.com.gescolar.dto;

import java.io.Serializable;

public class TurmaPeriodoDTO  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long codigo;
	private String dia;
	private String periodo;
	private DisciplinaTurmaDTO disciplinaTurma;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getDia() {
		return dia;
	}
	public void setDia(String dia) {
		this.dia = dia;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public DisciplinaTurmaDTO getDisciplinaTurma() {
		return disciplinaTurma;
	}
	public void setDisciplinaTurma(DisciplinaTurmaDTO disciplinaTurma) {
		this.disciplinaTurma = disciplinaTurma;
	}

	

	
}
