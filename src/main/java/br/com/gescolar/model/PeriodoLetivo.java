package br.com.gescolar.model;


import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="periodo_letivo")
public class PeriodoLetivo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo")
	private Long codigo;
	
	@NotNull
	@Column(name="data_ini")
	private LocalDate dataIni;
	@NotNull
	@Column(name="data_fim")
	private LocalDate dataFim;
	
	@NotNull
	private String situacao;
	@NotNull
	@Column(name="minutos_periodo")
	private Integer minPeriodo;
	
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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
	
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public Integer getMinPeriodo() {
		return minPeriodo;
	}
	public void setMinPeriodo(Integer minPeriodo) {
		this.minPeriodo = minPeriodo;
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
		PeriodoLetivo other = (PeriodoLetivo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
	
	
	
	
}
