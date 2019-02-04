package br.com.gescolar.exception;

public class ChamadaNotFoundExcption extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChamadaNotFoundExcption(String msg) {
		super(msg);
	}
	
	public ChamadaNotFoundExcption() {
	}
	
	public ChamadaNotFoundExcption(String msg, Throwable cause) {
		super(msg, cause);
	}
}
