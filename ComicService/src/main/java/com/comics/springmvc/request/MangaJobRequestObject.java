package com.comics.springmvc.request;

import com.comics.springmvc.model.JobType;
import com.comics.springmvc.vo.MangaJobVO;

public class MangaJobRequestObject extends GenericModelRequestObject<MangaJobVO>{
	
	@Override
	public String toString() {
		return "MangaJobRequestObject [model=" + model + ", ids=" + ids + ", operation=" + operation + ", objectType="
				+ objectType + ", others=" + others + "]";
	}
	
	
}
