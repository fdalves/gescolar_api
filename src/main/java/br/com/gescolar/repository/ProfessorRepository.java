package br.com.gescolar.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import br.com.gescolar.model.Professor;
import br.com.gescolar.model.Usuario;
import java.util.List;



public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	
	public Page<Professor> findByNomeContaining(String nome, Pageable pageable);
	
	public List<Professor> findByUsuario(Usuario usuario);

	public boolean existsByCpf(String cpf);
}
