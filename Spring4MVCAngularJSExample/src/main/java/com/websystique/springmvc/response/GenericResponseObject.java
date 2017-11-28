package com.websystique.springmvc.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.request.ObjectType;
import com.websystique.springmvc.request.RequestType;
@JsonTypeInfo(  
        use = JsonTypeInfo.Id.NAME,  
        include = JsonTypeInfo.As.WRAPPER_OBJECT,  
        property = "type")  
@JsonSubTypes({     
    @Type(value=WebResponseObject.class, name="WebResponseObject")
//    @Type(value=CPCustomizationResponseStatus.class, name="CPCustomizationResponseStatus")
}) 
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericResponseObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 9023885583087152076L;
	private RequestType operation;
	private ObjectType objectType;
    private String uniqueName;
    private Boolean success;
    private String message;
    private Boolean multiPart;
    
    
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
	
	public Boolean getMultiPart() {
		return multiPart;
	}
	public void setMultiPart(Boolean multiPart) {
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
