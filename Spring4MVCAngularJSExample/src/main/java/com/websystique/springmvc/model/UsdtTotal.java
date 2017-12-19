package com.websystique.springmvc.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

//save total percent by hour: max 60 records in hour
@Document(collection = UsdtTotal.COLLECTION_NAME)
public class UsdtTotal extends GenericModel<ObjectId> {


	/**
	 * 
	 */
	private static final long serialVersionUID = -942217593723048843L;

	public static final String COLLECTION_NAME = "UsdtTotal";
	public static final String ATTR_TIME = "time";

	@NotNull
	@Indexed(unique = true)
	private String name;
	
	private List<Integer> coins = new ArrayList<>();
	private List<Double> inputs = new ArrayList<>();
	private List<Double> lastPrices = new ArrayList<>();
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

	private Map<Long, Double> profitPercent = new HashMap<>();
	
	private static final int TOTAL_SIZE = 60;
	
	public void addProfitPercent(long time, double value){
		if(TOTAL_SIZE == profitPercent.size()){
			Entry<Long, Double> entry = profitPercent.entrySet().iterator().next();
			long key = entry.getKey();
			profitPercent.remove(key);
		}
		profitPercent.put(time, value);
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

	public List<Double> getInputs() {
		return inputs;
	}

	public void setInputs(List<Double> inputs) {
		this.inputs = inputs;
	}

	public List<Integer> getCoins() {
		return coins;
	}

	public void setCoins(List<Integer> coins) {
		this.coins = coins;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
