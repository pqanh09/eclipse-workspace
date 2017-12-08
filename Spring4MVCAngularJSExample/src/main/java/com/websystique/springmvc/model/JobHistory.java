package com.websystique.springmvc.model;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = JobHistory.COLLECTION_NAME)
public class JobHistory extends GenericModel<ObjectId> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2754956607830601140L;

	public static final String COLLECTION_NAME = "JobHistory";
	private String jobId;
	private JobType jobType;
	private long startTime;
	private long endTime;
	private boolean success;
	private String description;
	private List<String> totalElemendIds = new ArrayList<>();
	private List<String> completedElemendIds = new ArrayList<>();
	private List<JobHistoryDetail> jobDetails = new ArrayList<>();

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getEndTime() {
		return endTime;
	}

	public void setEndTime(long endTime) {
		this.endTime = endTime;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<JobHistoryDetail> getJobDetails() {
		return jobDetails;
	}

	public void setJobDetails(List<JobHistoryDetail> jobDetails) {
		this.jobDetails = jobDetails;
	}

	public JobType getJobType() {
		return jobType;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public List<String> getTotalElemendIds() {
		return totalElemendIds;
	}

	public void setTotalElemendIds(List<String> totalElemendIds) {
		this.totalElemendIds = totalElemendIds;
	}

	public List<String> getCompletedElemendIds() {
		return completedElemendIds;
	}

	public void setCompletedElemendIds(List<String> completedElemendIds) {
		this.completedElemendIds = completedElemendIds;
	}

}
