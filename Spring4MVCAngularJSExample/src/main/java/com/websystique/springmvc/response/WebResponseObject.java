package com.websystique.springmvc.response;

import java.util.ArrayList;
import java.util.List;

import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.vo.WebVO;

public class WebResponseObject extends GenericResponseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5320663543270808230L;
	private List<WebVO> list = new ArrayList<>();
	private List<PartResponseStatus> partStatus = new ArrayList<>();
	public List<WebVO> getList() {
		return list;
	}
	public void setList(List<WebVO> list) {
		this.list = list;
	}
	public WebResponseObject(GenericRequestObject request) {
		super(request);
	}
	public List<PartResponseStatus> getPartStatus() {
		return partStatus;
	}
	public void setPartStatus(List<PartResponseStatus> partStatus) {
		this.partStatus = partStatus;
	}
	
	
}
