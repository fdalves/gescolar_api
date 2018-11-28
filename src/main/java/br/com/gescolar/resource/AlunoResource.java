package br.com.gescolar.resource;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gescolar.event.RecursoCriadoEvent;
import br.com.gescolar.model.Aluno;
import br.com.gescolar.repository.AlunoRepository;
import br.com.gescolar.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoResource {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<Aluno> criar(@Valid @RequestBody Aluno aluno, HttpServletResponse response) {
		Aluno alunoSalvo = alunoService.salvar(aluno);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, alunoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(alunoSalvo);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Aluno> buscarPeloCodigo(@PathVariable Long codigo) {
		Aluno aluno = alunoRepository.getOne(codigo);
		return aluno != null ? ResponseEntity.ok(aluno) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		alunoRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Aluno> atualizar(@PathVariable Long codigo, @Valid @RequestBody Aluno aluno) {
		Aluno alunoSalvo = alunoService.atualizar(codigo, aluno);
		return ResponseEntity.ok(alunoSalvo);
	}

	@GetMapping
	public Page<Aluno> pesquisar(@RequestParam(required = false, defaultValue = "%") String nome,
	        @RequestParam(required = false, defaultValue = "%") String matricula,
	        Pageable pageable) {
		return alunoRepository.findByNomeContainingAndMatriculaContaining(nome, matricula, pageable);
	}

	@GetMapping("/matriculaExistente/{matricula}")
	@ResponseBody
	public ResponseEntity<Boolean> isValid(@PathVariable String matricula, @RequestParam(required = false, defaultValue = "") String codigo) {
		Long codigoLong = null;
		if (!StringUtils.isEmpty(codigo) && !codigo.equals("null")) {
			codigoLong = new Long(codigo);
		}
		return ResponseEntity.ok(alunoService.verificaMatricula(matricula, codigoLong));
	}

}
