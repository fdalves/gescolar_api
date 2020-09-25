package br.com.gescolar.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gescolar.model.CalendarioProfessor;


public interface CalendarioProfessorRepository  extends JpaRepository<CalendarioProfessor, Long> {
	
	@Query("select cp from CalendarioProfessor cp where cp.professor.codigo in (:professores)")
	List<CalendarioProfessor> findByProfessorIn(@Param("professores")List<Long> professores);
	
	
}