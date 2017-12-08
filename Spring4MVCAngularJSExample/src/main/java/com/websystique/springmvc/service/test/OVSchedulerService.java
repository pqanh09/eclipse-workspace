package com.websystique.springmvc.service.test;

import java.util.Set;

import com.websystique.springmvc.model.Job;

public interface OVSchedulerService {


	boolean schedule(Job job);

	boolean cancelJob(String jobID);

	Set<String> getJobList();
	
	int getPercent();

	String getTriggerType();

	String getTriggerName();

	String getTriggerGroupName(String groupName);
	
	/*
	JobSummary getJobSummary(JobID jobId);

	List<JobSummary> getJobSummaryList();
*/


}
