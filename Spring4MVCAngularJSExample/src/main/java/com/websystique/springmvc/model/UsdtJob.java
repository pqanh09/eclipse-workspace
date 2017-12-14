package com.websystique.springmvc.model;

import java.util.ArrayList;
import java.util.List;

public class UsdtJob extends Job{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8619038195301778387L;
	
	private String url;
	
	private List<Integer> coins = new ArrayList<>();
	
	private List<Double> inputs = new ArrayList<>();

	
	
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}



	public UsdtJob() {
		super();
	}

	@Override
	public String toString() {
		return "UsdtJob [url=" + url + ", coins=" + coins + ", inputs=" + inputs + "]";
	}


}
