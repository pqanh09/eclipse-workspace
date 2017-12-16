package com.websystique.springmvc.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsdtLastPriceVO extends GenericVO{

private long time;
	
	private Map<Integer, String> logError = new HashMap<>();
	

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	

	
	private Map<Long, List<Double>> list = new HashMap<>();

	public Map<Long, List<Double>> getList() {
		return list;
	}

	public void setList(Map<Long, List<Double>> list) {
		this.list = list;
	}

	public Map<Integer, String> getLogError() {
		return logError;
	}

	public void setLogError(Map<Integer, String> logError) {
		this.logError = logError;
	}

	public UsdtLastPriceVO() {
		super();
	}
	
}
