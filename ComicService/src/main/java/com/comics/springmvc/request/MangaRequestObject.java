package com.comics.springmvc.request;

import com.comics.springmvc.vo.MangaVO;

public class MangaRequestObject extends GenericModelRequestObject<MangaVO>{
	@Override
	public String toString() {
		return "MangaRequestObject [model=" + model + ", ids=" + ids + ", operation=" + operation + ", objectType="
				+ objectType + ", others=" + others + "]";
	}
	
	
}
