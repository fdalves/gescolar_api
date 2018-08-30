package br.com.gescolar.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.Chamada;


public interface ChamadaRepository extends JpaRepository<Chamada, Long>  {
	
		
	
}
