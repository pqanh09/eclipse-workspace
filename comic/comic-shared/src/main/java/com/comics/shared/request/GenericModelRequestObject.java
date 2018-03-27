package com.comics.shared.request;

import java.util.ArrayList;
import java.util.List;

import com.comics.shared.vo.GenericVO;

public class GenericModelRequestObject<T extends GenericVO> extends GenericRequestObject{
	protected T model;
	protected List<String> ids = new ArrayList<String>();
	
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	public T getModel() {
		return model;
	}
	public void setModel(T model) {
		this.model = model;
	}
	@Override
	public String toString() {
		return "GenericModelRequestObject [model=" + model + ", ids=" + ids + ", operation=" + operation
				+ ", objectType=" + objectType + ", others=" + others + "]";
	}
	
	
}
