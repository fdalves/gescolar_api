package br.com.gescolar.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gescolar.dto.EventoDTO;
import br.com.gescolar.exception.GescolarExcption;
import br.com.gescolar.model.Aluno;
import br.com.gescolar.model.CalendarioProfessor;
import br.com.gescolar.model.CalendarioTurma;
import br.com.gescolar.model.Evento;
import br.com.gescolar.model.Mensagem;
import br.com.gescolar.model.Professor;
import br.com.gescolar.model.Turma;
import br.com.gescolar.model.Usuario;
import br.com.gescolar.repository.CalendarioProfessorRepository;
import br.com.gescolar.repository.CalendarioTurmaRepository;
import br.com.gescolar.repository.EventoRepository;
import br.com.gescolar.repository.ProfessorRepository;
import br.com.gescolar.repository.TurmaRepository;
import br.com.gescolar.repository.UsuarioRepository;

@Service
public class CalendarioService {

	Logger logger = LoggerFactory.getLogger(CalendarioService.class);
	
	@Autowired
	private MensageService mensageService; 
	@Autowired
	private EventoRepository eventoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ProfessorRepository professorRepository;
	@Autowired
	private CalendarioProfessorRepository calendarioProfessorRepository;
	@Autowired
	private TurmaRepository turmaRepository;
	@Autowired 
	private CalendarioTurmaRepository calendarioTurmaRepository;
	
	
	public void saveEvento(EventoDTO dto) {
		Evento evento = new Evento();
		evento.setTitulo(dto.getDescEvento());
		evento.setDataInical(this.parseDate(dto.getDataIni()));
		evento.setDataFinal(this.parseDate(dto.getDataFim()));
		Mensagem mensagem = new Mensagem();
		mensagem.setFrom(usuarioRepository.getOne(dto.getCodigoUsuario()));
		mensagem.setMensagem(dto.getDescEvento());
		mensagem.setTitulo("Novo evento adicionado ao calentario escolar!");
		mensagem.setDataCadastro(new Date());
		List<Date> listDate = this.parseList(dto.getDatasNotificar());
		if (dto.getSelectedOpcao().equals("GERAL")) {
			evento.setTipoEvento("GERAL");
			this.eventoRepository.save(evento);
			this.mensageService.saveMensagemGeral(mensagem,listDate);
		} else if (dto.getSelectedOpcao().equals("ESPECIFICA")) {
			evento.setTipoEvento("ESPECIFICA");
			this.eventoRepository.save(evento);
			if (!dto.getProfessoresSelecionados().isEmpty()) {
				List<Usuario> list = new ArrayList<>();
				for (Long codigoProf : dto.getProfessoresSelecionados()) {
					Professor p = this.professorRepository.getOne(codigoProf);
					list.add(p.getUsuario());
					CalendarioProfessor calendarioProfessor = new CalendarioProfessor();
					calendarioProfessor.setEvento(evento);
					calendarioProfessor.setProfessor(p);
					this.calendarioProfessorRepository.save(calendarioProfessor);
					this.mensageService.saveMensagem(list,mensagem,listDate);
				}
			}
			
			if (!dto.getTurmasSelecionados().isEmpty()) {
				List<Usuario> list = new ArrayList<>();
				for (Long codigoTurma : dto.getTurmasSelecionados()) {
					Turma t = this.turmaRepository.getOne(codigoTurma);
					list.addAll(this.getUsuarios(t));
					CalendarioTurma calendarioTurma = new CalendarioTurma();
					calendarioTurma.setEvento(evento);
					calendarioTurma.setTurma(t);
					this.calendarioTurmaRepository.save(calendarioTurma);
					this.mensageService.saveMensagem(list, mensagem,listDate);
				}
			}
		}
	}


	private Collection<? extends Usuario> getUsuarios(Turma t) {
		List<Usuario> list = new ArrayList<>();
		for (Aluno a : t.getAlunos()) {
			list.add(a.getUsuario());
		}
		return list;
	}


	private List<Date> parseList(List<String> datasNotificar) {
		List<Date> list = new ArrayList<>();
		if (datasNotificar != null) {
			for (String date : datasNotificar) {
				list.add(this.parseDate(date));
			}
		}
		return list;
	}


	private Date parseDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
			throw new GescolarExcption("Erro serviço, data invalida..");
		}
	}
	
	
}
