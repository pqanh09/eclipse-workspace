package com.ov.utils;

public enum ResponseStatus {
	SUCCESS("SUCCESS"),
	FAIL("FAIL");
	
	private String name;
	
	ResponseStatus(String name){
		this.name = name;
	}
	public String getName() {
	    return name;
	}
}
