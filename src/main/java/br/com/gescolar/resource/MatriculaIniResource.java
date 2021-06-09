package br.com.gescolar.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.gescolar.dto.AtivarMatrciulaDTO;
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
	
	
	@PostMapping("/ativar")
	public  ResponseEntity<Object> ativar(@Valid @RequestBody AtivarMatrciulaDTO ativarMatrciulaDTO) {
		 matriculaIniService.ativarMatrciula(ativarMatrciulaDTO);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	

	
	@GetMapping("/{codigo}")
	public ResponseEntity<MatriculaDTO> buscarPeloCodigo(@PathVariable Long codigo) {
		MatriculaDTO matriculaDTO = matriculaIniService.buscarPeloCodigo(codigo);
		return matriculaDTO != null ? ResponseEntity.ok(matriculaDTO) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<MatriculaDTO> atualizar(@PathVariable Long codigo, @Valid @RequestBody MatriculaDTO matriculaDTO) {
		MatriculaDTO dto  = matriculaIniService.atualizar(codigo, matriculaDTO);
		return ResponseEntity.ok(dto);
	}

	
	@GetMapping
	public Page<MatriculaDTO> pesquisar(@RequestParam(required = false, defaultValue = "%") String nome,
	        Pageable pageable) {
		return matriculaIniService.findByNomeContaining(nome, pageable);
	}
	
}
