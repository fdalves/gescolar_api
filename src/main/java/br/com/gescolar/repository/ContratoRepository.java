package br.com.gescolar.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.Contrato;
import br.com.gescolar.model.MatriculaIni;


public interface ContratoRepository  extends JpaRepository<Contrato, Long> {
	
	public Contrato findByMatricula(MatriculaIni matriculaIni);

}