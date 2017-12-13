package com.websystique.springmvc.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

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
	private static final int HISTORY_SIZE = 12;

	@NotNull
	@Size(max = 250)
	@Indexed(unique = true)
	private String name;

	private JobType type;
	
	
	private String description;
	
	//TODO change name
	private List<String> mangas = new ArrayList<>();
	
	//time interval
	private int timeInterval = 3600;
	
	private int intervalType = Calendar.SECOND;
	
	//cron expressions
	private String cronExpression;
	
	private long timeStart = 0;
	
	private long timeFinish = 0;
	
	private JobState status = JobState.unknown;
	
	private List<String> jobHistory = new ArrayList<>();
	

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
	
	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public int getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(int timeInterval) {
		this.timeInterval = timeInterval;
	}

	public int getIntervalType() {
		return intervalType;
	}

	public void setIntervalType(int intervalType) {
		this.intervalType = intervalType;
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

	public JobType getType() {
		return type;
	}

	public void setType(JobType type) {
		this.type = type;
	}
	
	public JobState getStatus() {
		return status;
	}

	public void setStatus(JobState status) {
		this.status = status;
	}
	
	public List getJobHistory() {
		return jobHistory;
	}

	public void setJobHistory(List jobHistory) {
		this.jobHistory = jobHistory;
	}
	//TODO synchronize
	public String addHistory(String historyId){
		String removeId = null;
		if(HISTORY_SIZE == jobHistory.size()){
			removeId = jobHistory.remove(0);
		}
		jobHistory.add(historyId);
		return removeId;
	}

	public Job() {
		super();
	}

	
	public Job(ObjectId instanceid, Date createDate, Date modifiedDate, String lastUpdatedBy) {
		super(instanceid, createDate, modifiedDate, lastUpdatedBy);
	}

	@Override
	public String toString() {
		return "Job [name=" + name + ", type=" + type + ", description=" + description + ", mangas=" + mangas
				+ ", timeInterval=" + timeInterval + ", intervalType=" + intervalType + ", timeStart=" + timeStart
				+ ", timeFinish=" + timeFinish + ", status=" + status + "]";
	}

	

}
