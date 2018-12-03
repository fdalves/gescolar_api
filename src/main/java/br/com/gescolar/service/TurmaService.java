package br.com.gescolar.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.gescolar.dto.DisciplinaTurmaDTO;
import br.com.gescolar.dto.Periodo;
import br.com.gescolar.dto.PeriodosDTO;
import br.com.gescolar.dto.TurmaPeriodoDTO;
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

	/**
	 * salvar
	 * @param turma
	 * @return Turma
	 */
	@Transactional
	public Turma salvar(Turma turma) {
		turma = turmaRepository.save(turma);
		this.savePeridos(this.periodosToTurmaPeriodos(turma));
		return turma;
	}

	/**
	 * atualizar
	 * @param codigo
	 * @param turma
	 * @return Turma
	 */
	@Transactional
	public Turma atualizar(Long codigo, Turma turma) {
		this.verifyPeridos(turma);
		Turma turmaSalva = this.turmaRepository.getOne(codigo);
		turmaSalva.setTurmaPeriodos(this.turmaPeriodoRepository.findByTurma(turmaSalva));
		List<TurmaPeriodo> turmaPeriodoList = this.periodosToTurmaPeriodos(turma);
		this.insertTurmaPeriodo(turmaSalva.getTurmaPeriodos(), turmaPeriodoList);
		this.deleteTurmaPeriodo(turmaSalva.getTurmaPeriodos(), turmaPeriodoList);
		BeanUtils.copyProperties(turma, turmaSalva, "periodos", "turmaPeriodos");
		turmaSalva = turmaRepository.save(turmaSalva);
		turmaSalva.setPeriodos(turma.getPeriodos());
		return turmaSalva;

	}

	/**
	 * @param turma
	 */
	private void verifyPeridos(Turma turma) {
		if (turma != null
		        && turma.getPeriodos() != null
		        && turma.getPeriodos().size() == 6
		        && turma.getQuantidadeDiasSemana() == 5) {
			turma.getPeriodos().remove(5);
		}

	}

	/**
	 * savePeridos
	 * @param periodos
	 */
	private void savePeridos(List<TurmaPeriodo> periodos) {
		if (periodos != null && !periodos.isEmpty()) {
			for (TurmaPeriodo periodo : periodos) {
				turmaPeriodoRepository.save(periodo);
			}
		}
	}

	/**
	 * loadPeriodos
	 * @param turma
	 */
	public void loadPeriodos(Turma turma) {
		List<Periodo> peirodos = turmaPeriodoRepository.findPeriodos(turma.getCodigo());
		Collections.sort(peirodos, new Comparator<Periodo>() {
			@Override
			public int compare(Periodo o1, Periodo o2) {
				return DiaEnum.getDiaInt(o1.getDia()).compareTo(DiaEnum.getDiaInt(o2.getDia()));
			}
		});
		turma.setPeriodos(peirodos);
	}

	/**
	 * insertTurmaPeriodo
	 * @param turmaPeriodos
	 * @param periodos
	 */
	private void insertTurmaPeriodo(List<TurmaPeriodo> turmaPeriodosSavos, List<TurmaPeriodo> turmaPeriodos) {
		for (TurmaPeriodo turmaPeriodo : turmaPeriodos) {
			boolean novo = true;
			for (TurmaPeriodo turmaPeriodoSalvo : turmaPeriodosSavos) {
				if (turmaPeriodo.getDia().equals(turmaPeriodoSalvo.getDia())
				        && turmaPeriodo.getPeriodo().equals(turmaPeriodoSalvo.getPeriodo())) {
					novo = false;
					break;
				}
			}
			if (novo) {
				turmaPeriodoRepository.save(turmaPeriodo);
			}
		}
	}

	/**
	 * deleteTurmaPeriodo
	 * @param turmaPeriodos
	 * @param periodos
	 */
	private void deleteTurmaPeriodo(List<TurmaPeriodo> turmaPeriodosSavos, List<TurmaPeriodo> turmaPeriodos) {
		for (TurmaPeriodo turmaPeriodoSalvo : turmaPeriodosSavos) {
			boolean delete = true;
			for (TurmaPeriodo turmaPeriodo : turmaPeriodos) {
				if (turmaPeriodo.getDia().equals(turmaPeriodoSalvo.getDia())
				        && turmaPeriodo.getPeriodo().equals(turmaPeriodoSalvo.getPeriodo())) {
					delete = false;
					break;
				}
			}
			if (delete) {
				turmaPeriodoRepository.delete(turmaPeriodoSalvo);
			}
		}
	}

	/**
	 * periodosToTurmaPeriodos
	 * @param turma
	 * @return
	 */
	private List<TurmaPeriodo> periodosToTurmaPeriodos(Turma turma) {
		if (turma != null && turma.getPeriodos() != null) {
			List<TurmaPeriodo> turmaPeriodos = new ArrayList<>();
			for (Periodo periodo : turma.getPeriodos()) {
				int quantidadePeriodos = periodo.getQuant();
				for (int i = 1; i <= quantidadePeriodos; i++) {
					TurmaPeriodo turmaPeriodo = new TurmaPeriodo();
					turmaPeriodo.setDia(DiaEnum.getDia(Integer.valueOf(periodo.getValue())));
					turmaPeriodo.setTurma(turma);
					turmaPeriodo.setPeriodo(PeriodoEnum.getPeriodoEnum(i));
					turmaPeriodos.add(turmaPeriodo);
				}
			}
			return turmaPeriodos;
		}
		return Collections.emptyList();
	}

	
	/**
	 * getTurmaPeriodo
	 * @param codigoTurma
	 * @return TurmaPeriodoDTO
	 */
	public PeriodosDTO getTurmaPeriodo(long codigoTurma) {
		Turma turma = turmaRepository.getOne(codigoTurma);
		if (turma == null) return null;
		List<TurmaPeriodo> turmaPeriodosList = this.turmaPeriodoRepository.findByTurma(turma);
		if (turmaPeriodosList == null || turmaPeriodosList.isEmpty()) return null;
		return this.getTurmaPeriodoDTO(turmaPeriodosList);
	}
	
	/**
	 * getTurmaPeriodoDTO
	 * @param turmaPeriodosList
	 * @return TurmaPeriodoDTO
	 */
	private PeriodosDTO getTurmaPeriodoDTO(List<TurmaPeriodo> turmaPeriodosList) {
		PeriodosDTO dto = new PeriodosDTO();
		List<TurmaPeriodoDTO> segundaList = new ArrayList<>();
		List<TurmaPeriodoDTO> tercaList = new ArrayList<>();
		List<TurmaPeriodoDTO> quartaList = new ArrayList<>();
		List<TurmaPeriodoDTO> quintaList = new ArrayList<>();
		List<TurmaPeriodoDTO> sextaList = new ArrayList<>();
		List<TurmaPeriodoDTO> sabadoList = new ArrayList<>();
		List<TurmaPeriodoDTO> periodosVagos = new ArrayList<>();
		dto.setVagos(periodosVagos);
		
		for (TurmaPeriodo turmaPeriodo : turmaPeriodosList) {
			 if (turmaPeriodo.getDia().equals(DiaEnum.SEGUNDA)) {
				 segundaList.add(this.parseToDTO(turmaPeriodo,dto));
			 } else if (turmaPeriodo.getDia().equals(DiaEnum.TERCA)) {
				 tercaList.add(this.parseToDTO(turmaPeriodo,dto));
			 } else if (turmaPeriodo.getDia().equals(DiaEnum.QUARTA)) {
				 quartaList.add(this.parseToDTO(turmaPeriodo,dto));
			 } else if (turmaPeriodo.getDia().equals(DiaEnum.QUINTA)) {
				 quintaList.add(this.parseToDTO(turmaPeriodo,dto));
			 } else if (turmaPeriodo.getDia().equals(DiaEnum.SEXTA)) {
				 sextaList.add(this.parseToDTO(turmaPeriodo,dto));
			 } else if (turmaPeriodo.getDia().equals(DiaEnum.SABADO)) {
				 sabadoList.add(this.parseToDTO(turmaPeriodo,dto));
			 }
		}
		dto.setSegunda(segundaList);
		dto.setTerca(tercaList);
		dto.setQuarta(quartaList);
		dto.setQuinta(quintaList);
		dto.setSexta(sextaList);
		dto.setSabado(sabadoList);
		
		List<TurmaPeriodoDTO> all = new ArrayList<>();
		all.addAll(segundaList);
		all.addAll(tercaList);
		all.addAll(quartaList);
		all.addAll(quintaList);
		all.addAll(sextaList);
		all.addAll(sabadoList);
		
		dto.setAll(all);
		
		
		
		return dto;
	}
	
	
	private TurmaPeriodoDTO parseToDTO(TurmaPeriodo periodo, PeriodosDTO objDto) {
		TurmaPeriodoDTO dto = new TurmaPeriodoDTO();
		dto.setCodigo(periodo.getCodigo());
		dto.setDia(periodo.getDia().toString());
		dto.setPeriodo(periodo.getPeriodo().toString());
		if (periodo.getDisciplinaTurma() != null && periodo.getDisciplinaTurma().getCodigo() != null) {
			DisciplinaTurmaDTO disciplinaTurmaDTO = new DisciplinaTurmaDTO();
			disciplinaTurmaDTO.setCodigo(periodo.getDisciplinaTurma().getCodigo());
			disciplinaTurmaDTO.setCodigoDisciplina(periodo.getDisciplinaTurma().getDisciplina().getCodigo());
			disciplinaTurmaDTO.setCodigoProfessor(periodo.getDisciplinaTurma().getProfessor().getCodigo());
			disciplinaTurmaDTO.setNomeDisciplina(periodo.getDisciplinaTurma().getDisciplina().getNome());
			disciplinaTurmaDTO.setNomeProfessor(periodo.getDisciplinaTurma().getProfessor().getNome());
			dto.setDisciplinaTurma(disciplinaTurmaDTO);
		} else {
			objDto.getVagos().add(dto);
		}
		return dto;
	}
	
	
}
