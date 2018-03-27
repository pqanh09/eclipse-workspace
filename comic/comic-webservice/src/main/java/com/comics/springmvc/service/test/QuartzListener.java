package com.comics.springmvc.service.test;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

import com.comics.springmvc.service.jobnotuse.MyJobListener;

public class QuartzListener implements JobListener{
	
	protected String jobId;
	
	protected String jobName;

	public QuartzListener(String jobId, String jobName) {
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
	public void jobToBeExecuted(JobExecutionContext context) {
		MyJobListener ovJobListener = (MyJobListener) context.getMergedJobDataMap().get(JobConstant.JOB_DATA_MAP_LISTENER);
		ovJobListener.onJobStart(context.getMergedJobDataMap());
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
	}
	
	@Override
	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException jobException) {
		
		MyJobListener ovJobListener = (MyJobListener) context.getMergedJobDataMap().get(JobConstant.JOB_DATA_MAP_LISTENER);
		
		if(jobException != null){
			ovJobListener.onJobFailed(context.getMergedJobDataMap(), jobException);
		}else{
			ovJobListener.onJobComplete(context.getMergedJobDataMap());
		}
		
	}

	@Override
	public String getName() {
		return jobId;
	}

}
