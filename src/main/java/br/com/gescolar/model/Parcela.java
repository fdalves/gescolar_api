package br.com.gescolar.model;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.gescolar.dto.BoletoDTO;
import br.com.gescolar.types.StatusParcelaEnum;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
	@Enumerated(EnumType.STRING)
	private StatusParcelaEnum  status;
	
	@Column(name="nr_parcela")
	private Integer nrParcela;

	@Column(name="valor")
	private Double valor;
	
	@Column(name="valor_juros")
	private Double valorJuros;
	
	@Column(name="nosso_numero")
	private String nossoNumero;
	
	@Column(name="digito_nosso_numero")
	private String digitoNossoNumero;
	
	@Column(name="seu_numero")
	private String seuNumero;
	
	@Column(name="nome_boleto")
	private String nomeBoleto;

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

	public StatusParcelaEnum getStatus() {
		return status;
	}

	public void setStatus(StatusParcelaEnum status) {
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

	public String getNossoNumero() {
		return nossoNumero;
	}

	public void setNossoNumero(String nossoNumero) {
		this.nossoNumero = nossoNumero;
	}

	public String getDigitoNossoNumero() {
		return digitoNossoNumero;
	}

	public void setDigitoNossoNumero(String digitoNossoNumero) {
		this.digitoNossoNumero = digitoNossoNumero;
	}

	public String getSeuNumero() {
		return seuNumero;
	}

	public void setSeuNumero(String seuNumero) {
		this.seuNumero = seuNumero;
	}

	public String getNomeBoleto() {
		return nomeBoleto;
	}

	public void setNomeBoleto(String nomeBoleto) {
		this.nomeBoleto = nomeBoleto;
	}

	
	
	public static BoletoDTO parseBoletoDTO(Parcela parcela) {
		BoletoDTO boletoDTO = new BoletoDTO();
		boletoDTO.setNome(parcela.getNomeBoleto());
		boletoDTO.setCodigo(parcela.getCodigo());
		boletoDTO.setStatus(parcela.getStatus().name());
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		boletoDTO.setData(formatters.format(parcela.getDataEmisao()));
		return boletoDTO;
	}
	
}
