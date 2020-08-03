package br.com.gescolar.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.CalendarioTurma;


public interface CalendarioTurmaRepository  extends JpaRepository<CalendarioTurma, Long> {
	
}