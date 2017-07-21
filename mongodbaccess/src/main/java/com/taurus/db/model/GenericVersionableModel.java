package com.taurus.db.model;

import org.springframework.data.annotation.Version;

public class GenericVersionableModel<T> extends GenericModel<T>{

	private static final long serialVersionUID = 337866012081873933L;
	@Version
	private Integer version = null;
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
}
