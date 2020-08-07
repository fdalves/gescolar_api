package br.com.gescolar.dto;

import java.io.Serializable;

public class DataCalendarioDTO implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String title;
	private String start;
	private String end;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	
	
	

}
