package br.com.gescolar.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gescolar.dto.CalendarioFiltro;
import br.com.gescolar.dto.DataCalendarioDTO;
import br.com.gescolar.dto.EventoDTO;
import br.com.gescolar.event.RecursoCriadoEvent;
import br.com.gescolar.model.Evento;
import br.com.gescolar.service.CalendarioService;

@RestController
@RequestMapping("/calendario")
public class CalendarioResource {
	
	@Autowired
	private CalendarioService calendarioService; 
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<Evento> criar(@Valid @RequestBody EventoDTO eventoDTO, HttpServletResponse response) {
		Evento evento =  calendarioService.saveEvento(eventoDTO);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, evento.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(evento);
	}
	
	@PostMapping("/carregarEventos")
	public List<DataCalendarioDTO> carregar(@Valid @RequestBody CalendarioFiltro filtro) {
		return calendarioService.carregar(filtro);
	}

	
}
