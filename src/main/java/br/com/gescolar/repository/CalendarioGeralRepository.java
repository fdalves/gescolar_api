package br.com.gescolar.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.CalendarioGeral;


public interface CalendarioGeralRepository  extends JpaRepository<CalendarioGeral, Long> {
	
}