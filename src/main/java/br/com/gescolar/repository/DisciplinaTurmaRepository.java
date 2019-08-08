package br.com.gescolar.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.DisciplinaTurma;
import br.com.gescolar.model.Professor;
import br.com.gescolar.model.Turma;

import java.util.List;
import java.util.Optional;

import br.com.gescolar.model.Disciplina;


public interface DisciplinaTurmaRepository extends JpaRepository<DisciplinaTurma, Long> {
	
	List<DisciplinaTurma> findByProfessor(Professor professor);
	
	List<DisciplinaTurma> findByTurma(Turma turma);
	
	Optional<DisciplinaTurma> findByDisciplinaAndProfessorAndTurma(Disciplina disciplina, Professor professor, Turma turma);
}
