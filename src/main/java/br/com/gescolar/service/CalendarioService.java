package br.com.gescolar.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gescolar.dto.EventoDTO;
import br.com.gescolar.exception.GescolarExcption;
import br.com.gescolar.model.CalendarioGeral;
import br.com.gescolar.model.Evento;
import br.com.gescolar.model.Mensagem;
import br.com.gescolar.repository.CalendarioGeralRepository;
import br.com.gescolar.repository.EventoRepository;

@Service
public class CalendarioService {

	Logger logger = LoggerFactory.getLogger(CalendarioService.class);
	
	@Autowired
	private CalendarioGeralRepository calendarioGeralRepository;
	@Autowired
	private EventoRepository eventoRepository;
	
	
	public void saveEvento(EventoDTO dto) {
		if (dto != null && dto.getSelectedOpcao().equals("GERAL")) {
			Evento evento = new Evento();
			evento.setTitulo(dto.getDescEvento());
			evento.setDataInical(this.parseDate(dto.getDataIni()));
			evento.setDataFinal(this.parseDate(dto.getDataFim()));
			evento.setTipoEvento("GERAL");
			this.eventoRepository.save(evento);
			
			CalendarioGeral calendarioGeral = new CalendarioGeral();
			calendarioGeral.setEvento(evento);
			this.calendarioGeralRepository.save(calendarioGeral);
			
			Mensagem mensagem = new Mensagem();
			//mensagem.
			
		}
		
	}


	private Date parseDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			throw new GescolarExcption("Erro serviço, data invalida..");
		}
	}
	
	
}
