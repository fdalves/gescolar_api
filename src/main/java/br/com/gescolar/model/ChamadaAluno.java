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
@Table(name="chamada_aluno")
public class ChamadaAluno implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo")
	private Long codigo;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_chamada")
	private Chamada chamada;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_aluno")
	private Aluno aluno;
	
	@NotNull
	private Boolean presenca;
	
	private Boolean notificado;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Chamada getChamada() {
		return chamada;
	}

	public void setChamada(Chamada chamada) {
		this.chamada = chamada;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Boolean getPresenca() {
		return presenca;
	}

	public void setPresenca(Boolean presenca) {
		this.presenca = presenca;
	}

	public Boolean getNotificado() {
		return notificado;
	}

	public void setNotificado(Boolean notificado) {
		this.notificado = notificado;
	}
	
	
}
