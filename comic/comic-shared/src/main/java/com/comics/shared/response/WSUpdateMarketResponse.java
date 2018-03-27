package com.comics.shared.response;

import java.util.ArrayList;
import java.util.List;

public class WSUpdateMarketResponse {
	private long time;
	private List<Double> lastPrice = new ArrayList<Double>();
	private List<Double> percent = new ArrayList<Double>();
	private Double total;

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public WSUpdateMarketResponse(long time, List<Double> lastPrice, List<Double> percent, Double total) {
		super();
		this.time = time;
		this.lastPrice = lastPrice;
		this.percent = percent;
		this.total = total;
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

	public List<Double> getPercent() {
		return percent;
	}

	public void setPercent(List<Double> percent) {
		this.percent = percent;
	}

	public WSUpdateMarketResponse() {
		super();
	}

}
