package br.com.gescolar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.gescolar.dto.Periodo;
import br.com.gescolar.model.DisciplinaTurma;
import br.com.gescolar.model.Turma;
import br.com.gescolar.model.TurmaPeriodo;
import br.com.gescolar.types.DiaEnum;

public interface TurmaPeriodoRepository extends JpaRepository<TurmaPeriodo, Long> {

	@Query(" SELECT new  br.com.gescolar.dto.Periodo(p.dia, COUNT(p)) "
	        +
	        "FROM TurmaPeriodo p "
	        +
	        "WHERE p.turma.codigo = ?1 "
	        +
	        "GROUP BY p.dia")
	List<Periodo> findPeriodos(Long codigoTurma);

	List<TurmaPeriodo> findByTurma(Turma turma);
	List<TurmaPeriodo> findByDisciplinaTurma(DisciplinaTurma disciplinaTurma);
	
	List<TurmaPeriodo> findByDisciplinaTurmaAndDia(DisciplinaTurma disciplinaTurma,DiaEnum diaEnum );
	
}
