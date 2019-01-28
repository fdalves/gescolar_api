package br.com.gescolar.dto;

import java.io.Serializable;

public class DisciplinaTurmaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long codigo;
	private Integer quantPeriodosSemana;
	private Long codigoTurma;
	private String nomeTurma;
	private Long codigoProfessor;
	private String nomeProfessor;
	private String nomeDisciplina;
	private Long codigoDisciplina;
	private Long codigoTurmaPeriodo;
	private String turmaDisciplina;
	
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
	public Long getCodigoTurmaPeriodo() {
		return codigoTurmaPeriodo;
	}
	public void setCodigoTurmaPeriodo(Long codigoTurmaPeriodo) {
		this.codigoTurmaPeriodo = codigoTurmaPeriodo;
	}
	public String getNomeTurma() {
		return nomeTurma;
	}
	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}
	public String getTurmaDisciplina() {
		return turmaDisciplina;
	}
	public void setTurmaDisciplina(String turmaDisciplina) {
		this.turmaDisciplina = turmaDisciplina;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DisciplinaTurmaDTO other = (DisciplinaTurmaDTO) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DisciplinaTurmaDTO [codigo=" + codigo + ", quantPeriodosSemana=" + quantPeriodosSemana
				+ ", codigoTurma=" + codigoTurma + ", nomeTurma=" + nomeTurma + ", codigoProfessor=" + codigoProfessor
				+ ", nomeProfessor=" + nomeProfessor + ", nomeDisciplina=" + nomeDisciplina + ", codigoDisciplina="
				+ codigoDisciplina + ", codigoTurmaPeriodo=" + codigoTurmaPeriodo + ", turmaDisciplina="
				+ turmaDisciplina + "]";
	}
	
	
}
