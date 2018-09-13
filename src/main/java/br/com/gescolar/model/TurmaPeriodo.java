package br.com.gescolar.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.gescolar.types.DiaEnum;
import br.com.gescolar.types.PeriodoEnum;

@Entity
@Table(name = "TURMA_PERIODO")
public class TurmaPeriodo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo")
	private Long codigo;

	@Enumerated(EnumType.STRING)
	private DiaEnum dia;

	@Enumerated(EnumType.STRING)
	private PeriodoEnum periodo;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "turma_codigo")
	private Turma turma;

	@Column(name = "codigo_discipliana_turma", nullable = true)
	private Long codigoDisciplinaTurma;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public DiaEnum getDia() {
		return dia;
	}

	public void setDia(DiaEnum dia) {
		this.dia = dia;
	}

	public PeriodoEnum getPeriodo() {
		return periodo;
	}

	public void setPeriodo(PeriodoEnum periodo) {
		this.periodo = periodo;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public Long getCodigoDisciplinaTurma() {
		return codigoDisciplinaTurma;
	}

	public void setCodigoDisciplinaTurma(Long codigoDisciplinaTurma) {
		this.codigoDisciplinaTurma = codigoDisciplinaTurma;
	}

}
