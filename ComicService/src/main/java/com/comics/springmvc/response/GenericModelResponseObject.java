package com.comics.springmvc.response;

import java.util.ArrayList;
import java.util.List;

import com.comics.springmvc.request.GenericRequestObject;
import com.comics.springmvc.vo.GenericVO;

public class GenericModelResponseObject<T extends GenericVO> extends GenericResponseObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7606940436484675239L;
	
	protected List<T> list = new ArrayList<>();
	protected List<PartResponseStatus> partStatus = new ArrayList<>();
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public GenericModelResponseObject(GenericRequestObject request) {
		super(request);
	}
	public List<PartResponseStatus> getPartStatus() {
		return partStatus;
	}
	public void setPartStatus(List<PartResponseStatus> partStatus) {
		this.partStatus = partStatus;
	}
}
