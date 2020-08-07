package br.com.gescolar.resource;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gescolar.dto.CalendarioFiltro;
import br.com.gescolar.dto.DataCalendarioDTO;
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
	
	@PostMapping("/carregarEventos")
	public List<DataCalendarioDTO> carregar(@Valid @RequestBody CalendarioFiltro filtro) {
		DataCalendarioDTO calendarioDTO = new DataCalendarioDTO();
		calendarioDTO.setId("1");
		calendarioDTO.setTitle("teste...");
		calendarioDTO.setStart("2020-08-06T16:00:00");
		calendarioDTO.setEnd("2020-08-08T10:00:00");
		List<DataCalendarioDTO> list = new ArrayList<>();
		list.add(calendarioDTO);
		return list;
	}

	
}
