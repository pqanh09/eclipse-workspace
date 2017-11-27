package com.websystique.springmvc.request;

import java.util.ArrayList;
import java.util.List;

import com.websystique.springmvc.vo.GenericVO;

public class ModelRequestObject<T extends GenericVO> extends GenericRequestObject{
	private T t;
	private List<String> ids = new ArrayList<>();
	public T getT() {
		return t;
	}
	public void setT(T t) {
		this.t = t;
	}
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}

}
