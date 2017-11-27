package com.websystique.springmvc.vo;

import com.websystique.springmvc.model.Web;
import com.websystique.springmvc.request.ObjectType;

public class WebVO extends AbstractDelegationObjectIdVO<Web>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7847737761798581330L;

	public WebVO(Web web){
		super(web);
	}
	@Override
	protected Web newInstance() {
		// TODO Auto-generated method stub
		return new Web();
	}

	@Override
	public String retrieveNGObjectType() {
		// TODO Auto-generated method stub
		return ObjectType.Web.toString();
	}
	
	public String getName() {
		return delegate.getName();
	}

	public void setName(String name) {
		delegate.setName(name);
	}
	
	public String getUrl() {
		return delegate.getUrl();
	}

	public void setUrl(String url) {
		delegate.setUrl(url);
	}

	public boolean isFrom() {
		return delegate.isFrom();
	}

	public void setFrom(boolean isFrom) {
		delegate.setFrom(isFrom);
	}

}
