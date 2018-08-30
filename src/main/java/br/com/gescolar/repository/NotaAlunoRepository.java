package br.com.gescolar.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.NotaAluno;



public interface NotaAlunoRepository extends JpaRepository<NotaAluno, Long>{
	
}
