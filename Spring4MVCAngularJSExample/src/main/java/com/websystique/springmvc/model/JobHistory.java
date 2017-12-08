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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((completedElemendIds == null) ? 0 : completedElemendIds.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((jobDetails == null) ? 0 : jobDetails.hashCode());
		result = prime * result + ((jobId == null) ? 0 : jobId.hashCode());
		result = prime * result + ((jobType == null) ? 0 : jobType.hashCode());
		result = prime * result + (success ? 1231 : 1237);
		result = prime * result + ((totalElemendIds == null) ? 0 : totalElemendIds.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JobHistory other = (JobHistory) obj;
		if (completedElemendIds == null) {
			if (other.completedElemendIds != null)
				return false;
		} else if (!completedElemendIds.equals(other.completedElemendIds))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (jobDetails == null) {
			if (other.jobDetails != null)
				return false;
		} else if (!jobDetails.equals(other.jobDetails))
			return false;
		if (jobId == null) {
			if (other.jobId != null)
				return false;
		} else if (!jobId.equals(other.jobId))
			return false;
		if (jobType != other.jobType)
			return false;
		if (success != other.success)
			return false;
		if (totalElemendIds == null) {
			if (other.totalElemendIds != null)
				return false;
		} else if (!totalElemendIds.equals(other.totalElemendIds))
			return false;
		return true;
	}

	
}
