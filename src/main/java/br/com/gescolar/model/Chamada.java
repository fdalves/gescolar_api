package br.com.gescolar.model;


import java.io.Serializable;
import java.util.Date;

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
@Table(name="CHAMADA")
public class Chamada implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_chamada")
	private Long idChamada;
	
	@NotNull
	private Boolean presenca;
	
	@NotNull
	private Date data;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_disciplina_turma")
	private DisciplinaTurma disciplinaTurma;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "id_aluno")
	private Aluno aluno;
	
	public Long getIdChamada() {
		return idChamada;
	}
	public void setIdChamada(Long idChamada) {
		this.idChamada = idChamada;
	}
	public Boolean getPresenca() {
		return presenca;
	}
	public void setPresenca(Boolean presenca) {
		this.presenca = presenca;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aluno == null) ? 0 : aluno.hashCode());
		result = prime * result + ((idChamada == null) ? 0 : idChamada.hashCode());
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
		Chamada other = (Chamada) obj;
		if (aluno == null) {
			if (other.aluno != null)
				return false;
		} else if (!aluno.equals(other.aluno))
			return false;
		if (idChamada == null) {
			if (other.idChamada != null)
				return false;
		} else if (!idChamada.equals(other.idChamada))
			return false;
		return true;
	}
	
	
}
