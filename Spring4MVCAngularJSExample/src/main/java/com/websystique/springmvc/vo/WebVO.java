package com.websystique.springmvc.vo;

import org.bson.types.ObjectId;

public class WebVO {

	private ObjectId instanceid;
	
	
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


	public ObjectId getInstanceid() {
		return instanceid;
	}

	public void setInstanceid(ObjectId instanceid) {
		this.instanceid = instanceid;
	}

	@Override
	public String toString() {
		return "WebVO [id=" + instanceid.toString() + ", name=" + name + ", url=" + url + ", isFrom=" + isFrom + "]";
	}

	public WebVO() {
		super();
	}
	
}
