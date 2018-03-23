package com.comics.springmvc.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Web.COLLECTION_NAME)
public class Web extends GenericModel<ObjectId> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9152250095558348755L;

	public static final String COLLECTION_NAME = "Web";

	@NotNull
	@Size(max = 250)
	@Indexed(unique = true)
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

	@Override
	public String toString() {
		return "Web [name=" + name + ", url=" + url + ", isFrom=" + isFrom + "]";
	}

	public Web() {
		super();
	}

	public Web(ObjectId instanceid, Date createDate, Date modifiedDate, String lastUpdatedBy) {
		super(instanceid, createDate, modifiedDate, lastUpdatedBy);
	}

}
