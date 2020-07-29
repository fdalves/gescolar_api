package br.com.gescolar.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gescolar.dto.EventoDTO;
import br.com.gescolar.service.CalendarioService;

@RestController
@RequestMapping("/calendario")
public class CalendarioResource {
	
	@Autowired
	private CalendarioService calendarioService; 
	
	
	@PostMapping
	public ResponseEntity<EventoDTO> criar(@Valid @RequestBody EventoDTO eventoDTO, HttpServletResponse response) {
		System.out.println(eventoDTO);
		calendarioService.saveEvento(eventoDTO);
		return null;
	}

	
}
