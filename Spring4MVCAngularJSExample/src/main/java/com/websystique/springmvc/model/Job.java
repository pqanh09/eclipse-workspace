package com.websystique.springmvc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Job.COLLECTION_NAME)
public class Job extends GenericModel<ObjectId> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5016057474295657663L;

	public static final String COLLECTION_NAME = "Job";

	@NotNull
	@Size(max = 250)
	@Indexed(unique = true)
	private String name;

	private String description;
	
	private List<String> mangas = new ArrayList<>();
	
	//cron expressions
	private String cronString;
	
	private long timeStart;
	
	private long timeFinish;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getMangas() {
		return mangas;
	}

	public void setMangas(List<String> mangas) {
		this.mangas = mangas;
	}

	public String getCronString() {
		return cronString;
	}

	public void setCronString(String cronString) {
		this.cronString = cronString;
	}

	public long getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}

	public long getTimeFinish() {
		return timeFinish;
	}

	public void setTimeFinish(long timeFinish) {
		this.timeFinish = timeFinish;
	}

	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Job(ObjectId instanceid, Date createDate, Date modifiedDate, String lastUpdatedBy) {
		super(instanceid, createDate, modifiedDate, lastUpdatedBy);
		// TODO Auto-generated constructor stub
	}

	

}
