package br.com.gescolar.repository.listener;

import javax.persistence.PostLoad;

import org.springframework.util.StringUtils;

import br.com.gescolar.GescolarApplication;
import br.com.gescolar.storage.S3;

public class UrlFotoListener {

	@PostLoad
	public void postLoad(Object object) {

		if (object instanceof S3UrlFoto) {
			S3UrlFoto s3UrlFotoModel = ((S3UrlFoto) object);
			if (StringUtils.hasText(s3UrlFotoModel.getFoto())) {
				S3 s3 = GescolarApplication.getBean(S3.class);
				s3UrlFotoModel.setUrlFoto(s3.configurarUrl(s3UrlFotoModel.getFoto()));
			}
		}
	}
}
