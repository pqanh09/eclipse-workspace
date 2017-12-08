package com.websystique.springmvc.request;

import com.websystique.springmvc.vo.JobVO;

public class JobRequestObject extends GenericModelRequestObject<JobVO>{
	private boolean force;
	
	
	public boolean isForce() {
		return force;
	}


	public void setForce(boolean force) {
		this.force = force;
	}


	@Override
	public String toString() {
		return "JobRequestObject [model=" + model + ", ids=" + ids + ", operation=" + operation + ", objectType="
				+ objectType + ", others=" + others + "]";
	}
	
	
}
