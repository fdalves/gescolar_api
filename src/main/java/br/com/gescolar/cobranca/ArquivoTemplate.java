package br.com.gescolar.cobranca;

public interface ArquivoTemplate {
	
	String getTemplate();
	Linha gerarLinha(TipoLinhaEnum tipoLinhaEnum);
	void setValor(Linha linha, Integer posicao, String valor);
	String printFile(); 
}
