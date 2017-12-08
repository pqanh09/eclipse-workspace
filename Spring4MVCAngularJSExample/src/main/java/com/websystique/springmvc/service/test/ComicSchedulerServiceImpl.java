package com.websystique.springmvc.service.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.Set;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.Job;
import com.websystique.springmvc.model.JobState;
import com.websystique.springmvc.repositories.JobRepository;

@Service("comicSchedulerServiceImpl")
public class ComicSchedulerServiceImpl implements OVSchedulerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ComicSchedulerServiceImpl.class);
	private static Hashtable<String, TriggerKey> triggerKeyMap = new Hashtable<String, TriggerKey>();
	private static Hashtable<String, JobKey> jobKeyMap = new Hashtable<String, JobKey>();
	
	@Autowired
	ComicJobSchedulerHandler comicJobSchedulerHandler;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Override
	public String getTriggerName() {
		return UUID.randomUUID().toString();
	}

	
	@Override
	public String getTriggerGroupName(String groupName) {
		return "Trigger-" + groupName;
	}

	@Override
	public boolean schedule(Job job) {
		try {

			String jobId = job.getInstanceid().toString();
			String jobName = job.getName();
			String groupName = job.getType().toString();
			String triggerName = getTriggerName();
			String triggerGroupName = getTriggerGroupName(groupName);

			JobKey jobKey = new JobKey(jobId, groupName);

			HandlerRegistryManager manager = HandlerRegistryManager.getManager();
			manager.register(groupName, comicJobSchedulerHandler);

			JobDetail jobDetail = JobBuilder.newJob(QJob.class).withIdentity(jobKey).build();
			jobDetail.getJobDataMap().put(JobConstant.JOB_DATA_MAP_JOB_ID, jobId);
//			jobDetail.getJobDataMap().put(JobConstant.JOB_DATA_MAP_TOTAL, job.getMangas());
//			jobDetail.getJobDataMap().put(JobConstant.JOB_DATA_MAP_COMPLETED, new ArrayList<String>());
			
			//jobDetail.getJobDataMap().put(JobConstant.JOB_DATA_MAP_LISTENER, new ComicJobListener(jobId, jobName));

			// Trigger cronTrigger = TriggerBuilder.newTrigger()
			// .withIdentity(triggerName, triggerGroupName)
			// .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
			// .build();

			Date startTime = null;
			if (job.getTimeStart() > 0) {
				startTime = new Date(job.getTimeStart());
			} else {
				startTime = Calendar.getInstance().getTime();
				//TODO change time start
//				startTime.setHours(startTime.getHours() + 1);
				startTime.setHours(startTime.getHours());
//				startTime.setMinutes(0);
				startTime.setMinutes(startTime.getMinutes());
//				startTime.setSeconds(0);
				startTime.setSeconds(startTime.getSeconds() + 30);
			}
			// Date startTime = cronTrigger.getStartTime();
			// Date endTime = cronTrigger.getEndTime();
			// Date nextfireTime = cronTrigger.getNextFireTime();
			// Date previousfireTime = cronTrigger.getPreviousFireTime();
			// Date finalFireTime = cronTrigger.getFinalFireTime();

			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
					// TODO update time period
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(job.getTimeInterval())
							.repeatForever())
					.startAt(startTime).build();


			TriggerKey triggerKey = trigger.getKey();


			Scheduler sched = new StdSchedulerFactory().getScheduler();
			//TODO jobListenerName = jobID 
			sched.getListenerManager().addJobListener(new QuartzListener(jobId, jobName),
					KeyMatcher.keyEquals(jobKey));
			sched.scheduleJob(jobDetail, trigger);
			sched.start();
			triggerKeyMap.put(jobId, triggerKey);
			jobKeyMap.put(jobId, jobKey);
			job.setStatus(JobState.running);
			jobRepository.safeSave(job);
		} catch (Exception e) {
			LOGGER.info("Job: {}", job.toString());
			LOGGER.error("An error when starting job: ", e);
			LOGGER.info("Try to stop job");
			if(cancelJob(job.getInstanceid().toString())){
				LOGGER.info("Stop successfully");
				job.setStatus(JobState.stop);
				jobRepository.safeSave(job);
			} else {
				LOGGER.info("Stop job fail");
			}
			return false;
		}
		
		return true;
	}

	@Override
	public boolean cancelJob(String jobId) {
		try {
			Scheduler sched = new StdSchedulerFactory().getScheduler();
			TriggerKey trigKey = triggerKeyMap.get(jobId);
			if(trigKey == null) {
				LOGGER.error("Not found Job {} in list schedule", jobId);
				return false;
			}
			sched.unscheduleJob(trigKey);
			LOGGER.info("Job {} is cancelled", jobId);
			Job jobdb = jobRepository.findOne(new ObjectId(jobId));
			jobdb.setStatus(JobState.stop);
			jobRepository.safeSave(jobdb);
			LOGGER.info("Job {} is updated state", jobId);
			triggerKeyMap.remove(jobId);
			jobKeyMap.remove(jobId);
			LOGGER.info("Job {} is remove from list schedule", jobId);
		} catch (SchedulerException e) {
			LOGGER.error("An error when canceling Job:", e);
			return false;
		}
		return true;
		
	}

	@Override
	public Set<String> getJobList() {
		Set<String> JobIDs = triggerKeyMap.keySet();
		return JobIDs;
	}

	@Override
	public String getTriggerType() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int getPercent() {
		// TODO Auto-generated method stub
		return 0;
	}

	

	


	

}

