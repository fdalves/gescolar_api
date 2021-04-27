package br.com.gescolar.cobranca;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArquivoCnab240SicrediTest {

	@Autowired
	private ArquivoCnab240Sicredi sicredi;
	
	@Test
	public void teste_cnab_240() throws Exception {
		sicredi.carregarArquivo();
		sicredi.printFile();
		Assert.assertEquals("", "");
	}
}
