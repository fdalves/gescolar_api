package br.com.gescolar.resource;

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

import br.com.gescolar.dto.MatriculaDTO;
import br.com.gescolar.event.RecursoCriadoEvent;
import br.com.gescolar.service.MatriculaIniService;

@RestController
@RequestMapping("/matricula")
public class MatriculaIniResource {

	@Autowired
	private MatriculaIniService matriculaIniService; 
	

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<MatriculaDTO> criar(@Valid @RequestBody MatriculaDTO matriculaIni, HttpServletResponse response) {
		MatriculaDTO matriculaIniSalva = matriculaIniService.salvar(matriculaIni);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, Long.valueOf(matriculaIniSalva.getCodigo())));
		return ResponseEntity.status(HttpStatus.CREATED).body(matriculaIniSalva);
	}

	

	
}
