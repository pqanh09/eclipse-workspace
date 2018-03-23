package com.comics.springmvc.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.index.Indexed;

public abstract class AbstractGenericModel<T> extends GenericModel<T>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7688954135844584716L;

	public static final String ATTR_NAME = "name";
	
	@NotNull
	@Size(max=250)	
	@Indexed(unique = true)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
