package br.com.gescolar.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gescolar.model.Usuario;
import br.com.gescolar.repository.UsuarioRepository;

@RestController
@RequestMapping("/device")
public class DeviceResource {
	
	Logger logger = LoggerFactory.getLogger(DeviceResource.class);
	
	
	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@GetMapping("atualiza/{codigoUsuario}/{token}")
	public void atualizaTokenDevice(@PathVariable Long codigoUsuario,@PathVariable String token) {
		logger.info(String.format("into atualizaTokenDevice usuario: %s , token: %s",codigoUsuario,token));
		Usuario usuario = usuarioRepository.getOne(codigoUsuario);
		usuario.setDiviceId(token);
		usuarioRepository.save(usuario);
	}
}
