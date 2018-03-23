package com.comics.springmvc.vo;

public class WebVO extends GenericVO{
	
	private String url;

	private boolean isFrom = false;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isFrom() {
		return isFrom;
	}

	public void setFrom(boolean isFrom) {
		this.isFrom = isFrom;
	}

	
	@Override
	public String toString() {
		return "WebVO [id=" + objectId + ", name=" + name + ", url=" + url + ", isFrom=" + isFrom + "]";
	}

	public WebVO() {
		super();
	}
	
}
