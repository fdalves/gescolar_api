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
		TurmaPeriodoDTO other = (TurmaPeriodoDTO) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TurmaPeriodoDTO [codigo=" + codigo + ", dia=" + dia + ", periodo=" + periodo + ", disciplinaTurma="
				+ disciplinaTurma + "]";
	}

	

	
}
