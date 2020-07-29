package br.com.gescolar.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.Evento;


public interface EventoRepository  extends JpaRepository<Evento, Long> {
	
}