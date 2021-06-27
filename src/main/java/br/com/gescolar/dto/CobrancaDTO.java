package br.com.gescolar.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.gescolar.types.StatusParcelaEnum;

public class CobrancaDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long codigo;
	private String fotoUrl;
	private String nome;
	private String nrParcela;
	private String dtVencimento;
	private String status;
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getFotoUrl() {
		return fotoUrl;
	}
	public void setFotoUrl(String fotoUrl) {
		this.fotoUrl = fotoUrl;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNrParcela() {
		return nrParcela;
	}
	public void setNrParcela(String nrParcela) {
		this.nrParcela = nrParcela;
	}
	public String getDtVencimento() {
		return dtVencimento;
	}
	public void setDtVencimento(String dtVencimento) {
		this.dtVencimento = dtVencimento;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public static List<CobrancaDTO> generateMook() {
		List<CobrancaDTO> list = new ArrayList<>();
		CobrancaDTO cobrancaDTO = new CobrancaDTO();
		cobrancaDTO.setNome("Caloteiro 1");
		cobrancaDTO.setFotoUrl("https://gescolar.s3-sa-east-1.amazonaws.com/39812a77-b94a-401e-8ccd-539905f3ff03_.jpg");
		cobrancaDTO.setNrParcela("3");
		cobrancaDTO.setDtVencimento("05/03/2021");
		cobrancaDTO.setStatus(StatusParcelaEnum.EM_ATRASO.toString());
		list.add(cobrancaDTO);
		
		CobrancaDTO cobrancaDTO2 = new CobrancaDTO();
		cobrancaDTO2.setNome("Caloteiro 22");
		cobrancaDTO2.setFotoUrl("https://gescolar.s3-sa-east-1.amazonaws.com/39812a77-b94a-401e-8ccd-539905f3ff03_.jpg");
		cobrancaDTO2.setNrParcela("4");
		cobrancaDTO2.setDtVencimento("05/04/2021");
		cobrancaDTO2.setStatus(StatusParcelaEnum.EM_ATRASO.toString());
		list.add(cobrancaDTO2);
		
		CobrancaDTO cobrancaDTO3 = new CobrancaDTO();
		cobrancaDTO3.setNome("Caloteiro 333");
		cobrancaDTO3.setFotoUrl("https://gescolar.s3-sa-east-1.amazonaws.com/39812a77-b94a-401e-8ccd-539905f3ff03_.jpg");
		cobrancaDTO3.setNrParcela("4");
		cobrancaDTO3.setDtVencimento("05/04/2021");
		cobrancaDTO3.setStatus(StatusParcelaEnum.EM_ATRASO.toString());
		list.add(cobrancaDTO3);
		
		return list;
		
	}
	
}
