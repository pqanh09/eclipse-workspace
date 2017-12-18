package com.websystique.springmvc.service.test;

import java.util.Set;

import com.websystique.springmvc.model.Job;
import com.websystique.springmvc.model.JobState;

public interface SchedulerService {

	boolean startJob(Job job);

	boolean stopJob(String jobID, JobState status);
	
	void addHandler(String handlerName, JobSchedulerHandler jobSchedulerHandler);

	Set<String> getJobList();
	
	String getTriggerName();

	String getTriggerGroupName(String groupName);
	
}
