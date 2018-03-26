package com.example.pqanh.myapp2.resttest;

import java.io.Serializable;
public class GenericResponseObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9023885583087152076L;
	private RequestType operation;
	private ObjectType objectType;
    private String uniqueName;
    private boolean success;
    private String message;
    private boolean multiPart;
    
    
	public ObjectType getObjectType() {
		return objectType;
	}
	public void setObjectType(ObjectType objectType) {
		this.objectType = objectType;
	}
	public RequestType getOperation() {
		return operation;
	}
	public void setOperation(RequestType operation) {
		this.operation = operation;
	}
	public String getUniqueName() {
		return uniqueName;
	}
	public void setUniqueName(String uniqueName) {
		this.uniqueName = uniqueName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public boolean isMultiPart() {
		return multiPart;
	}
	public void setMultiPart(boolean multiPart) {
		this.multiPart = multiPart;
	}
	public GenericResponseObject(RequestType operation, ObjectType objectType, String uniqueName, Boolean success,
			String message) {
		super();
		this.operation = operation;
		this.objectType = objectType;
		this.uniqueName = uniqueName;
		this.success = success;
		this.message = message;
	}
	public GenericResponseObject() {
		super();
	}
	
}
