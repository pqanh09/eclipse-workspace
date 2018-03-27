package com.comics.shared.response;

import java.util.ArrayList;
import java.util.List;

import com.comics.shared.request.GenericRequestObject;
import com.comics.shared.vo.GenericVO;
import com.google.gson.annotations.SerializedName;

public class GenericModelResponseObject<T extends GenericVO> extends GenericResponseObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7606940436484675239L;
	@SerializedName("list")
	protected List<T> list = new ArrayList<T>();
	@SerializedName("partStatus")
	protected List<PartResponseStatus> partStatus = new ArrayList<PartResponseStatus>();
	
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
