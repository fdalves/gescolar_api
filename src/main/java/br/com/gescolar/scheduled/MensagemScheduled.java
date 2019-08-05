package br.com.gescolar.scheduled;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.gescolar.firebase.FCMService;
import br.com.gescolar.firebase.dto.PushNotificationRequest;
import br.com.gescolar.model.Mensagem;
import br.com.gescolar.repository.MensagemRepository;

@Profile("cron")
@Component
@EnableScheduling
public class MensagemScheduled {
	
	Logger logger = LoggerFactory.getLogger(MensagemScheduled.class);
	
	@Autowired
	private MensagemRepository mensagemRepository;
	@Autowired
	private FCMService fcmService;
	
	
	private final long SEGUNDO = 1000;
	private final long MINUTO = SEGUNDO * 60;
	//private final long HORA = MINUTO * 60;
	
	@Scheduled(fixedDelay = (MINUTO * 2))
	public void processMensagens() {
		logger.info("into processMensagens...");
		List<Mensagem> list = mensagemRepository.findMenssagemNotificar(new Date());
		if (list == null || list.isEmpty()) {
			logger.info("no menssages to processMensagens...");
			return;
		}
		
		for (Mensagem mensagem : list) {
			PushNotificationRequest request = new PushNotificationRequest();
			request.setMessage(mensagem.getMensagem());
			request.setTitle(mensagem.getTitulo());
			request.setToken(mensagem.getTo().getDiviceId());
			request.setTopic(" ");
			try {
				logger.info(String.format("send message to user: %s ------- device id: %s",mensagem.getTo().getLogin(), mensagem.getTo().getDiviceId()));
				fcmService.sendMessageToToken(request);
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				mensagem.setError(true);
				mensagem.setMsgErro(e.getMessage());
				mensagemRepository.save(mensagem);
			}
		}
		
	}
}
