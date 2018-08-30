package br.com.gescolar.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gescolar.dto.Serie;
import br.com.gescolar.event.RecursoCriadoEvent;
import br.com.gescolar.model.Turma;
import br.com.gescolar.repository.TurmaRepository;
import br.com.gescolar.service.TurmaService;

@RestController
@RequestMapping("/turmas")
public class TurmaResource {

	@Autowired
	private TurmaRepository turmaRepository;
	
	@Autowired
	private TurmaService turmaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<Turma> criar(@Valid @RequestBody Turma turma, HttpServletResponse response) {
		Turma turmaSalvo = turmaService.salvar(turma);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, turmaSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(turmaSalvo);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Turma> buscarPeloCodigo(@PathVariable Long codigo) {
		Turma turma = turmaRepository.getOne(codigo);
		return turma != null ? ResponseEntity.ok(turma) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/listar")
	public List<Turma> listar() {
		return turmaRepository.findAll();
	}
	
	@GetMapping("/series")
	public List<Serie> listarSeries() {
		Serie s = new Serie();
		return  s.listarTodasSeries();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		turmaRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Turma> atualizar(@PathVariable Long codigo, @Valid @RequestBody Turma turma) {
		Turma turmaSalva = turmaService.atualizar(codigo, turma);
		return ResponseEntity.ok(turmaSalva);
	}
	
	@GetMapping
	public Page<Turma> pesquisar(@RequestParam(required = false, defaultValue = "%") String nome, Pageable pageable) {
		return turmaRepository.findByNomeContaining(nome, pageable);
	}
	

}
