package com.websystique.springmvc.request;

import com.websystique.springmvc.vo.WebVO;

public class WebRequestObject extends GenericModelRequestObject<WebVO>{

	@Override
	public String toString() {
		return "WebRequestObject [model=" + model + ", ids=" + ids + ", operation=" + operation + ", objectType="
				+ objectType + ", others=" + others + "]";
	}
	
	
}
