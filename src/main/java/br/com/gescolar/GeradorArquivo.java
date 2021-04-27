package br.com.gescolar;

import java.io.File;
import java.io.IOException;

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
		
		GeradorArquivo arquivo = new GeradorArquivo();
		
		arquivo.gerarArquvivo();

	}

}
