package com.websystique.springmvc.response;

import java.util.ArrayList;
import java.util.List;

public class WSBittresResponse {
	private long time;
	private List<Double> lastPrice = new ArrayList<>();
	private List<Double> input = new ArrayList<>(); 
	private List<Double> percent = new ArrayList<>();
	
	
	
	
	
	public WSBittresResponse(long time, List<Double> lastPrice, List<Double> input, List<Double> percent) {
		super();
		this.time = time;
		this.lastPrice = lastPrice;
		this.input = input;
		this.percent = percent;
	}





	public long getTime() {
		return time;
	}





	public void setTime(long time) {
		this.time = time;
	}





	public List<Double> getLastPrice() {
		return lastPrice;
	}





	public void setLastPrice(List<Double> lastPrice) {
		this.lastPrice = lastPrice;
	}





	public List<Double> getInput() {
		return input;
	}





	public void setInput(List<Double> input) {
		this.input = input;
	}





	public List<Double> getPercent() {
		return percent;
	}





	public void setPercent(List<Double> percent) {
		this.percent = percent;
	}





	public WSBittresResponse() {
		super();
	}
	
}
