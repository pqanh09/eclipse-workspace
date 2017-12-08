package com.websystique.springmvc.service.test;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QJob implements Job
{
	private static final Logger LOGGER = LoggerFactory.getLogger(QJob.class);
	public void execute(JobExecutionContext context)
	throws JobExecutionException {
		JobDetail jobDetail = context.getJobDetail();
        JobKey jobKey = jobDetail.getKey();
        LOGGER.info(String.format("START executing job - %s.%s", jobKey.getGroup(), jobKey.getName()));
        try {
        	// retrieve handler
            JobSchedulerHandler handler = HandlerRegistryManager.getManager().retrieve(jobKey.getGroup());
            if (handler != null) {
            	LOGGER.info(String.format("Handle %s.%s Job", jobKey.getGroup(), jobKey.getName()));
                handler.process(jobDetail.getJobDataMap());
            } else {
            	throw new JobExecutionException("Not found Handler for " + jobKey.getGroup());
            }
        } catch (Exception e) {
        	LOGGER.error("Error in processing job {}.{}: ", jobKey.getGroup(), jobKey.getName(), e); 
        	throw new JobExecutionException(e.toString());
        }
	}
	
}
