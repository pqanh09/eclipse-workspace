package com.websystique.springmvc.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UsdtTotalVO extends GenericVO {
	private List<Integer> coins = new ArrayList<>();
	private List<Double> inputs = new ArrayList<>();
	private List<Double> lastPrices = new ArrayList<>();
	private Map<Long, Double> profitPercent = new HashMap<>();
	private List<Double> costs = new ArrayList<>();
	private List<Double> units = new ArrayList<>();
	private double totalProfit = 0;
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

	public double getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(double profit) {
		this.totalProfit = profit;
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


	public Map<Long, Double> getProfitPercent() {
		return profitPercent;
	}



	public void setProfitPercent(Map<Long, Double> totals) {
		this.profitPercent = totals;
	}



	public UsdtTotalVO() {
		super();
	}

}
