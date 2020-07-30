package br.com.gescolar.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.Notificacao;


public interface NotificacaoRepository  extends JpaRepository<Notificacao, Long> {
	
}