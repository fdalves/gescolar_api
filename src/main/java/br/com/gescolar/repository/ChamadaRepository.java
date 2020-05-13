package br.com.gescolar.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gescolar.dto.ProcessChamadaDTO;
import br.com.gescolar.model.Chamada;
import br.com.gescolar.model.TurmaPeriodo;


public interface ChamadaRepository extends JpaRepository<Chamada, Long>  {
	
	public List<Chamada> findByDataChamadaAndTurmaPeriodo(Date data, TurmaPeriodo turmaPeriodo);
	
	@Query(" SELECT c FROM TurmaPeriodo tp join "
			+ " Chamada c  ON (tp.codigo = c.turmaPeriodo.codigo) where tp.disciplinaTurma.codigo = :disciplinaTurma  and "
			+ " c.dataChamada >= :dtIni and c.dataChamada <= :dtFim " )
	public List<Chamada> searchChamada(@Param("disciplinaTurma") Long disciplinaTurma,
			                           @Param("dtIni")Date dtIni,
			                           @Param("dtFim")Date dtFim);
	
	@Query("SELECT c FROM Aluno a "
			+ " join Turma t  ON (t.codigo = a.turma.codigo) "
			+"  join DisciplinaTurma dt ON (dt.turma.codigo = t.codigo) "
			+"  join TurmaPeriodo tp ON (tp.disciplinaTurma.codigo = dt.codigo) "
			+"  join Chamada c ON (c.turmaPeriodo.codigo = tp.codigo) "
			+"  join ChamadaAluno ca ON (ca.chamada.codigo = c.codigo and a.codigo = ca.aluno.codigo) "
			+"  where tp.disciplinaTurma.disciplina.codigo = :disciplinaTurma  and a.codigo= :codigoAluno and ca.presenca = :status "
			+ " and  c.dataChamada >= :dtIni and c.dataChamada <= :dtFim " )
	public List<Chamada> searchChamadaAluno(@Param("disciplinaTurma") Long disciplinaTurma,
											@Param("codigoAluno") Long codigoAluno,
											@Param("dtIni")Date dtIni,
											@Param("dtFim")Date dtFim,
											@Param("status") Boolean status);
	
	  
	
	@Query( "  	SELECT new br.com.gescolar.dto.ProcessChamadaDTO(a.nome as nomeAluno, "+
			"  	d.nome as nomeDisciplina , c.dataChamada ,ca.aluno.codigo as codigoAluno, ca.presenca) FROM " + 
			"  	Chamada c join ChamadaAluno ca  ON (c.codigo = ca.chamada.codigo) "  + 
			"	JOIN Aluno a ON (a.codigo = ca.aluno.codigo) " + 
			"	JOIN TurmaPeriodo tp ON (tp.codigo = c.turmaPeriodo.codigo) " + 
			"	JOIN DisciplinaTurma dt ON (dt.codigo = tp.disciplinaTurma.codigo) " + 
			"	JOIN Disciplina d ON (d.codigo = dt.disciplina.codigo) " + 
			"	WHERE ca.presenca = 0 and  (ca.notificado is null or ca.notificado = 0) " + 
			"	GROUP BY  a.nome, d.nome , c.dataChamada ,ca.aluno.codigo,ca.presenca " + 
			"	ORDER BY ca.aluno.codigo  ")
	public List<ProcessChamadaDTO> processChamada();
	
	
	
	
}
