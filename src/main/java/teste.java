import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class teste {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		String agencia = "0116"; 
		String posto= "05";
		String conta = "01671";
		SimpleDateFormat dateFormat = new SimpleDateFormat("YY");
		String ano = dateFormat.format(new Date());
		String byteStr = "2";
		String sequencial = "00002";
		
		String vlr = agencia + posto + conta + ano + byteStr + sequencial;
		
		System.out.println(vlr);
		
		
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
		
		//System.out.println(nossoNumero);
		
		
		
		
		
		
	}

}
