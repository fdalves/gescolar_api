package br.com.gescolar.professor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gescolar.model.Professor;
import br.com.gescolar.service.ProfessorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfessorTest {

	@Autowired
	private ProfessorService professorService; 
	
	@Test
	public void exampleTest() {
		
		Professor professor = new Professor();
		professor.setNome("Teste ...");
		professorService.salvar(professor);
	}
}
