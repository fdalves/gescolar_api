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
import br.com.gescolar.model.Aluno;
import br.com.gescolar.model.Chamada;
import br.com.gescolar.model.DisciplinaTurma;
import br.com.gescolar.model.Professor;
import br.com.gescolar.model.TurmaPeriodo;
import br.com.gescolar.repository.AlunoRepository;
import br.com.gescolar.repository.ChamadaRepository;
import br.com.gescolar.repository.DisciplinaTurmaRepository;
import br.com.gescolar.repository.ProfessorRepository;
import br.com.gescolar.repository.TurmaPeriodoRepository;
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
		return listPeriodos;
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
	 * @param listChamada
	 */
	public void cadastrarChamada(List<ChamadaDTO> listChamada) {
		for (ChamadaDTO dto : listChamada) {
			Chamada chamada = new Chamada();
			chamada.setAluno(alunoRepository.getOne(dto.getCodigoAluno()));
			chamada.setTurmaPeriodo(turmaPeriodoRepository.getOne(dto.getCodigoTurmaPeriodo()));
			chamada.setPresenca(dto.getPresenca());
			chamada.setDataInclusao(new Date());
			chamada.setDataChamada(dto.getDate());
			chamadaRepository.save(chamada);
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
