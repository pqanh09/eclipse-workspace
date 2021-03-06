package com.websystique.springmvc.service.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.websystique.springmvc.model.JobType;

public class MangaJobListener extends AbstractJobListener {
	private static final Logger LOGGER = LoggerFactory.getLogger(MangaJobListener.class);
	
	public MangaJobListener(String jobId, String jobName) {
		super(jobId, jobName);
	}

	@Override
	public JobType getJobType() {
		return JobType.Manga;
	}
}
