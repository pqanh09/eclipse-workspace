package com.comics.main.test2;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

public class CronTriggerExample {
	public static void main( String[] args ) throws Exception
    {
    	   	
		
		
		JobKey jobKeyA = new JobKey("JobA", "group1");
    	JobDetail jobA = JobBuilder.newJob(JobA.class)
				.withIdentity(jobKeyA).build();

    	Trigger triggerA = TriggerBuilder
				.newTrigger()
				.withIdentity("triggerAJobA", "group1")
				.withSchedule(
						CronScheduleBuilder.cronSchedule("0/2 * * * * ?"))
				.build();
    	
    	
//    	JobKey jobKeyB = new JobKey("JobB", "group1");
//    	JobDetail jobB = JobBuilder.newJob(JobB.class)
//				.withIdentity(jobKeyB).build();
//
//    	Trigger triggerB = TriggerBuilder
//				.newTrigger()
//				.withIdentity("triggerBJobB", "group1")
//				.withSchedule(
//						CronScheduleBuilder.cronSchedule("0/3 * * * * ?"))
//				.build();

    	
    	
    	
    	Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    	
    	//Listener attached to jobKey
    	scheduler.getListenerManager().addJobListener(
    			new HelloJobListener(), KeyMatcher.keyEquals(jobKeyA)
    	);
//    	scheduler.getListenerManager().addJobListener(
//    			new HelloJobListener(), KeyMatcher.keyEquals(jobKeyB)
//    	);
    	
    	//Listener attached to group named "group 1" only.
    	//scheduler.getListenerManager().addJobListener(
    	//		new HelloJobListener(), GroupMatcher.jobGroupEquals("group1")
    	//);

    	scheduler.start();
    	scheduler.scheduleJob(jobA, triggerA);
//    	scheduler.scheduleJob(jobB, triggerB);
    	
    	System.out.println("@(*&$*(@!&$*(%(&@&*(");
    	Thread.sleep(3000);
    	scheduler.unscheduleJob(triggerA.getKey());
    	System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
    	Thread.sleep(5000);
    	System.out.println("^^^^^^^^++++++++++++^^^");
    	scheduler.scheduleJob(jobA, triggerA);
    	
//    	Thread.sleep(5000);
//    	scheduler.unscheduleJob(triggerB.getKey());
    
    }
}
