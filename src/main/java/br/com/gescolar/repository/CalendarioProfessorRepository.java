package br.com.gescolar.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.CalendarioProfessor;


public interface CalendarioProfessorRepository  extends JpaRepository<CalendarioProfessor, Long> {
	
	
}