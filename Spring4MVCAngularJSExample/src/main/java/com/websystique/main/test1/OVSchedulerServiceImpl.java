package com.websystique.main.test1;

import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OVSchedulerServiceImpl implements OVSchedulerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OVSchedulerServiceImpl.class);
	private static Hashtable<JobID, TriggerKey> scheduledJobMap = new Hashtable<JobID, TriggerKey>();
	private static Hashtable<TriggerKey, Map<String, Date>> scheduledJobDetails = new Hashtable<TriggerKey, Map<String, Date>>();
	
	public String getTriggerName() {

		String triggerName = "";
		String uuid = UUID.randomUUID().toString();
		triggerName = uuid;
		return triggerName;
	}

	public String getTriggerGroupName() {
		String triggergroupName = "OVtriggerGroup";
		return triggergroupName;
	}



	@Override
	public JobID schedule(OVJob job, MyCronExpression trigger) {

		String cronExpression = trigger.getCronExpression();
		String jobName = job.getJobName();
		String groupName = job.getGroupName();
		String triggerName = getTriggerName();
		String triggerGroupName = getTriggerGroupName();

		JobKey jobKey = new JobKey(jobName, groupName);
		
		JobDetail jobDetail = JobBuilder.newJob(QJob.class)
				.withIdentity(jobKey).build();
		jobDetail.getJobDataMap().put("OVJob", job);
		OVJobListener ovJobListener = job.getOVJobListener();
		jobDetail.getJobDataMap().put("OVJobListener", ovJobListener);
		OVJobContext oContext = new OVJobContextImpl(jobDetail.getJobDataMap());
		jobDetail.getJobDataMap().put("OVContext", oContext);
		
		JobID jobID = new JobID();

		jobID.createJobId(cronExpression, jobKey.getName(), jobKey.getGroup());

		Trigger cronTrigger = TriggerBuilder.newTrigger()
				.withIdentity(triggerName, triggerGroupName)
				.withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
				.build();

		Date startTime = cronTrigger.getStartTime();
		Date endTime = cronTrigger.getEndTime();
		Date nextfireTime = cronTrigger.getNextFireTime();
		Date previousfireTime = cronTrigger.getPreviousFireTime();
		Date finalFireTime = cronTrigger.getFinalFireTime();

		Map<String, Date> scheduledJob = new Hashtable<String, Date>();

		// System.out.println("start time issss>"+startTime);
		scheduledJob.put("StartTime", startTime);
		// scheduledJob.put("EndTime", endTime);
		// scheduledJob.put("NextFireTime", nextfireTime);
		// scheduledJob.put("PreviousFireTime", previousfireTime);
		// scheduledJob.put("FinalFireTime", finalFireTime);

		TriggerKey triggerKey = cronTrigger.getKey();

		scheduledJobDetails.put(triggerKey, scheduledJob);

		try {
			Scheduler sched = new StdSchedulerFactory().getScheduler();
			sched.getListenerManager().addJobListener(
					new QuartzListener(jobKey+"OVJobListener"), KeyMatcher.keyEquals(jobKey));
			sched.scheduleJob(jobDetail, cronTrigger);
			sched.start();
		} catch (SchedulerException e) {

			e.printStackTrace();
		}
		scheduledJobMap.put(jobID, triggerKey);
		return jobID;
	}

	@Override
	public void cancelJob(JobID jobID) {
		try {
			Scheduler sched = new StdSchedulerFactory().getScheduler();
			TriggerKey trigKey = scheduledJobMap.get(jobID);
			sched.unscheduleJob(trigKey);
			LOGGER.info("Job cancelled...");
		} catch (SchedulerException e) {

			e.printStackTrace();
		}
		
	}

	@Override
	public Set<JobID> getJobList() {
		Set<JobID> JobIDs = scheduledJobMap.keySet();
		return JobIDs;
	}

	@Override
	public String getTriggerType() {
		// TODO Auto-generated method stub
		return null;
	}


	

}

