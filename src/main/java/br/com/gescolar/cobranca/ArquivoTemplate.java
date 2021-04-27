package br.com.gescolar.cobranca;

public interface ArquivoTemplate {
	
	String getTemplate();
	void carregarArquivo();
	Linha gerarLinha(TipoLinhaEnum tipoLinhaEnum);
	void setValor(Linha linha, Integer posicao, String Valor);
	void printFile() throws Exception;
}
