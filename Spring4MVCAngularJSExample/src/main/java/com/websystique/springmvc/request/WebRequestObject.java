package com.websystique.springmvc.request;

import java.util.ArrayList;
import java.util.List;

import com.websystique.springmvc.vo.WebVO;

public class WebRequestObject extends GenericRequestObject{
	private WebVO web;
	private List<String> ids = new ArrayList<>();
	
	public WebVO getWeb() {
		return web;
	}
	public void setWeb(WebVO web) {
		this.web = web;
	}
	public List<String> getIds() {
		return ids;
	}
	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	
}
