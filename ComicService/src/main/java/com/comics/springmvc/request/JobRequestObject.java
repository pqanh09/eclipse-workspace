package com.comics.springmvc.request;

import com.comics.springmvc.vo.JobVO;

public class JobRequestObject extends GenericModelRequestObject<JobVO> {

	@Override
	public String toString() {
		return "JobRequestObject [model=" + model + ", ids=" + ids + ", operation=" + operation + ", objectType="
				+ objectType + ", others=" + others + "]";
	}

	
}
