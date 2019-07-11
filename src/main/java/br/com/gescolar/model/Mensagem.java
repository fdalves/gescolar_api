package br.com.gescolar.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name="MENSAGEM")
public class Mensagem implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo")
	private Long codigo;
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "codigo_usuario_from")
	private Usuario from;
	
	@NotNull
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "codigo_usuario_to")
	private Usuario to;
	
	@Column(name="data_cadastro")
	private Date dataCadastro;
	
	@Column(name="data_notificacao")
	private Date dataNotificacao;
	
	
	private String titulo;
	private String mensagem;
	private Boolean notificado;
	private Boolean notificar;
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Usuario getFrom() {
		return from;
	}
	public void setFrom(Usuario from) {
		this.from = from;
	}
	public Usuario getTo() {
		return to;
	}
	public void setTo(Usuario to) {
		this.to = to;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Date getDataNotificacao() {
		return dataNotificacao;
	}
	public void setDataNotificacao(Date dataNotificacao) {
		this.dataNotificacao = dataNotificacao;
	}
	
	public Boolean getNotificar() {
		return notificar;
	}
	public void setNotificar(Boolean notificar) {
		this.notificar = notificar;
	}
	public Boolean getNotificado() {
		return notificado;
	}
	public void setNotificado(Boolean notificado) {
		this.notificado = notificado;
	}
	
	
	

}
