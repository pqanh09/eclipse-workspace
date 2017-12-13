package com.websystique.springmvc.vo;

import java.util.ArrayList;
import java.util.List;

public class UsdtInputVO extends GenericVO {
	private long time;
	
	private List<Double> list = new ArrayList<>(); 

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
	public List<Double> getList() {
		return list;
	}

	public void setList(List<Double> list) {
		this.list = list;
	}

	
	public UsdtInputVO(long time, List<Double> list) {
		super();
		this.time = time;
		this.list = list;
	}

	
	public UsdtInputVO() {
		super();
	}

	@Override
	public String toString() {
		return "UsdtInputVO [time=" + time + ", list=" + list + "]";
	}
	
}
