package br.com.gescolar.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.gescolar.model.Parcela;


public interface ParcelaRepository  extends JpaRepository<Parcela, Long> {
	
	@Query("SELECT p FROM Parcela p, Contrato c WHERE p.contrato.codigo = c.codigo and c.matricula.codigo = :codigoMatricula order by p.codigo ")
	List<Parcela> boletosByMatricula(@Param("codigoMatricula") Long codigoMatricula); 
		
	

}