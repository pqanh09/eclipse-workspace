package com.comics.springmvc.service.test;

import java.util.Set;

import com.comics.shared.common.JobState;
import com.comics.springmvc.model.Job;

public interface SchedulerService {

	boolean startJob(Job job);

	boolean stopJob(String jobID, JobState status);
	
	void addHandler(String handlerName, JobSchedulerHandler jobSchedulerHandler);

	Set<String> getJobList();
	
	String getTriggerName();

	String getTriggerGroupName(String groupName);
	
}
