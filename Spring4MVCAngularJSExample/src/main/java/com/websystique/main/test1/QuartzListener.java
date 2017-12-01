package com.websystique.main.test1;
import java.util.Arrays;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzListener implements JobListener{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QuartzListener.class);
	private String jobListenerName = "OVJobListener";
	public String getName() {
		
		return jobListenerName;
	}

	
	public QuartzListener() {
		super();
	}


	public QuartzListener(String jobListenerName) {
		super();
		this.jobListenerName = jobListenerName;
	}


	public void jobToBeExecuted(JobExecutionContext context) {
		OVJobListener ovJobListener = (OVJobListener) context.getMergedJobDataMap().get("OVJobListener");
        OVJobContext ovContext = new OVJobContextImpl(context.getMergedJobDataMap());
		ovContext.putAsString("hi", true);
		ovJobListener.onJobStart(ovContext);
	}

	public void jobExecutionVetoed(JobExecutionContext context) {
	}

	public void jobWasExecuted(JobExecutionContext context,
			JobExecutionException jobException) {
		
		OVJobListener ovJobListener = (OVJobListener) context.getMergedJobDataMap().get("OVJobListener");
		OVJobContext ovContext = (OVJobContext)context.getMergedJobDataMap().get("OVContext");
		
		
		if(jobException != null){
			LOGGER.info("exception message is "+ Arrays.toString(jobException.getCause().getStackTrace()));
			ovContext.put("ErrorMsg", new String("Exception thrown by the job execution"));
			ovJobListener.onJobFailed(ovContext);
		}else{
			ovJobListener.onJobComplete(ovContext);
		}
		
	}

}
