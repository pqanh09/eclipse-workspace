package com.comics.springmvc.service.test;

import java.util.Set;

import com.comics.springmvc.model.Job;
import com.comics.springmvc.model.JobState;

public interface SchedulerService {

	boolean startJob(Job job);

	boolean stopJob(String jobID, JobState status);
	
	void addHandler(String handlerName, JobSchedulerHandler jobSchedulerHandler);

	Set<String> getJobList();
	
	String getTriggerName();

	String getTriggerGroupName(String groupName);
	
}
