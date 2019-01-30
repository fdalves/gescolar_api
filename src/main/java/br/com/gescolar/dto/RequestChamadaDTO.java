package br.com.gescolar.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
@JsonAutoDetect
public class RequestChamadaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date data;
	private Long codigoTurmaDiciplina;
	
	
	
	public Long getCodigoTurmaDiciplina() {
		return codigoTurmaDiciplina;
	}
	public void setCodigoTurmaDiciplina(Long codigoTurmaDiciplina) {
		this.codigoTurmaDiciplina = codigoTurmaDiciplina;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoTurmaDiciplina == null) ? 0 : codigoTurmaDiciplina.hashCode());
		result = prime * result + ((data == null) ? 0 : data.hashCode());
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
		RequestChamadaDTO other = (RequestChamadaDTO) obj;
		if (codigoTurmaDiciplina == null) {
			if (other.codigoTurmaDiciplina != null)
				return false;
		} else if (!codigoTurmaDiciplina.equals(other.codigoTurmaDiciplina))
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}
	
	
}
