package com.comics.springmvc.service.test;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.comics.springmvc.model.JobType;
import com.comics.springmvc.service.jobnotuse.MyJobListener;

public class AbstractJobListener implements MyJobListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractJobListener.class);

	protected String jobId;
	
	protected String jobName;

	public AbstractJobListener(String jobId, String jobName) {
		super();
		this.jobId = jobId;
		this.jobName = jobName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	@Override
	public void onJobStart(JobDataMap jobDataMap) {
		LOGGER.info("Job {} . \" {} \" ( {} ) is going to start...", getJobType().toString(), jobName, jobId);
	}
	@Override
	public void onJobComplete(JobDataMap jobDataMap) {
		LOGGER.info("Job {} . \" {} \" ( {} ) is completed", getJobType().toString(), jobName, jobId);
	}
	@Override
	public void onJobFailed(JobDataMap jobDataMap, JobExecutionException jobException) {
		LOGGER.error("Job {} . \" {} \" ( {} ) is fail", getJobType().toString(), jobName, jobId);
		LOGGER.error("Error: ", jobException);
		JobSchedulerHandler handler = HandlerRegistryManager.getManager().retrieve(JobType.Error.toString());
		try {
			LOGGER.info("Try to record error in job history");
			handler.processFail(jobDataMap, jobException);
		} catch (Exception e) {
			LOGGER.error("An error when recording error in job history: ", e);
		}
	}


	@Override
	public JobType getJobType() {
		return JobType.Unknown;
	}
}