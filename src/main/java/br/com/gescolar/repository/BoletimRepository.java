package br.com.gescolar.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.Boletim;


public interface BoletimRepository extends JpaRepository<Boletim, Long> {
	
}
