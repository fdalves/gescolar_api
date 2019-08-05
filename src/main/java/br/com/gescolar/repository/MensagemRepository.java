package br.com.gescolar.repository;


import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.gescolar.model.Mensagem;


public interface MensagemRepository  extends JpaRepository<Mensagem, Long> {
	
	@Query("select m from  Mensagem m where m.dataNotificacao <= ?1 and (m.notificado is null or  m.notificado = 0) and (m.error is null or m.error=0 ) and m.notificar = 1")
	List<Mensagem> findMenssagemNotificar(Date dataNotificacao);
}