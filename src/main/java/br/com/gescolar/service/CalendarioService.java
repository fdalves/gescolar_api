package br.com.gescolar.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import br.com.gescolar.repository.UsuarioRepository;

@Service
public class CalendarioService {

	Logger logger = LoggerFactory.getLogger(CalendarioService.class);
	
	@Autowired
	private MensageService mensageService; 
	@Autowired
	private CalendarioGeralRepository calendarioGeralRepository;
	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	public void saveEvento(EventoDTO dto) {
		
		Evento evento = new Evento();
		evento.setTitulo(dto.getDescEvento());
		evento.setDataInical(this.parseDate(dto.getDataIni()));
		evento.setDataFinal(this.parseDate(dto.getDataFim()));
		
		if (dto.getSelectedOpcao().equals("GERAL")) {
			evento.setTipoEvento("GERAL");
			this.eventoRepository.save(evento);
			CalendarioGeral calendarioGeral = new CalendarioGeral();
			calendarioGeral.setEvento(evento);
			this.calendarioGeralRepository.save(calendarioGeral);
			
			Mensagem mensagem = new Mensagem();
			mensagem.setFrom(usuarioRepository.getOne(dto.getCodigoUsuario()));
			mensagem.setMensagem(dto.getDescEvento());
			mensagem.setTitulo("Novo evento adicionado ao calentario escolar!");
			mensagem.setDataCadastro(new Date());
			mensageService.saveMensagemGeral(mensagem,this.parseList(dto.getDatasNotificar()));
		}
		
	}


	private List<Date> parseList(List<String> datasNotificar) {
		List<Date> list = new ArrayList<>();
		if (datasNotificar != null) {
			for (String date : datasNotificar) {
				list.add(this.parseDate(date));
			}
		}
		return list;
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
