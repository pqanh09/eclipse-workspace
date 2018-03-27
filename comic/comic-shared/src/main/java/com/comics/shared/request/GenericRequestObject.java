package com.comics.shared.request;

import java.util.HashMap;
import java.util.Map;

public class GenericRequestObject {
	protected RequestType operation;
	protected ObjectType objectType;
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
