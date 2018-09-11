package br.com.gescolar.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gescolar.dto.Periodo;
import br.com.gescolar.model.Turma;
import br.com.gescolar.model.TurmaPeriodo;
import br.com.gescolar.repository.TurmaPeriodoRepository;
import br.com.gescolar.repository.TurmaRepository;
import br.com.gescolar.types.DiaEnum;
import br.com.gescolar.types.PeriodoEnum;

@Service
public class TurmaService {

	@Autowired
	private TurmaRepository turmaRepository;

	@Autowired
	private TurmaPeriodoRepository turmaPeriodoRepository;

	@Transactional
	public Turma salvar(Turma turma) {
		turma = turmaRepository.save(turma);
		this.savePeridos(turma.getPeriodos(), turma);
		return turma;
	}

	private void savePeridos(List<Periodo> periodos, Turma turma) {
		if (periodos != null && !periodos.isEmpty()) {
			for (Periodo periodo : periodos) {
				int quantidadePeriodos = periodo.getQuant();
				for (int i = 1; i <= quantidadePeriodos; i++) {
					TurmaPeriodo turmaPeriodo = new TurmaPeriodo();
					turmaPeriodo.setDia(DiaEnum.getDia(Integer.valueOf(periodo.getValue())));
					turmaPeriodo.setTurma(turmaRepository.getOne(turma.getCodigo()));
					turmaPeriodo.setPeriodo(PeriodoEnum.getPeriodoEnum(i));
					turmaPeriodoRepository.save(turmaPeriodo);
				}
			}
		}
	}

	public void loadPeriodos(Turma turma) {
		List<Periodo> peirodos = turmaPeriodoRepository.findPeriodos(turma.getCodigo());
		turma.setPeriodos(peirodos);
	}

	public Turma atualizar(Long codigo, Turma turma) {
		return null;
	}

	public Turma deletar() {
		return null;
	}

}
