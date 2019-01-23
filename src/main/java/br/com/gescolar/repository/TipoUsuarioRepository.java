package br.com.gescolar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gescolar.model.TipoUsuario;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long>{

	TipoUsuario findByDescTipoUsuario(String tipoUsuario);
	
}
