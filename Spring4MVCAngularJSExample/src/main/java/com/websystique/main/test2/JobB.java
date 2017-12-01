package com.websystique.main.test2;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobB implements Job
{
	public void execute(JobExecutionContext context)
	throws JobExecutionException {
		
		System.out.println("Hello Quartz! BBB");	
		
		//Throw exception for testing
		throw new JobExecutionException("Testing Exception");
	}
	
}
