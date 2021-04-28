package br.com.gescolar.cobranca;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gescolar.model.Cnab;
import br.com.gescolar.repository.CnabRepository;

@Service
public class ArquivoCnab240Sicredi extends ArquivoCnab {

	private static final String CNAB240_SICREDI_XML = "cnab240Sicredi.xml";
	
	@Autowired
	private CnabRepository cnabRepository;
	
	private Cnab cnab;
	
	public ArquivoCnab240Sicredi() {
		super();
	}
	
	
	@Override
	public String getTemplate()  {
		return CNAB240_SICREDI_XML;
	}


	@Override
	public void carregarArquivo() {
		
		Optional<Cnab> cOptional = cnabRepository.findById(1L);
		if(cOptional.isEmpty()) return;
		cnab = cOptional.get();
		
		Linha linhaHeader = super.gerarLinha(TipoLinhaEnum.HEADER);
		super.setValor(linhaHeader, 5, cnab.getTipoPessoa());
		super.setValor(linhaHeader, 6, cnab.getCpfCnpj());
		super.setValor(linhaHeader, 8, cnab.getAgencia());
		super.setValor(linhaHeader, 10, cnab.getConta());
		super.setValor(linhaHeader, 11, cnab.getDigitoConta());
		super.setValor(linhaHeader, 13, cnab.getNome());
		super.setValor(linhaHeader, 17, super.getDataGeracao());
		super.setValor(linhaHeader, 18, super.getHoraGeracao());
		super.setValor(linhaHeader, 19, this.getNumeroSequencialHeader());
		
		Linha linhaHeaderLote = super.gerarLinha(TipoLinhaEnum.HEADER_LOTE);
		super.setValor(linhaHeaderLote, 9, cnab.getTipoPessoa());
		super.setValor(linhaHeaderLote, 12, cnab.getAgencia());
		super.setValor(linhaHeaderLote, 14, cnab.getConta());
		super.setValor(linhaHeaderLote, 15, cnab.getDigitoConta());
		super.setValor(linhaHeaderLote, 17, cnab.getNome());
		super.setValor(linhaHeaderLote, 20, this.getNumeroSequencialLoteHeader());
		super.setValor(linhaHeaderLote, 21, this.getDataGeracao());
		
		Linha linhaSeguimentoP = super.gerarLinha(TipoLinhaEnum.DETALHE_SEGMENTO_P);
		super.setValor(linhaSeguimentoP, 4, getNumeroSequencialSegP());
		super.setValor(linhaSeguimentoP, 8, cnab.getAgencia());
		super.setValor(linhaSeguimentoP, 10, cnab.getConta());
		super.setValor(linhaSeguimentoP, 11, cnab.getDigitoConta());
		super.setValor(linhaSeguimentoP, 13, getNossoNumero(cnab));
		super.setValor(linhaSeguimentoP, 19, getSeqString(cnab.getSeqSeuNumero(),5));
		super.setValor(linhaSeguimentoP, 20, getVencimento());
		super.setValor(linhaSeguimentoP, 21, getvalor());
		super.setValor(linhaSeguimentoP, 26, getDataEmissao());
		super.setValor(linhaSeguimentoP, 27, getTipoJuros());
		super.setValor(linhaSeguimentoP, 28, getDataJuros());
		super.setValor(linhaSeguimentoP, 29, getJuros());
		
		Linha linhaSeguimentoQ = super.gerarLinha(TipoLinhaEnum.DETALHE_SEGMENTO_Q);
		super.setValor(linhaSeguimentoQ, 4, getNumeroSequencialSegP());
		super.setValor(linhaSeguimentoQ, 8, cnab.getTipoPessoa());
		super.setValor(linhaSeguimentoQ, 9, cnab.getCpfCnpj());
		super.setValor(linhaSeguimentoQ, 10, cnab.getNome());
		super.setValor(linhaSeguimentoQ, 11, cnab.getEndereco());
		super.setValor(linhaSeguimentoQ, 13, cnab.getCep());
		super.setValor(linhaSeguimentoQ, 15, cnab.getCidade());
		super.setValor(linhaSeguimentoQ, 16, cnab.getUf());
		
		Linha linhaTraillerLote = super.gerarLinha(TipoLinhaEnum.TRAILLER_DO_LOTE);
		super.setValor(linhaTraillerLote, 5, getTotalTraillerLote());
		
		Linha linhaTraillerArquivo = super.gerarLinha(TipoLinhaEnum.TRAILLER_DO_ARQUIVO);
		super.setValor(linhaTraillerArquivo, 6, getTotalTraillerArquivo());
		
		
	}

	
	private String getTotalTraillerArquivo() {
		return "000001";
	}



	private String getTotalTraillerLote() {
		return "000001";
	}



	private String getJuros() {
		// TODO Auto-generated method stub
		return "000000";
	}



	private String getDataJuros() {
		// TODO Auto-generated method stub
		return " ";
	}



	private String getTipoJuros() {
		// TODO Auto-generated method stub
		return " ";
	}



	private String getDataEmissao() {
		// TODO Auto-generated method stub
		return " ";
	}



	private String getvalor() {
		// TODO Auto-generated method stub
		return " ";
	}



	private String getVencimento() {
		// TODO Auto-generated method stub
		return " ";
	}



	private String getSeuNumero() {
		// TODO Auto-generated method stub
		return "0123456789";
	}



	private String getNossoNumero(Cnab cnab) {
		
		String agencia = cnab.getAgencia(); 
		String posto= cnab.getPosto();
		String conta = cnab.getConta();
		SimpleDateFormat dateFormat = new SimpleDateFormat("YY");
		String ano = dateFormat.format(new Date());
		String byteStr = "2";
		int sequencial = cnab.getSeqNossoNumero();
		
		String sequencialString = getSeqString(sequencial, 5);
		
		String vlr = agencia + posto + conta + ano + byteStr + sequencialString;
		
		int soma = 0;  
		
		for (int i = 0; i < vlr.length() ; i++) {
			 char x  = vlr.charAt(i);
			 int valor = Integer.parseInt(String.valueOf(x));
			 int fator = 0;
			 if (i == 0) fator = 4;
			 if (i == 1) fator = 3;
			 if (i == 2) fator = 2;
			 if (i == 3) fator = 9;
			 if (i == 4) fator = 8;
			 if (i == 5) fator = 7;
			 if (i == 6) fator = 6;
			 if (i == 7) fator = 5;
			 if (i == 8) fator = 4;
			 if (i == 9) fator = 3;
			 if (i == 10) fator = 2;
			 if (i == 11) fator = 9;
			 if (i == 12) fator = 8;
			 if (i == 13) fator = 7;
			 if (i == 14) fator = 6;
			 if (i == 15) fator = 5;
			 if (i == 16) fator = 4;
			 if (i == 17) fator = 3;
			 if (i == 18) fator = 2;
			 
			 soma = soma + (valor * fator);
		}
		
		double mod11 =  (soma / 11d) * 11d;
		Double result = 11 - (soma - mod11); 
		
		if (result > 9) result = 0d;
		
		String nossoNumero = vlr.substring(11,19);
		nossoNumero = nossoNumero + result.intValue();
		
		return nossoNumero;
	}



	private String getSeqString(int seq, int quant) {
		
		String seqStr  = String.valueOf(seq);
		
		if (seqStr.length() < quant) {
			
			int  i = quant - seqStr.length();
			StringBuilder zeros =  new StringBuilder();
			
			for (int j = 0; j < i; j++) {
				zeros.append("0");
			}
			
			seqStr  = zeros.toString() + seqStr;
			
		}
		
		return seqStr;
	}


	private String getNumeroSequencialSegP() {
		return "00001";
	}

	//TODO
	private String getNumeroSequencialHeader() {
		return "000001";
	}

	private String getNumeroSequencialLoteHeader() {
		return "00000001";
	}



	public CnabRepository getCnabRepository() {
		return cnabRepository;
	}



	public void setCnabRepository(CnabRepository cnabRepository) {
		this.cnabRepository = cnabRepository;
	}



	public Cnab getCnab() {
		return cnab;
	}



	public void setCnab(Cnab cnab) {
		this.cnab = cnab;
	}



	
	

}
