package com.tuanOv.models;

public class ValidatedResult {
	
	public enum MessageConst {
		ERR_NOT_EXISTS("%1s must not empty"),
		ERR_MAXIMUM_EXCEED("%1s must not exceed 256 characters");
		
		private String value;
		
		MessageConst(String value) {
			this.value = value;
		}
		
		public String value() {
			return value;
		}
	}	
	
	public ValidatedResult(boolean result, String message) {
		this.result = result;
		this.message = message;
	}
	
	public ValidatedResult() {
		this.result = true;
		this.message = "";
	}

	private boolean result;
	private String message;
	
	public boolean getResult() {
		return result;
	}
	
	public void setResult(boolean result) {
		this.result = result;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
}
