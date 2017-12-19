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
	private Map<Long, Double> totalPercent = new HashMap<>();
	private List<Double> costs = new ArrayList<>();
	private List<Double> units = new ArrayList<>();
	private double profit = 0;
	public List<Double> getCosts() {
		return costs;
	}

	public void setCosts(List<Double> costs) {
		this.costs = costs;
	}

	public List<Double> getUnits() {
		return units;
	}

	public void setUnits(List<Double> units) {
		this.units = units;
	}

	public double getProfit() {
		return profit;
	}

	public void setProfit(double profit) {
		this.profit = profit;
	}
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



	public Map<Long, Double> getTotalPercent() {
		return totalPercent;
	}



	public void setTotalPercent(Map<Long, Double> totals) {
		this.totalPercent = totals;
	}



	public UsdtTotalVO() {
		super();
	}

}
