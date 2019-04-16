package br.com.gescolar.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gescolar.model.Chamada;
import br.com.gescolar.model.TurmaPeriodo;


public interface ChamadaRepository extends JpaRepository<Chamada, Long>  {
	
	public List<Chamada> findByDataChamadaAndTurmaPeriodo(Date data, TurmaPeriodo turmaPeriodo);
	
	@Query(" SELECT c FROM TurmaPeriodo tp join "
			+ " Chamada c  ON (tp.codigo = c.turmaPeriodo.codigo) where tp.disciplinaTurma.codigo = :disciplinaTurma  and "
			+ " c.dataChamada between :dtIni and :dtFim " )
	public List<Chamada> searchChamada(@Param("disciplinaTurma") Long disciplinaTurma,
			                           @Param("dtIni")Date dtIni,
			                           @Param("dtFim")Date dtFim);
	
}
