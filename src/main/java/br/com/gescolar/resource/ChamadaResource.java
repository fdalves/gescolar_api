package br.com.gescolar.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gescolar.dto.DisciplinaTurmaDTO;
import br.com.gescolar.service.ChamadaService;

@RestController
@RequestMapping("/chamada")
public class ChamadaResource {

	@Autowired
	private ChamadaService chamadaService; 
	
	@GetMapping("/getTurmasProfessor/{codigoProfessor}")
	public List<DisciplinaTurmaDTO> getTurmasProfessor(@PathVariable Long codigoProfessor) {
		return this.chamadaService.listarTurmarProfessor(codigoProfessor); 
	}
}
