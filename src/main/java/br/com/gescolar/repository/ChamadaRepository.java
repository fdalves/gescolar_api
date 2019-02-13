package br.com.gescolar.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.Chamada;
import br.com.gescolar.model.TurmaPeriodo;


public interface ChamadaRepository extends JpaRepository<Chamada, Long>  {
	
	public List<Chamada> findByDataChamadaAndTurmaPeriodo(Date data, TurmaPeriodo turmaPeriodo);
	
}
