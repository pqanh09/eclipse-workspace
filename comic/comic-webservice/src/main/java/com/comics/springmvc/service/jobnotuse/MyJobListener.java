package com.comics.springmvc.service.jobnotuse;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionException;

import com.comics.shared.common.JobType;

public interface MyJobListener {
    
	   void onJobStart(JobDataMap jobDataMap);

	   void onJobComplete(JobDataMap jobDataMap);

	   void onJobFailed(JobDataMap jobDataMap, JobExecutionException jobException);
	   
	   JobType getJobType();

	} 
