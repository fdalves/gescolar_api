package br.com.gescolar.resource;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gescolar.dto.ChamadaDTO;
import br.com.gescolar.dto.DisciplinaTurmaDTO;
import br.com.gescolar.dto.RequestChamadaDTO;
import br.com.gescolar.dto.TurmaPeriodoDTO;
import br.com.gescolar.model.Aluno;
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
	
	
	@PostMapping("/getPeriodos")
	public List<TurmaPeriodoDTO> getPeriodos(@RequestBody RequestChamadaDTO dto) {
		Date date = Date.from(dto.getData().atStartOfDay(ZoneId.systemDefault()).toInstant());
		return this.chamadaService.listarPeriodos(dto.getCodigoTurmaDiciplina(),date); 
	}
	
	@GetMapping("/getAlunos/{codigoTurmaDisciplina}")
	public List<Aluno> getAlunos(@PathVariable Long codigoTurmaDisciplina) {
		return this.chamadaService.getAlunos(codigoTurmaDisciplina); 
	}
	
	
	@PostMapping
	public ChamadaDTO chamada(@RequestBody ChamadaDTO chamadaDTO) {
		
		return this.chamadaService.cadastrarChamada(chamadaDTO);
		
	}
	
	
	
}
