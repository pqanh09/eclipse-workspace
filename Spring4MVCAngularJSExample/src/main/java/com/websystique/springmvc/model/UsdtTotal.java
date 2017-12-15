package com.websystique.springmvc.model;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

//save total percent by hour: max 60 records in hour
@Document(collection = UsdtTotal.COLLECTION_NAME)
public class UsdtTotal extends GenericModel<ObjectId> {


	/**
	 * 
	 */
	private static final long serialVersionUID = -942217593723048843L;

	public static final String COLLECTION_NAME = "UsdtTotal";
	public static final String ATTR_TIME = "time";

	@NotNull
	@Indexed(unique = true)
	private long time;
	
	private String jobId;
	
	
	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
	
	private Map<Long, Double> list = new HashMap<>();
	public Map<Long, Double> getList() {
		return list;
	}

	public void setList(Map<Long, Double> list) {
		this.list = list;
	}

	public UsdtTotal(long time) {
		super();
		this.time = time;
	}

	
}
