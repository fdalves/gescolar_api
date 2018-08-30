package br.com.gescolar.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gescolar.model.Responsavel;
import br.com.gescolar.repository.ResponsavelRepository;

@Service
public class ResponsavelService {

	@Autowired
	private ResponsavelRepository responsavelRepository;
	
	
	
	public Responsavel salvar(Responsavel responsavel) {
		return responsavelRepository.save(responsavel);
	}
	
	public Responsavel atualizar(Long codigo, Responsavel responsavel) {
		Responsavel responsavelSalvo = buscarResponsavelPeloCodigo(codigo);
		BeanUtils.copyProperties(responsavel, responsavelSalvo, "codigo","usuario");
		return responsavelRepository.save(responsavelSalvo);
	}
	
	public Responsavel deletar() {
		return null;
	}
	
	public Responsavel buscarResponsavelPeloCodigo(Long codigo) {
		Responsavel responsavelSalvo = responsavelRepository.getOne(codigo);
		if (responsavelSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return responsavelSalvo;
	}

	public boolean verificaCpf(String cpf, Long codigo) {
		if (codigo != null) {
			Responsavel responsavel = this.buscarResponsavelPeloCodigo(codigo);
			if (responsavel.getCpf().equals(cpf)) return false;
		}
		return responsavelRepository.existsByCpf(cpf);
	}
	
}
