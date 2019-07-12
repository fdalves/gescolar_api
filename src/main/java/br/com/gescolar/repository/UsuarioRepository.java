package br.com.gescolar.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.TipoUsuario;
import br.com.gescolar.model.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	
	Optional<Usuario> findByLogin(String login);

	List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);
		
	
}
