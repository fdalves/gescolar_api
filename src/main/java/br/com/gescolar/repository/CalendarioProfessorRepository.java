package br.com.gescolar.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.CalendarioProfessor;


public interface CalendarioProfessorRepository  extends JpaRepository<CalendarioProfessor, Long> {

	List<CalendarioProfessor> findByProfessorIn(List<Long> professores);
	
	
}