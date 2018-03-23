package com.comics.springmvc.service.test;

import java.util.Hashtable;
import java.util.Set;
import java.util.UUID;

import org.bson.types.ObjectId;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.comics.springmvc.model.Job;
import com.comics.springmvc.model.JobState;
import com.comics.springmvc.repositories.JobRepository;

public class AbstractSchedulerService implements SchedulerService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractSchedulerService.class);
	protected static Hashtable<String, TriggerKey> triggerKeyMap = new Hashtable<String, TriggerKey>();
	protected static Hashtable<String, JobKey> jobKeyMap = new Hashtable<String, JobKey>();
	
	@Autowired
	protected JobRepository jobRepository;
	
	@Override
	public String getTriggerName() {
		return UUID.randomUUID().toString();
	}

	
	@Override
	public String getTriggerGroupName(String groupName) {
		return "Trigger-" + groupName;
	}
	
	@Override
	public boolean stopJob(String jobId, JobState status) {
		try {
			Scheduler sched = new StdSchedulerFactory().getScheduler();
			TriggerKey trigKey = triggerKeyMap.get(jobId);
			if(trigKey != null) {
				LOGGER.error("Not found Job {} in list schedule", jobId);
				sched.unscheduleJob(trigKey);
			}
			LOGGER.info("Job {} is cancelled", jobId);
			Job jobdb = jobRepository.findOne(new ObjectId(jobId));
			jobdb.setStatus(JobState.stop);
			jobRepository.safeSave(jobdb);
			LOGGER.info("Job {} is updated state", jobId);
			triggerKeyMap.remove(jobId);
			jobKeyMap.remove(jobId);
			LOGGER.info("Job {} is remove from list schedule", jobId);
		} catch (SchedulerException e) {
			LOGGER.error("An error when stopping Job:", e);
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
	public boolean startJob(Job job) {
		return false;
	}


	@Override
	public void addHandler(String handlerName, JobSchedulerHandler jobSchedulerHandler) {
		HandlerRegistryManager manager = HandlerRegistryManager.getManager();
		manager.register(handlerName, jobSchedulerHandler);
	}


}
