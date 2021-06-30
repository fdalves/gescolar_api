package br.com.gescolar.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import br.com.gescolar.model.Contrato;

public class ContratoDBUtil {

	private static final String DATE = "${date}";
	private static final String VALOR_TOTAL = "${valorTotal}";
	private static final String VALOR = "${valor}";
	private static final String DT_FIM = "${dtFim}";
	private static final String DT_INI = "${dtIni}";
	private static final String NOME = "${nome}";
	private static final String DD_MM_YYYY = "dd/MM/yyyy";
	private static final String CONTRATO_DOCX = "/contrato.docx";


	private ContratoDBUtil() {
	    throw new IllegalStateException("Utility class");
	  }


	public static byte[] criarContrato(String nome, Contrato contrato) {
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern(DD_MM_YYYY);
		Locale localeBR = new Locale("pt","BR");
		NumberFormat dinheiro = NumberFormat.getCurrencyInstance(localeBR);
		LocalDate date = LocalDate.parse(formatters.format(LocalDate.now()), formatters);
		String dtIni = formatters.format(contrato.getDataIni());
		String dtFim = formatters.format(contrato.getDataFim());
		String dateAtual = formatters.format(date);
		String valor = dinheiro.format(contrato.getValor());
		String valorTotal = dinheiro.format(contrato.getValor() * contrato.getNrParcela());
		
		Resource resource = new ClassPathResource(CONTRATO_DOCX);

		try (XWPFDocument doc = new XWPFDocument(
				Files.newInputStream(Paths.get(resource.getFile().getAbsolutePath())));) {

			List<XWPFParagraph> xwpfParagraphList = doc.getParagraphs();
			for (XWPFParagraph xwpfParagraph : xwpfParagraphList) {
				for (XWPFRun xwpfRun : xwpfParagraph.getRuns()) {
					String docText = xwpfRun.getText(0);
					if (docText == null)
						continue;
					docText = docText.replace(NOME, nome);
					docText = docText.replace(DT_INI, dtIni);
					docText = docText.replace(DT_FIM, dtFim);
					docText = docText.replace(VALOR, valor);
					docText = docText.replace(VALOR_TOTAL, valorTotal);
					docText = docText.replace(DATE, dateAtual);
					xwpfRun.setText(docText, 0);
				}
			}

			try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
				doc.write(out);
				return out.toByteArray();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return new byte[0];
	}

}