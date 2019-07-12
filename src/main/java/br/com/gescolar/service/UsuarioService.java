package br.com.gescolar.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gescolar.model.TipoUsuario;
import br.com.gescolar.model.Usuario;
import br.com.gescolar.repository.TipoUsuarioRepository;
import br.com.gescolar.repository.UsuarioRepository;
import br.com.gescolar.security.util.GeradorSenha;
import br.com.gescolar.types.TipoUsuarioEnum;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;

	
	
	public Usuario gerarUsuarioDefault(String login,TipoUsuarioEnum tipoUsuarioEnum) {
		TipoUsuario tipoUsuario = tipoUsuarioRepository.findByDescTipoUsuario(tipoUsuarioEnum.toString());
		Usuario usuario = new Usuario();
		usuario.setTipoUsuario(tipoUsuario);
		usuario.setLogin(login);
		usuario.setSenha(GeradorSenha.gerarCryptSenha(login));
		return usuarioRepository.save(usuario);
	}
	
	
	public Usuario getUsuarioAdm() {
		TipoUsuario tipoUsuario = tipoUsuarioRepository.findByDescTipoUsuario("ADM");
		List<Usuario> list = usuarioRepository.findByTipoUsuario(tipoUsuario);
		if (list != null  && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}
}