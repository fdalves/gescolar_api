package br.com.gescolar.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.Aluno;
import br.com.gescolar.model.Chamada;
import br.com.gescolar.model.ChamadaAluno;


public interface ChamadaAlunoRepository extends JpaRepository<ChamadaAluno, Long>  {
	
	List<ChamadaAluno> findByChamada(Chamada chamada);
	List<ChamadaAluno> findByAlunoAndPresenca(Aluno a, Boolean presenca);
	
}
