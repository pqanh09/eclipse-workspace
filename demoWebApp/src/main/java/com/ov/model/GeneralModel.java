package com.ov.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public abstract class GeneralModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	protected String id;
	public GeneralModel(){}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
