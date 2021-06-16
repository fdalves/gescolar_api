package br.com.gescolar;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.gescolar.model.Cnab;

public class GeradorArquivo {

	private String codigoBeneficiario = "01671";
	
	private String  getdateMdd() {
		return "314";

	}
	
	private String  getSeq() {
		return "001";

	}
	
	
	public void gerarArquvivo() throws IOException {
		String fileName = codigoBeneficiario + getdateMdd() + "." + getSeq();
		File file = new File(fileName);
		file.createNewFile();
		
	}
	
	
	
	
	public static void main(String[] args) throws IOException {
		
			String vlr = "0116050167121200028";
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

			Double r1 = (soma / 11d);
			int r2 = r1.intValue() * 11;
			int r3 =  soma - r2;
			int result = 11 - r3;

			if (result > 9)
				result = 0;
			
			String  nossoNumero = vlr.substring(11, 19);
			String digitoNossoNumero = String.valueOf(result);
			System.out.println(nossoNumero + digitoNossoNumero);
		}
		
	

}
