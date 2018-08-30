package br.com.gescolar.storage;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.ObjectTagging;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.SetObjectTaggingRequest;
import com.amazonaws.services.s3.model.Tag;

import br.com.gescolar.service.ImageService;

@Component
public class S3 {
	
	private static final Logger logger = LoggerFactory.getLogger(S3.class);
	
	@Value("${s3.bucket}")
	private String bucket;
	
	@Value("${img.profile.size}")
	private Integer size;
	
	@Autowired
	private AmazonS3 amazonS3;
	@Autowired
	private ImageService imageService;
	
	public String salvarTemporariamente(MultipartFile arquivo) throws IOException {
		AccessControlList acl = new AccessControlList();
		acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
		
		String nomeUnico = gerarNomeUnico(arquivo.getOriginalFilename());
		BufferedImage jpgImage = imageService.getJpgImageFromFile(arquivo);
		jpgImage = imageService.cropSquare(jpgImage);
		jpgImage = imageService.resize(jpgImage, size);
		
		
		InputStream inputImage = imageService.getInputStream(jpgImage,"jpg");
		
			
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(arquivo.getContentType());
		objectMetadata.setContentLength(inputImage.available());
		
		PutObjectRequest putObjectRequest = new PutObjectRequest(
				bucket,
				nomeUnico,
				inputImage, 
				objectMetadata)
				.withAccessControlList(acl);
		
		putObjectRequest.setTagging(new ObjectTagging(
				Arrays.asList(new Tag("expirar", "true"))));
		
		amazonS3.putObject(putObjectRequest);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Arquivo {} enviado com sucesso para o S3.", 
					arquivo.getOriginalFilename());
		}
		
		return nomeUnico;
	}
	
	public String configurarUrl(String objeto) {
		return "\\\\" + bucket +
				".s3.amazonaws.com/" + objeto;
	}
	
	public void salvar(String objeto) {
		SetObjectTaggingRequest setObjectTaggingRequest = new SetObjectTaggingRequest(
				bucket, 
				objeto, 
				new ObjectTagging(Collections.emptyList()));
		
		amazonS3.setObjectTagging(setObjectTaggingRequest);
	}

	public void remover(String objeto) {
		DeleteObjectRequest deleteObjectRequest = new DeleteObjectRequest(
				bucket, objeto);
		
		amazonS3.deleteObject(deleteObjectRequest);
	}
	
	public void substituir(String objetoAntigo, String objetoNovo) {
		if (StringUtils.hasText(objetoAntigo)) {
			this.remover(objetoAntigo);
		}
		
		salvar(objetoNovo);
	}
	
	private String gerarNomeUnico(String originalFilename) {
		return UUID.randomUUID().toString()	+ "_" + ".jpg";
	}


}
