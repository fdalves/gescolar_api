package br.com.gescolar.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gescolar.dto.EventoDTO;

@RestController
@RequestMapping("/calendario")
public class CalendarioResource {
	
	
	@PostMapping
	public ResponseEntity<EventoDTO> criar(@Valid @RequestBody EventoDTO eventoDTO, HttpServletResponse response) {
		//Aluno alunoSalvo = alunoService.salvar(aluno);
			
		//return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
		System.out.println(eventoDTO);
		return null;
	}

	
}
