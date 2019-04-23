package br.com.gescolar.exception;

public class ChamadaFiltroNotFoundExcption extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChamadaFiltroNotFoundExcption(String msg) {
		super(msg);
	}
	
	public ChamadaFiltroNotFoundExcption() {
	}
	
	public ChamadaFiltroNotFoundExcption(String msg, Throwable cause) {
		super(msg, cause);
	}
}
