package br.com.gescolar.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gescolar.model.CalendarioTurma;


public interface CalendarioTurmaRepository  extends JpaRepository<CalendarioTurma, Long> {
	
	@Query("select ct from CalendarioTurma ct where ct.turma.codigo in (:turmas)")
	List<CalendarioTurma> findByTurmaIn(@Param("turmas")List<Long> turmas);
	
}