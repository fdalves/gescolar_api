package br.com.gescolar.resource;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.gescolar.dto.Foto;
import br.com.gescolar.storage.S3;



@RestController
@RequestMapping("/fotos")
public class FotoResource {
	
	@Autowired
	private S3 s3;
	
	@PostMapping("/upload")
	public Foto upload(@RequestParam MultipartFile anexo) throws IOException {
		String nome = s3.salvarTemporariamente(anexo);
		return new Foto(nome, s3.configurarUrl(nome));
	}

}
