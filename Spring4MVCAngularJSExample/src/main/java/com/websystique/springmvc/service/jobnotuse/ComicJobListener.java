package com.websystique.springmvc.service.jobnotuse;

import org.quartz.JobDataMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.websystique.springmvc.model.JobType;

public class ComicJobListener implements MyJobListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(ComicJobListener.class);

	private String jobId;
	
	private String jobName;

	public ComicJobListener(String jobId, String jobName) {
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

	public void onJobStart(JobDataMap jobDataMap) {
		LOGGER.info("Job {} . \" {} \" ( {} ) is going to start...", getJobType(), jobName, jobId);
	}

	public void onJobComplete(JobDataMap jobDataMap) {
		//TODO
	}

	public void onJobFailed(JobDataMap jobDataMap) {
		//TODO
	}


	@Override
	public String getJobType() {
		return JobType.Comic.toString();
	}
}
