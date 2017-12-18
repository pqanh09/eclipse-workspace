package com.websystique.springmvc.model;

public class UsdtJob extends Job {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8619038195301778387L;

	private String url;

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
		return "UsdtJob [url=" + url + "]";
	}

}
