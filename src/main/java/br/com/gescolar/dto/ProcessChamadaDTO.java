package br.com.gescolar.dto;

import java.io.Serializable;
import java.util.Date;

public class ProcessChamadaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nomeAluno;
	private String nomeDisciplina;
	private Date dataChamada;
	private Long codigoAluno;
	private Boolean presenca;
	
	
	
	
	public ProcessChamadaDTO(String nomeAluno, String nomeDisciplina, Date dataChamada, Long codigoAluno,
			Boolean presenca) {
		super();
		this.nomeAluno = nomeAluno;
		this.nomeDisciplina = nomeDisciplina;
		this.dataChamada = dataChamada;
		this.codigoAluno = codigoAluno;
		this.presenca = presenca;
	}
	
	
	public String getNomeAluno() {
		return nomeAluno;
	}
	public void setNomeAluno(String nomeAluno) {
		this.nomeAluno = nomeAluno;
	}
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}
	public Date getDataChamada() {
		return dataChamada;
	}
	public void setDataChamada(Date dataChamada) {
		this.dataChamada = dataChamada;
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


	@Override
	public String toString() {
		return "ProcessChamadaDTO [nomeAluno=" + nomeAluno + ", nomeDisciplina=" + nomeDisciplina + ", dataChamada="
				+ dataChamada + ", codigoAluno=" + codigoAluno + ", presenca=" + presenca + "]";
	}
	
	
	
	
}
