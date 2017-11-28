package com.websystique.springmvc.response;

public class PartResponseStatus {
	private String uniqueName;
    private Boolean success;
    private String message;
	public String getUniqueName() {
		return uniqueName;
	}
	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "PartResponseStatus [uniqueName=" + uniqueName + ", success=" + success + ", message=" + message + "]";
	}
	public PartResponseStatus(String uniqueName, Boolean success, String message) {
		super();
		this.uniqueName = uniqueName;
		this.success = success;
		this.message = message;
	}
    
}
