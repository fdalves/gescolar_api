package br.com.gescolar.chamada;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gescolar.dto.DisciplinaTurmaDTO;
import br.com.gescolar.dto.TurmaPeriodoDTO;
import br.com.gescolar.service.ChamadaService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChamadaTest {

	@Autowired
	private ChamadaService chamadaService;
	
	@Test
	public void teste() {
		List<DisciplinaTurmaDTO> list = this.chamadaService.listarTurmarProfessor(3l);
		for (DisciplinaTurmaDTO disciplinaTurmaDTO : list) {
			System.out.println(disciplinaTurmaDTO);
		}
		
		Calendar c  = Calendar.getInstance();
		c.setTime(new Date());
		
		for (DisciplinaTurmaDTO disciplinaTurmaDTO : list) {
			 List<TurmaPeriodoDTO> list2  = chamadaService.listarPeriodos(disciplinaTurmaDTO.getCodigo(), c.getTime());
			 for (TurmaPeriodoDTO DTO2 : list2) {
				System.out.println(DTO2);
			}
		}
	}
	
}
