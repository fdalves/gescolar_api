package br.com.gescolar.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.Aluno;
import br.com.gescolar.model.MatriculaIni;


public interface MatriculaIniRepository  extends JpaRepository<MatriculaIni, Long> {

	Page<Aluno> findByNomeContaining(String nome, Pageable pageable);
	
	

}