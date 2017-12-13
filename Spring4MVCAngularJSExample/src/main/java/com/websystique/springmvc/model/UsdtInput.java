package com.websystique.springmvc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = UsdtInput.COLLECTION_NAME)
public class UsdtInput extends GenericModel<ObjectId> {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3811401222919592281L;

	public static final String COLLECTION_NAME = "UsdtInput";

	@NotNull
	@Indexed(unique = true)
	private long time;
	

	private List<Double> list = new ArrayList<>(); 

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
	public List<Double> getList() {
		return list;
	}

	public void setList(List<Double> list) {
		this.list = list;
	}
	

	public UsdtInput(long time, List<Double> list) {
		super();
		this.time = time;
		this.list = list;
	}

	public UsdtInput() {
		super();
	}

	public UsdtInput(ObjectId instanceid, Date createDate, Date modifiedDate, String lastUpdatedBy) {
		super(instanceid, createDate, modifiedDate, lastUpdatedBy);
		// TODO Auto-generated constructor stub
	}


	
}
