package com.tuanOv.models;

import java.util.ArrayList;

public class RestResponseGeneralBuilder {
	
	private String status;
	private Object data;
	private String message;
	
	public RestResponseGeneralBuilder() {
		this.status = RestResponseGeneral.StatusConst.SUCCESS.value();
		this.data = new ArrayList<Object>();
		this.message = "";
	}
	
	public RestResponseGeneralBuilder setStatus(String status) {
		this.status = status;
		return this;
	}
	
	public RestResponseGeneralBuilder setData(Object data) {
		this.data = data;
		return this;
	}
	
	public RestResponseGeneralBuilder setMessage(String message) {
		this.message = message;
		return this;
	}
	
	public RestResponseGeneral build() {
		return new RestResponseGeneral(this.status, this.data, this.message);
	}
	
	public RestResponseGeneral buildSuccessResponse() {
		String status = RestResponseGeneral.StatusConst.SUCCESS.value();
		return new RestResponseGeneral(status, this.data, this.message);
	}
	
	public RestResponseGeneral buildErrorResponse() {
		String status = RestResponseGeneral.StatusConst.ERROR.value();
		return new RestResponseGeneral(status, this.data, this.message);
	}
	
}