package com.websystique.springmvc.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsdtTotalVO extends GenericVO {
	private List<Integer> coins = new ArrayList<>();
	private List<Double> inputs = new ArrayList<>();
	private List<Double> lastPrices = new ArrayList<>();
	private List<Double> percents = new ArrayList<>();
	private Map<Long, Double> totals = new HashMap<>();

	public List<Integer> getCoins() {
		return coins;
	}



	public void setCoins(List<Integer> coins) {
		this.coins = coins;
	}



	public List<Double> getInputs() {
		return inputs;
	}



	public void setInputs(List<Double> inputs) {
		this.inputs = inputs;
	}



	public List<Double> getLastPrices() {
		return lastPrices;
	}



	public void setLastPrices(List<Double> lastPrices) {
		this.lastPrices = lastPrices;
	}



	public List<Double> getPercents() {
		return percents;
	}



	public void setPercents(List<Double> percents) {
		this.percents = percents;
	}



	public Map<Long, Double> getTotals() {
		return totals;
	}



	public void setTotals(Map<Long, Double> totals) {
		this.totals = totals;
	}



	public UsdtTotalVO() {
		super();
	}

}
