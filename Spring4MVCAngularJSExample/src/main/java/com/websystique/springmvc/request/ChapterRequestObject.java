package com.websystique.springmvc.request;

import com.websystique.springmvc.vo.ChapterVO;

public class ChapterRequestObject extends GenericModelRequestObject<ChapterVO>{

	@Override
	public String toString() {
		return "ChapterRequestObject [model=" + model + ", ids=" + ids + ", operation=" + operation + ", objectType="
				+ objectType + ", others=" + others + "]";
	}
	
}
