package br.com.gescolar.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.Turma;



public interface TurmaRepository extends JpaRepository<Turma, Long> {

	public Page<Turma> findByNomeContaining(String nome, Pageable pageable);
	
	
}
