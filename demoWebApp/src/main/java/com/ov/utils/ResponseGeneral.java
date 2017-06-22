package com.ov.utils;

public abstract class ResponseGeneral {
	private int statusCode;
	private String status;
	private String version;
	private String typeResponse;
	private Object response;
	public ResponseGeneral(){}
	public ResponseGeneral(int statusCode, String status, String version, String typeResponse, Object response){
		setStatus(status);
		setStatusCode(statusCode);
		setTypeResponse(typeResponse);
		setVersion(version);
		setResponse(response);
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getTypeResponse() {
		return typeResponse;
	}
	public void setTypeResponse(String typeResponse) {
		this.typeResponse = typeResponse;
	}
	public Object getResponse() {
		return response;
	}
	public void setResponse(Object response) {
		this.response = response;
	}
	@Override
	public String toString() {
		return "ResponseGeneral [statusCode=" + statusCode + ", status="
				+ status + ", version=" + version + ", typeResponse="
				+ typeResponse + ", response=" + response + "]";
	}
	
}
