package com.websystique.springmvc.service.test;
import java.util.Arrays;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzListener implements JobListener{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QuartzListener.class);

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
		LOGGER.info("Job \" {} \" ( {} ) is going to start...", jobName, jobId);
//		MyJobListener ovJobListener = (MyJobListener) context.getMergedJobDataMap().get(JobConstant.JOB_DATA_MAP_LISTENER);
//		ovJobListener.onJobStart(context.getMergedJobDataMap());
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext context) {
	}
	
	@Override
	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException jobException) {
		
//		MyJobListener ovJobListener = (MyJobListener) context.getMergedJobDataMap().get("OVJobListener");
//		OVJobContext ovContext = (OVJobContext)context.getMergedJobDataMap().get("OVContext");
		
		
		if(jobException != null){
			LOGGER.info("exception message is "+ Arrays.toString(jobException.getCause().getStackTrace()));
//			ovContext.put("ErrorMsg", new String("Exception thrown by the job execution"));
//			ovJobListener.onJobFailed(context.getMergedJobDataMap());
		}else{
//			ovJobListener.onJobComplete(context.getMergedJobDataMap());
		}
		
	}

	@Override
	public String getName() {
		return jobId;
	}

}
