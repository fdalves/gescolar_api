package br.com.gescolar.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gescolar.dto.ChamadaDTO;
import br.com.gescolar.dto.DisciplinaTurmaDTO;
import br.com.gescolar.dto.TurmaPeriodoDTO;
import br.com.gescolar.exception.ChamadaNotFoundExcption;
import br.com.gescolar.model.Aluno;
import br.com.gescolar.model.DisciplinaTurma;
import br.com.gescolar.model.Professor;
import br.com.gescolar.model.TurmaPeriodo;
import br.com.gescolar.repository.AlunoRepository;
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
	//@Autowired
	//private ChamadaRepository chamadaRepository;
	
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
				List<Aluno> alunos = periodo.getTurma().getAlunos();
				this.saveChamada(periodo,alunos,chamadaDTO);
			}
		}
		return chamadaDTO;
	}
	
	/**
	 * saveChamada
	 * @param periodo
	 * @param alunos
	 * @param chamadaDTO
	 */
	private void saveChamada(TurmaPeriodo periodo, List<Aluno> alunos, ChamadaDTO chamadaDTO) {
		
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
