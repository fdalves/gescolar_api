package br.com.gescolar.cobranca;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.Exemplos;
import org.jrimum.bopepo.parametro.ParametroBancoSicredi;
import org.jrimum.domkee.financeiro.banco.ParametrosBancariosMap;
import org.jrimum.domkee.financeiro.banco.febraban.Agencia;
import org.jrimum.domkee.financeiro.banco.febraban.Carteira;
import org.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import org.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import org.jrimum.domkee.financeiro.banco.febraban.TipoDeCobranca;
import org.jrimum.domkee.financeiro.banco.febraban.Titulo;

public class Teste {

	public static void main(String[] args) {
		Titulo titulo = Exemplos.crieTitulo();
		
		/*
		 * Campos específicos para o Banco Sicredi.
		 */
		
		ContaBancaria contaBancaria = titulo.getContaBancaria();
		
		/*
		 * Banco Sicredi 748
		 */
		contaBancaria.setBanco(BancosSuportados.BANCO_SICREDI.create());
		
		contaBancaria.setAgencia(new Agencia(116));
		contaBancaria.setNumeroDaConta(new NumeroDaConta(1671));
		contaBancaria.setCarteira(new Carteira(1, TipoDeCobranca.SEM_REGISTRO));

		titulo.setNossoNumero("07200003");
		titulo.setDigitoDoNossoNumero("1");

		titulo.setParametrosBancarios(new ParametrosBancariosMap(ParametroBancoSicredi.POSTO_DA_AGENCIA, 5));
		
		Boleto boleto = Exemplos.crieBoleto(titulo);
		
		Exemplos.execute(boleto);
	}

}
