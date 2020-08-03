package br.com.gescolar.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gescolar.model.Mensagem;
import br.com.gescolar.model.Notificacao;
import br.com.gescolar.model.Usuario;
import br.com.gescolar.repository.MensagemRepository;
import br.com.gescolar.repository.NotificacaoRepository;
import br.com.gescolar.repository.UsuarioRepository;

@Service
public class MensageService {

	@Autowired
	private UsuarioRepository  usuarioRepository;
	@Autowired
	private MensagemRepository mensagemRepository;
	@Autowired
	private NotificacaoRepository notificacaoRepository;
	
	public void saveMensagemGeral(Mensagem mensagem, List<Date> parseList) {
		List<Usuario> lisUsuarios = usuarioRepository.findAll();
		this.saveMensagem(lisUsuarios, mensagem, parseList);
	}
	
	public void saveMensagem(List<Usuario> list, Mensagem mensagem, List<Date> parseList) {
		for (Usuario usuarioTo : list) {
			Mensagem mensagemSave = this.clone(mensagem);
			mensagemSave.setTo(usuarioTo);
			mensagemSave.setNotificar(!parseList.isEmpty());
			this.mensagemRepository.save(mensagemSave);
			
			if (mensagemSave.getNotificar().booleanValue()) {
				for (Date date : parseList) {
					Notificacao notificacao = new Notificacao();
					notificacao.setDataNotificacao(date);
					notificacao.setMensagem(mensagemSave);
					this.notificacaoRepository.save(notificacao);
				}
			}
		}
	}
	
	
	private Mensagem clone(Mensagem mensagemBase) {
		Mensagem mensagem = new Mensagem();
		BeanUtils.copyProperties(mensagemBase,mensagem);
		return mensagem;
	}

	

	
	
}
