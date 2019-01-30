package br.com.gescolar.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gescolar.model.Professor;
import br.com.gescolar.model.Usuario;
import br.com.gescolar.repository.ProfessorRepository;
import br.com.gescolar.types.TipoUsuarioEnum;

@Service
public class ProfessorService {
	
	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private FotoService fotoService;
	
	public Professor atualizar(Long codigo, Professor professor) {
		Professor professorSalvo = buscarProfessorPeloCodigo(codigo);
		fotoService.atualizar(professorSalvo.getFoto(), professor.getFoto());
		BeanUtils.copyProperties(professor, professorSalvo, "codigo","usuario");
		return professorRepository.save(professorSalvo);
	}

	public Professor buscarProfessorPeloCodigo(Long codigo) {
		Professor professorSalvo = professorRepository.getOne(codigo);
		if (professorSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return professorSalvo;
	}
	
	public Professor salvar(Professor professor) {
		Usuario usuario = usuarioService.gerarUsuarioDefault(professor.getCpf().replaceAll("[^0-9]", ""), TipoUsuarioEnum.PROFESSOR);
		professor.setUsuario(usuario);
		fotoService.salvar(professor.getFoto());
		return this.professorRepository.save(professor);
	}

	public boolean verificaCpf(String cpf, Long codigo) {
		if (codigo != null) {
			Professor professor = this.buscarProfessorPeloCodigo(codigo);
			if (professor.getCpf().equals(cpf)) return false;
		}
		return professorRepository.existsByCpf(cpf);
	}
	
}
