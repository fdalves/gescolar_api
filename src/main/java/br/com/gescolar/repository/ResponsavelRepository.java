package br.com.gescolar.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.Responsavel;


public interface ResponsavelRepository extends JpaRepository<Responsavel, Long>{

	boolean existsByCpf(String cpf);

	Page<Responsavel> findByNomeContaining(String nome, Pageable pageable);
	
	
	
}
