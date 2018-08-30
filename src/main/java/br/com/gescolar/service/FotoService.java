package br.com.gescolar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.gescolar.storage.S3;

@Service
public class FotoService {

	@Autowired
	private S3 s3;

	public void salvar(String foto) {
		if (StringUtils.hasText(foto)) {
			s3.salvar(foto);
		}
	}
	
	public void atualizar(String fotoSalva, String foto) {
		if (StringUtils.isEmpty(foto)
				&& StringUtils.hasText(fotoSalva)) {
			s3.remover(fotoSalva);
		} else if (StringUtils.hasText(foto)
				&& !foto.equals(fotoSalva)) {
			s3.substituir(fotoSalva, foto);
		}
	}
}
