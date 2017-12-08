package com.websystique.springmvc.service.jobnotuse;

import org.quartz.JobDataMap;

public interface MyJobListener {
    
	   void onJobStart(JobDataMap jobDataMap);

	   void onJobComplete(JobDataMap jobDataMap);

	   void onJobFailed(JobDataMap jobDataMap);
	   
	   String getJobType();

	} 
