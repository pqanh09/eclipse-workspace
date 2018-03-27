package com.comics.springmvc.service.test;

public class JobID {
	
	public String jobId;
	
	public void createJobId(String scheduledTime,String JobName,String groupName) {
		
		this.jobId = scheduledTime+JobName+groupName; //unique for every job
	
	}
	
	public String getJobId() {
		
		return jobId;
	}
}
