package br.com.gescolar.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.Mensagem;


public interface MensagemRepository  extends JpaRepository<Mensagem, Long> {
	


}