package br.com.gescolar.cobranca;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import org.jrimum.bopepo.BancosSuportados;
import org.jrimum.bopepo.Boleto;
import org.jrimum.bopepo.parametro.ParametroBancoSicredi;
import org.jrimum.bopepo.view.BoletoViewer;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.gescolar.model.Cnab;
import br.com.gescolar.model.MatriculaIni;
import br.com.gescolar.model.Parcela;
import br.com.gescolar.repository.CnabRepository;
import br.com.gescolar.types.ResponsavelFinanceiroEnum;

@Component
public class BoletoSicredi {

	@Autowired
	private CnabRepository cnabRepository;
	private Cnab cnab;
	
	public BoletoSicredi() {
		super();
	}

	public byte[] gerarBoleto(Parcela parcela)  {
		Optional<Cnab> cOptional = cnabRepository.findById(1L);
		if(cOptional.isEmpty()) return new byte[0];
		cnab = cOptional.get();
		
		ContaBancaria contaBancaria = crieContaBancaria(this.cnab.getAgencia(), this.cnab.getConta());
		Cedente cedente = crieCedente(this.cnab.getNome(), this.cnab.getCpfCnpj() , contaBancaria);
		Sacado sacado = crieSacado(parcela.getContrato().getMatricula());
		Titulo titulo = crieTitulo(cedente, sacado, parcela);
		Boleto boleto  = crieBoleto(titulo);
		return execute(boleto);
	}

	private Cedente crieCedente(String nome, String cnpj, ContaBancaria contaBancaria) {
		Cedente cedente = new Cedente(nome, cnpj);
		cedente.addContaBancaria(contaBancaria);
		return cedente;
	}

	private ContaBancaria crieContaBancaria(String agencia, String conta) {
		ContaBancaria contaBancaria = new ContaBancaria();
		contaBancaria.setBanco(BancosSuportados.BANCO_SICREDI.create());
		contaBancaria.setAgencia(new Agencia(Integer.valueOf(agencia)));
		contaBancaria.setNumeroDaConta(new NumeroDaConta(Integer.valueOf(conta)));
		contaBancaria.setCarteira(new Carteira(1, TipoDeCobranca.COM_REGISTRO));
		return contaBancaria;
	}

	private Sacado crieSacado(MatriculaIni matriculaIni) {
		Sacado sacado = null;
		if (matriculaIni.getResponsavelFinanceiro() == ResponsavelFinanceiroEnum.PAI) {
			sacado = new Sacado(matriculaIni.getNomePai(), matriculaIni.getCpfPai());
		} else if (matriculaIni.getResponsavelFinanceiro() == ResponsavelFinanceiroEnum.MAE) {
			sacado = new Sacado(matriculaIni.getNomePai(), matriculaIni.getCpfPai());
		} else {
			sacado = new Sacado(matriculaIni.getNomeResposavel(), matriculaIni.getCpfResposavel());
		}
		/*
		 * Endereco endereco = new Endereco();
		 * endereco.setUF(UnidadeFederativa.valueOf(matriculaIni.getUf()));
		 * endereco.setLocalidade(matriculaIni.getCidade());
		 * endereco.setCep(matriculaIni.getCep());
		 * endereco.setBairro(matriculaIni.getBairro());
		 * endereco.setLogradouro(matriculaIni.getRua());
		 * endereco.setNumero(matriculaIni.getNumero()); sacado.addEndereco(endereco);
		 */
		return sacado;
	}

	private Titulo crieTitulo(Cedente cedente, Sacado sacado, Parcela parcela) {
		ContaBancaria contaBancariaDoCedente = cedente.getContasBancarias().iterator().next();
		Titulo titulo = null;
		titulo = new Titulo(contaBancariaDoCedente, sacado, cedente);
		titulo.setNumeroDoDocumento(parcela.getSeuNumero());
		titulo.setNossoNumero(parcela.getNossoNumero());
		titulo.setMora(BigDecimal.valueOf(parcela.getValorJuros()));
		titulo.setDigitoDoNossoNumero(parcela.getDigitoNossoNumero());
		titulo.setValor(BigDecimal.valueOf(parcela.getValor()));
		titulo.setDataDoDocumento(asDate(parcela.getDataEmisao()));
		titulo.setDataDoVencimento(asDate(parcela.getDataVencimento()));
		titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
		titulo.setAceite(Aceite.A);
		titulo.setParametrosBancarios(new ParametrosBancariosMap(ParametroBancoSicredi.POSTO_DA_AGENCIA, Integer.valueOf(cnab.getPosto())));
		return titulo;
	}
	
	private Boleto crieBoleto(Titulo titulo) {
		Boleto boleto = new Boleto(titulo);
		boleto.setLocalPagamento(cnab.getLocalPagamentoBoleto());
		boleto.setInstrucao1(cnab.getInstrucaoBoleto());
		return boleto;
	}
	
	
	private static byte[] execute(Boleto boleto) {
		BoletoViewer viewer = new BoletoViewer(boleto);
		//File boletoPDF = viewer.getPdfAsFile("BOLETO_" + boleto.getClass().getSimpleName().toUpperCase() + ".PDF");
		//mostreBoletoNaTela(boletoPDF);
		return viewer.getPdfAsByteArray();
	}
	
	
	private Date asDate(LocalDate localDate) {
	    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	  }
	
	public static void mostreBoletoNaTela(File arquivoBoleto) {
		
		try {
			 java.awt.Desktop.getDesktop().open(arquivoBoleto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
