package com.ov.responseEntity;

public abstract class GeneralObject {
	protected String id;
	
	protected GeneralObject(String id){
		setId(id);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
