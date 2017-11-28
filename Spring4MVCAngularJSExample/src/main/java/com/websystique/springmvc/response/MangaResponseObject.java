package com.websystique.springmvc.response;

import java.util.ArrayList;
import java.util.List;

import com.websystique.springmvc.request.GenericRequestObject;
import com.websystique.springmvc.vo.MangaVO;

public class MangaResponseObject extends GenericResponseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8567105733026399621L;
	private List<MangaVO> list = new ArrayList<>();
	private List<PartResponseStatus> partStatus = new ArrayList<>();
	public List<MangaVO> getList() {
		return list;
	}
	public void setList(List<MangaVO> list) {
		this.list = list;
	}
	public MangaResponseObject(GenericRequestObject request) {
		super(request);
	}
	public List<PartResponseStatus> getPartStatus() {
		return partStatus;
	}
	public void setPartStatus(List<PartResponseStatus> partStatus) {
		this.partStatus = partStatus;
	}
}
