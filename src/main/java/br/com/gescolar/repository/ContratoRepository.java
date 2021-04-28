package br.com.gescolar.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.Contrato;


public interface ContratoRepository  extends JpaRepository<Contrato, Long> {
	
	

}