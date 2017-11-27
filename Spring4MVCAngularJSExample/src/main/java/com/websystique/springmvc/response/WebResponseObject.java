package com.websystique.springmvc.response;

import java.util.ArrayList;
import java.util.List;

import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.request.ObjectType;
import com.websystique.springmvc.request.RequestType;
import com.websystique.springmvc.vo.WebVO;

public class WebResponseObject extends GenericResponseObject{

	public WebResponseObject(RequestType operation, ObjectType objectType, String uniqueName, Boolean success,
			String message) {
		super(operation, objectType, uniqueName, success, message);
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 5320663543270808230L;
	private List<WebVO> list = new ArrayList<>();
	public List<WebVO> getList() {
		return list;
	}
	public void setList(List<WebVO> list) {
		this.list = list;
	}
	public WebResponseObject(GenericRequestObject request) {
		super(request);
	}
	
	
}
