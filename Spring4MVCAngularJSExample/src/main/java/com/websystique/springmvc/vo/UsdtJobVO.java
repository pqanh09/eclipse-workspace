package com.websystique.springmvc.vo;

import java.util.ArrayList;
import java.util.List;

public class UsdtJobVO extends JobVO {
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


	public UsdtJobVO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
