package br.com.gescolar.exception;

public class GescolarExcption extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GescolarExcption(String msg) {
		super(msg);
	}
	
	public GescolarExcption() {
	}
	
	public GescolarExcption(String msg, Throwable cause) {
		super(msg, cause);
	}
}
