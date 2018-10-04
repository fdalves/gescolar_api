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
import br.com.gescolar.model.Professor;
import br.com.gescolar.repository.ProfessorRepository;
import br.com.gescolar.service.ProfessorService;

@RestController
@RequestMapping("/professores")
public class ProfessorResource {

	@Autowired
	private ProfessorRepository professorRepository;

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<Professor> criar(@Valid @RequestBody Professor professor, HttpServletResponse response) {
		Professor professorSalva = professorService.salvar(professor);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, professorSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(professorSalva);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Professor> buscarPeloCodigo(@PathVariable Long codigo) {
		Professor professor = professorRepository.getOne(codigo);
		return professor != null ? ResponseEntity.ok(professor) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		professorRepository.deleteById(codigo);
	}

	@PutMapping("/{codigo}")
	public ResponseEntity<Professor> atualizar(@PathVariable Long codigo, @Valid @RequestBody Professor professor) {
		Professor professorSalva = professorService.atualizar(codigo, professor);
		return ResponseEntity.ok(professorSalva);
	}

	@GetMapping
	public Page<Professor> pesquisar(@RequestParam(required = false, defaultValue = "%") String nome, Pageable pageable) {
		return professorRepository.findByNomeContaining(nome, pageable);
	}

	@GetMapping("/cpfExistente")
	@ResponseBody
	public ResponseEntity<Boolean> isValid(@RequestParam(required = true) String cpf, @RequestParam(required = false, defaultValue = "") String codigo) {
		Long codigoLong = null;
		if (!StringUtils.isEmpty(codigo) && !codigo.equals("null")) {
			codigoLong = new Long(codigo);
		}
		return ResponseEntity.ok(professorService.verificaCpf(cpf, codigoLong));
	}
}
