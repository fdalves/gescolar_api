package br.com.gescolar.service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gescolar.cobranca.ArquivoCnab240Sicredi;
import br.com.gescolar.cobranca.BoletoSicredi;
import br.com.gescolar.cobranca.NossoNumeroSicredi;
import br.com.gescolar.dto.AtivarMatrciulaDTO;
import br.com.gescolar.dto.BoletoDTO;
import br.com.gescolar.dto.ContratoDTO;
import br.com.gescolar.dto.MatriculaDTO;
import br.com.gescolar.exception.GescolarExcption;
import br.com.gescolar.model.Cnab;
import br.com.gescolar.model.Contrato;
import br.com.gescolar.model.ContratoDoc;
import br.com.gescolar.model.MatriculaIni;
import br.com.gescolar.model.Parcela;
import br.com.gescolar.model.ParcelaDoc;
import br.com.gescolar.repository.CnabRepository;
import br.com.gescolar.repository.ContratoDocRepository;
import br.com.gescolar.repository.ContratoRepository;
import br.com.gescolar.repository.MatriculaIniRepository;
import br.com.gescolar.repository.ParcelaDocRepository;
import br.com.gescolar.repository.ParcelaRepository;
import br.com.gescolar.types.StatusMatriculaEnum;
import br.com.gescolar.types.StatusParcelaEnum;
import br.com.gescolar.util.ContratoDBUtil;

@Service
@Transactional
public class MatriculaIniService {

	@Autowired
	private MatriculaIniRepository matriculaIniRepository;
	@Autowired
	private ContratoRepository contratoRepository;
	@Autowired
	private ContratoDocRepository contratoDocRepository;
	@Autowired
	private ParcelaRepository parcelaRepository;
	@Autowired
	private ParcelaDocRepository parcelaDocRepository; 
	@Autowired
	private CnabRepository cnabRepository;
	@Autowired
	private BoletoSicredi boletoSicredi;
	@Autowired
	private ArquivoCnab240Sicredi arquivoCnab240Sicredi;
	@Autowired
	private FotoService fotoService;
	

	public MatriculaDTO salvar(MatriculaDTO matriculaDTO) {
		MatriculaIni matriculaIni = new MatriculaIni();
		matriculaIni.parseDtoToModel(matriculaDTO);
		matriculaIni.setStatus(StatusMatriculaEnum.ATIVAR.toString());
		matriculaIniRepository.save(matriculaIni);
		matriculaDTO.setCodigo(matriculaIni.getCodigo().toString());
		fotoService.salvar(matriculaIni.getFoto());
		return matriculaDTO;
	}

	public MatriculaDTO buscarPeloCodigo(Long codigo) {
		MatriculaIni matriculaIni = matriculaIniRepository.getOne(codigo);
		return MatriculaIni.parseDtoToDto(matriculaIni);
	}

	public MatriculaDTO atualizar(Long codigo, @Valid MatriculaDTO matriculaDTO) {
		MatriculaIni matriculaIni = matriculaIniRepository.getOne(codigo);
		matriculaIni.parseDtoToModel(matriculaDTO);
		fotoService.atualizar(matriculaIni.getFoto(), matriculaDTO.getFoto());
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
		
		this.validarDatasMatricula(dateIni, dateEnd, formatters);
		
		Long duracaoContrato = ChronoUnit.MONTHS.between(
			     YearMonth.from(dateIni), 
			     YearMonth.from(dateEnd)
			);
		
		this.validaDuracaoContrato(duracaoContrato);
		
		MatriculaIni matriculaIni = this.matriculaIniRepository.getOne(Long.valueOf(ativarMatrciulaDTO.getCodigo()));
		Contrato contrato = this.criarContrato(ativarMatrciulaDTO, dateIni, dateEnd, duracaoContrato, matriculaIni);
		List<Parcela> parcelas =  this.gerarParcelas(ativarMatrciulaDTO, dateIni, duracaoContrato, contrato, matriculaIni);
		contrato.setArquivoCnab(arquivoCnab240Sicredi.gerarArquivo(parcelas));
		contratoRepository.save(contrato);
		matriculaIni.setStatus(StatusMatriculaEnum.ATIVA.name());
		matriculaIniRepository.save(matriculaIni);
	}

	private void validaDuracaoContrato(Long duracaoContrato) {
		if (duracaoContrato > 12) {
			throw new GescolarExcption("Periodo do contrado só deve ter no maximo 1 ano de duração.");
		}
		if (duracaoContrato < 1 ) {
			throw new GescolarExcption("Periodo do contrado deve ter minimo 1 mes de duração.");
		}
	}

	private void validarDatasMatricula(LocalDate dateIni, LocalDate dateEnd, DateTimeFormatter formatters) {
		LocalDate date = LocalDate.parse(formatters.format(LocalDate.now()), formatters);	
		if (dateIni.isBefore(date))
			throw new GescolarExcption("Data incial da matrícula deve ser maior que data atual");
		if (dateIni.isAfter(dateEnd))
			throw new GescolarExcption("Data incial da matrícula deve ser menor que data final");
	}

	private List<Parcela> gerarParcelas(AtivarMatrciulaDTO ativarMatrciulaDTO, LocalDate dateIni, Long monthsBetween,
			Contrato contrato, MatriculaIni matriculaIni) {
		List<Parcela> parcelas = new ArrayList<>();
		LocalDate date = LocalDate.of(dateIni.getYear(), dateIni.getMonth(), Integer.valueOf(ativarMatrciulaDTO.getDiaBoleto()));
		monthsBetween++;
		for (int i = 1; i <= monthsBetween; i++) {
			Parcela parcela = new Parcela();
			parcela.setContrato(contrato);
			parcela.setStatus(StatusParcelaEnum.EM_ABERTO);
			parcela.setNrParcela(i);
			parcela.setValorJuros(Double.valueOf(ativarMatrciulaDTO.getJuros()));
			parcela.setValor(Double.valueOf(ativarMatrciulaDTO.getValor()));
			parcela.setDataEmisao(date);
			parcela.setDataVencimento(date.withDayOfMonth(Integer.valueOf(ativarMatrciulaDTO.getDiaVencimento())));
			this.setNosoNumero(parcela);
			parcela.setNomeBoleto(matriculaIni.getNome().replace(" ", "_") + "_" + date.getMonthValue() +  "_" + date.getYear() + ".pdf");
			parcela = parcelaRepository.save(parcela);
			ParcelaDoc parcelaDoc = new ParcelaDoc();
			parcelaDoc.setParcela(parcela);
			parcelaDoc.setBoleto(boletoSicredi.gerarBoleto(parcela));
			parcelaDocRepository.save(parcelaDoc);
			parcelas.add(parcela);
			date = date.plusMonths(1);
		}
		return parcelas;
	}


	private void setNosoNumero(Parcela parcela) {
		Optional<Cnab> cOptional = cnabRepository.findById(1L);
		if(!cOptional.isPresent()) return;
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
			Long monthsBetween, MatriculaIni matriculaIni) {
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
		contrato.setDataArquivoCnab(LocalDate.now());
		contrato.setStatusArquivoCnab("Não Processado");
		String day = String.valueOf(LocalDate.now().getDayOfMonth());
		String mount = getMountCnab();  
		contrato.setNomeArquivoCnab("01671"+mount+day+".crm");
		contrato = contratoRepository.save(contrato);
		ContratoDoc contratoDoc = new ContratoDoc();
		contratoDoc.setContrato(contrato);
		contratoDoc.setContratoPdf(ContratoDBUtil.criarContrato(matriculaIni.getNome(), contrato));
		contratoDocRepository.save(contratoDoc);
		return contrato;
	}

	private String getMountCnab() {
		String month = String.valueOf(LocalDate.now().getMonthValue());
		if (month.length() == 1) {
			return month;
		} else if (month.length() > 1 && month.substring(0,1).equals("0")) {
			return month.substring(1,2);
		} else if (month.length() > 1 && month.substring(0,1).equals("1")) {
			if (month.equals("10")) {
				return "O";
			} else if (month.equals("11")) {
				return "N";
			} else if (month.equals("12")) {
				return "D";
			}
		}
		return "";
	}

	public List<BoletoDTO> buscarBoletos(Long codigo) {
		return parcelaRepository.boletosByMatricula(codigo).
				stream()
				.map(Parcela::parseBoletoDTO)
				.collect(Collectors.toList());
	}

	public Resource downloadContrato(Long codigo) {
		MatriculaIni matriculaIni = matriculaIniRepository.getOne(codigo);
		Contrato contrato = contratoRepository.findByMatricula(matriculaIni);
		ContratoDoc contratoDoc = contratoDocRepository.findByContrato(contrato);
		return new ByteArrayResource(contratoDoc.getContratoPdf());
	}

	public List<ContratoDTO> buscarContratos() {
		return contratoRepository.findAll()
				.stream()
				.map(Contrato::parseDto)
				.collect(Collectors.toList());
	}

	public Resource downloadCnab(Long codigo) {
		Contrato contrato = contratoRepository.getOne(codigo);
		return new ByteArrayResource(contrato.getArquivoCnab().getBytes());
	}

	public Resource downloadBoleto(Long codigo) {
		Parcela parcela = parcelaRepository.getOne(codigo);
		ParcelaDoc parcelaDoc = parcelaDocRepository.findByParcela(parcela);
		return new ByteArrayResource(parcelaDoc.getBoleto());
	}


}
