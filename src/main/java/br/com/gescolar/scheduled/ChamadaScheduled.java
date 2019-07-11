package br.com.gescolar.scheduled;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.gescolar.dto.ProcessChamadaDTO;
import br.com.gescolar.repository.ChamadaRepository;

@Profile("cron")
@Component
@EnableScheduling
public class ChamadaScheduled {

	@Autowired
	private ChamadaRepository chamadaRepository;
	
	private final long SEGUNDO = 1000;
	private final long MINUTO = SEGUNDO * 60;
	//private final long HORA = MINUTO * 60;
	
	
	 @Scheduled(fixedDelay = (MINUTO * 1))
	public void processChamada() {
		List<ProcessChamadaDTO> list = chamadaRepository.processChamada();
		for (ProcessChamadaDTO processChamadaDTO : list) {
			System.out.println(processChamadaDTO);
		}
	}
}
