package br.com.gescolar.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.Disciplina;


public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
		
}
