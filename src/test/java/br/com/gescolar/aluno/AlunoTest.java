package br.com.gescolar.aluno;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gescolar.repository.AlunoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AlunoTest {

	@Autowired
	private AlunoRepository alunoRepository; 
	
	@Test
	public void exampleTest() {
		
		boolean x = alunoRepository.existsByMatricula("12345");
		
		System.out.println(x);
		
	}
}
