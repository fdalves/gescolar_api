package br.com.gescolar.exception;

public class ChamadaJaCadastradaExcption extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChamadaJaCadastradaExcption(String msg) {
		super(msg);
	}
	
	public ChamadaJaCadastradaExcption() {
	}
	
	public ChamadaJaCadastradaExcption(String msg, Throwable cause) {
		super(msg, cause);
	}
}
