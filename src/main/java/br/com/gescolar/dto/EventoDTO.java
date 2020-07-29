package br.com.gescolar.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String dataIni;
	private String dataFim;
	private List<LocalDateTime> datasNotificar;
	private String descEvento;
	private List<Long> professoresSelecionados;
	private List<Long> turmasSelecionados;
	private String selectedOpcao;
	private Long codigoUsuario;
	
	
	
	public List<LocalDateTime> getDatasNotificar() {
		return datasNotificar;
	}
	public void setDatasNotificar(List<LocalDateTime> datasNotificar) {
		this.datasNotificar = datasNotificar;
	}
	public String getDescEvento() {
		return descEvento;
	}
	public void setDescEvento(String descEvento) {
		this.descEvento = descEvento;
	}
	public List<Long> getProfessoresSelecionados() {
		return professoresSelecionados;
	}
	public void setProfessoresSelecionados(List<Long> professoresSelecionados) {
		this.professoresSelecionados = professoresSelecionados;
	}
	public List<Long> getTurmasSelecionados() {
		return turmasSelecionados;
	}
	public void setTurmasSelecionados(List<Long> turmasSelecionados) {
		this.turmasSelecionados = turmasSelecionados;
	}
	public String getSelectedOpcao() {
		return selectedOpcao;
	}
	public void setSelectedOpcao(String selectedOpcao) {
		this.selectedOpcao = selectedOpcao;
	}
	
	public String getDataIni() {
		return dataIni;
	}
	public void setDataIni(String dataIni) {
		this.dataIni = dataIni;
	}
	public String getDataFim() {
		return dataFim;
	}
	public void setDataFim(String dataFim) {
		this.dataFim = dataFim;
	}
	
	
	public Long getCodigoUsuario() {
		return codigoUsuario;
	}
	public void setCodigoUsuario(Long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}
	@Override
	public String toString() {
		return "EventoDTO [dataIni=" + dataIni + ", dataFim=" + dataFim + ", datasNotificar=" + datasNotificar
				+ ", descEvento=" + descEvento + ", professoresSelecionados=" + professoresSelecionados
				+ ", turmasSelecionados=" + turmasSelecionados + ", selectedOpcao=" + selectedOpcao + ", codigoUsuario="
				+ codigoUsuario + "]";
	}
	
	
	
	
	

	
	
	
	
}
