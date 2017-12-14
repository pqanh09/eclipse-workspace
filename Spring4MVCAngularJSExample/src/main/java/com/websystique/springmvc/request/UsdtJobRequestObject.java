package com.websystique.springmvc.request;

import com.websystique.springmvc.vo.UsdtJobVO;

public class UsdtJobRequestObject extends GenericModelRequestObject<UsdtJobVO>{

	@Override
	public String toString() {
		return "JobRequestObject [model=" + model + ", ids=" + ids + ", operation=" + operation + ", objectType="
				+ objectType + ", others=" + others + "]";
	}
	
	
}
