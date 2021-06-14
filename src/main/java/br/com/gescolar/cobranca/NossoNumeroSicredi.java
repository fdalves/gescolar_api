package br.com.gescolar.cobranca;

import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.gescolar.model.Cnab;

public class NossoNumeroSicredi {

	private static final String BYTESTR = "2";
	private static final String YY = "YY";
	
	
	private String nossoNumero;
	private String digitoNossoNumero;
	private String seuNumero;
	
	public String gerarNossoNumero(Cnab cnab) {
		String agencia = cnab.getAgencia();
		String posto = cnab.getPosto();
		String conta = cnab.getConta();
		SimpleDateFormat dateFormat = new SimpleDateFormat(YY);
		String ano = dateFormat.format(new Date());
		String byteStr = BYTESTR;
		int sequencial = cnab.getSeqNossoNumero();

		String sequencialString = getSeqString(sequencial, 5);
		String vlr = agencia + posto + conta + ano + byteStr + sequencialString;
		int soma = 0;

		for (int i = 0; i < vlr.length(); i++) {
			char x = vlr.charAt(i);
			int valor = Integer.parseInt(String.valueOf(x));
			int fator = 0;
			if (i == 0)
				fator = 4;
			if (i == 1)
				fator = 3;
			if (i == 2)
				fator = 2;
			if (i == 3)
				fator = 9;
			if (i == 4)
				fator = 8;
			if (i == 5)
				fator = 7;
			if (i == 6)
				fator = 6;
			if (i == 7)
				fator = 5;
			if (i == 8)
				fator = 4;
			if (i == 9)
				fator = 3;
			if (i == 10)
				fator = 2;
			if (i == 11)
				fator = 9;
			if (i == 12)
				fator = 8;
			if (i == 13)
				fator = 7;
			if (i == 14)
				fator = 6;
			if (i == 15)
				fator = 5;
			if (i == 16)
				fator = 4;
			if (i == 17)
				fator = 3;
			if (i == 18)
				fator = 2;

			soma = soma + (valor * fator);
		}

		double mod11 = (soma / 11d) * 11d;
		Double result = 11 - (soma - mod11);

		if (result > 9)
			result = 0d;
		
		this.nossoNumero = vlr.substring(11, 19);
		this.digitoNossoNumero = String.valueOf(result.intValue());
		return nossoNumero + digitoNossoNumero;
	}

	private static String getSeqString(int seq, int quant) {
		String seqStr = String.valueOf(seq);
		if (seqStr.length() < quant) {
			int i = quant - seqStr.length();
			StringBuilder zeros = new StringBuilder();
			for (int j = 0; j < i; j++) {
				zeros.append("0");
			}
			seqStr = zeros.toString() + seqStr;
		}
		return seqStr;
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
	
	
	
	

}
