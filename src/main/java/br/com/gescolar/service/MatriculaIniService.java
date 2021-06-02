package br.com.gescolar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gescolar.dto.MatriculaDTO;
import br.com.gescolar.model.MatriculaIni;
import br.com.gescolar.repository.MatriculaIniRepository;

@Service
public class MatriculaIniService {

	@Autowired
	private MatriculaIniRepository matriculaIniRepository;
	
	
	public MatriculaDTO salvar(MatriculaDTO matriculaDTO) {
		MatriculaIni matriculaIni = new MatriculaIni();
		matriculaIni.parseDtoToModel(matriculaDTO);
		matriculaIniRepository.save(matriculaIni);
		matriculaDTO.setCodigo(matriculaIni.getCodigo().toString());
		return matriculaDTO;
	
	}


	public MatriculaDTO buscarPeloCodigo(Long codigo) {
		MatriculaIni matriculaIni = matriculaIniRepository.getOne(codigo);
		return matriculaIni.parseDtoToDto();
	}

	
	
}
