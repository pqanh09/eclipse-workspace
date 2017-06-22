package com.tuanOv.models;


public class RestResponseGeneral {	
			
	public enum StatusConst {
		SUCCESS("SUCCESS"),
		ERROR("ERROR");
		
		private String value;
		
		StatusConst(String value) {
			this.value = value;
		}
		
		public String value() {
			return value;
		}
	}	
	
	private String status;
	private Object data;
	private String message;
	
	public RestResponseGeneral(String status, Object data, String message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}


