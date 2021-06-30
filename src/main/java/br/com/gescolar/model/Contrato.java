package br.com.gescolar.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.gescolar.dto.ContratoDTO;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
	
	@Column(name="data_ini", columnDefinition = "DATE")
	private LocalDate dataIni;
	
	@Column(name="data_fim", columnDefinition = "DATE")
	private LocalDate dataFim;
	
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
	
	
	@Column(name="arquivo_cnab")
	private String arquivoCnab;
	
	@Column(name="status_arquivo_cnab")
	private String statusArquivoCnab;
	
	@Column(name="nome_arquivo_cnab")
	private String nomeArquivoCnab;
	
	@Column(name="data_arquivo_cnab", columnDefinition = "DATE")
	private LocalDate dataArquivoCnab;

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

	public String getArquivoCnab() {
		return arquivoCnab;
	}

	public void setArquivoCnab(String arquivoCnab) {
		this.arquivoCnab = arquivoCnab;
	}

	public String getStatusArquivoCnab() {
		return statusArquivoCnab;
	}

	public void setStatusArquivoCnab(String statusArquivoCnab) {
		this.statusArquivoCnab = statusArquivoCnab;
	}

	public String getNomeArquivoCnab() {
		return nomeArquivoCnab;
	}

	public void setNomeArquivoCnab(String nomeArquivoCnab) {
		this.nomeArquivoCnab = nomeArquivoCnab;
	}

	public LocalDate getDataArquivoCnab() {
		return dataArquivoCnab;
	}

	public void setDataArquivoCnab(LocalDate dataArquivoCnab) {
		this.dataArquivoCnab = dataArquivoCnab;
	}

	public static ContratoDTO parseDto(Contrato contrato) {
		ContratoDTO contratoDTO = new ContratoDTO();
		contratoDTO.setNome(contrato.getMatricula().getNome());
		contratoDTO.setCodigo(contrato.getCodigo());
		contratoDTO.setNomeArquivo(contrato.getNomeArquivoCnab());
		contratoDTO.setStatus(contrato.getStatusArquivoCnab());
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		contratoDTO.setDate(formatters.format(contrato.getDataArquivoCnab()));
		return contratoDTO;
	} 
}
