package com.websystique.main.test1;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QJob implements Job
{
	public void execute(JobExecutionContext context)
	throws JobExecutionException {
		
		System.out.println("Hello Quartz! AAA");	
		
		//Throw exception for testing
		//throw new JobExecutionException("Testing Exception");
	}
	
}
