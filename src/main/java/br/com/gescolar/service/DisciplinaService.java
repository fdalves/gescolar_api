package br.com.gescolar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gescolar.model.Aluno;
import br.com.gescolar.model.Disciplina;
import br.com.gescolar.model.DisciplinaTurma;
import br.com.gescolar.repository.AlunoRepository;
import br.com.gescolar.repository.DisciplinaTurmaRepository;

/**
 * DisciplinaService
 * 
 * @author faalves
 *
 */

@Service
public class DisciplinaService {
	
	
	@Autowired
	private DisciplinaTurmaRepository disciplinaTurmaRepository;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	/**
	 * getDisciplinas
	 * @param codigoAluno
	 * @return List<Disciplina>
	 */
	public List<Disciplina> getDisciplinas(Long codigoAluno) {
		Aluno aluno = alunoRepository.getOne(codigoAluno);
		List<DisciplinaTurma> list = disciplinaTurmaRepository.findByTurma(aluno.getTurma());
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		for (DisciplinaTurma disciplina : list) {
			disciplinas.add(disciplina.getDisciplina());
		}
		return disciplinas;
	}

	
}
