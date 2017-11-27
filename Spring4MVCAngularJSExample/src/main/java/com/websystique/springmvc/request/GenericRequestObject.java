package com.websystique.springmvc.request;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(  
        use = JsonTypeInfo.Id.NAME,  
        include = JsonTypeInfo.As.WRAPPER_OBJECT,  
        property = "type")  
@JsonSubTypes({     
    @Type(value=MangaRequestObject.class, name="ManagaRequestObject"),
    @Type(value=WebRequestObject.class, name="WebRequestObject")
}) 
@JsonIgnoreProperties(ignoreUnknown = true)
public class GenericRequestObject {
	private RequestType operation;
	private ObjectType objectType;
	protected Map<String, Object> others = new HashMap<String, Object>();
	public RequestType getOperation() {
		return operation;
	}
	public void setOperation(RequestType operation) {
		this.operation = operation;
	}
	public ObjectType getObjectType() {
		return objectType;
	}
	public void setObjectType(ObjectType objectType) {
		this.objectType = objectType;
	}
	public Map<String, Object> getOthers() {
		return others;
	}
	public void setOthers(Map<String, Object> others) {
		this.others = others;
	}
	public GenericRequestObject(RequestType operation, ObjectType objectType, Map<String, Object> others) {
		super();
		this.operation = operation;
		this.objectType = objectType;
		this.others = others;
	}
	public GenericRequestObject() {
		super();
	}
	
	
}
