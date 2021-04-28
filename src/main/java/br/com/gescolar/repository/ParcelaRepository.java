package br.com.gescolar.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.Parcela;


public interface ParcelaRepository  extends JpaRepository<Parcela, Long> {
	
	

}