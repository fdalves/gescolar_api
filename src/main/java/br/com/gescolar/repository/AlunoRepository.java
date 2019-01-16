package br.com.gescolar.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.Aluno;
import br.com.gescolar.model.Turma;


public interface AlunoRepository  extends JpaRepository<Aluno, Long> {
	
	public Page<Aluno> findByNomeContainingAndMatriculaContaining(String nome, String matricula, Pageable pageable);
	
	public boolean existsByMatricula(String matricula);
	
	public List<Aluno> findByTurma(Turma turma);
	
	public Page<Aluno> findByTurmaAndNomeContainingAndMatriculaContaining(Turma t, String nome, String matricula, Pageable pageable );

}