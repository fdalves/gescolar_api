package br.com.gescolar.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.Evento;


public interface EventoRepository  extends JpaRepository<Evento, Long> {
	
	List<Evento> findByTipoEvento(String tipo);
}