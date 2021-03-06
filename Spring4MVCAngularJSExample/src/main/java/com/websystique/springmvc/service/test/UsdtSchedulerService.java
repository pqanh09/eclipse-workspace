package com.websystique.springmvc.service.test;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
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
import com.websystique.springmvc.model.UsdtJob;

@Service("usdtSchedulerService")
public class UsdtSchedulerService extends AbstractSchedulerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsdtSchedulerService.class);
	
	@Autowired
	UsdtJobSchedulerHandler usdtJobSchedulerHandler;
	

	@Override
	public boolean startJob(Job job) {
		try {
			//check input
			if(!(job instanceof UsdtJob)){
				LOGGER.error("Not support this {} Job", job.getType());
				return false;
			}
			UsdtJob usdtJob = (UsdtJob) job;
			String jobId = usdtJob.getInstanceid().toString();
			String jobName = usdtJob.getName();
			String groupName = usdtJob.getType().toString();
			String triggerName = getTriggerName();
			String triggerGroupName = getTriggerGroupName(groupName);

			JobKey jobKey = new JobKey(jobId, groupName);

			//add Handler for job
			addHandler(groupName, usdtJobSchedulerHandler);

			JobDetail jobDetail = JobBuilder.newJob(QJob.class).withIdentity(jobKey).build();
			jobDetail.getJobDataMap().put(JobConstant.JOB_DATA_MAP_JOB_ID, jobId);
//			jobDetail.getJobDataMap().put(JobConstant.JOB_DATA_MAP_BITTREX_INPUTS, usdtJob.getInputs());
//			jobDetail.getJobDataMap().put(JobConstant.JOB_DATA_MAP_BITTREX_COINS, usdtJob.getCoins());
//			jobDetail.getJobDataMap().put(JobConstant.JOB_DATA_MAP_BITTREX_URL, usdtJob.getUrl());
			jobDetail.getJobDataMap().put(JobConstant.JOB_DATA_MAP_LISTENER, new UsdtJobListener(jobId, jobName));

			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
					// TODO update time period
					.withSchedule(CronScheduleBuilder.cronSchedule(usdtJob.getCronExpression()))
					.build();
			TriggerKey triggerKey = trigger.getKey();
			Scheduler sched = new StdSchedulerFactory().getScheduler();
			//TODO jobListenerName = jobID 
			sched.getListenerManager().addJobListener(new QuartzListener(jobId, jobName),
					KeyMatcher.keyEquals(jobKey));
			sched.scheduleJob(jobDetail, trigger);
			sched.start();
			triggerKeyMap.put(jobId, triggerKey);
			jobKeyMap.put(jobId, jobKey);
			usdtJob.setStatus(JobState.running);
			usdtJob.setTimeStart(new Date().getTime());
			jobRepository.safeSave(usdtJob);
		} catch (Exception e) {
			LOGGER.info("Job: {}", job.toString());
			LOGGER.error("An error when starting job: ", e);
			LOGGER.info("Try to stop job");
			if(stopJob(job.getInstanceid().toString(), JobState.stop)){
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
}

