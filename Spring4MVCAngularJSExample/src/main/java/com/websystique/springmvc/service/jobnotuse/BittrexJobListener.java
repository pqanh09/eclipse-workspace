package com.websystique.springmvc.service.jobnotuse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.websystique.springmvc.model.JobType;

public class BittrexJobListener extends AbstractJobListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(BittrexJobListener.class);
	
	public BittrexJobListener(String jobId, String jobName) {
		super(jobId, jobName);
	}

	@Override
	public JobType getJobType() {
		return JobType.Bittrex;
	}
}
