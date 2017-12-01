package com.websystique.main.test1;

import java.util.Date;

public class Main {
	public static void main(String[] args) {
		OVSchedulerService schedulerService = new OVSchedulerServiceImpl();
		
		
		NewJob job =new NewJob();
		job.setjobName("myjob");
		job.setGroupName("myjobgroup");
		job.setOVJobListener(new MyOVJobListener());
//		NewJob newJob = new NewJob();
//		newJob.setGroupName("jobgroupName");
//		newJob.setjobName("jobName");;
//		JobID jobs=schedulerService.schedule(newJob, new Date());
//		MyCronExpression cronTrigger=new MyCronExpression("0/5 * * * * ?");
		
		MyCronExpression cronTrigger=new MyCronExpression("0 */1 * ? * *");
//		JobID jobids=schedulerService.scheduleAtFixedRate(job,5000);
		
		
		JobID jobs = schedulerService.schedule(job, cronTrigger);
		
//		JobID jobs=schedulerService.scheduleAtFixedRate( job, new Date(), 5000);
//		Date date=schedulerService.getStartTime(jobs);
		//Date finalfireTime=schedulerService.getFinalFireTime(jobs);
//		System.out.println("Job id is "+jobs.getJobId());
//		System.out.println("start time is "+date);
		//System.out.println("final fire  time is "+finalfireTime);
		
		//List<JobID> list =schedulerService.getJobList();
		//System.out.println("list size is"+list.size());
		//schedulerService.cancelJob(jobid);
	}
}
