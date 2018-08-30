package br.com.gescolar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
	@Value("${senha.default}")
	private String senhaDefault;
	
	public Usuario gerarUsuarioDefault(String login,TipoUsuarioEnum tipoUsuarioEnum) {
		TipoUsuario tipoUsuario = tipoUsuarioRepository.findByTipoUsuario(tipoUsuarioEnum.toString());
		Usuario usuario = new Usuario();
		usuario.setTipoUsuario(tipoUsuario);
		usuario.setLogin(login);
		usuario.setSenha(this.getSenhaDefault());
		return usuarioRepository.save(usuario);
	}

	private String getSenhaDefault() {
		return GeradorSenha.gerarCryptSenha(senhaDefault);
	}
}
