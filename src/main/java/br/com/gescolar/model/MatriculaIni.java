package br.com.gescolar.model;


import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.ObjectUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.gescolar.dto.MatriculaDTO;
import br.com.gescolar.repository.listener.UrlFotoListener;
import br.com.gescolar.types.ResponsavelFinanceiroEnum;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@EntityListeners(UrlFotoListener.class)
@Entity
@Table(name="matricula_ini")
public class MatriculaIni  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo")
	private Long codigo;
	
	@NotNull
	@Length(max=1000)
	@NotEmpty
	private String nome;
	
	@Column(name="status")
	private String status;
	
	@Column(name="grupo_etnico")
	private String grupoEtnico;
	@Column(name="data_nasc")
	private Date dataNasc; 
	
	@Column(name="naturalidade")
	private String naturalidade;
	
	@Column(name="nacionalidade")
	private String nacionalidade;
	   
	@Column(name="certidao_nasc")
	private String certidaoNasc;
	
	 
	@Column(name="certidao_nasc_data_emissao")
	private Date certidaoNascDataEmissao;
	
	@Column(name="rg")
	private String rg;
	
	@Column(name="religiao")
	private String religiao;
	
	@Column(name="pediatra")
	private String pediatra;
	   
	@Column(name="pediatra_tel")
	private String pediatraTel;
	
	@Column(name="convenio")
	private String convenio;
	
	@Column(name="convenio_tel")
	private String convenioTel;
	
	@Column(name="convenio_nr")
	private String convenioNr;
	   
	@Column(name="sus_nr")
	private String susNr;
	
	
	@Column(name="alergico_medicamento")
	private Boolean alergicoMedicamento;
	
	@Column(name="alergico_medicamento_qual")
	private String alergicoMedicamentoQual;
	
	@Column(name="alergico_alimento")
	private Boolean alergicoAlimento;
	
	@Column(name="alergico_alimento_qual")
	private String alergicoAlimentoQual;
	
	@Column(name="medicamento_uso_continuo")
	private Boolean medicamentoUsoContinuo;
	
	@Column(name="medicamento_uso_continuo_qual")
	private String medicamentoUsoContinuoQual;
	
	@Column(name="medicamento_uso_continuo_receita")
	private Boolean medicamentoUsoContinuoReceita;
	
	@Column(name="bolsa_familia")
	private Boolean bolsaFamilia;
	
	
	@Column(name="transporte")
	private String transporte;
	
	@Column(name="nome_pai")
	private String nomePai;  
	@Column(name="dt_nasc_pai")
	private Date dtNascPai;  
	@Column(name="cpf_pai")
	private String cpfPai;
	@Column(name="rg_pai")
	private String rgPai;
	@Column(name="grau_instrucao_pai")
	private String grauInstrucaoPai; 
	@Column(name="religiao_pai")
	private String religiaoPai;
	@Column(name="profissao_pai")
	private String profissaoPai;
	@Column(name="local_trabalho_pai")
	private String localTrabalhoPai;
	@Column(name="telefone_trabalho_pai")
	private String telefoneTrabalhoPai;
	@Column(name="celular_pai")
	private String celularPai;
	   
	@Column(name="nome_mae")
	private String nomeMae;  
	@Column(name="dtNascMae")
	private Date dtDascMae;  
	@Column(name="cpf_mae")
	private String cpfMae;
	@Column(name="rg_mae")
	private String rgMae;
	@Column(name="grau_instrucao_mae")
	private String grauInstrucaoMae; 
	@Column(name="religiao_mae")
	private String religiaoMae;
	@Column(name="profissao_mae")
	private String profissaoMae;
	@Column(name="local_trabalho_mae")
	private String localTrabalhoMae;
	@Column(name="telefone_trabalho_mae")
	private String telefoneTrabalhoMae; 
	@Column(name="celular_mae")
	private String celularMae;
	   
	@Column(name="nome_resposavel")
	private String nomeResposavel;  
	@Column(name="dt_nasc_resposavel")
	private Date dtNascResposavel;  
	@Column(name="cpf_resposavel")
	private String cpfResposavel;
	@Column(name="rg_resposavel")
	private String rgResposavel;
	@Column(name="grau_instrucao_resposavel")
	private String grauInstrucaoResposavel; 
	@Column(name="religiao_resposavel")
	private String religiaoResposavel;
	@Column(name="profissao_resposavel")
	private String profissaoResposavel;
	@Column(name="local_trabalho_resposavel")
	private String localTrabalhoResposavel;
	@Column(name="telefone_trabalho_resposavel")
	private String telefoneTrabalhoResposavel; 
	@Column(name="celular_resposavel")
	private String celularResposavel;   
	  
	 
	@Column(name="nome_caso_imprevisto")
	private String nomeCasoImprevisto;     
	@Column(name="telefone_caso_imprevisto")
	private String telefoneCasoImprevisto;    
	@Column(name="endereco_caso_imprevisto")
	private String enderecoCasoImprevisto;  
	   
	   
	@Column(name="nome_autorizado_1")
	private String nomeAutorizado1;     
	@Column(name="rg_autorizado_1")
	private String rgAutorizado1;    
	@Column(name="grau_parentesco_autorizado_1")
	private String grauParentescoAutorizado1;    
	  
	@Column(name="nome_autorizado_2")
	private String nomeAutorizado2;     
	@Column(name="rg_autorizado_2")
	private String rgAutorizado2;    
	@Column(name="grau_parentesco_autorizado_2")
	private String grauParentescoAutorizado2;   
	
	@Column(name="nome_autorizado_3")
	private String nomeAutorizado3;     
	@Column(name="rg_autorizado_3")
	private String rgAutorizado3;    
	@Column(name="grau_parentesco_autorizado_3")
	private String grauParentescoAutorizado3;
	
	
	@Column(name="nome_autorizado_4")
	private String nomeAutorizado4;     
	@Column(name="rg_autorizado_4")
	private String rgAutorizado4;    
	@Column(name="grau_parentesco_autorizado_4")
	private String grauParentescoAutorizado4;
	
	
	@Column(name="nome_autorizado_5")
	private String nomeAutorizado5;     
	@Column(name="rg_autorizado_5")
	private String rgAutorizado5;    
	@Column(name="grau_parentesco_autorizado_5")
	private String grauParentescoAutorizado5;
	
	@Enumerated(EnumType.STRING)
	@Column(name="responsavel_financeiro")
	private ResponsavelFinanceiroEnum responsavelFinanceiro ;
	@Column(name="uf_responsavel_financeiro")
	private String uf;
	@Column(name="cidade_responsavel_financeiro")
	private String cidade;
	@Column(name="rua_responsavel_financeiro")
	private String rua;
	@Column(name="cep_responsavel_financeiro")
	private String cep;
	@Column(name="bairro_responsavel_financeiro")
	private String bairro;
	@Column(name="numero_responsavel_financeiro")
	private String numero;
	
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getGrupoEtnico() {
		return grupoEtnico;
	}
	public void setGrupoEtnico(String grupoEtnico) {
		this.grupoEtnico = grupoEtnico;
	}
	public Date getDataNasc() {
		return dataNasc;
	}
	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}
	public String getNaturalidade() {
		return naturalidade;
	}
	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}
	public String getNacionalidade() {
		return nacionalidade;
	}
	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}
	public String getCertidaoNasc() {
		return certidaoNasc;
	}
	public void setCertidaoNasc(String certidaoNasc) {
		this.certidaoNasc = certidaoNasc;
	}
	public Date getCertidaoNascDataEmissao() {
		return certidaoNascDataEmissao;
	}
	public void setCertidaoNascDataEmissao(Date certidaoNascDataEmissao) {
		this.certidaoNascDataEmissao = certidaoNascDataEmissao;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getReligiao() {
		return religiao;
	}
	public void setReligiao(String religiao) {
		this.religiao = religiao;
	}
	public String getPediatra() {
		return pediatra;
	}
	public void setPediatra(String pediatra) {
		this.pediatra = pediatra;
	}
	public String getPediatraTel() {
		return pediatraTel;
	}
	public void setPediatraTel(String pediatraTel) {
		this.pediatraTel = pediatraTel;
	}
	public String getConvenio() {
		return convenio;
	}
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}
	public String getConvenioTel() {
		return convenioTel;
	}
	public void setConvenioTel(String convenioTel) {
		this.convenioTel = convenioTel;
	}
	public String getConvenioNr() {
		return convenioNr;
	}
	public void setConvenioNr(String convenioNr) {
		this.convenioNr = convenioNr;
	}
	public String getSusNr() {
		return susNr;
	}
	public void setSusNr(String susNr) {
		this.susNr = susNr;
	}
	public Boolean getAlergicoMedicamento() {
		return alergicoMedicamento;
	}
	public void setAlergicoMedicamento(Boolean alergicoMedicamento) {
		this.alergicoMedicamento = alergicoMedicamento;
	}
	public String getAlergicoMedicamentoQual() {
		return alergicoMedicamentoQual;
	}
	public void setAlergicoMedicamentoQual(String alergicoMedicamentoQual) {
		this.alergicoMedicamentoQual = alergicoMedicamentoQual;
	}
	public Boolean getAlergicoAlimento() {
		return alergicoAlimento;
	}
	public void setAlergicoAlimento(Boolean alergicoAlimento) {
		this.alergicoAlimento = alergicoAlimento;
	}
	public String getAlergicoAlimentoQual() {
		return alergicoAlimentoQual;
	}
	public void setAlergicoAlimentoQual(String alergicoAlimentoQual) {
		this.alergicoAlimentoQual = alergicoAlimentoQual;
	}
	public Boolean getMedicamentoUsoContinuo() {
		return medicamentoUsoContinuo;
	}
	public void setMedicamentoUsoContinuo(Boolean medicamentoUsoContinuo) {
		this.medicamentoUsoContinuo = medicamentoUsoContinuo;
	}
	public String getMedicamentoUsoContinuoQual() {
		return medicamentoUsoContinuoQual;
	}
	public void setMedicamentoUsoContinuoQual(String medicamentoUsoContinuoQual) {
		this.medicamentoUsoContinuoQual = medicamentoUsoContinuoQual;
	}
	public Boolean getMedicamentoUsoContinuoReceita() {
		return medicamentoUsoContinuoReceita;
	}
	public void setMedicamentoUsoContinuoReceita(Boolean medicamentoUsoContinuoReceita) {
		this.medicamentoUsoContinuoReceita = medicamentoUsoContinuoReceita;
	}
	public Boolean getBolsaFamilia() {
		return bolsaFamilia;
	}
	public void setBolsaFamilia(Boolean bolsaFamilia) {
		this.bolsaFamilia = bolsaFamilia;
	}
	public String getTransporte() {
		return transporte;
	}
	public void setTransporte(String transporte) {
		this.transporte = transporte;
	}
	public String getNomePai() {
		return nomePai;
	}
	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}
	public Date getDtNascPai() {
		return dtNascPai;
	}
	public void setDtNascPai(Date dtNascPai) {
		this.dtNascPai = dtNascPai;
	}
	public String getCpfPai() {
		return cpfPai;
	}
	public void setCpfPai(String cpfPai) {
		this.cpfPai = cpfPai;
	}
	public String getRgPai() {
		return rgPai;
	}
	public void setRgPai(String rgPai) {
		this.rgPai = rgPai;
	}
	public String getGrauInstrucaoPai() {
		return grauInstrucaoPai;
	}
	public void setGrauInstrucaoPai(String grauInstrucaoPai) {
		this.grauInstrucaoPai = grauInstrucaoPai;
	}
	public String getReligiaoPai() {
		return religiaoPai;
	}
	public void setReligiaoPai(String religiaoPai) {
		this.religiaoPai = religiaoPai;
	}
	public String getProfissaoPai() {
		return profissaoPai;
	}
	public void setProfissaoPai(String profissaoPai) {
		this.profissaoPai = profissaoPai;
	}
	public String getLocalTrabalhoPai() {
		return localTrabalhoPai;
	}
	public void setLocalTrabalhoPai(String localTrabalhoPai) {
		this.localTrabalhoPai = localTrabalhoPai;
	}
	public String getTelefoneTrabalhoPai() {
		return telefoneTrabalhoPai;
	}
	public void setTelefoneTrabalhoPai(String telefoneTrabalhoPai) {
		this.telefoneTrabalhoPai = telefoneTrabalhoPai;
	}
	public String getCelularPai() {
		return celularPai;
	}
	public void setCelularPai(String celularPai) {
		this.celularPai = celularPai;
	}
	public String getNomeMae() {
		return nomeMae;
	}
	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}
	public Date getDtDascMae() {
		return dtDascMae;
	}
	public void setDtDascMae(Date dtDascMae) {
		this.dtDascMae = dtDascMae;
	}
	public String getCpfMae() {
		return cpfMae;
	}
	public void setCpfMae(String cpfMae) {
		this.cpfMae = cpfMae;
	}
	public String getRgMae() {
		return rgMae;
	}
	public void setRgMae(String rgMae) {
		this.rgMae = rgMae;
	}
	public String getGrauInstrucaoMae() {
		return grauInstrucaoMae;
	}
	public void setGrauInstrucaoMae(String grauInstrucaoMae) {
		this.grauInstrucaoMae = grauInstrucaoMae;
	}
	public String getReligiaoMae() {
		return religiaoMae;
	}
	public void setReligiaoMae(String religiaoMae) {
		this.religiaoMae = religiaoMae;
	}
	public String getProfissaoMae() {
		return profissaoMae;
	}
	public void setProfissaoMae(String profissaoMae) {
		this.profissaoMae = profissaoMae;
	}
	public String getLocalTrabalhoMae() {
		return localTrabalhoMae;
	}
	public void setLocalTrabalhoMae(String localTrabalhoMae) {
		this.localTrabalhoMae = localTrabalhoMae;
	}
	public String getTelefoneTrabalhoMae() {
		return telefoneTrabalhoMae;
	}
	public void setTelefoneTrabalhoMae(String telefoneTrabalhoMae) {
		this.telefoneTrabalhoMae = telefoneTrabalhoMae;
	}
	public String getCelularMae() {
		return celularMae;
	}
	public void setCelularMae(String celularMae) {
		this.celularMae = celularMae;
	}
	public String getNomeResposavel() {
		return nomeResposavel;
	}
	public void setNomeResposavel(String nomeResposavel) {
		this.nomeResposavel = nomeResposavel;
	}
	public Date getDtNascResposavel() {
		return dtNascResposavel;
	}
	public void setDtNascResposavel(Date dtNascResposavel) {
		this.dtNascResposavel = dtNascResposavel;
	}
	public String getCpfResposavel() {
		return cpfResposavel;
	}
	public void setCpfResposavel(String cpfResposavel) {
		this.cpfResposavel = cpfResposavel;
	}
	public String getRgResposavel() {
		return rgResposavel;
	}
	public void setRgResposavel(String rgResposavel) {
		this.rgResposavel = rgResposavel;
	}
	public String getGrauInstrucaoResposavel() {
		return grauInstrucaoResposavel;
	}
	public void setGrauInstrucaoResposavel(String grauInstrucaoResposavel) {
		this.grauInstrucaoResposavel = grauInstrucaoResposavel;
	}
	public String getReligiaoResposavel() {
		return religiaoResposavel;
	}
	public void setReligiaoResposavel(String religiaoResposavel) {
		this.religiaoResposavel = religiaoResposavel;
	}
	public String getProfissaoResposavel() {
		return profissaoResposavel;
	}
	public void setProfissaoResposavel(String profissaoResposavel) {
		this.profissaoResposavel = profissaoResposavel;
	}
	public String getLocalTrabalhoResposavel() {
		return localTrabalhoResposavel;
	}
	public void setLocalTrabalhoResposavel(String localTrabalhoResposavel) {
		this.localTrabalhoResposavel = localTrabalhoResposavel;
	}
	public String getTelefoneTrabalhoResposavel() {
		return telefoneTrabalhoResposavel;
	}
	public void setTelefoneTrabalhoResposavel(String telefoneTrabalhoResposavel) {
		this.telefoneTrabalhoResposavel = telefoneTrabalhoResposavel;
	}
	public String getCelularResposavel() {
		return celularResposavel;
	}
	public void setCelularResposavel(String celularResposavel) {
		this.celularResposavel = celularResposavel;
	}
	public String getNomeCasoImprevisto() {
		return nomeCasoImprevisto;
	}
	public void setNomeCasoImprevisto(String nomeCasoImprevisto) {
		this.nomeCasoImprevisto = nomeCasoImprevisto;
	}
	public String getTelefoneCasoImprevisto() {
		return telefoneCasoImprevisto;
	}
	public void setTelefoneCasoImprevisto(String telefoneCasoImprevisto) {
		this.telefoneCasoImprevisto = telefoneCasoImprevisto;
	}
	public String getEnderecoCasoImprevisto() {
		return enderecoCasoImprevisto;
	}
	public void setEnderecoCasoImprevisto(String enderecoCasoImprevisto) {
		this.enderecoCasoImprevisto = enderecoCasoImprevisto;
	}
	public String getNomeAutorizado1() {
		return nomeAutorizado1;
	}
	public void setNomeAutorizado1(String nomeAutorizado1) {
		this.nomeAutorizado1 = nomeAutorizado1;
	}
	public String getRgAutorizado1() {
		return rgAutorizado1;
	}
	public void setRgAutorizado1(String rgAutorizado1) {
		this.rgAutorizado1 = rgAutorizado1;
	}
	public String getGrauParentescoAutorizado1() {
		return grauParentescoAutorizado1;
	}
	public void setGrauParentescoAutorizado1(String grauParentescoAutorizado1) {
		this.grauParentescoAutorizado1 = grauParentescoAutorizado1;
	}
	public String getNomeAutorizado2() {
		return nomeAutorizado2;
	}
	public void setNomeAutorizado2(String nomeAutorizado2) {
		this.nomeAutorizado2 = nomeAutorizado2;
	}
	public String getRgAutorizado2() {
		return rgAutorizado2;
	}
	public void setRgAutorizado2(String rgAutorizado2) {
		this.rgAutorizado2 = rgAutorizado2;
	}
	public String getGrauParentescoAutorizado2() {
		return grauParentescoAutorizado2;
	}
	public void setGrauParentescoAutorizado2(String grauParentescoAutorizado2) {
		this.grauParentescoAutorizado2 = grauParentescoAutorizado2;
	}
	public String getNomeAutorizado3() {
		return nomeAutorizado3;
	}
	public void setNomeAutorizado3(String nomeAutorizado3) {
		this.nomeAutorizado3 = nomeAutorizado3;
	}
	public String getRgAutorizado3() {
		return rgAutorizado3;
	}
	public void setRgAutorizado3(String rgAutorizado3) {
		this.rgAutorizado3 = rgAutorizado3;
	}
	public String getGrauParentescoAutorizado3() {
		return grauParentescoAutorizado3;
	}
	public void setGrauParentescoAutorizado3(String grauParentescoAutorizado3) {
		this.grauParentescoAutorizado3 = grauParentescoAutorizado3;
	}
	public String getNomeAutorizado4() {
		return nomeAutorizado4;
	}
	public void setNomeAutorizado4(String nomeAutorizado4) {
		this.nomeAutorizado4 = nomeAutorizado4;
	}
	public String getRgAutorizado4() {
		return rgAutorizado4;
	}
	public void setRgAutorizado4(String rgAutorizado4) {
		this.rgAutorizado4 = rgAutorizado4;
	}
	public String getGrauParentescoAutorizado4() {
		return grauParentescoAutorizado4;
	}
	public void setGrauParentescoAutorizado4(String grauParentescoAutorizado4) {
		this.grauParentescoAutorizado4 = grauParentescoAutorizado4;
	}
	public String getNomeAutorizado5() {
		return nomeAutorizado5;
	}
	public void setNomeAutorizado5(String nomeAutorizado5) {
		this.nomeAutorizado5 = nomeAutorizado5;
	}
	public String getRgAutorizado5() {
		return rgAutorizado5;
	}
	public void setRgAutorizado5(String rgAutorizado5) {
		this.rgAutorizado5 = rgAutorizado5;
	}
	public String getGrauParentescoAutorizado5() {
		return grauParentescoAutorizado5;
	}
	public void setGrauParentescoAutorizado5(String grauParentescoAutorizado5) {
		this.grauParentescoAutorizado5 = grauParentescoAutorizado5;
	}
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public ResponsavelFinanceiroEnum getResponsavelFinanceiro() {
		return responsavelFinanceiro;
	}
	public void setResponsavelFinanceiro(ResponsavelFinanceiroEnum responsavelFinanceiro) {
		this.responsavelFinanceiro = responsavelFinanceiro;
	}
	public void parseDtoToModel(MatriculaDTO dto) {
				
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	
				
				nome = dto.getNome(); 
				try {
					if (dto.getDtNascimento() != null && !dto.getDtNascimento().equals("")) 
						dataNasc = dateFormat.parse(dto.getDtNascimento());
					if (dto.getDtNascMae() != null && !dto.getDtNascMae().equals(""))
						dtDascMae =  dateFormat.parse(dto.getDtNascMae());
					if (dto.getDtNascResp() != null && !dto.getDtNascResp().equals(""))
						dtNascResposavel = dateFormat.parse(dto.getDtNascResp());
					if (dto.getDtNascPai() != null && !dto.getDtNascPai().equals(""))
						dtNascPai =   dateFormat.parse(dto.getDtNascPai());
					
				} catch (ParseException e) {
					
				}  
				
				if (!ObjectUtils.isEmpty(dto.getStatus())) {
					status = dto.getStatus();
				}
				
				grupoEtnico = dto.getGrupoEtinico();
				naturalidade = dto.getNaturalidade();
				nacionalidade =  dto.getNacionalidade();
				certidaoNasc = dto.getCertidaoNasc();
				//certidaoNascDataEmissao =  dto.getd
				rg = dto.getRg(); 
				religiao =  dto.getReligiao();
				pediatra =  dto.getNomePediatra();
				pediatraTel = dto.getTelefonePediatra();
				convenio = dto.getConvenio();
				convenioTel = dto.getTelefoneConvenio();
				convenioNr =  dto.getNumeroCarteiraConvenio();
				susNr =  dto.getNumeroCartaoSus();
				alergicoMedicamento = this.parseBoolean(dto.getAlergicoMedicamento());
				alergicoMedicamentoQual = dto.getQualMedicamento();
				alergicoAlimento = this.parseBoolean(dto.getAlergicoAlimento());
				alergicoAlimentoQual = dto.getQualAlimento();
				medicamentoUsoContinuo = this.parseBoolean(dto.getMedicamentoUsoContinuo());
				medicamentoUsoContinuoQual = dto.getQualMedicamentoUsoContinuo(); 
				medicamentoUsoContinuoReceita = this.parseBoolean(dto.getPossuiReceita());
				bolsaFamilia = this.parseBoolean(dto.getBolsaFamilia());
				transporte = dto.getFamiliarEscolar();
				nomePai =   dto.getNomePai();
				cpfPai = dto.getCpfPai();
				rgPai = dto.getRgPai();
				grauInstrucaoPai = dto.getGrauInstrucaoPai();
				religiaoPai = dto.getReligiaoPai();
				profissaoPai = dto.getProfissaoPai();
				localTrabalhoPai = dto.getLocalTrabalhoPai();
				telefoneTrabalhoPai = dto.getTelComercialPai();
				celularPai = dto.getCelPai();
				nomeMae =   dto.getNomeMae();
				
				cpfMae = dto.getCpfMae();
				rgMae = dto.getRgMae();
				grauInstrucaoMae =  dto.getGrauInstrucaoMae();
				religiaoMae = dto.getReligiaoMae();
				profissaoMae = dto.getProfissaoMae();
				localTrabalhoMae = dto.getLocalTrabalhoMae();
				telefoneTrabalhoMae =  dto.getTelComercialMae();
				celularMae = dto.getCelMae();
				nomeResposavel = dto.getNomeResp();  
				
				cpfResposavel = dto.getCpfResp();
				rgResposavel = dto.getRgResp();
				grauInstrucaoResposavel =  dto.getGrauInstrucaoResp();
				religiaoResposavel = dto.getReligiaoResp();
				profissaoResposavel = dto.getProfissaoResp();
				localTrabalhoResposavel = dto.getLocalTrabalhoResp();
				telefoneTrabalhoResposavel =  dto.getTelComercialResp();
				celularResposavel =    dto.getCelResp();
				nomeCasoImprevisto =  dto.getNomeImpre();    
				telefoneCasoImprevisto =  dto.getTelImpre();   
				enderecoCasoImprevisto =   dto.getEndImpre();
				nomeAutorizado1 =      dto.getNomeRet1();
				rgAutorizado1 =     dto.getRgRet1();
				grauParentescoAutorizado1 =  dto.getGrauRet1();  
				nomeAutorizado2 =      dto.getNomeRet2();
				rgAutorizado2 =   dto.getRgRet2();  
				grauParentescoAutorizado2 =  dto.getGrauRet2();  
				nomeAutorizado3 =  dto.getNomeRet3();    
				rgAutorizado3 =   dto.getRgRet3();  
				grauParentescoAutorizado3 = dto.getGrauRet3();
				nomeAutorizado4 = dto.getNomeRet4();      
				rgAutorizado4 =   dto.getRgRet4();  
				grauParentescoAutorizado4 = dto.getGrauRet4();
				nomeAutorizado5 = dto.getNomeRet5();     
				rgAutorizado5 =  dto.getRgRet5();   
				grauParentescoAutorizado5 = dto.getGrauRet5();
	}
	
	private Boolean parseBoolean(String alergicoAlimento) {
		if (alergicoAlimento == null || alergicoAlimento.equals("") || alergicoAlimento.equals("N"))
			return false;
		return true;
	}
	
	
	public static MatriculaDTO parseDtoToDto(MatriculaIni matriculaIni) {
		MatriculaDTO dto = new MatriculaDTO();
		
		dto.setCodigo(matriculaIni.getCodigo() != null ? matriculaIni.getCodigo().toString() : null);
		dto.setStatus(matriculaIni.getStatus());
		
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

	private static String parseBoolean(Boolean condicao) {
		if (condicao == null || !condicao)
			return "N";
		return "S";
	}
	
}
