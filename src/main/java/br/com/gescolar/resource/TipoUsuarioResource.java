package br.com.gescolar.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.gescolar.model.TipoUsuario;
import br.com.gescolar.repository.TipoUsuarioRepository;

@RestController
@RequestMapping("/tipoUsuario")
public class TipoUsuarioResource {
	
	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;  
	
	@GetMapping
	public List<TipoUsuario> listar() {
		return this.tipoUsuarioRepository.findAll();
	}
	
	
	@GetMapping("/{codigo}")
	public ResponseEntity<TipoUsuario> buscarPeloCodigo(@PathVariable Long codigo) {
		TipoUsuario tipoUsuario  = tipoUsuarioRepository.getOne(codigo);
		return tipoUsuario != null ? ResponseEntity.ok(tipoUsuario) : ResponseEntity.notFound().build();
	}

	
	@PostMapping
	public ResponseEntity<TipoUsuario> criar(@Valid @RequestBody TipoUsuario lancamento, HttpServletResponse response) {
		TipoUsuario lancamentoSalvo = tipoUsuarioRepository.save(lancamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}
	
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		tipoUsuarioRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<TipoUsuario> atualizar(@PathVariable Long codigo, @Valid @RequestBody TipoUsuario lancamento) {
		try {
			TipoUsuario lancamentoSalvo = tipoUsuarioRepository.save(lancamento);
			return ResponseEntity.ok(lancamentoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	

}
