package br.com.gescolar.model;


import java.io.Serializable;
import java.time.LocalDate;
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
@Table(name="parcela")
public class Parcela  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo")
	private Long codigo;
	
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "codigo_contrato")
	private Contrato contrato;
	
	@Column(name="data_emisao", columnDefinition = "DATE")
	private LocalDate dataEmisao;
	
	@Column(name="data_vencimento", columnDefinition = "DATE")
	private LocalDate dataVencimento;
	
	@Column(name="status")
	private String  status;
	
	@Column(name="nr_parcela")
	private Integer nrParcela;

	@Column(name="valor")
	private Double valor;
	
	@Column(name="valor_juros")
	private Double valorJuros;
	
	@Column(name="boleto")
	private byte[] boleto;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}

	public LocalDate getDataEmisao() {
		return dataEmisao;
	}

	public void setDataEmisao(LocalDate dataEmisao) {
		this.dataEmisao = dataEmisao;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getNrParcela() {
		return nrParcela;
	}

	public void setNrParcela(Integer nrParcela) {
		this.nrParcela = nrParcela;
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

	public byte[] getBoleto() {
		return boleto;
	}

	public void setBoleto(byte[] boleto) {
		this.boleto = boleto;
	}

	
	
	
	
}
