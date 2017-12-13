package com.websystique.springmvc.response;

import java.util.ArrayList;
import java.util.List;

public class WSBittresResponse {
	private List<Double> list = new ArrayList<>();
	private long time;
	public List<Double> getList() {
		return list;
	}
	public void setList(List<Double> list) {
		this.list = list;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public WSBittresResponse(long time, List<Double> list){
		super();
		this.list = list;
		this.time = time;
	}
	public WSBittresResponse() {
		super();
	}
	
}
