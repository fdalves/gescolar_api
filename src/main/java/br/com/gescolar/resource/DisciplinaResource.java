package br.com.gescolar.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gescolar.model.Disciplina;
import br.com.gescolar.repository.DisciplinaRepository;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaResource {
	
	@Autowired
	private DisciplinaRepository disciplinaRepository;
	
	@GetMapping
	public List<Disciplina> listar() {
		return disciplinaRepository.findAll(new Sort(Sort.Direction.ASC, "nome"));
	}

}
