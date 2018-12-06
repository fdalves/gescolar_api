package br.com.gescolar.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.gescolar.model.Aluno;
import br.com.gescolar.model.Usuario;
import br.com.gescolar.repository.AlunoRepository;
import br.com.gescolar.repository.TurmaRepository;
import br.com.gescolar.types.TipoUsuarioEnum;

@Service
public class AlunoService {

	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private FotoService fotoService;
	@Autowired
	private TurmaRepository turmaRepository;
	
	/**
	 * salvar
	 * @param aluno
	 * @return Aluno
	 */
	public Aluno salvar(Aluno aluno) {
		Usuario usuario = usuarioService.gerarUsuarioDefault(aluno.getMatricula(), TipoUsuarioEnum.ALUNO_RESPONSSAVEL);
		aluno.setUsuario(usuario);
		fotoService.salvar(aluno.getFoto());
		return alunoRepository.save(aluno);
	}
	
	/**
	 * atualizar
	 * @param codigo
	 * @param aluno
	 * @return Aluno
	 */
	public Aluno atualizar(Long codigo, Aluno aluno) {
		Aluno alunoSalvo = buscarAlunoPeloCodigo(codigo);
		setResponsaveis(aluno, alunoSalvo);
		fotoService.atualizar(alunoSalvo.getFoto(), aluno.getFoto());
		BeanUtils.copyProperties(aluno, alunoSalvo, "idAluno","usuario","responsaveis");
		return alunoRepository.save(alunoSalvo);
	}
	
	/**
	 * setResponsaveis
	 * @param aluno
	 * @param alunoSalvo
	 */
	private void setResponsaveis(Aluno aluno, Aluno alunoSalvo) {
		if (!alunoSalvo.getResponsaveis().isEmpty()) {
			alunoSalvo.getResponsaveis().clear();
			alunoSalvo.getResponsaveis().addAll(aluno.getResponsaveis());
		} else {
			alunoSalvo.setResponsaveis(aluno.getResponsaveis());
		}
	}
	
	/**
	 * buscarAlunoPeloCodigo
	 * @param codigo
	 * @return Aluno
	 */
	public Aluno buscarAlunoPeloCodigo(Long codigo) {
		Aluno AlunoSalvo = alunoRepository.getOne(codigo);
		if (AlunoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return AlunoSalvo;
	}
	
	/**
	 * verificaMatricula
	 * @param matricula
	 * @param codigo
	 * @return boolean
	 */
	public boolean verificaMatricula(String matricula, Long codigo) {
		if (codigo != null) {
			Aluno aluno = this.buscarAlunoPeloCodigo(codigo);
			if (aluno.getMatricula().equals(matricula)) return false;
		}
		return alunoRepository.existsByMatricula(matricula);
	}
	
	/**
	 * pesquisarPorTurma
	 * @param codigoTruma
	 * @return List<Aluno>
	 */
	public List<Aluno> pesquisarPorTurma(Long codigoTruma) {
		return this.alunoRepository.findByTurma(this.turmaRepository.getOne(codigoTruma));
		
	}
	
}
