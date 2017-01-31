package main.java.vao;

import java.io.Serializable;

public class CountryVAO implements Serializable{
	/**
	 * 
	 */  
	private static final long serialVersionUID = 1L;
	private String name;
	private String id;

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
