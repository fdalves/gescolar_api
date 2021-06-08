package br.com.gescolar.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.gescolar.dto.MatriculaDTO;
import br.com.gescolar.model.MatriculaIni;
import br.com.gescolar.repository.MatriculaIniRepository;
import br.com.gescolar.types.StatusMatriculaEnum;

@Service
public class MatriculaIniService {

	@Autowired
	private MatriculaIniRepository matriculaIniRepository;

	public MatriculaDTO salvar(MatriculaDTO matriculaDTO) {
		MatriculaIni matriculaIni = new MatriculaIni();
		matriculaIni.parseDtoToModel(matriculaDTO);
		matriculaIni.setStatus(StatusMatriculaEnum.ATIVAR.toString());
		matriculaIniRepository.save(matriculaIni);
		matriculaDTO.setCodigo(matriculaIni.getCodigo().toString());
		return matriculaDTO;
	}

	public MatriculaDTO buscarPeloCodigo(Long codigo) {
		MatriculaIni matriculaIni = matriculaIniRepository.getOne(codigo);
		return MatriculaIni.parseDtoToDto(matriculaIni);
	}

	public MatriculaDTO atualizar(Long codigo, @Valid MatriculaDTO matriculaDTO) {
		MatriculaIni matriculaIni = matriculaIniRepository.getOne(codigo);
		matriculaIni.parseDtoToModel(matriculaDTO);
		return MatriculaIni.parseDtoToDto(matriculaIniRepository.save(matriculaIni));
	}

	public Page<MatriculaDTO> findByNomeContaining(String nome, Pageable pageable) {
		return matriculaIniRepository
				.findByNomeContaining(nome, pageable)
				.map(MatriculaIni::parseDtoToDto);
	}

}
