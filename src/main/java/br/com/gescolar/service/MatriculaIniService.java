package br.com.gescolar.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gescolar.cobranca.BoletoSicredi;
import br.com.gescolar.cobranca.NossoNumeroSicredi;
import br.com.gescolar.dto.AtivarMatrciulaDTO;
import br.com.gescolar.dto.MatriculaDTO;
import br.com.gescolar.model.Cnab;
import br.com.gescolar.model.Contrato;
import br.com.gescolar.model.MatriculaIni;
import br.com.gescolar.model.Parcela;
import br.com.gescolar.repository.CnabRepository;
import br.com.gescolar.repository.ContratoRepository;
import br.com.gescolar.repository.MatriculaIniRepository;
import br.com.gescolar.repository.ParcelaRepository;
import br.com.gescolar.types.StatusMatriculaEnum;

@Service
@Transactional
public class MatriculaIniService {

	@Autowired
	private MatriculaIniRepository matriculaIniRepository;
	@Autowired
	private ContratoRepository contratoRepository;
	@Autowired
	private ParcelaRepository parcelaRepository;
	@Autowired
	private CnabRepository cnabRepository;
	@Autowired
	private BoletoSicredi boletoSicredi;
	

	public MatriculaDTO salvar(MatriculaDTO matriculaDTO) {
		MatriculaIni matriculaIni = new MatriculaIni();
		matriculaIni.parseDtoToModel(matriculaDTO);
		matriculaIni.setStatus(StatusMatriculaEnum.ATIVAR.toString());
		matriculaIniRepository.save(matriculaIni);
		matriculaDTO.setCodigo(matriculaIni.getCodigo().toString());
		return matriculaDTO;
	}

	public MatriculaDTO buscarPeloCodigo(Long codigo) {
		MatriculaIni matriculaIni = matriculaIniRepository.getOne(codigo);
		return MatriculaIni.parseDtoToDto(matriculaIni);
	}

	public MatriculaDTO atualizar(Long codigo, @Valid MatriculaDTO matriculaDTO) {
		MatriculaIni matriculaIni = matriculaIniRepository.getOne(codigo);
		matriculaIni.parseDtoToModel(matriculaDTO);
		return MatriculaIni.parseDtoToDto(matriculaIniRepository.save(matriculaIni));
	}

	public Page<MatriculaDTO> findByNomeContaining(String nome, Pageable pageable) {
		return matriculaIniRepository
				.findByNomeContaining(nome, pageable)
				.map(MatriculaIni::parseDtoToDto);
	}
	
	
	public void ativarMatrciula(AtivarMatrciulaDTO ativarMatrciulaDTO) {
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate dateIni = LocalDate.parse(ativarMatrciulaDTO.getDtInicial(), formatters);
		LocalDate dateEnd = LocalDate.parse(ativarMatrciulaDTO.getDtFinal(), formatters);
		
		Long monthsBetween = ChronoUnit.MONTHS.between(
			     YearMonth.from(dateIni), 
			     YearMonth.from(dateEnd)
			);
			
		Contrato contrato = criarContrato(ativarMatrciulaDTO, dateIni, dateEnd, monthsBetween);
		gerarParcelas(ativarMatrciulaDTO, dateIni, monthsBetween, contrato);
		
	}

	private void gerarParcelas(AtivarMatrciulaDTO ativarMatrciulaDTO, LocalDate dateIni, Long monthsBetween,
			Contrato contrato) {
		LocalDate date = LocalDate.of(dateIni.getYear(), dateIni.getMonth(), Integer.valueOf(ativarMatrciulaDTO.getDiaBoleto()));
		for (int i = 1; i <= monthsBetween; i++) {
			Parcela parcela = new Parcela();
			parcela.setContrato(contrato);
			parcela.setNrParcela(i);
			parcela.setValorJuros(Double.valueOf(ativarMatrciulaDTO.getJuros()));
			parcela.setValor(Double.valueOf(ativarMatrciulaDTO.getValor()));
			parcela.setDataEmisao(date);
			parcela.setDataVencimento(date.withDayOfMonth(Integer.valueOf(ativarMatrciulaDTO.getDiaVencimento())));
			this.setNosoNumero(parcela);
			parcela.setBoleto(boletoSicredi.gerarBoleto(parcela));
			parcelaRepository.save(parcela);
			date = date.plusMonths(1);
		}
	}

	private void setNosoNumero(Parcela parcela) {
		Optional<Cnab> cOptional = cnabRepository.findById(1L);
		if(cOptional.isEmpty()) return;
		Cnab cnab = cOptional.get();
		NossoNumeroSicredi nossoNumeroSicrediUtil = new NossoNumeroSicredi();
		nossoNumeroSicrediUtil.gerarNossoNumero(cnab);
		parcela.setNossoNumero(nossoNumeroSicrediUtil.getNossoNumero());
		parcela.setDigitoNossoNumero(nossoNumeroSicrediUtil.getDigitoNossoNumero());
		parcela.setSeuNumero(nossoNumeroSicrediUtil.getSeuNumero());
		cnab.setSeqNossoNumero(cnab.getSeqNossoNumero() + 1);
		cnab.setSeqSeuNumero(cnab.getSeqSeuNumero() + 1);
		cnabRepository.save(cnab);
	}

	private Contrato criarContrato(AtivarMatrciulaDTO ativarMatrciulaDTO, LocalDate dateIni, LocalDate dateEnd,
			Long monthsBetween) {
		MatriculaIni matriculaIni = this.matriculaIniRepository.getOne(Long.valueOf(ativarMatrciulaDTO.getCodigo()));
		Contrato contrato = new Contrato();
		contrato.setAtivo(true);
		contrato.setDataIni(dateIni);
		contrato.setDataFim(dateEnd);
		contrato.setMatricula(matriculaIni);
		contrato.setNrDiaPagamento(Integer.valueOf(ativarMatrciulaDTO.getDiaBoleto()));
		contrato.setNrDiaVencimento(Integer.valueOf(ativarMatrciulaDTO.getDiaVencimento()));
		contrato.setValorJuros(Double.valueOf(ativarMatrciulaDTO.getJuros()));
		contrato.setValor(Double.valueOf(ativarMatrciulaDTO.getValor()));
		contrato.setNrParcela(monthsBetween.intValue());
		return contratoRepository.save(contrato);
	}

}
