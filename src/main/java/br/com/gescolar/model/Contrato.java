package br.com.gescolar.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.gescolar.repository.listener.UrlFotoListener;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(UrlFotoListener.class)
@Entity
@Table(name="contrato")
public class Contrato  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo")
	private Long codigo;
	
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "codigo_matricula")
	private MatriculaIni matricula;
	
	@Column(name="data_ini")
	@Temporal(TemporalType.DATE)
	private Date dataIni;
	
	@Column(name="data_fim")
	@Temporal(TemporalType.DATE)
	private Date dataFim;
	
	@Column(name="ativo")
	private Boolean ativo;
	
	@Column(name="nr_parcela")
	private Integer nrParcela;
	
	@Column(name="nr_dia_pagamento")
	private Integer nrDiaPagamento;
	
	@Column(name="nr_dia_vencimento")
	private Integer nrDiaVencimento;
	
	@Column(name="valor")
	private Double valor;
	
	@Column(name="valor_juros")
	private Double valorJuros;
	
	@Column(name="contrato")
	private byte[] contratoPdf;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public MatriculaIni getMatricula() {
		return matricula;
	}

	public void setMatricula(MatriculaIni matricula) {
		this.matricula = matricula;
	}

	public Date getDataIni() {
		return dataIni;
	}

	public void setDataIni(Date dataIni) {
		this.dataIni = dataIni;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public Integer getNrParcela() {
		return nrParcela;
	}

	public void setNrParcela(Integer nrParcela) {
		this.nrParcela = nrParcela;
	}

	public Integer getNrDiaPagamento() {
		return nrDiaPagamento;
	}

	public void setNrDiaPagamento(Integer nrDiaPagamento) {
		this.nrDiaPagamento = nrDiaPagamento;
	}

	public Integer getNrDiaVencimento() {
		return nrDiaVencimento;
	}

	public void setNrDiaVencimento(Integer nrDiaVencimento) {
		this.nrDiaVencimento = nrDiaVencimento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getValorJuros() {
		return valorJuros;
	}

	public void setValorJuros(Double valorJuros) {
		this.valorJuros = valorJuros;
	}

	public byte[] getContratoPdf() {
		return contratoPdf;
	}

	public void setContratoPdf(byte[] contratoPdf) {
		this.contratoPdf = contratoPdf;
	}

	
	
	
}
