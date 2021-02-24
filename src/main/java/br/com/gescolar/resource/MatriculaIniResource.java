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

import br.com.gescolar.event.RecursoCriadoEvent;
import br.com.gescolar.model.Aluno;
import br.com.gescolar.model.MatriculaIni;
import br.com.gescolar.repository.MatriculaIniRepository;
import br.com.gescolar.service.MatriculaIniService;

@RestController
@RequestMapping("/matricula")
public class MatriculaIniResource {

	@Autowired
	private MatriculaIniRepository matriculaIniRepository; 

	@Autowired
	private MatriculaIniService matriculaIniService; 
	

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<MatriculaIni> criar(@Valid @RequestBody MatriculaIni matriculaIni, HttpServletResponse response) {
		MatriculaIni matriculaIniSalva = matriculaIniService.salvar(matriculaIni);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, matriculaIniSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(matriculaIniSalva);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<MatriculaIni> buscarPeloCodigo(@PathVariable Long codigo) {
		MatriculaIni matriculaIniSalva = matriculaIniRepository.getOne(codigo);
		return matriculaIniSalva != null ? ResponseEntity.ok(matriculaIniSalva) : ResponseEntity.notFound().build();
	}

	

	@PutMapping("/{codigo}")
	public ResponseEntity<MatriculaIni> atualizar(@PathVariable Long codigo, @Valid @RequestBody MatriculaIni matriculaIni) {
		MatriculaIni matriculaIniSalva = matriculaIniService.atualizar(codigo, matriculaIni);
		return ResponseEntity.ok(matriculaIniSalva);
	}

	@GetMapping
	public Page<Aluno> pesquisar(@RequestParam(required = false, defaultValue = "%") String nome,
	        Pageable pageable) {
		return matriculaIniRepository.findByNomeContaining(nome,  pageable);
	}

	
}
