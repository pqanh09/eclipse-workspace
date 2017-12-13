package com.websystique.springmvc.request;

import com.websystique.springmvc.vo.UsdtInputVO;

public class UsdtInputRequestObject extends GenericModelRequestObject<UsdtInputVO>{

	@Override
	public String toString() {
		return "UsdtInputRequestObject [model=" + model + ", ids=" + ids + ", operation=" + operation + ", objectType="
				+ objectType + ", others=" + others + "]";
	}
	
}
