
package com.websystique.springmvc.service.test;

import org.bson.types.ObjectId;
import org.quartz.JobDataMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.websystique.springmvc.model.Job;
import com.websystique.springmvc.model.JobState;
import com.websystique.springmvc.response.WSBittresResponse;

@Service("bittrexJobSchedulerHandler")
public class BittrexJobSchedulerHandler extends AbstractJobSchedulerHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(BittrexJobSchedulerHandler.class);

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	@SuppressWarnings("unchecked")
	@Override
	public void process(JobDataMap jobParams) throws Exception {
		Object objId = jobParams.get(JobConstant.JOB_DATA_MAP_JOB_ID);
		// check job null
		if (objId == null) {
			LOGGER.error("No JobID to process.");
			return;
		}

		if (simpMessagingTemplate != null) {
			simpMessagingTemplate.convertAndSend("/topic/pollData", new WSBittresResponse(true, false));
		} else {
			LOGGER.error("SimpMessagingTemplate is null.");
			return;
		}
		String jobId = objId.toString();
		LOGGER.info("Process Job {}:", jobId);
		Job jobDb = jobRepository.findOne(new ObjectId(jobId));
		// update job
		jobDb.setStatus(JobState.scheduled);

		jobRepository.safeSave(jobDb);
	}


}
