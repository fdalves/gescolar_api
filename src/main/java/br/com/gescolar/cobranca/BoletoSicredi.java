package br.com.gescolar.cobranca;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.parametro.ParametroBancoSicredi;
import org.jrimum.bopepo.view.BoletoViewer;
import org.jrimum.domkee.comum.pessoa.endereco.Endereco;
import org.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import org.jrimum.domkee.financeiro.banco.ParametrosBancariosMap;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.Cedente;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.Sacado;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeCobranca;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo.Aceite;

public class BoletoSicredi {

	public static void main(String[] args) throws Exception {
		ContaBancaria contaBancaria = crieContaBancaria("116", "1671");
		Cedente cedente = crieCedente("Centro de Educacao Infantil MA","05854255000135", contaBancaria);
		Sacado sacado = crieSacado("Francisco Alves", "84021225072", "RS", "Gravataí", "94050010", "cohab a",
				"Jaraguá do sul", "131");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		Date dateVenc = new Date();
		
		date = dateFormat.parse("20/06/2021");
		dateVenc = dateFormat.parse("25/06/2021");
		
		Titulo titulo = crieTitulo(cedente, sacado, "00001", "21200001", "0", BigDecimal.valueOf(1100d), date, dateVenc, "5");
		Boleto boleto  = crieBoleto(titulo);
		execute(boleto);
	}

	public static Cedente crieCedente(String nome, String cnpj, ContaBancaria contaBancaria) {
		Cedente cedente = new Cedente(nome, cnpj);
		cedente.addContaBancaria(contaBancaria);
		return cedente;
	}

	public static ContaBancaria crieContaBancaria(String agencia, String conta) {
		ContaBancaria contaBancaria = new ContaBancaria();
		contaBancaria.setBanco(BancosSuportados.BANCO_SICREDI.create());
		contaBancaria.setAgencia(new Agencia(Integer.valueOf(agencia)));
		contaBancaria.setNumeroDaConta(new NumeroDaConta(Integer.valueOf(conta)));
		contaBancaria.setCarteira(new Carteira(1, TipoDeCobranca.COM_REGISTRO));
		return contaBancaria;
	}

	public static Sacado crieSacado(String nome, String cpf, String uf, String cidade, String cep, String bairo,
			String rua, String numero) {
		Sacado sacado = new Sacado(nome, cpf);
		Endereco endereco = new Endereco();
		endereco.setUF(UnidadeFederativa.valueOf(uf));
		endereco.setLocalidade(cidade);
		endereco.setCep(cep);
		endereco.setBairro(bairo);
		endereco.setLogradouro(rua);
		endereco.setNumero(numero);
		sacado.addEndereco(endereco);
		return sacado;
	}

	public static Titulo crieTitulo(Cedente cedente, Sacado sacado, String numeroDodocumento, String nossoNumero, String digitoNossoNumero, BigDecimal valor, Date dataDoc, Date dataVen, String posto) {
		ContaBancaria contaBancariaDoCedente = cedente.getContasBancarias().iterator().next();
		Titulo titulo = null;
		titulo = new Titulo(contaBancariaDoCedente, sacado, cedente);
		titulo.setNumeroDoDocumento(numeroDodocumento);
		titulo.setNossoNumero(nossoNumero);
		//titulo.setMora(BigDecimal.valueOf(2d));
		titulo.setDigitoDoNossoNumero(digitoNossoNumero);
		titulo.setValor(valor);
		titulo.setDataDoDocumento(dataDoc);
		titulo.setDataDoVencimento(dataVen);
		titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
		titulo.setAceite(Aceite.A);
		titulo.setParametrosBancarios(new ParametrosBancariosMap(ParametroBancoSicredi.POSTO_DA_AGENCIA, Integer.valueOf(posto)));
		return titulo;
	}
	
	public static Boleto crieBoleto(Titulo titulo) {
		Boleto boleto = new Boleto(titulo);
		boleto.setLocalPagamento("Pagável preferencialmente em canais da sua instituição financeira");
		boleto.setInstrucao1("Após vencimento cobrar multa de 2%.");
		return boleto;
	}
	
	
	private static void execute(Boleto boleto) {
		BoletoViewer viewer = new BoletoViewer(boleto);
		File boletoPDF = viewer.getPdfAsFile("BOLETO_" + boleto.getClass().getSimpleName().toUpperCase() + ".PDF");
		mostreBoletoNaTela(boletoPDF);
	}
	
	public static void mostreBoletoNaTela(File arquivoBoleto) {
		
		try {
			 java.awt.Desktop.getDesktop().open(arquivoBoleto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
