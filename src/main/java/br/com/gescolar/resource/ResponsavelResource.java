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
import br.com.gescolar.model.Responsavel;
import br.com.gescolar.repository.ResponsavelRepository;
import br.com.gescolar.service.ResponsavelService;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelResource {

	@Autowired
	private ResponsavelRepository responsavelRepository;
	
	@Autowired
	private ResponsavelService responsavelService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<Responsavel> criar(@Valid @RequestBody Responsavel responsavel, HttpServletResponse response) {
		Responsavel responsavelSalva = responsavelService.salvar(responsavel);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, responsavelSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(responsavelSalva);
	}

	@GetMapping("/{codigo}")
	public ResponseEntity<Responsavel> buscarPeloCodigo(@PathVariable Long codigo) {
		Responsavel responsavel = responsavelRepository.getOne(codigo);
		return responsavel != null ? ResponseEntity.ok(responsavel) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		responsavelRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Responsavel> atualizar(@PathVariable Long codigo, @Valid @RequestBody Responsavel responsavel) {
		Responsavel responsavelSalva = responsavelService.atualizar(codigo, responsavel);
		return ResponseEntity.ok(responsavelSalva);
	}
	
	@GetMapping
	public Page<Responsavel> pesquisar(@RequestParam(required = false, defaultValue = "%") String nome, Pageable pageable) {
		return responsavelRepository.findByNomeContaining(nome, pageable);
	}

	
	@GetMapping("/cpfExistente")
	@ResponseBody
	public ResponseEntity<Boolean> isValid(@RequestParam (required = true, defaultValue = "") String cpf, @RequestParam (required = false, defaultValue = "") String codigo) {
		Long codigoLong = null;
		if (!StringUtils.isEmpty(codigo)) codigoLong = new Long(codigo); 
		return ResponseEntity.ok(responsavelService.verificaCpf(cpf,codigoLong));
	}
}
