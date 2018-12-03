package br.com.gescolar.dto;

import java.io.Serializable;

public class DisciplinaTurmaDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long codigo;
	private Integer quantPeriodosSemana;
	private Long codigoTurma;
	private Long codigoProfessor;
	private String nomeProfessor;
	private String nomeDisciplina;
	private Long codigoDisciplina;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Integer getQuantPeriodosSemana() {
		return quantPeriodosSemana;
	}
	public void setQuantPeriodosSemana(Integer quantPeriodosSemana) {
		this.quantPeriodosSemana = quantPeriodosSemana;
	}
	public Long getCodigoTurma() {
		return codigoTurma;
	}
	public void setCodigoTurma(Long codigoTurma) {
		this.codigoTurma = codigoTurma;
	}
	public Long getCodigoProfessor() {
		return codigoProfessor;
	}
	public void setCodigoProfessor(Long codigoProfessor) {
		this.codigoProfessor = codigoProfessor;
	}
	public Long getCodigoDisciplina() {
		return codigoDisciplina;
	}
	public void setCodigoDisciplina(Long codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}
	public String getNomeProfessor() {
		return nomeProfessor;
	}
	public void setNomeProfessor(String nomeProfessor) {
		this.nomeProfessor = nomeProfessor;
	}
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	
	
	
	

}
