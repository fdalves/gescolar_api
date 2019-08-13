package br.com.gescolar.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gescolar.dto.ChamadaDTO;
import br.com.gescolar.dto.DisciplinaTurmaDTO;
import br.com.gescolar.dto.TurmaPeriodoDTO;
import br.com.gescolar.exception.ChamadaJaCadastradaExcption;
import br.com.gescolar.exception.ChamadaNotFoundExcption;
import br.com.gescolar.model.Aluno;
import br.com.gescolar.model.Chamada;
import br.com.gescolar.model.ChamadaAluno;
import br.com.gescolar.model.DisciplinaTurma;
import br.com.gescolar.model.Professor;
import br.com.gescolar.model.TurmaPeriodo;
import br.com.gescolar.repository.AlunoRepository;
import br.com.gescolar.repository.ChamadaAlunoRepository;
import br.com.gescolar.repository.ChamadaRepository;
import br.com.gescolar.repository.DisciplinaTurmaRepository;
import br.com.gescolar.repository.ProfessorRepository;
import br.com.gescolar.repository.TurmaPeriodoRepository;
import br.com.gescolar.repository.listener.UrlFotoListener;
import br.com.gescolar.types.DiaEnum;

/**
 * ChamadaService
 * 
 * @author Francisco Alves
 *
 */
@Service
public class ChamadaService {

	@Autowired
	private DisciplinaTurmaRepository disciplinaTurmaRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private TurmaPeriodoRepository turmaPeriodoRepository;
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private ChamadaRepository chamadaRepository;
	@Autowired
	private ChamadaAlunoRepository chamadaAlunoRepository;
	
	
	
	/**
	 * getPercentualPresenca
	 * @param codigoAluno
	 * @return Double
	 */
	public Double getPercentualPresenca (Long codigoAluno) {
		Aluno aluno = alunoRepository.getOne(codigoAluno);
		int total = chamadaAlunoRepository.countByAluno(aluno);
		int totalFaltas = chamadaAlunoRepository.countByAlunoFalta(aluno);
		BigDecimal bigDecimal = new BigDecimal(new Double(100 - (new Double(totalFaltas)/new Double(total) * 100)));
		return bigDecimal.setScale(2,RoundingMode.HALF_EVEN).doubleValue();
	}
	
	
	/**
	 * listarChamadaAluno
	 * @param chamada
	 * @return List
	 */
	public List<Aluno> listarChamadaAluno(Chamada chamada) {
		List<ChamadaAluno> chamadaAluno =  chamadaAlunoRepository.findByChamada(chamada);
		List<Aluno> alunos = new ArrayList<>();
		for (ChamadaAluno chamadaAlunoInner : chamadaAluno) {
			Aluno aluno = alunoRepository.getOne(chamadaAlunoInner.getAluno().getCodigo());
			if (chamadaAlunoInner.getPresenca() != null && chamadaAlunoInner.getPresenca().booleanValue()) {
				aluno.setChamada(true);
			}
			alunos.add(aluno);
		}
		return alunos;
	}
	
	
	
	/**
	 * listarTurmarProfessor
	 * @param codigoProfessor
	 * @return List<Professor>
	 */
	public List<DisciplinaTurmaDTO> listarTurmarProfessor(Long codigoProfessor){
		Professor professor = this.professorRepository.getOne(codigoProfessor);
		List<DisciplinaTurma> list = this.disciplinaTurmaRepository.findByProfessor(professor);
		List<DisciplinaTurmaDTO> disTurmas = new ArrayList<>();
		if (list != null && !list.isEmpty()) {
			for (DisciplinaTurma disciplinaTurma : list) {
				DisciplinaTurmaDTO dto = new DisciplinaTurmaDTO();
				dto.setCodigo(disciplinaTurma.getCodigo());
				dto.setTurmaDisciplina(disciplinaTurma.getTurma().getNome() + " - " + disciplinaTurma.getDisciplina().getNome());;
				disTurmas.add(dto);
			}
		}
		return disTurmas;
	}

	
	/**
	 * listarPeriodos
	 * @param codigoProfessor
	 * @param codigoTurma
	 * @param dia
	 * @return List<Periodo>
	 */
	public List<TurmaPeriodoDTO> listarPeriodos(Long codigoDisciplaTurma, Date data){
		DiaEnum dia = getDiaEnum(data);
		DisciplinaTurma disciplinaTurma = this.disciplinaTurmaRepository.getOne(codigoDisciplaTurma);
		List<TurmaPeriodo> list = this.turmaPeriodoRepository.findByDisciplinaTurmaAndDia(disciplinaTurma, dia);
		List<TurmaPeriodoDTO> listPeriodos = new ArrayList<>();
		if (list != null && !list.isEmpty()) {
			for (TurmaPeriodo turmaPeriodo : list) {
				TurmaPeriodoDTO dto = new TurmaPeriodoDTO();
				dto.setCodigo(turmaPeriodo.getCodigo());
				dto.setPeriodo(turmaPeriodo.getPeriodo().toString());
				listPeriodos.add(dto);
			}
		}
		
		if (listPeriodos.isEmpty()) throw new ChamadaNotFoundExcption();
		return listPeriodos;
	}
	
	/**
	 * getAlunos
	 * @param codigoTurmaDisciplina
	 * @return List<Aluno>
	 */
	public List<Aluno> getAlunos(Long codigoTurmaDisciplina) {
		DisciplinaTurma disciplinaTurma = this.disciplinaTurmaRepository.getOne(codigoTurmaDisciplina);
		return this.carregaFotos(this.alunoRepository.findByTurma(disciplinaTurma.getTurma()));
	}
	
	/**
	 * carregaFotos
	 * @param findByTurma
	 * @return List<Aluno>
	 */
	private List<Aluno> carregaFotos(List<Aluno> findByTurma) {
		UrlFotoListener fotoListener = new UrlFotoListener();
		for (Aluno aluno : findByTurma) {
			fotoListener.postLoad(aluno);
		}
		return findByTurma;
	}


	/**
	 * listarAlunosTurmaPeriodo
	 * @param codigoTurmaPeriodo
	 * @return List<Aluno>
	 */
	public List<Aluno> listarAlunosTurmaPeriodo(Long codigoTurmaPeriodo){
		TurmaPeriodo turmaPeriodo = this.turmaPeriodoRepository.getOne(codigoTurmaPeriodo);
		return alunoRepository.findByTurma(turmaPeriodo.getTurma());
	}
	
	/**
	 * cadastrarChamada
	 * @param chamadaDTO
	 */
	public ChamadaDTO cadastrarChamada(ChamadaDTO chamadaDTO) {
		List<Long> codigoPeriodos = chamadaDTO.getPeriodosSelecionados();
		if (codigoPeriodos != null && !codigoPeriodos.isEmpty()) {
			for (Long codigo : codigoPeriodos) {
				TurmaPeriodo periodo = this.turmaPeriodoRepository.getOne(codigo);
				Date dataChamada =  Date.from(chamadaDTO.getDateChamada().atStartOfDay(ZoneId.systemDefault()).toInstant());
				Chamada chamada= new Chamada();
				if (chamadaDTO.getCodigo() != null) {
					chamada = chamadaRepository.getOne(chamadaDTO.getCodigo());
					chamada.setDataInclusao(new Date());
				} else {
					this.validaChamada(periodo,dataChamada);
					chamada.setDataInclusao(new Date());
					chamada.setDataChamada(dataChamada);
					chamada.setTurmaPeriodo(periodo);
					chamada = chamadaRepository.save(chamada);
				}
				this.saveChamadaAlunos(chamada, chamadaDTO);
			}
		}
		return chamadaDTO;
	}
	
	/**
	 * alterarChamada
	 * @param chamadaDTO
	 * @return ChamadaDTO
	 */
	@Transactional
	public ChamadaDTO alterarChamada(ChamadaDTO chamadaDTO) {
		Chamada chamada = chamadaRepository.getOne(chamadaDTO.getCodigo());
		List<ChamadaAluno> list = chamadaAlunoRepository.findByChamada(chamada);
		for (ChamadaAluno chamadaAluno : list) {
			chamadaAlunoRepository.delete(chamadaAluno);
		}
		return this.cadastrarChamada(chamadaDTO);
	}
	
	
	/**
	 * validaChamada
	 * @param periodo
	 * @param dataChamada
	 */
	private void validaChamada(TurmaPeriodo periodo, Date dataChamada) {
		List<Chamada> list = chamadaRepository.findByDataChamadaAndTurmaPeriodo(dataChamada, periodo);
		if (list != null && !list.isEmpty()) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			throw new ChamadaJaCadastradaExcption(("J� existe uma Chamada Cadastrada para o " + periodo.getPeriodo().toString() + ", na data: "+ sdf.format(dataChamada)));
		} 
	}


	/**
	 * saveChamadaAlunos
	 * @param periodo
	 * @param alunos
	 * @param chamadaDTO
	 */
	private void saveChamadaAlunos(Chamada chamada, ChamadaDTO chamadaDTO) {
		List<Aluno> alunos = chamada.getTurmaPeriodo().getTurma().getAlunos();
		for (Aluno aluno : alunos) {
			ChamadaAluno chamadaAluno = new ChamadaAluno();
			chamadaAluno.setAluno(aluno);
			chamadaAluno.setChamada(chamada);
			chamadaAluno.setPresenca(chamadaDTO.getAlunosPresentes().contains(aluno.getCodigo().toString()));
			chamadaAlunoRepository.save(chamadaAluno);
		}
	}


	/**
	 * getDiaEnum
	 * @param data
	 * @return DiaEnum
	 */
	private DiaEnum getDiaEnum(Date data) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		return DiaEnum.getDia(dayOfWeek - 1);
	}



}
