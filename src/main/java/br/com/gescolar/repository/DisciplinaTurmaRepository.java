package br.com.gescolar.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.DisciplinaTurma;


public interface DisciplinaTurmaRepository extends JpaRepository<DisciplinaTurma, Long> {
	
	
}
