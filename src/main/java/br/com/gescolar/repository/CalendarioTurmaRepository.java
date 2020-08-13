package br.com.gescolar.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.CalendarioTurma;


public interface CalendarioTurmaRepository  extends JpaRepository<CalendarioTurma, Long> {

	List<CalendarioTurma> findByTurmaIn(List<Long> turmas);
	
}