package br.com.gescolar.scheduled;
 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.gescolar.dto.ProcessChamadaDTO;
import br.com.gescolar.model.Aluno;
import br.com.gescolar.model.ChamadaAluno;
import br.com.gescolar.model.Mensagem;
import br.com.gescolar.repository.AlunoRepository;
import br.com.gescolar.repository.ChamadaAlunoRepository;
import br.com.gescolar.repository.ChamadaRepository;
import br.com.gescolar.repository.MensagemRepository;
import br.com.gescolar.service.UsuarioService;

@Profile("cron")
@Component
@EnableScheduling
public class ChamadaScheduled {

	Logger logger = LoggerFactory.getLogger(ChamadaScheduled.class);
	
	@Autowired
	private ChamadaRepository chamadaRepository;
	@Autowired
	private AlunoRepository alunoRepository;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private MensagemRepository mensagemRepository;
	@Autowired
	private ChamadaAlunoRepository chamadaAlunoRepository; 
	
	private final long SEGUNDO = 1000;
	private final long MINUTO = SEGUNDO * 60;
	//private final long HORA = MINUTO * 60;
	
	
	@Scheduled(fixedDelay = (MINUTO * 5))
	@Transactional
	public void processChamada() {
		logger.info("into processChamada...");
		List<Long> codProcessados = new ArrayList<Long>();
		List<ProcessChamadaDTO> list = chamadaRepository.processChamada();
		for (ProcessChamadaDTO processChamadaDTO : list) {
			if (codProcessados.contains(processChamadaDTO.getCodigoAluno())) continue;
			StringBuffer sbDisciplinas = new StringBuffer();
			for (ProcessChamadaDTO processChamadaDTOInner : list) {
				if (processChamadaDTO.getCodigoAluno().longValue() == processChamadaDTOInner.getCodigoAluno().longValue()) {
					sbDisciplinas.append(processChamadaDTOInner.getNomeDisciplina() + ", ");
				}
			}
			codProcessados.add(processChamadaDTO.getCodigoAluno());
			this.addMensagem(processChamadaDTO,sbDisciplinas);
			this.atualizaNotificacao(processChamadaDTO);
		}
	}
	
	
	private void atualizaNotificacao(ProcessChamadaDTO processChamadaDTO) {
		logger.info("into processChamada...");
		Aluno aluno = alunoRepository.getOne(processChamadaDTO.getCodigoAluno());
		List<ChamadaAluno> list = chamadaAlunoRepository.findByAlunoAndPresenca(aluno, false);
		if (list != null && !list.isEmpty()) {
			for (ChamadaAluno chamadaAluno : list) {
				chamadaAluno.setNotificado(true);
				chamadaAlunoRepository.save(chamadaAluno);
			}
		}
	}


	private void addMensagem(ProcessChamadaDTO processChamadaDTO, StringBuffer sbDisciplinas) {
		Mensagem mensagem = new Mensagem();
		mensagem.setDataCadastro(new Date());
		mensagem.setDataNotificacao(new Date());
		mensagem.setTitulo("Notificação Ausencia Aluno: "+ processChamadaDTO.getNomeAluno());
		mensagem.setMensagem("Foram notificadas faltas do dia de hoje, nas materias: " + sbDisciplinas.toString().substring(0, (sbDisciplinas.length() - 2) ));
		Aluno aluno = alunoRepository.getOne(processChamadaDTO.getCodigoAluno());
		mensagem.setTo(aluno.getUsuario());
		mensagem.setFrom(usuarioService.getUsuarioAdm());
		mensagem.setNotificado(false);
		mensagem.setNotificar(true);
		mensagemRepository.save(mensagem);
	}
}
