package com.websystique.springmvc.model;

import java.util.ArrayList;
import java.util.List;

public class UsdtJob extends Job{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8619038195301778387L;
	
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

	public UsdtJob() {
		super();
	}

	@Override
	public String toString() {
		return "UsdtJob [url=" + url + ", input=" + input + ", time=" + time + "]";
	}

}
