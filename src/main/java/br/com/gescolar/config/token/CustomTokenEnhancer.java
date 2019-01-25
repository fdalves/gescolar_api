package br.com.gescolar.config.token;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import br.com.gescolar.model.Professor;
import br.com.gescolar.repository.ProfessorRepository;
import br.com.gescolar.security.UsuarioSistema;
import br.com.gescolar.types.TipoUsuarioEnum;


public class CustomTokenEnhancer implements TokenEnhancer {

	
	@Autowired
	private ProfessorRepository professorRepository; 
	
	@Override
	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
		UsuarioSistema usuarioSistema = (UsuarioSistema) authentication.getPrincipal();
		Map<String, Object> addInfo = new HashMap<>();
		addInfo.put("tipoUsuario", usuarioSistema.getUsuario().getTipoUsuario());
		addInfo.put("codigoUsuario", usuarioSistema.getUsuario().getCodigo());
		
		if (usuarioSistema.getUsuario().getTipoUsuario().getDescTipoUsuario().equalsIgnoreCase(TipoUsuarioEnum.PROFESSOR.toString())) {
			List<Professor> list = professorRepository.findByUsuario(usuarioSistema.getUsuario());
			if (list != null && !list.isEmpty()) addInfo.put("codigoProfessor", list.get(0).getCodigo());
		}
		
		((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(addInfo);
		return accessToken;
	}

}
