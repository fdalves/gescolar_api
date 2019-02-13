package br.com.gescolar.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.ChamadaAluno;


public interface ChamadaAlunoRepository extends JpaRepository<ChamadaAluno, Long>  {
	
		
	
}
