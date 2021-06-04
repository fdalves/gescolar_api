package br.com.gescolar.service;

import java.text.SimpleDateFormat;

import javax.validation.Valid;

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
		return this.parseDtoToDto(matriculaIni);
	}

	public MatriculaDTO atualizar(Long codigo, @Valid MatriculaDTO matriculaDTO) {
		MatriculaIni matriculaIni = matriculaIniRepository.getOne(codigo);
		matriculaIni.parseDtoToModel(matriculaDTO);
		return parseDtoToDto(matriculaIniRepository.save(matriculaIni));
	}

	private MatriculaDTO parseDtoToDto(MatriculaIni matriculaIni) {
		MatriculaDTO dto = new MatriculaDTO();
		
		dto.setCodigo(matriculaIni.getCodigo() != null ? matriculaIni.getCodigo().toString() : null);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		dto.setNome(matriculaIni.getNome());
		if (matriculaIni.getDataNasc() != null )
			dto.setDtNascimento(dateFormat.format(matriculaIni.getDataNasc()));
		if (matriculaIni.getDtDascMae() != null )
			dto.setDtNascMae(dateFormat.format(matriculaIni.getDtDascMae()));
		if (matriculaIni.getDtNascResposavel() != null)
			dto.setDtNascResp(dateFormat.format(matriculaIni.getDtNascResposavel()));
		if (matriculaIni.getDtNascPai() != null)
			dto.setDtNascPai(dateFormat.format(matriculaIni.getDtNascPai()));
		
		dto.setGrupoEtinico(matriculaIni.getGrupoEtnico());
		dto.setNaturalidade(matriculaIni.getNaturalidade());
		dto.setNacionalidade(matriculaIni.getNacionalidade());
		dto.setCertidaoNasc(matriculaIni.getCertidaoNasc());
		// certidaoNascDataEmissao = dto.getd
		dto.setRg(matriculaIni.getRg());
		dto.setReligiao(matriculaIni.getReligiao());
		dto.setNomePediatra(matriculaIni.getPediatra());
		dto.setTelefonePediatra(matriculaIni.getPediatraTel());
		dto.setConvenio(matriculaIni.getConvenio());
		dto.setTelefoneConvenio(matriculaIni.getConvenioTel());
		dto.setNumeroCarteiraConvenio(matriculaIni.getConvenioNr());
		dto.setNumeroCartaoSus(matriculaIni.getSusNr());
		dto.setAlergicoMedicamento(parseBoolean(matriculaIni.getAlergicoMedicamento()));
		dto.setQualMedicamento(matriculaIni.getAlergicoMedicamentoQual());
		dto.setAlergicoAlimento(parseBoolean(matriculaIni.getAlergicoAlimento()));
		dto.setQualAlimento(matriculaIni.getAlergicoAlimentoQual());
		dto.setMedicamentoUsoContinuo(parseBoolean(matriculaIni.getMedicamentoUsoContinuo()));
		dto.setQualMedicamentoUsoContinuo(matriculaIni.getMedicamentoUsoContinuoQual());
		dto.setPossuiReceita(parseBoolean(matriculaIni.getMedicamentoUsoContinuoReceita()));
		dto.setBolsaFamilia(parseBoolean(matriculaIni.getBolsaFamilia()));
		dto.setFamiliarEscolar(matriculaIni.getTransporte());

		dto.setNomePai(matriculaIni.getNomePai());
		dto.setCpfPai(matriculaIni.getCpfPai());
		dto.setRgPai(matriculaIni.getRgPai());
		dto.setGrauInstrucaoPai(matriculaIni.getGrauInstrucaoPai());
		dto.setReligiaoPai(matriculaIni.getReligiaoPai());
		dto.setProfissaoPai(matriculaIni.getProfissaoPai());
		dto.setLocalTrabalhoPai(matriculaIni.getLocalTrabalhoPai());
		dto.setTelComercialPai(matriculaIni.getTelefoneTrabalhoPai());
		dto.setCelPai(matriculaIni.getCelularPai());

		dto.setNomeMae(matriculaIni.getNomeMae());
		dto.setCpfMae(matriculaIni.getCpfMae());
		dto.setRgMae(matriculaIni.getRgMae());
		dto.setGrauInstrucaoMae(matriculaIni.getGrauInstrucaoMae());
		dto.setReligiaoMae(matriculaIni.getReligiaoMae());
		dto.setProfissaoMae(matriculaIni.getProfissaoMae());
		dto.setLocalTrabalhoMae(matriculaIni.getLocalTrabalhoMae());
		dto.setTelComercialMae(matriculaIni.getTelefoneTrabalhoMae());
		dto.setCelMae(matriculaIni.getCelularMae());

		dto.setNomeResp(matriculaIni.getNomeResposavel());
		dto.setCpfResp(matriculaIni.getCpfResposavel());
		dto.setRgResp(matriculaIni.getRgResposavel());
		dto.setGrauInstrucaoResp(matriculaIni.getGrauInstrucaoResposavel());
		dto.setReligiaoResp(matriculaIni.getReligiaoResposavel());
		dto.setProfissaoResp(matriculaIni.getProfissaoResposavel());
		dto.setLocalTrabalhoResp(matriculaIni.getLocalTrabalhoResposavel());
		dto.setTelComercialResp(matriculaIni.getTelefoneTrabalhoResposavel());
		dto.setCelResp(matriculaIni.getCelularResposavel());

		dto.setNomeImpre(matriculaIni.getNomeCasoImprevisto());
		dto.setTelImpre(matriculaIni.getTelefoneCasoImprevisto());
		dto.setEndImpre(matriculaIni.getEnderecoCasoImprevisto());

		dto.setNomeRet1(matriculaIni.getNomeAutorizado1());
		dto.setRgRet1(matriculaIni.getRgAutorizado1());
		dto.setGrauRet1(matriculaIni.getGrauParentescoAutorizado1());

		dto.setNomeRet2(matriculaIni.getNomeAutorizado2());
		dto.setRgRet2(matriculaIni.getRgAutorizado2());
		dto.setGrauRet2(matriculaIni.getGrauParentescoAutorizado2());

		dto.setNomeRet3(matriculaIni.getNomeAutorizado3());
		dto.setRgRet3(matriculaIni.getRgAutorizado3());
		dto.setGrauRet3(matriculaIni.getGrauParentescoAutorizado3());

		dto.setNomeRet4(matriculaIni.getNomeAutorizado4());
		dto.setRgRet4(matriculaIni.getRgAutorizado4());
		dto.setGrauRet4(matriculaIni.getGrauParentescoAutorizado4());

		dto.setNomeRet5(matriculaIni.getNomeAutorizado5());
		dto.setRgRet5(matriculaIni.getRgAutorizado5());
		dto.setGrauRet5(matriculaIni.getGrauParentescoAutorizado5());

		return dto;
	}

	private String parseBoolean(Boolean condicao) {
		if (condicao == null || !condicao)
			return "N";
		return "S";
	}

}
