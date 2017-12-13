package com.websystique.springmvc.vo;

import java.util.HashMap;
import java.util.Map;

public class UsdtTotalVO extends GenericVO{
	private long time;
	
	private Map<String, Double> list = new HashMap<>();

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	

	public Map<String, Double> getList() {
		return list;
	}

	public void setList(Map<String, Double> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "UsdtTotalVO [time=" + time + ", list=" + list + "]";
	}

	public UsdtTotalVO(long time, Map<String, Double> list) {
		super();
		this.time = time;
		this.list = list;
	}

	public UsdtTotalVO() {
		super();
	}

}
