package br.com.gescolar.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cnab")
public class Cnab  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo")
	private Long codigo;
	
	@Column(name="tipo_pessoa")
	private String tipoPessoa;
	
	@Column(name="cpf_cnpj")
	private String cpfCnpj;
	
	@Column(name="agencia")
	private String agencia;
	
	@Column(name="conta")
	private String conta;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="endereco")
	private String endereco;
	
	@Column(name="cep")
	private String cep;
	
	@Column(name="cidade")
	private String cidade;
	
	@Column(name="uf")
	private String uf;
	
	@Column(name="digito_conta")
	private String digitoConta;
	
	@Column(name="posto")
	private String posto;
	
	@Column(name="seq_nosso_numero")
	private Integer seqNossoNumero;
	
	@Column(name="seq_seu_numero")
	private Integer seqSeuNumero;
	
	@Column(name="local_pagamento_boleto")
	private String localPagamentoBoleto;
	
	@Column(name="instrucao_boleto")
	private String instrucaoBoleto;
	
	

	public String getLocalPagamentoBoleto() {
		return localPagamentoBoleto;
	}

	public void setLocalPagamentoBoleto(String localPagamentoBoleto) {
		this.localPagamentoBoleto = localPagamentoBoleto;
	}

	public String getInstrucaoBoleto() {
		return instrucaoBoleto;
	}

	public void setInstrucaoBoleto(String instrucaoBoleto) {
		this.instrucaoBoleto = instrucaoBoleto;
	}

	public String getPosto() {
		return posto;
	}

	public void setPosto(String posto) {
		this.posto = posto;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getConta() {
		return conta;
	}

	public void setConta(String conta) {
		this.conta = conta;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Integer getSeqNossoNumero() {
		return seqNossoNumero;
	}

	public void setSeqNossoNumero(Integer seqNossoNumero) {
		this.seqNossoNumero = seqNossoNumero;
	}

	public Integer getSeqSeuNumero() {
		return seqSeuNumero;
	}

	public void setSeqSeuNumero(Integer seqSeuNumero) {
		this.seqSeuNumero = seqSeuNumero;
	}

	public String getDigitoConta() {
		return digitoConta;
	}

	public void setDigitoConta(String digitoConta) {
		this.digitoConta = digitoConta;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
}
