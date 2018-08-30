package br.com.gescolar.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import br.com.gescolar.model.Professor;



public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	
	public Page<Professor> findByNomeContaining(String nome, Pageable pageable);

	public boolean existsByCpf(String cpf);
}
