package com.websystique.springmvc.vo;

import java.util.HashMap;
import java.util.Map;

public class UsdtTotalVO extends GenericVO{
	private long time;
	
	private Map<Long, Double> list = new HashMap<>();

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	

	public Map<Long, Double> getList() {
		return list;
	}

	public void setList(Map<Long, Double> list) {
		this.list = list;
	}

	private String jobId;
	
	
	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	

	public UsdtTotalVO() {
		super();
	}

}
