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
	@Column(name="codigo")
	private Long codigo;
	
	@NotNull
	@Column(name="data_chamada")
	private Date dataChamada;
	
	@NotNull
	@Column(name="data_inclusao")
	private Date dataInclusao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_tuma_periodo")
	private TurmaPeriodo turmaPeriodo;
	
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


	public Date getDataChamada() {
		return dataChamada;
	}

	public void setDataChamada(Date dataChamada) {
		this.dataChamada = dataChamada;
	}

	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	public TurmaPeriodo getTurmaPeriodo() {
		return turmaPeriodo;
	}

	public void setTurmaPeriodo(TurmaPeriodo turmaPeriodo) {
		this.turmaPeriodo = turmaPeriodo;
	}

}
