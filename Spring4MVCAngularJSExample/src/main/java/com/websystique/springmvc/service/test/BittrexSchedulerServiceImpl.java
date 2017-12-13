package com.websystique.springmvc.service.test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
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
import com.websystique.springmvc.model.UsdtInput;
import com.websystique.springmvc.repositories.UsdtInputRepository;
import com.websystique.springmvc.service.jobnotuse.BittrexJobListener;
import com.websystique.springmvc.service.jobnotuse.ComicJobListener;
import com.websystique.springmvc.vo.UsdtInputVO;

@Service("bittrexSchedulerServiceImpl")
public class BittrexSchedulerServiceImpl extends AbstractSchedulerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(BittrexSchedulerServiceImpl.class);
	
	@Autowired
	BittrexJobSchedulerHandler bittrexJobSchedulerHandler;
	

	@Autowired
	private UsdtInputRepository usdtInputRepository;
	
	@Override
	public boolean startJob(Job job) {
		try {
			
			//check input
			
			List<UsdtInput> list = usdtInputRepository.findAll();
			
			if(list == null || list.isEmpty()){
				LOGGER.info("There is no Input to calculate");
				return false;
			}
			String jobId = job.getInstanceid().toString();
			String jobName = job.getName();
			String groupName = job.getType().toString();
			String triggerName = getTriggerName();
			String triggerGroupName = getTriggerGroupName(groupName);

			JobKey jobKey = new JobKey(jobId, groupName);

			//add Handler for job
			addHandler(groupName, bittrexJobSchedulerHandler);

			JobDetail jobDetail = JobBuilder.newJob(QJob.class).withIdentity(jobKey).build();
			jobDetail.getJobDataMap().put(JobConstant.JOB_DATA_MAP_JOB_ID, jobId);
			jobDetail.getJobDataMap().put(JobConstant.JOB_DATA_MAP_LISTENER, new BittrexJobListener(jobId, jobName));

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

			Trigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerName, triggerGroupName)
					// TODO update time period
					.withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
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
			if(stopJob(job.getInstanceid().toString())){
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

