package com.websystique.springmvc.vo;

import java.util.ArrayList;
import java.util.List;

public class UsdtJobVO extends JobVO {
	private String url;

	private List<Double> input = new ArrayList<>();

	private long time;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Double> getInput() {
		return input;
	}

	public void setInput(List<Double> input) {
		this.input = input;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public UsdtJobVO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
