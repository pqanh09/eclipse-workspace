package com.websystique.main.test1;

import java.util.Set;

public interface OVSchedulerService {


	JobID schedule(OVJob job, MyCronExpression trigger);


	void cancelJob(JobID jobID);

	Set<JobID> getJobList();

	String getTriggerType();

	String getTriggerName();

	String getTriggerGroupName();
	/*
	JobSummary getJobSummary(JobID jobId);

	List<JobSummary> getJobSummaryList();
*/


}
