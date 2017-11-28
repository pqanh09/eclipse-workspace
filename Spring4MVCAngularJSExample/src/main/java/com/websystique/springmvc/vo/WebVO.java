package com.websystique.springmvc.vo;

public class WebVO {

	private String objectId;
	
	private String name;

	private String url;

	private boolean isFrom = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

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

	
	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	@Override
	public String toString() {
		return "WebVO [id=" + objectId + ", name=" + name + ", url=" + url + ", isFrom=" + isFrom + "]";
	}

	public WebVO() {
		super();
	}
	
}
