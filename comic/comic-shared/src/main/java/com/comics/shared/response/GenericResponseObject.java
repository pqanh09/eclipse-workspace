package com.comics.shared.response;

import java.io.Serializable;

import com.comics.shared.request.GenericRequestObject;
import com.comics.shared.request.ObjectType;
import com.comics.shared.request.RequestType;
import com.google.gson.annotations.SerializedName;
public class GenericResponseObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9023885583087152076L;
	@SerializedName("operation")
	private RequestType operation;
	@SerializedName("objectType")
	private ObjectType objectType;
	@SerializedName("uniqueName")
    private String uniqueName;
	@SerializedName("success")
    private boolean success;
	@SerializedName("message")
    private String message;
	@SerializedName("multiPart")
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
	public GenericResponseObject(GenericRequestObject request){
		this.operation = request.getOperation();
		this.objectType = request.getObjectType();
	}
	public GenericResponseObject() {
		super();
	}
	
}
