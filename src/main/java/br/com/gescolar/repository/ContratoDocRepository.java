package br.com.gescolar.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.Contrato;
import br.com.gescolar.model.ContratoDoc;


public interface ContratoDocRepository  extends JpaRepository<ContratoDoc, Long> {
	
	public ContratoDoc findByContrato(Contrato contrato);
}