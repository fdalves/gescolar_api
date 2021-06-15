package br.com.gescolar.cobranca;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@Component
public abstract class ArquivoCnab implements ArquivoTemplate {

	Logger logger = LoggerFactory.getLogger(ArquivoCnab.class);

	private Arquivo arquivolayout;
	private Arquivo arquivo;
	private int nrLinha = 0;

	ArquivoCnab() {
		arquivo = new Arquivo();
		arquivo.setLinhas(new ArrayList<>());
		this.carregarLayout();
	}

	private void carregarLayout() {
		File file = new File(getTemplate());
		XmlMapper xmlMapper = new XmlMapper();
		try {
			this.arquivolayout = xmlMapper.readValue(file, Arquivo.class);

			arquivolayout.getLinhas().stream().forEach(l -> l.getCampos().stream().forEach(c -> {
				this.validaCampo(l, c);
			}));

		} catch (Exception e) {
			throw new CobrancaException(e.getMessage());
		}
	}

	@Override
	public Linha gerarLinha(TipoLinhaEnum tipoLinhaEnum) {
		Optional<Linha> linha = arquivolayout.getLinhas().stream().filter(l -> l.getLinhaEnum().equals(tipoLinhaEnum))
				.findFirst();
		if (linha.isPresent()) {
			Linha linhaAux = linha.get().clone();
			arquivo.getLinhas().add(linhaAux);
			nrLinha++;
			linhaAux.setPosicao(nrLinha);
			return linhaAux;
		}

		throw new CobrancaException("Linha não encontrada");
	}

	@Override
	public void setValor(Linha linha, Integer posicao, String valor) {

		if (Objects.isNull(valor))
			throw new CobrancaException(
					String.format("Linha pos %s, Campo pos %s, Valor não pode ser nulo", linha.getPosicao(), posicao));

		Optional<Campo> optional = linha.getCampos().stream().filter(c -> c.getPosicao().equals(posicao)).findFirst();
		if (optional.isPresent()) {
			Campo campo = optional.get();
			campo.setValor(valor);
			validaCampo(linha, campo);
		} else {
			throw new CobrancaException("Linha não encontrada");
		}

	}

	private void validaCampo(Linha linha, Campo campo) {
		String valor = campo.getValor();
		if (Objects.isNull(valor))
			return;

		int length = campo.getPosFim() - campo.getPosIni();
		length++;
		if (valor.length() > length)
			throw new CobrancaException(String.format("Linha pos %s, Campo pos %s, Valor tamanho incoreto. ",
					linha.getPosicao(), campo.getPosicao()));

		if (Objects.nonNull(campo.getAlinhadoDireita()) && Objects.nonNull(campo.getZeroEsquerda())
				&& campo.getAlinhadoDireita().booleanValue() && campo.getZeroEsquerda().booleanValue()) {

			StringBuilder valorBuilder = new StringBuilder();
			int lengthZeros = length - campo.getValor().length();
			for (int i = 0; i < lengthZeros; i++) {
				valorBuilder.append("0");
			}
			String valorNew = valorBuilder.toString() + campo.getValor();
			campo.setValor(valorNew);
		}

		if (campo.getTpoCampo().equals(TpoCampoEnum.EM_BRACO)) {
			StringBuilder valorBuilder = new StringBuilder();
			for (int i = 0; i < length; i++) {
				valorBuilder.append(" ");
			}
			campo.setValor(valorBuilder.toString());

		} else if (campo.getTpoCampo().equals(TpoCampoEnum.ZEROS)) {
			StringBuilder valorBuilder = new StringBuilder();
			for (int i = 0; i < length; i++) {
				valorBuilder.append("0");
			}
			campo.setValor(valorBuilder.toString());

		} else if (length > campo.getValor().length()) {
			StringBuilder valorBuilder = new StringBuilder();
			int lengthZeros = length - campo.getValor().length();
			for (int i = 0; i < lengthZeros; i++) {
				valorBuilder.append(" ");
			}
			String valorNew = campo.getValor() + valorBuilder.toString();
			campo.setValor(valorNew);
		}
	}

	public String printFile() {
		StringBuilder sb = new StringBuilder();
		for (Linha l : arquivo.getLinhas()) {
			String x = "";
			for (Campo campo : l.getCampos()) {
				x = x + campo.getValor();
			}
			sb.append(x+"\n");
		}
		return sb.toString();
	}

	public String getSeqString(int seq, int quant) {
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

	public String formatData(LocalDate dataVencimento) {
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("ddMMyyyy");
		return formatters.format(dataVencimento);
	}

	public String getDataGeracao() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
		return formatter.format(LocalDate.now());
	}

	public String getHoraGeracao() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hhmmss");
		return formatter.format(LocalTime.now());
	}

	public Arquivo getArquivolayout() {
		return arquivolayout;
	}

	public void setArquivolayout(Arquivo arquivolayout) {
		this.arquivolayout = arquivolayout;
	}

	public Arquivo getArquivo() {
		return arquivo;
	}

	public void setArquivo(Arquivo arquivo) {
		this.arquivo = arquivo;
	}

	public int getNrLinha() {
		return nrLinha;
	}

	public void setNrLinha(int nrLinha) {
		this.nrLinha = nrLinha;
	}

}
