package br.com.gescolar.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.gescolar.model.Aluno;
import br.com.gescolar.model.Chamada;
import br.com.gescolar.model.ChamadaAluno;


public interface ChamadaAlunoRepository extends JpaRepository<ChamadaAluno, Long>  {
	
	List<ChamadaAluno> findByChamada(Chamada chamada);
	List<ChamadaAluno> findByAlunoAndPresenca(Aluno a, Boolean presenca);
	
	@Query("select count(c) from ChamadaAluno c where c.aluno = ?1")
	int countByAluno(Aluno a);
	
	@Query("select count(c) from ChamadaAluno c where c.aluno = ?1 and (c.presenca is null or c.presenca = 0) ")
	int countByAlunoFalta(Aluno a);
	
}
