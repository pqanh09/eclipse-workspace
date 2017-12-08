package com.websystique.springmvc.service.test;

import org.bson.types.ObjectId;

import com.websystique.springmvc.model.Job;

public class Main {
	public static void main(String[] args) {
		SchedulerService schedulerService = new ComicSchedulerServiceImpl();
		
		
//		ComicJob job =new ComicJob();
//		job.setjobName("myjob");
//		job.setGroupName("myjobgroup");
//		job.setOVJobListener(new ComicJobListener());
//		NewJob newJob = new NewJob();
//		newJob.setGroupName("jobgroupName");
//		newJob.setjobName("jobName");;
//		JobID jobs=schedulerService.schedule(newJob, new Date());
//		MyCronExpression cronTrigger=new MyCronExpression("0/5 * * * * ?");
		
//		MyCronExpression cronTrigger=new MyCronExpression("0 */1 * ? * *");
//		JobID jobids=schedulerService.scheduleAtFixedRate(job,5000);
		
		Job job = new  Job();
//		job.setCronExpression("0 */1 * ? * *");
		job.setInstanceid(new ObjectId("5a1f9283d2b9dca6dad59ff2"));
		job.setDescription("Hellpoafklas;fk;laskf;");
		
		
//		JobID jobs = schedulerService.schedule(job);
		
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
