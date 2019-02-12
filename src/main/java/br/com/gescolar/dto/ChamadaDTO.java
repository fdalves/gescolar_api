package br.com.gescolar.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
@JsonAutoDetect
public class ChamadaDTO implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private Long codigo;
	private Long codigoTurmaPeriodo;
	private Long codigoAluno;
	private Boolean presenca;
	private Date date;
	private List<String> alunosPresentes;
	private List<Long> periodosSelecionados;
	private LocalDate dateChamada;
	
	
	
	
	public LocalDate getDateChamada() {
		return dateChamada;
	}
	public void setDateChamada(LocalDate dateChamada) {
		this.dateChamada = dateChamada;
	}
	public List<String> getAlunosPresentes() {
		return alunosPresentes;
	}
	public void setAlunosPresentes(List<String> alunosPresentes) {
		this.alunosPresentes = alunosPresentes;
	}
	
	public List<Long> getPeriodosSelecionados() {
		return periodosSelecionados;
	}
	public void setPeriodosSelecionados(List<Long> periodosSelecionados) {
		this.periodosSelecionados = periodosSelecionados;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Long getCodigoTurmaPeriodo() {
		return codigoTurmaPeriodo;
	}
	public void setCodigoTurmaPeriodo(Long codigoTurmaPeriodo) {
		this.codigoTurmaPeriodo = codigoTurmaPeriodo;
	}
	public Long getCodigoAluno() {
		return codigoAluno;
	}
	public void setCodigoAluno(Long codigoAluno) {
		this.codigoAluno = codigoAluno;
	}
	public Boolean getPresenca() {
		return presenca;
	}
	public void setPresenca(Boolean presenca) {
		this.presenca = presenca;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
		ChamadaDTO other = (ChamadaDTO) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ChamadaDTO [codigo=" + codigo + ", codigoTurmaPeriodo=" + codigoTurmaPeriodo + ", codigoAluno="
				+ codigoAluno + ", presenca=" + presenca + ", date=" + date + "]";
	}
	
	

}
