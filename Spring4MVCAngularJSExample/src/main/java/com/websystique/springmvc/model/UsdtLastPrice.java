package com.websystique.springmvc.model;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

//save data by hour: max 60 records in hour
@Document(collection = UsdtLastPrice.COLLECTION_NAME)
public class UsdtLastPrice extends GenericModel<ObjectId> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2329879922793320976L;

	public static final String COLLECTION_NAME = "UsdtLastPrice";

	@NotNull
	@Indexed(unique = true)
	private long time;
	
	private Map<Integer, String> logError = new HashMap<>();
	
	private Map<Integer, Map<Integer, Double>> list = new HashMap<>();

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	

	public Map<Integer, Map<Integer, Double>> getList() {
		return list;
	}

	public void setList(Map<Integer, Map<Integer, Double>> list) {
		this.list = list;
	}

	public Map<Integer, String> getLogError() {
		return logError;
	}

	public void setLogError(Map<Integer, String> logError) {
		this.logError = logError;
	}

	public UsdtLastPrice(long time) {
		super();
		this.time = time;
	}

	
}
