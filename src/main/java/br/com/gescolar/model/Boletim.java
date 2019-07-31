package br.com.gescolar.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="boletim")
public class Boletim implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_boletim")
	private Long idBoletim;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_disciplina_turma")
	private DisciplinaTurma disciplinaTurma;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_aluno")
	private Aluno aluno;
	
	@NotNull
	private Double nota;
	
	@NotNull
	@Column(name="bi_tri")
	private String bimestreTrimestre;
	
	
	
	public Long getIdBoletim() {
		return idBoletim;
	}
	public void setIdBoletim(Long idBoletim) {
		this.idBoletim = idBoletim;
	}
	public DisciplinaTurma getDisciplinaTurma() {
		return disciplinaTurma;
	}
	public void setDisciplinaTurma(DisciplinaTurma disciplinaTurma) {
		this.disciplinaTurma = disciplinaTurma;
	}
	public Aluno getAluno() {
		return aluno;
	}
	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}
	public Double getNota() {
		return nota;
	}
	public void setNota(Double nota) {
		this.nota = nota;
	}
	public String getBimestreTrimestre() {
		return bimestreTrimestre;
	}
	public void setBimestreTrimestre(String bimestreTrimestre) {
		this.bimestreTrimestre = bimestreTrimestre;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((idBoletim == null) ? 0 : idBoletim.hashCode());
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
		Boletim other = (Boletim) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (idBoletim == null) {
			if (other.idBoletim != null)
				return false;
		} else if (!idBoletim.equals(other.idBoletim))
			return false;
		return true;
	}
	
	
}
