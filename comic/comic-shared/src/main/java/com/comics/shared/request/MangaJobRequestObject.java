package com.comics.shared.request;

import com.comics.shared.vo.MangaJobVO;

public class MangaJobRequestObject extends GenericModelRequestObject<MangaJobVO>{
	
	@Override
	public String toString() {
		return "MangaJobRequestObject [model=" + model + ", ids=" + ids + ", operation=" + operation + ", objectType="
				+ objectType + ", others=" + others + "]";
	}
	
	
}
