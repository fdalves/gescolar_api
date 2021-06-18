package br.com.gescolar.cobranca;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gescolar.model.Cnab;
import br.com.gescolar.model.Parcela;
import br.com.gescolar.repository.CnabRepository;

@Service
public class ArquivoCnab240Sicredi extends ArquivoCnab {

	private static final String CNAB240_SICREDI_XML = "cnab240Sicredi.xml";
	
	@Autowired
	private CnabRepository cnabRepository;
	private Cnab cnab;
	private int numeroSequencialHeader = 1;
	private int numeroSequencialLoteHeader = 1;
	private int numeroSequencialSegPeQ = 1;
	
	public ArquivoCnab240Sicredi() {
		super();
	}
	
	
	@Override
	public String getTemplate()  {
		return CNAB240_SICREDI_XML;
	}


	public String gerarArquivo(List<Parcela> parcelas) {
		init();
		numeroSequencialHeader = 1;
		numeroSequencialLoteHeader = 1;
		numeroSequencialSegPeQ = 1;
		
		Optional<Cnab> cOptional = cnabRepository.findById(1L);
		if(cOptional.isEmpty()) return StringUtils.EMPTY;
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
		super.setValor(linhaHeader, 19, getSeqString(numeroSequencialHeader, 6));
		getArquivo().getLinhas().add(linhaHeader);
		
		Linha linhaHeaderLote = super.gerarLinha(TipoLinhaEnum.HEADER_LOTE);
		super.setValor(linhaHeaderLote, 9, cnab.getTipoPessoa());
		super.setValor(linhaHeaderLote, 10, cnab.getCpfCnpj());
		super.setValor(linhaHeaderLote, 12, cnab.getAgencia());
		super.setValor(linhaHeaderLote, 14, cnab.getConta());
		super.setValor(linhaHeaderLote, 15, cnab.getDigitoConta());
		super.setValor(linhaHeaderLote, 17, cnab.getNome());
		super.setValor(linhaHeaderLote, 20, getSeqString(numeroSequencialLoteHeader, 8));
		super.setValor(linhaHeaderLote, 21, this.getDataGeracao());
		getArquivo().getLinhas().add(linhaHeaderLote);

		for (Parcela parcela : parcelas) {
			Linha linhaSeguimentoP = super.gerarLinha(TipoLinhaEnum.DETALHE_SEGMENTO_P);
			super.setValor(linhaSeguimentoP, 4, getSeqString(numeroSequencialSegPeQ, 5));
			super.setValor(linhaSeguimentoP, 8, cnab.getAgencia());
			super.setValor(linhaSeguimentoP, 10, cnab.getConta());
			super.setValor(linhaSeguimentoP, 11, cnab.getDigitoConta());
			super.setValor(linhaSeguimentoP, 13, parcela.getNossoNumero() + parcela.getDigitoNossoNumero());
			super.setValor(linhaSeguimentoP, 19, parcela.getSeuNumero());
			super.setValor(linhaSeguimentoP, 20, formatData(parcela.getDataVencimento()));
			super.setValor(linhaSeguimentoP, 21, parcela.getValor().toString().replace(".", "") + "0");
			super.setValor(linhaSeguimentoP, 26, formatData(parcela.getDataEmisao()));
			super.setValor(linhaSeguimentoP, 27, "2");
			super.setValor(linhaSeguimentoP, 28, formatData(parcela.getDataVencimento().plusDays(1)));
			super.setValor(linhaSeguimentoP, 29, String.valueOf(parcela.getValorJuros().intValue()));
			getArquivo().getLinhas().add(linhaSeguimentoP);
			numeroSequencialSegPeQ++;
			Linha linhaSeguimentoQ = super.gerarLinha(TipoLinhaEnum.DETALHE_SEGMENTO_Q);
			super.setValor(linhaSeguimentoQ, 4, getSeqString(numeroSequencialSegPeQ, 5));
			super.setValor(linhaSeguimentoQ, 8, cnab.getTipoPessoa());
			super.setValor(linhaSeguimentoQ, 9, cnab.getCpfCnpj());
			super.setValor(linhaSeguimentoQ, 10, cnab.getNome());
			super.setValor(linhaSeguimentoQ, 11, cnab.getEndereco());
			super.setValor(linhaSeguimentoQ, 13, cnab.getCep());
			super.setValor(linhaSeguimentoQ, 15, cnab.getCidade());
			super.setValor(linhaSeguimentoQ, 16, cnab.getUf());
			getArquivo().getLinhas().add(linhaSeguimentoQ);
			numeroSequencialSegPeQ++;
		}
		
		
		Linha linhaTraillerLote = super.gerarLinha(TipoLinhaEnum.TRAILLER_DO_LOTE);
		super.setValor(linhaTraillerLote, 5, getSeqString(numeroSequencialSegPeQ + 1, 6));
		getArquivo().getLinhas().add(linhaTraillerLote);
		
		Linha linhaTraillerArquivo = super.gerarLinha(TipoLinhaEnum.TRAILLER_DO_ARQUIVO);
		super.setValor(linhaTraillerArquivo, 6, getSeqString(numeroSequencialSegPeQ + 3, 6));
		getArquivo().getLinhas().add(linhaTraillerArquivo);
		
		return super.printFile();
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


	public int getNumeroSequencialHeader() {
		return numeroSequencialHeader;
	}


	public void setNumeroSequencialHeader(int numeroSequencialHeader) {
		this.numeroSequencialHeader = numeroSequencialHeader;
	}


	public int getNumeroSequencialLoteHeader() {
		return numeroSequencialLoteHeader;
	}


	public void setNumeroSequencialLoteHeader(int numeroSequencialLoteHeader) {
		this.numeroSequencialLoteHeader = numeroSequencialLoteHeader;
	}


	public int getNumeroSequencialSegPeQ() {
		return numeroSequencialSegPeQ;
	}


	public void setNumeroSequencialSegPeQ(int numeroSequencialSegPeQ) {
		this.numeroSequencialSegPeQ = numeroSequencialSegPeQ;
	}

	
	
	

}
