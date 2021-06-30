package br.com.gescolar.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.Parcela;
import br.com.gescolar.model.ParcelaDoc;


public interface ParcelaDocRepository  extends JpaRepository<ParcelaDoc, Long> {
	
	public ParcelaDoc findByParcela(Parcela parcela);
}